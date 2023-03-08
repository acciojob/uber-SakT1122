package com.driver.services.impl;

import java.util.List;

import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Admin;
import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.AdminRepository;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository1;

	@Autowired
	DriverRepository driverRepository1;

	@Autowired
	CustomerRepository customerRepository1;

	@Override
	public void adminRegister(Admin admin) {
		//Save the admin in the database
		Admin admin1=new Admin();
		admin1.setAdminId(admin.getAdminId());
		admin1.setUserName(admin.getUserName());
		admin1.setPassword(admin.getPassword());
		adminRepository1.save(admin1);
	}

	@Override
	public Admin updatePassword(Integer adminId, String password) {
		//Update the password of admin with given id
		Admin upd= adminRepository1.findById(adminId).get();
		upd.setPassword(password);
		adminRepository1.save(upd);
		return upd;

	}

	@Override
	public void deleteAdmin(int adminId){
		// Delete admin without using deleteById function
		Admin del=adminRepository1.findById(adminId).get();
		adminRepository1.delete(del);

	}

	@Override
	public List<Driver> getListOfDrivers() {
		//Find the list of all drivers
		List<Driver> lst=driverRepository1.findAll();
		return lst;


	}

	@Override
	public List<Customer> getListOfCustomers() {
		//Find the list of all customers
		List<Customer> cus=customerRepository1.findAll();
		return cus;

	}

}
