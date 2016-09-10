package worktimer;

import javaslang.control.Option;
import javaslang.control.Try;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

/**
 * Created by linoor on 9/8/16.
 */
@RepositoryRestController
public class CommuteController {

    private final CommuteRepository commuteRepository;
    private final MeasurementRepository measurementRepository;
    private final EntityLinks entityLinks;

    @Autowired
    public CommuteController(CommuteRepository commuteRepository, MeasurementRepository measurementRepository, EntityLinks entityLinks) {
        this.commuteRepository = commuteRepository;
        this.measurementRepository = measurementRepository;
        this.entityLinks = entityLinks;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/commutes/start")
    public @ResponseBody ResponseEntity<?> startCommute() {
        Commute commute = commuteRepository.save(new Commute("", "type"));
        Measurement measurement = measurementRepository.save(new Measurement(Measurement.Type.START, commute));
        commute.addMeasurement(measurement);

        Resource<Commute> resource = new Resource<>(commute);
        resource.add(entityLinks.linkToSingleResource(Commute.class, commute.getId()));

        return ResponseEntity.ok(resource);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/commutes/stop/{id}")
    public @ResponseBody ResponseEntity<?> endCommute(@PathVariable("id") long id) {
        try {
            Commute commute = commuteRepository.findOne(id);

            List<Measurement> stoppedMeasurements = measurementRepository.findAllByCommuteAndType(commute, Measurement.Type.STOP);
            List<Measurement> startMeasurements = measurementRepository.findAllByCommuteAndType(commute, Measurement.Type.START);
            if (startMeasurements.size() == 0) {
                return ResponseEntity.status(HttpStatus.OK).body("This commute has not even been started");
            }
            else if (stoppedMeasurements.size() >= startMeasurements.size()) {
                return ResponseEntity.status(HttpStatus.OK).body("This commute has already been stopped");
            }

            Measurement measurement = measurementRepository.save(new Measurement(Measurement.Type.STOP, commute));

            Resource<Measurement> resource = new Resource<>(measurement);
            resource.add(entityLinks.linkToSingleResource(Commute.class, commute.getId()));

            return ResponseEntity.ok(resource);
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
