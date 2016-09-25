package worktimer.Place;

import lombok.Data;
import worktimer.commute.Commute;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linoor on 9/25/16.
 */

@Data
@Entity
public class Place {

    private @Id @GeneratedValue Long id;

    private String name;
    private String address;

    private Place() {}

    @OneToMany
    private List<Commute> commutes = new ArrayList<>();

    private Place(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }
}
