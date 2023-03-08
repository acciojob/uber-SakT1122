package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Driver")
public class Driver{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int driverId;
    private String mobNo;
    private String password;

    @OneToOne
    @JoinColumn
    private Cab cab;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    List<TripBooking> tripBookings=new ArrayList<>();

    public Driver(){

    }

    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    public List<TripBooking> getTripBookings() {
        return tripBookings;
    }

    public void setTripBookings(List<TripBooking> tripBookings) {
        this.tripBookings = tripBookings;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}