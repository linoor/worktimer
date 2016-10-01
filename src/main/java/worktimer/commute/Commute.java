package worktimer.commute;

import lombok.Data;
import worktimer.place.Place;
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

    @ManyToOne
    private Place fromPlace;
    @ManyToOne
    private Place toPlace;

    @OneToMany
    private List<Measurement> measurements = new ArrayList<>();

    private Commute() {}

    public Commute(String note, String type) {
        this.note = note;
        this.type = type;
    }

    public Commute(String note, String type, Place fromPlace, Place toPlace) {
        this.note = note;
        this.type = type;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
    }

    public Long getId() {
        return id;
    }

    public void addMeasurement(Measurement measurement) {
        this.measurements.add(measurement);
    }

    public void setFromPlace(Place fromPlace) {
        this.fromPlace = fromPlace;
    }

    public void setToPlace(Place toPlace) {
        this.toPlace = toPlace;
    }
}
