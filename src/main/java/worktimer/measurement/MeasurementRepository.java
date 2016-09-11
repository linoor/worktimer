package worktimer.measurement;

import org.springframework.data.repository.CrudRepository;
import worktimer.commute.Commute;

import java.util.List;

/**
 * Created by linoor on 9/8/16.
 */
public interface MeasurementRepository extends CrudRepository<Measurement, Long> {
    List<Measurement> findAllByCommuteAndType(Commute commute, Measurement.Type type);
    List<Measurement> findAllByCommute(Commute commute);
}

