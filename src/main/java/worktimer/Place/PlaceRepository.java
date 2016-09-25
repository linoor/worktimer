package worktimer.Place;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by linoor on 9/25/16.
 */
@CrossOrigin
public interface PlaceRepository extends CrudRepository<Place, Long> {
}
