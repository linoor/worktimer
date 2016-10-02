package worktimer.commute;

import javaslang.Tuple;
import javaslang.Tuple2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;

import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import worktimer.place.PlaceRepository;
import worktimer.measurement.Measurement;
import worktimer.measurement.MeasurementRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by linoor on 9/8/16.
 */
@CrossOrigin(origins = "http://localhost:3000")
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
    @CrossOrigin(origins = "http://localhost:3000")
    public @ResponseBody ResponseEntity<?> startCommute() {
        Commute commute = commuteRepository.save(new Commute("", "type"));
        Measurement measurement = measurementRepository.save(new Measurement(Measurement.Type.START, commute));
        commute.addMeasurement(measurement);

        Resource<Measurement> resource = new Resource<>(measurement);
        resource.add(entityLinks.linkToSingleResource(Commute.class, commute.getId()));

        return ResponseEntity.ok(resource);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/commutes/{id}/stop")
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

    @RequestMapping(method = RequestMethod.GET, value = "/commutes/{id}/measurements")
    public @ResponseBody ResponseEntity<?> getMeasurements(@PathVariable("id") long commuteId) {
        try {
            Commute commute = commuteRepository.findOne(commuteId);
            List<Measurement> measurements = (measurementRepository.findAllByCommute(commute));

            Resources<Measurement> resources = new Resources<>(measurements);
            resources.add(linkTo(methodOn(CommuteController.class).getMeasurements(commuteId)).withSelfRel());
            return ResponseEntity.ok(resources);
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/commutes/{id}/measurements/elapsed")
    public @ResponseBody ResponseEntity<?> getElapsed(@PathVariable("id") long commuteId) {
        try {
            Commute commute = commuteRepository.findOne(commuteId);
            javaslang.collection.List<Measurement> measurements =
                    javaslang.collection.List.ofAll(measurementRepository.findAllByCommute(commute));

            javaslang.collection.List<Tuple2<Measurement.Type, javaslang.collection.List<Date>>> dates
                    = measurements.groupBy(Measurement::getType)
                                  .map(group -> Tuple.of(group._1, group._2.map(Measurement::getTimestamp))).toList();

            Number result = dates.get(1)._2.zip(dates.get(0)._2)
                    .map(tuple -> tuple._1.getTime() - tuple._2.getTime())
                    .sum();

            Resource<Number> resources = new Resource<>(result);
            resources.add(linkTo(methodOn(CommuteController.class).getElapsed(commuteId)).withSelfRel());
            return ResponseEntity.ok(resources);
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/commutes/{id}/start")
    public @ResponseBody ResponseEntity<?> restartCommute(@PathVariable("id") long commuteId) {
        try {
            Commute commute = commuteRepository.findOne(commuteId);

            List<Measurement> stoppedMeasurements = measurementRepository.findAllByCommuteAndType(commute, Measurement.Type.STOP);
            List<Measurement> startMeasurements = measurementRepository.findAllByCommuteAndType(commute, Measurement.Type.START);
            if (startMeasurements.size() == 0) {
                return ResponseEntity.status(HttpStatus.OK).body("This commute has not even been started");
            }
            else if (startMeasurements.size() > stoppedMeasurements.size()) {
                return ResponseEntity.status(HttpStatus.OK).body("This commute has already been started");
            }

            Measurement measurement = measurementRepository.save(new Measurement(Measurement.Type.START, commute));
            commute.addMeasurement(measurement);


            Resource<Measurement> resource = new Resource<>(measurement);
            resource.add(entityLinks.linkToSingleResource(Commute.class, commute.getId()));

            return ResponseEntity.ok(resource);
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
