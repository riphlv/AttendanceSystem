package lv.venta.AttendanceSystem.services;

import java.time.LocalDateTime;

import lv.venta.AttendanceSystem.models.Attendance;
import lv.venta.AttendanceSystem.models.Guest;
import lv.venta.AttendanceSystem.models.User;

public interface _CRUDService {
	public void testData();
	
	public void createEmployee(User employee);
	public void createGuest(Guest guest);
	//public void createEmployee(SEmployee employee);
	public boolean createEmployeeAttendance(User user, LocalDateTime date);
	public boolean createGuestAttendance(Guest guest,LocalDateTime date, LocalDateTime date2);
	
	public Attendance updateAttendance(int id/*, Date left*/);
	//public Attendance updateAttendance(int id, Date arrived, Date left);
	public User updateEmployee(int id);
	public Guest updateGuest(int id);
	
	public boolean deleteEmployee(User user);
	public boolean deleteEmployee(int id);
	public boolean deleteGuest(Guest guest);
	public boolean deleteGuest(int id);

	
	
}
