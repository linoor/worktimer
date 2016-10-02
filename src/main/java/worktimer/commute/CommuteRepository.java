package worktimer.commute;

import org.springframework.data.repository.CrudRepository;
import worktimer.measurement.Measurement;
import worktimer.place.Place;

import java.util.List;

/**
 * Created by linoor on 9/7/16.
 */
public interface CommuteRepository extends CrudRepository<Commute, Long> {
}
