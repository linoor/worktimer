package worktimer.commute

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.hateoas.EntityLinks
import org.springframework.hateoas.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import worktimer.measurement.Measurement
import worktimer.measurement.MeasurementRepository
import worktimer.place.PlaceRepository

/**
 * Created by linoor on 11/5/16.
 */

//@CrossOrigin("http://localhost:3000")
//@RepositoryRestController
//class CommuteControllerKotlin @Autowired
//constructor(val commuteRepository: CommuteRepository,
//            val measurementRepository: MeasurementRepository,
//            val placeRepository: PlaceRepository,
//            val entityLinks: EntityLinks) {
//
//    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "/commutes/{id}/continue")
//    fun continueCommute(@PathVariable("id") commuteId: Long) = {
//        val commute = commuteRepository.findOne(commuteId)
//        val stoppedMeasurements: List<Measurement> = measurementRepository.findAllByCommuteAndType(commute, Measurement.Type.STOP);
//        val startMeasurements: List<Measurement> = measurementRepository.findAllByCommuteAndType(commute, Measurement.Type.START);
//
//        if (startMeasurements.size == 0) {
//            ResponseEntity.status(HttpStatus.OK).body("This commute has not even been started")
//        } else if (startMeasurements.size > stoppedMeasurements.size) {
//            ResponseEntity.status(HttpStatus.OK).body("This commute has already been started")
//        } else {
//            val measurement: Measurement = measurementRepository.save(Measurement(Measurement.Type.START, commute))
//            commute.addMeasurement(measurement)
//
//            val resource: Resource<Measurement> = Resource(measurement)
//            resource.add(entityLinks.linkToSingleResource { commute.getId() })
//
//            ResponseEntity.ok(resource)
//        }
//    }
//}
//
//