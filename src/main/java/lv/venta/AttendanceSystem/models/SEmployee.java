package lv.venta.AttendanceSystem.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

import lv.venta.AttendanceSystem.enums.Gender;
import lv.venta.AttendanceSystem.enums.Occupation;
@Entity
public class SEmployee extends User implements _Employee {
	@Min(0)
	@Column(name="WeeklySalary")
	private float weeklySalary;
	
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
	public float calculatePay(int year, int week) {
		return weeklySalary;
		
	}
	
}
