package com.driver.model;


import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name="cab")
public class Cab {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;

    private int ratePerKm;
    private boolean available;

    @OneToOne
    @JoinColumn
    private Driver driver;

    public Cab(){

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getRatePerKm() {
        return ratePerKm;
    }

    public void setRatePerKm(int ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}