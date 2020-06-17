package lv.venta.AttendanceSystem.services;

import java.util.ArrayList;
import java.util.Collection;

import lv.venta.AttendanceSystem.models.Attendance;
import lv.venta.AttendanceSystem.models.Guest;
import lv.venta.AttendanceSystem.models.HEmployee;
import lv.venta.AttendanceSystem.models.SEmployee;
import lv.venta.AttendanceSystem.models.User;

public interface _FilterService {
	ArrayList<User>selectAllUsers();
	ArrayList<Guest>selectAllGuests();
	ArrayList<HEmployee>selectAllHEmployees();
	ArrayList<SEmployee>selectAllSEmployees();
	Collection<Attendance>userAttendances(int id);
	Collection<Attendance>userAttendances(int id,int year, int week);
	User findUserById(int id);
	
}
