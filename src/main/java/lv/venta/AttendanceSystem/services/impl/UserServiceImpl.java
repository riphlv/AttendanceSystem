package lv.venta.AttendanceSystem.services.impl;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.AttendanceSystem.models.Attendance;
import lv.venta.AttendanceSystem.models.HEmployee;
import lv.venta.AttendanceSystem.models.SEmployee;
import lv.venta.AttendanceSystem.models.User;
import lv.venta.AttendanceSystem.repositories._AttendanceRepo;
import lv.venta.AttendanceSystem.repositories._UserRepo;
import lv.venta.AttendanceSystem.services._UserService;

@Service
public class UserServiceImpl implements _UserService {
	@Autowired
	_UserRepo userRepo;
	@Autowired
	_AttendanceRepo attendanceRepo;

	@Override
	public float calculatePay(User employee,int year, int week) {
		float overtime = 0f;
		//					 LocalDateTime.of(year, month, dayOfMonth, hour, minute)
		LocalDateTime date = LocalDateTime.of(year,1,1,0,0);
		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		date = date.withYear(year)
                .with(weekFields.weekOfYear(), week)
                .with(weekFields.dayOfWeek(), 2); // Start of the week 1 = Sunday, 2 = Monday
		
		//Monday to Sunday
		//Get employees date, calculate difference hours between IN & OUT
		//Sum hours
		LocalDateTime endDate = date.plusDays(7);
		float workedHours = 0f;
		for (LocalDateTime startDate = date ; startDate.isBefore(endDate); startDate = startDate.plusDays(1)){
			for(Attendance attendance : employee.getAttendance()) {
		    	if(attendance.getRegisterIN().getYear() == startDate.getYear() &&
		    			attendance.getRegisterIN().getMonth() == startDate.getMonth()&&
		    			attendance.getRegisterIN().getDayOfMonth() == startDate.getDayOfMonth() ) {
		    		workedHours = workedHours+ attendance.calculateWorkedHours();
		    		//System.out.println("WORKED:"+workedHours);
		    	}
		    }
		}
		//Calculate overtime
		if(workedHours > 40) {
			overtime = workedHours - 40;
		}
		workedHours = workedHours - overtime;
		
		if(employee instanceof HEmployee) {
			HEmployee empl = (HEmployee) userRepo.findById(employee.getUser_id()).get();
			//System.out.println("PAY:"+empl.getHourlyWage() * workedHours + (overtime * (empl.getHourlyWage() * 1.5f)));
			return (empl.getHourlyWage() * workedHours + (overtime * (empl.getHourlyWage() * 1.5f)));
		}else if (employee instanceof SEmployee) {
			SEmployee empl = (SEmployee) userRepo.findById(employee.getUser_id()).get();
			return (empl.getWeeklySalary());
			//(empl.getWeeklySalary()/5)*workedDays
		}else {
			return 0;
		}
	}
	
}
