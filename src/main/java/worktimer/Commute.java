package worktimer;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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
    private Set<Measurement> measurements;

    private Commute() {}

    public Commute(String note, String type) {
        this.note = note;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setMeasurements(Set<Measurement> measurements) {
        this.measurements = measurements;
    }
}
