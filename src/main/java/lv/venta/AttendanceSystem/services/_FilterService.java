package lv.venta.AttendanceSystem.services;

import java.util.ArrayList;
import java.util.Collection;

import lv.venta.AttendanceSystem.models.Attendance;
import lv.venta.AttendanceSystem.models.Guest;
import lv.venta.AttendanceSystem.models.User;

public interface _FilterService {
	ArrayList<User>selectAllUsers();
	ArrayList<Guest>selectAllGuests();
	Collection<Attendance>userAttendances(int id);
	User findUserById(int id);
}
