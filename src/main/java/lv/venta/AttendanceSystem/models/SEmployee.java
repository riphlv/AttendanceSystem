package lv.venta.AttendanceSystem.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lv.venta.AttendanceSystem.enums.Gender;
import lv.venta.AttendanceSystem.enums.Occupation;
@Entity
@Table(name="SalaryEmployees")
public class SEmployee extends User implements _Employee {
	@Column(name="WeeklySalary")
	float weeklySalary;
	
	public SEmployee(String name, String surname, Gender gender, Occupation occupation, float weeklySalary) {
		super(name, surname, gender, occupation);
		this.weeklySalary = weeklySalary;
	}
	public SEmployee() {
		super();
		this.weeklySalary = 0.0f;
	}

	public float getWeeklySalary() {
		return weeklySalary;
	}

	public void setWeeklySalary(float weeklySalary) {
		this.weeklySalary = weeklySalary;
	}

	@Override
	public float calculatePay(int week) {
		return weeklySalary;
		
	}
	
}
