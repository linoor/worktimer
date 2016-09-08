package worktimer;

import lombok.Data;

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
    private final Date timestamp;

    private final Type type;

    @ManyToOne
    private final Commute commute;

    public Measurement(Date timestamp, Type type, Commute commute) {
        this.timestamp = timestamp;
        this.type = type;
        this.commute = commute;
    }

    enum Type {
        START, STOP
    }
}
