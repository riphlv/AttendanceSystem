package lv.venta.AttendanceSystem.services.impl;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.AttendanceSystem.enums.Gender;
import lv.venta.AttendanceSystem.enums.Occupation;
import lv.venta.AttendanceSystem.models.Attendance;
import lv.venta.AttendanceSystem.models.Guest;
import lv.venta.AttendanceSystem.models.HEmployee;
import lv.venta.AttendanceSystem.models.SEmployee;
import lv.venta.AttendanceSystem.models.User;
import lv.venta.AttendanceSystem.repositories._AttendanceRepo;
import lv.venta.AttendanceSystem.repositories._GuestRepo;
import lv.venta.AttendanceSystem.repositories._UserRepo;
import lv.venta.AttendanceSystem.services._CRUDService;

@Service
public class CRUDServiceImpl implements _CRUDService{
	@Autowired
	_AttendanceRepo attendanceRepo;
	@Autowired
	_GuestRepo guestRepo;
	@Autowired
	_UserRepo userRepo;
	@Autowired
	UserServiceImpl userService;
	@Override
	public void testData() {
		
		HEmployee h1 = new HEmployee("Cindi","Hatcher", Gender.female, Occupation.Accounting, 12f,0);
		HEmployee h2 = new HEmployee("Robert","Washington", Gender.male, Occupation.Analyst, 13f,0);
		
		SEmployee s1 = new SEmployee("Russell","Ryan",Gender.male,Occupation.HumanResources,500f);
		SEmployee s2 = new SEmployee("Jack","Turner",Gender.male,Occupation.ITAdministrator,500f);
		
		userRepo.save(h1);
		userRepo.save(h2);
		userRepo.save(s1);
		userRepo.save(s2);
		
		Guest g1 = new Guest("Diane", "Speed", "visit desc");
		guestRepo.save(g1);
		
		//hourly h1
		attendanceRepo.save(new Attendance(h1,
				LocalDateTime.of(2020,6,1,8,0),
				LocalDateTime.of(2020,6,1,16,0)));
		attendanceRepo.save(new Attendance(h1,
				LocalDateTime.of(2020,6,2,8,0),
				LocalDateTime.of(2020,6,2,16,0)));
		attendanceRepo.save(new Attendance(h1,
				LocalDateTime.of(2020,6,3,8,0),
				LocalDateTime.of(2020,6,3,16,0)));
		attendanceRepo.save(new Attendance(h1,
				LocalDateTime.of(2020,6,4,8,0),
				LocalDateTime.of(2020,6,4,16,0)));
		attendanceRepo.save(new Attendance(h1,
				LocalDateTime.of(2020,6,5,8,0),
				LocalDateTime.of(2020,6,5,16,0)));
		attendanceRepo.save(new Attendance(h1,
				LocalDateTime.of(2020,6,6,8,0),
				LocalDateTime.of(2020,6,6,16,0)));
		attendanceRepo.save(new Attendance(h1,
				LocalDateTime.of(2020,5,29,8,0),
				LocalDateTime.of(2020,5,29,16,0)));
		//hourly h2
		attendanceRepo.save(new Attendance(h2,
				LocalDateTime.of(2020,6,1,8,0),
				LocalDateTime.of(2020,6,1,16,0)));
		attendanceRepo.save(new Attendance(h2,
				LocalDateTime.of(2020,6,2,8,0),
				LocalDateTime.of(2020,6,2,16,0)));
		attendanceRepo.save(new Attendance(h2,
				LocalDateTime.of(2020,6,3,8,0),
				LocalDateTime.of(2020,6,3,16,0)));
		attendanceRepo.save(new Attendance(h2,
				LocalDateTime.of(2020,6,4,8,0),
				LocalDateTime.of(2020,6,4,16,0)));
		attendanceRepo.save(new Attendance(h2,
				LocalDateTime.of(2020,6,5,8,0),
				LocalDateTime.of(2020,6,5,16,0)));
		//salary s1
		attendanceRepo.save(new Attendance(s1,
				LocalDateTime.of(2020,6,1,8,0),
				LocalDateTime.of(2020,6,1,16,0)));
		attendanceRepo.save(new Attendance(s1,
				LocalDateTime.of(2020,6,2,8,0),
				LocalDateTime.of(2020,6,2,16,0)));
		attendanceRepo.save(new Attendance(s1,
				LocalDateTime.of(2020,6,3,8,0),
				LocalDateTime.of(2020,6,3,16,0)));
		attendanceRepo.save(new Attendance(s1,
				LocalDateTime.of(2020,6,4,8,0),
				LocalDateTime.of(2020,6,4,16,0)));
		attendanceRepo.save(new Attendance(s1,
				LocalDateTime.of(2020,6,5,8,0),
				LocalDateTime.of(2020,6,5,16,0)));
		attendanceRepo.save(new Attendance(s1,
				LocalDateTime.of(2020,6,6,8,0),
				LocalDateTime.of(2020,6,6,16,0)));
		attendanceRepo.save(new Attendance(s1,
				LocalDateTime.of(2020,6,20,8,0),
				LocalDateTime.of(2020,6,20,16,0)));
		attendanceRepo.save(new Attendance(s1,
				LocalDateTime.of(2020,6,21,8,0),
				LocalDateTime.of(2020,6,21,16,0)));
		//salary s2
		attendanceRepo.save(new Attendance(s2,
				LocalDateTime.of(2020,6,1,8,0),
				LocalDateTime.of(2020,6,1,16,0)));
		attendanceRepo.save(new Attendance(s2,
				LocalDateTime.of(2020,6,2,8,0),
				LocalDateTime.of(2020,6,2,16,0)));
		attendanceRepo.save(new Attendance(s2,
				LocalDateTime.of(2020,6,3,8,0),
				LocalDateTime.of(2020,6,3,16,0)));
		attendanceRepo.save(new Attendance(s2,
				LocalDateTime.of(2020,6,4,8,0),
				LocalDateTime.of(2020,6,4,16,0)));
		attendanceRepo.save(new Attendance(s2,
				LocalDateTime.of(2020,6,5,8,0),
				LocalDateTime.of(2020,6,5,16,0)));
		//guest g1
		Attendance a5 = new Attendance(g1,LocalDateTime.of(2020,6,1,8,0), LocalDateTime.of(2020,6,5,16,0));
		attendanceRepo.save(a5);
		
		//System.out.println(a1.calculateWorkedHours());
	}
	@Override
	public void createEmployee(User employee) {
		userRepo.save(employee);
	}
	@Override
	public void createGuest(Guest guest) {
		Attendance temp = guest.getAttendance();
		guest.setAttendance(null);
		guestRepo.save(guest);
		attendanceRepo.save(new Attendance(guest,temp.getRegisterIN(),temp.getRegisterOUT()));
		
	}
	@Override
	public boolean createEmployeeAttendance(User user, LocalDateTime date) {
		//Update employee attendance on existing ID
		if(userRepo.existsById(user.getUser_id())) {
			attendanceRepo.save(new Attendance(user , date));
			return true;
		}else {
			return false;
		}
	}
	@Override
	public boolean createGuestAttendance(Guest guest, LocalDateTime date, LocalDateTime date2) {
		if(guestRepo.existsById(guest.getGuest_id())) {
			return false;
		}else {
			attendanceRepo.save(new Attendance(guest , date, date2));
			return true;
		}
		
	}
	@Override
	public Attendance updateAttendance(int id) {
		return attendanceRepo.findById(id).get();
	}
	@Override
	public User updateEmployee(int id) {
		return userRepo.findById(id).get();
	}
	public void updateAttendanceByObject(int id, Attendance attendance) {
		Attendance temp = attendanceRepo.findById(id).get();
		temp.setRegisterIN(attendance.getRegisterIN());
		temp.setRegisterOUT(attendance.getRegisterOUT());
		attendanceRepo.save(temp);
	}
	public void updateHEmployeeByObject(int id, HEmployee employee) {
		//Or just set employee object the id
		HEmployee temp = (HEmployee) userRepo.findById(id).get();
		temp.setAttendance(employee.getAttendance());
		temp.setGender(employee.getGender());
		temp.setHourlyWage(employee.getHourlyWage());
		temp.setHoursWorked(employee.getHoursWorked());
		temp.setName(employee.getName());
		temp.setOccupation(employee.getOccupation());
		temp.setSurname(employee.getSurname());
		userRepo.save(temp);
	}
	public void updateSEmployeeByObject(int id, SEmployee employee) {
		//Or just set employee object the id
		SEmployee temp = (SEmployee) userRepo.findById(id).get();
		temp.setAttendance(employee.getAttendance());
		temp.setGender(employee.getGender());
		temp.setWeeklySalary(employee.getWeeklySalary());
		temp.setName(employee.getName());
		temp.setOccupation(employee.getOccupation());
		temp.setSurname(employee.getSurname());
		userRepo.save(temp);
	}
	public void updateGuestByObject(int id, Guest guest) {
		Guest temp = guestRepo.findById(id).get();
		Attendance tempAttendance = attendanceRepo.findById(temp.getAttendance().getAttendance_id()).get();
		
		tempAttendance.setRegisterIN(guest.getAttendance().getRegisterIN());
		tempAttendance.setRegisterOUT(guest.getAttendance().getRegisterOUT());
		
		temp.setAttendance(tempAttendance);
		temp.setDescription(guest.getDescription());
		temp.setName(guest.getName());
		temp.setSurname(guest.getSurname());
		
		guestRepo.save(temp);
		attendanceRepo.save(tempAttendance);
	}
	@Override
	public Guest updateGuest(int id) {
		return guestRepo.findById(id).get();
	}
	@Override
	public boolean deleteEmployee(User user) {
		if(!userRepo.existsById(user.getUser_id())) {
			System.out.println("Employee with id "+ user.getUser_id()+ " not found!");
			return false;
		}else {
			userRepo.delete(user);
			return true;
		}
	}
	@Override
	public boolean deleteEmployee(int id) {
		if(!userRepo.existsById(id)) {
			System.out.println("Employee with id "+ id+ " not found!");
			return false;
		}else {
			Collection<Attendance> allAttendances = userRepo.findById(id).get().getAttendance();
			attendanceRepo.deleteAll(allAttendances); 
			userRepo.deleteById(id);
			return true;
		}
	}
	@Override
	public boolean deleteGuest(Guest guest) {
		if(!guestRepo.existsById(guest.getGuest_id())) {
			System.out.println("Guest with id "+ guest.getGuest_id()+ " not found!");
			return false;
		}else {
			guestRepo.delete(guest);
			return true;
		}
	}
	@Override
	public boolean deleteGuest(int id) {
		if(!guestRepo.existsById(id)) {
			System.out.println("Guest with id "+ id+ " not found!");
			return false;
		}else {
			Attendance attendance = guestRepo.findById(id).get().getAttendance();
			attendanceRepo.delete(attendance);
			guestRepo.deleteById(id);
			return true;
		}
	}

	
}
