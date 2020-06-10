package lv.venta.AttendanceSystem.services.impl;

import java.util.Date;

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
	@Override
	public void testData() {
		HEmployee h1 = new HEmployee("Name","Surname", Gender.female, Occupation.Accounting, 12f,0);
		HEmployee h2 = new HEmployee("Name2","Surname2", Gender.female, Occupation.Accounting, 12f,0);
		SEmployee s1 = new SEmployee("Name3","Surname3",Gender.male,Occupation.Analyst,500f);
		SEmployee s2 = new SEmployee("Name4","Surname4",Gender.male,Occupation.Analyst,500f);
		userRepo.save(h1);
		userRepo.save(h2);
		userRepo.save(s1);
		userRepo.save(s2);
		Guest g1 = new Guest("name", "name2", "description", new Date());
		guestRepo.save(g1);
		Attendance a1 = new Attendance(h1,new Date());
		Attendance a2 = new Attendance(h2,new Date());
		Attendance a3 = new Attendance(s1,new Date());
		Attendance a4 = new Attendance(s2,new Date());
		Attendance a5 = new Attendance(g1,new Date());
		attendanceRepo.save(a1);
		attendanceRepo.save(a2);
		attendanceRepo.save(a3);
		attendanceRepo.save(a4);
		attendanceRepo.save(a5);
	}
	@Override
	public void createEmployee(User employee) {
		userRepo.save(employee);
	}
	@Override
	public boolean createEmployeeAttendance(User user, Date date) {
		if(userRepo.existsById(user.getUser_id())) {
			return false;
		}else {
			attendanceRepo.save(new Attendance(user , date));
			return true;
		}
	}
	@Override
	public boolean createGuestAttendance(Guest guest, Date date) {
		if(guestRepo.existsById(guest.getGuest_id())) {
			return false;
		}else {
			attendanceRepo.save(new Attendance(guest , date));
			return true;
		}
		
	}
	@Override
	public Attendance updateAttendance(int id) {
		return attendanceRepo.findById(id).get();
	}
	@Override
	public User updateEmployee(int id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).get();
	}
	@Override
	public Guest updateGuest(int id) {
		// TODO Auto-generated method stub
		return guestRepo.findById(id).get();
	}
	@Override
	public boolean deleteEmployee(User user) {
		if(!userRepo.existsById(user.getUser_id())) {
			System.out.println("Employee with user id "+ user.getUser_id()+ " not found!");
			return false;
		}else {
			userRepo.delete(user);
			return true;
		}
	}
	@Override
	public boolean deleteEmployee(int id) {
		if(!userRepo.existsById(id)) {
			System.out.println("Employee with user id "+ id+ " not found!");
			return false;
		}else {
			userRepo.deleteById(id);;
			return true;
		}
	}
	@Override
	public boolean deleteGuest(Guest guest) {
		if(!guestRepo.existsById(guest.getGuest_id())) {
			System.out.println("Employee with user id "+ guest.getGuest_id()+ " not found!");
			return false;
		}else {
			guestRepo.delete(guest);
			return true;
		}
	}
	@Override
	public boolean deleteGuest(int id) {
		if(!guestRepo.existsById(id)) {
			System.out.println("Guest with user id "+ id+ " not found!");
			return false;
		}else {
			guestRepo.deleteById(id);;
			return true;
		}
	}

	
}
