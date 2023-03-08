package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Customer")
public class Customer{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int custometrId;
    private String mobNo;
    private String password;

    @OneToMany(mappedBy = "customer", cascade=CascadeType.ALL)
    List<TripBooking> tripBookings =new ArrayList<>();

    public List<TripBooking> getTripBookings() {
        return tripBookings;
    }

    public void setTripBookings(List<TripBooking> tripBookings) {
        this.tripBookings = tripBookings;
    }

    public Customer(){

    }

    public int getCustometrId() {
        return custometrId;
    }

    public void setCustometrId(int custometrId) {
        this.custometrId = custometrId;
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
