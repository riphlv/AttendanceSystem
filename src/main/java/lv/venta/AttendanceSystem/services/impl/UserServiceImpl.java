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
		//Variable to calculate amount of overtime worked
		float overtime = 0f;
		//Variable to calculate amount of hours worked
		float workedHours = 0f;
		
		byte daysWorked = 0;
		//					 LocalDateTime.of(year, month, dayOfMonth, hour, minute)
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
			for(Attendance attendance : employee.getAttendance()) {
				//If given day matches with year, month and day from the one aquired in repository
				// add to the hours worked
		    	if(attendance.getRegisterIN().getYear() == startDate.getYear() &&
		    			attendance.getRegisterIN().getMonth() == startDate.getMonth()&&
		    			attendance.getRegisterIN().getDayOfMonth() == startDate.getDayOfMonth() ) {
		    		workedHours = workedHours+ attendance.calculateWorkedHours();
		    		daysWorked++;
		    	}
		    }
		}
		//Calculate amount of overtime worked
		if(workedHours > 40) {
			overtime = workedHours - 40;
		}
		//Adjust amount of hours worked
		workedHours = workedHours - overtime;
		
		//Return salary depending of employee type
		if(employee instanceof HEmployee) {
			HEmployee empl = (HEmployee) userRepo.findById(employee.getUser_id()).get();
			return (empl.getHourlyWage() * workedHours + (overtime * (empl.getHourlyWage() * 1.5f)));
		}else if (employee instanceof SEmployee) {
			SEmployee empl = (SEmployee) userRepo.findById(employee.getUser_id()).get();
			return ((empl.getWeeklySalary()/5)*daysWorked);
			//(empl.getWeeklySalary()/5)*workedDays
		}else {
			return 0;
		}
	}
	
}
