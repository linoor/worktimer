package worktimer.measurement;

import lombok.Data;
import worktimer.commute.Commute;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by linoor on 9/8/16.
 */

@Data
@Entity
public class Measurement {

    private @Id @GeneratedValue Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    private Type type;

    @ManyToOne
    private Commute commute;

    private Measurement() {}

    public Measurement(Date timestamp, Type type, Commute commute) {
        this.timestamp = timestamp;
        this.type = type;
        this.commute = commute;
    }

    public Measurement(Type type, Commute commute) {
        this(new Date(), type, commute);
    }

    public enum Type {
        START, STOP
    }
}
