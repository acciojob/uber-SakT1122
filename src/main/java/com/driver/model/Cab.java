package com.driver.model;


import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name="cab")
public class Cab {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;

    private int perKmRate;
    private boolean available;

    @OneToOne
    @JoinColumn
    private Driver driver;

    public Cab(){

    }
    public Cab(int perKmRate,boolean available,Driver driver){
        this.perKmRate=perKmRate;
        this.available=available;
        this.driver=driver;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPerKmRate() {
        return perKmRate;
    }

    public void setPerKmRate(int perKmRate) {
        this.perKmRate = perKmRate;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }


    public void setAvailable(boolean available) {
        this.available = available;
    }
    public boolean getAvailable() {
        return available;
    }
}