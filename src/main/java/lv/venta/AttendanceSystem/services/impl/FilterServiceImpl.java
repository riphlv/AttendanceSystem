package lv.venta.AttendanceSystem.services.impl;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.AttendanceSystem.models.Attendance;
import lv.venta.AttendanceSystem.models.Guest;
import lv.venta.AttendanceSystem.models.HEmployee;
import lv.venta.AttendanceSystem.models.SEmployee;
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
	@Override
	public ArrayList<HEmployee>  selectAllHEmployees() {
		ArrayList<User> allUsers = (ArrayList<User>) userRepo.findAll();
		ArrayList<HEmployee> temp = new ArrayList<HEmployee>();
		for(User empl : allUsers) {
			if (empl instanceof HEmployee)
				temp.add((HEmployee) empl);
		}	
		return temp;
	}

	@Override
	public ArrayList<SEmployee> selectAllSEmployees() {
		ArrayList<User> allUsers = (ArrayList<User>) userRepo.findAll();
		ArrayList<SEmployee> temp = new ArrayList<SEmployee>();
		for(User empl : allUsers) {
			if (empl instanceof SEmployee)
				temp.add((SEmployee) empl);
		}	
		return temp;
	}

	@Override
	public Collection<Attendance> userAttendances(int id,int year, int week) {
		if(!userRepo.existsById(id)) {
			System.out.println("No user found to display attendances!");
			return null;
		}else {
			Collection<Attendance>weeksAttendances = new ArrayList<Attendance>();
			LocalDateTime date = LocalDateTime.of(year,1,1,0,0);
			WeekFields weekFields = WeekFields.of(Locale.getDefault());
			date = date.withYear(year)
	                .with(weekFields.weekOfYear(), week)
	                .with(weekFields.dayOfWeek(), 2); // Start of the week 1 = Sunday, 2 = Monday
			
			//Monday to Sunday
			//Get employees date, calculate difference hours between IN & OUT
			//Sum hours
			LocalDateTime endDate = date.plusDays(7);
			for (LocalDateTime startDate = date ; startDate.isBefore(endDate); startDate = startDate.plusDays(1)){
				for(Attendance attendance : userRepo.findById(id).get().getAttendance()) {
			    	if(attendance.getRegisterIN().getYear() == startDate.getYear() &&
			    			attendance.getRegisterIN().getMonth() == startDate.getMonth()&&
			    			attendance.getRegisterIN().getDayOfMonth() == startDate.getDayOfMonth() ) {
			    		weeksAttendances.add(attendance);
			    	}
			    }
			}
			return weeksAttendances;
		}
	}
	


}
