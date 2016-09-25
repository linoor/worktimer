package worktimer.place;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by linoor on 9/5/16.
 */
public interface PlaceRepository extends CrudRepository<Place, Long> {
}
