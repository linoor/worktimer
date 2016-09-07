package worktimer;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    private Commute() {}

    public Commute(String note, String type, Date startTime, Date endTime) {
        this.note = note;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
