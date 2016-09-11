package worktimer.commute;

import lombok.Data;
import worktimer.measurement.Measurement;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linoor on 9/5/16.
 */

@Data
@Entity
public class Commute {
    // time_started, time_ended, type, from_place, to_place, note

    private @Id @GeneratedValue Long id;

    private String note;
    private String type;

    @OneToMany
    private List<Measurement> measurements = new ArrayList<>();

    private Commute() {}

    public Commute(String note, String type) {
        this.note = note;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void addMeasurement(Measurement measurement) {
        this.measurements.add(measurement);
    }
}
