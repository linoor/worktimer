package worktimer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import worktimer.commute.Commute;
import worktimer.commute.CommuteRepository;
import worktimer.measurement.Measurement;
import worktimer.measurement.MeasurementRepository;
import worktimer.place.Place;
import worktimer.place.PlaceRepository;

import java.util.Date;

/**
 * Created by linoor on 9/5/16.
 */

@Component
public class DatabaseLoader implements CommandLineRunner {
    private final PlaceRepository placeRepository;
    private final CommuteRepository commuteRepository;
    private final MeasurementRepository measurementRepository;

    @Autowired DatabaseLoader(PlaceRepository placeRepository, CommuteRepository commuteRepository, MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
        this.placeRepository = placeRepository;
        this.commuteRepository = commuteRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Place dom = new Place("dom", "siemomysła 16");
        this.placeRepository.save(dom);
        Place work = new Place("work", "lorem ipsum");
        this.placeRepository.save(work);
        Commute commute = new Commute("", "", dom, work);
        this.commuteRepository.save(commute);
        Date startTime = new Date();
        Date stopTime = new Date(startTime.getTime() + 5000);
        Measurement start = new Measurement(startTime, Measurement.Type.START, commute);
        Measurement stop = new Measurement(stopTime, Measurement.Type.STOP, commute);
        measurementRepository.save(start);
        measurementRepository.save(stop);
        commute.addMeasurement(start);
        commute.addMeasurement(stop);
        this.commuteRepository.save(commute);
    }
}
