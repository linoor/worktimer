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

    public Measurement(Date timestamp, Type type) {
        this.timestamp = timestamp;
        this.type = type;
    }

    enum Type {
        START, STOP
    }
}
