package worktimer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by linoor on 9/5/16.
 */

@Component
public class DatabaseLoader implements CommandLineRunner {
    private final PlaceRepository placeRepository;

    @Autowired DatabaseLoader(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.placeRepository.save(new Place("dom", "siemomysła 16"));
    }
}
