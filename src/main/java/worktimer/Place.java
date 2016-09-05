package worktimer;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by linoor on 9/5/16.
 */

@Data
@Entity
public class Place {

    private @Id @GeneratedValue Long id;
    private String name;
    private String address;

    private Place() {}

    public Place(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
