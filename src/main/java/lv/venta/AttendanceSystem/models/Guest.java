package lv.venta.AttendanceSystem.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="Guests")
public class Guest {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Guest_ID")
	private int guest_id;
	@Column(name="Name")
	private String name;
	@Column(name="Surname")
	private String surname;
	@Column(name="Description")
	private String description; //reason visiting establishment
	@OneToOne(mappedBy="guest")
	private Attendance attendance;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Attendance getAttendance() {
		return attendance;
	}
	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}
	public int getGuest_id() {
		return guest_id;
	}
	public Guest(String name, String surname, String description, Date entered) {
		super();
		this.name = name;
		this.surname = surname;
		this.description = description;
	}
	public Guest(Date entered) {
		super();
		this.name = "null";
		this.surname = "null";
		this.description = "null";
	}
	public Guest() {
		super();
		this.name = "null";
		this.surname = "null";
		this.description = "null";
	}
	
}
