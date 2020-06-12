package lv.venta.AttendanceSystem.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.AttendanceSystem.models.Attendance;
import lv.venta.AttendanceSystem.models.Guest;
import lv.venta.AttendanceSystem.models.User;
import lv.venta.AttendanceSystem.repositories._GuestRepo;
import lv.venta.AttendanceSystem.repositories._UserRepo;
import lv.venta.AttendanceSystem.services._FilterService;

@Service
public class FilterServiceImpl implements _FilterService {
	@Autowired
	_UserRepo userRepo;
	@Autowired
	_GuestRepo guestRepo;
	
	@Override
	public ArrayList<User> selectAllUsers() {
		return (ArrayList<User>) userRepo.findAll();
	}

	@Override
	public ArrayList<Guest> selectAllGuests() {
		return (ArrayList<Guest>) guestRepo.findAll();
	}

	@Override
	public Collection<Attendance> userAttendances(int id) {
		if(!userRepo.existsById(id)) {
			System.out.println("No user found to display attendances!");
			return null;
		}else {
			return userRepo.findById(id).get().getAttendance();
		}
	}

	@Override
	public User findUserById(int id) {
		if(!userRepo.existsById(id)) {
			System.out.println("No user found by given id");
			return null;
		}else {
			return userRepo.findById(id).get();
		}
	}

}
