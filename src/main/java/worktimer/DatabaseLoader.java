package worktimer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import worktimer.commute.Commute;
import worktimer.commute.CommuteRepository;
import worktimer.place.Place;
import worktimer.place.PlaceRepository;

/**
 * Created by linoor on 9/5/16.
 */

@Component
public class DatabaseLoader implements CommandLineRunner {
    private final PlaceRepository placeRepository;
    private final CommuteRepository commuteRepository;

    @Autowired DatabaseLoader(PlaceRepository placeRepository, CommuteRepository commuteRepository) {
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
    }
}
