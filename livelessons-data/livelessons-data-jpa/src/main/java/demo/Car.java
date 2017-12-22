package demo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@ToString
public class Car {

    @Id
    @GeneratedValue
    private long id;

    @Getter
    private String make;

    @Getter
    private String model;

    @Getter
    private int year;


    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }
}
