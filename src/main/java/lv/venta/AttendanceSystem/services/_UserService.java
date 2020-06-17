package lv.venta.AttendanceSystem.services;

import lv.venta.AttendanceSystem.models.User;

public interface _UserService {
	public float calculatePay(User employee,int year,int week);
}
