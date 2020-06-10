package lv.venta.AttendanceSystem.models;

import javax.persistence.Column;
import javax.persistence.Entity;

import lv.venta.AttendanceSystem.enums.Gender;
import lv.venta.AttendanceSystem.enums.Occupation;
@Entity
//@Table(name="HourlyEmployees")
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
	public float calculatePay(int week) {// TODO week not implemented
		int overtime = 0;
		if(this.getHoursWorked() > 40) { //40 hours per week before overtime adds
			overtime = this.getHoursWorked()- 40;
		}
		return (float)(this.getHourlyWage()*getHoursWorked()+(overtime*(this.getHourlyWage()*1.5)));
	}
	
}
