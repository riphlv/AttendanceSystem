package lv.venta.AttendanceSystem.models;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;

import lv.venta.AttendanceSystem.enums.Gender;
import lv.venta.AttendanceSystem.enums.Occupation;
@Entity
public class HEmployee extends User implements _Employee {
	@Column(name="HourlyWage")
	private float hourlyWage;
	@Column(name="hoursWorked")
	private int hoursWorked;
	
	public HEmployee() {
		super();
		this.hourlyWage = 0.0f;
		this.hoursWorked = 0;
	}
	public HEmployee(String name, String surname, Gender gender, Occupation occupation, float hourlyWage, int hoursWorked) {
		super(name, surname, gender, occupation);
		this.hourlyWage = hourlyWage;
		this.hoursWorked = hoursWorked;
	}
	
	public float getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(float hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public int getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(int hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	
	@Override
	public String toString() {
		return "HEmployee [hourlyWage=" + hourlyWage + ", hoursWorked=" + hoursWorked + ", toString()="
				+ super.toString() + "]";
	}
	@Override
	public float calculatePay(int year, int week) {
		return 0;
	}
	
}
