package worktimer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
