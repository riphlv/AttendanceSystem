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
	
	//Returns a list of attendances in given year and week of a specific employee
	@Override
	public Collection<Attendance> userAttendances(int id,int year, int week) {
		if(!userRepo.existsById(id)) {
			System.out.println("No user found to display attendances!");
			return null;
		}else {
			//Empty list of attendances to fill
			Collection<Attendance>weeksAttendances = new ArrayList<Attendance>();
			//New LocalDateTime date of a given year
			LocalDateTime date = LocalDateTime.of(year,1,1,0,0);
			//WeekField object that contains todays week based on JVM instance
			WeekFields weekFields = WeekFields.of(Locale.getDefault());
			//Fills date object with YEAR, WEEK OF THE YEAR, DAY OF THE WEEK
			//Results date with the first day of the given week
			date = date.withYear(year)
	                .with(weekFields.weekOfYear(), week)
	                .with(weekFields.dayOfWeek(), 2); // Start of the week 1 = Sunday, 2 = Monday
			//Final day to look through
			LocalDateTime endDate = date.plusDays(7);
			//For each day, until reaches end date
			for (LocalDateTime startDate = date ; startDate.isBefore(endDate); startDate = startDate.plusDays(1)){
				//Look for for given day in users attendance repository
				for(Attendance attendance : userRepo.findById(id).get().getAttendance()) {
					//If given day matches with year, month and day from the one aquired in repository
					// add to the list of attendances
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
