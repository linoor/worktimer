package worktimer.place;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by linoor on 9/25/16.
 */
@RepositoryRestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/places")
public class PlaceController {
}
