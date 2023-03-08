package com.driver.services.impl;

import com.driver.model.TripBooking;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.model.TripStatus;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		Customer customer1=new Customer();
		customer1.setCustometrId(customer.getCustometrId());
		customer1.setMobNo(customer.getMobNo());
		customer1.setPassword(customer.getPassword());
		customer1.setTripBookings(customer.getTripBookings());
		customerRepository2.save(customer1);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
		Customer cust=customerRepository2.findById(customerId).get();

		List<TripBooking> lst = cust.getTripBookings();
		for(TripBooking trp: lst){
			if(trp.getStatus()==TripStatus.CONFIRMED){
				Driver driver=trp.getDriver();
				driver.getCab().setAvailable(true);
				driverRepository2.save(driver);
			}
		}
		customerRepository2.delete(cust);

	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
		List<Driver> driverList=driverRepository2.findAll();
		Collections.sort(driverList, new Comparator<Driver>() {
			@Override
			public int compare(Driver o1, Driver o2) {
				return o1.getDriverId()- o2.getDriverId();
			}
		});

		Driver wanted=null;
		for(Driver driver:driverList){
			if(driver.getCab().getAvailable()){
				wanted=driver;
				break;
			}
		}

		if(wanted==null) throw new Exception("No cab available!");

		//Getting Customer Details
		Customer cust=customerRepository2.findById(customerId).get();
		List<TripBooking> lst=cust.getTripBookings();

		//Creating nwe tripBooking
		TripBooking trip=new TripBooking(fromLocation,toLocation,distanceInKm);
		//Setting trip details
		trip.setStatus(TripStatus.CONFIRMED);
		trip.setDriver(wanted);
		trip.setBill(distanceInKm*wanted.getCab().getPerKmRate());

		//Adding trip to Customer
		cust.getTripBookings().add(trip);

		//Updatind driver details
		wanted.getCab().setAvailable(false);
		wanted.getTripBookings().add(trip);

		//Updating Driver Repository
		driverRepository2.save(wanted);
		//Updating Customer Repository
		customerRepository2.save(cust);

		return trip;

	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking trip=tripBookingRepository2.findById(tripId).get();
		trip.getDriver().getCab().setAvailable(true);
		trip.setBill(0);
		trip.setStatus(TripStatus.CANCELED);
		tripBookingRepository2.save(trip);

	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking trip=tripBookingRepository2.findById(tripId).get();
		trip.getDriver().getCab().setAvailable(true);
		trip.setStatus(TripStatus.COMPLETED);
		tripBookingRepository2.save(trip);

	}
}
