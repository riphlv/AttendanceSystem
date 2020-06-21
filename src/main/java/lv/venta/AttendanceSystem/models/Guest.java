package lv.venta.AttendanceSystem.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="Guests")
public class Guest {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Guest_ID")
	private int guest_id;
	@NotNull
	@Size(min=2, max=30)
	@Pattern(regexp="^[a-zA-Z]*$") //Allows only characters a-z & A-Z
	@Column(name="Name")
	private String name;
	@NotNull
	@Size(min=2, max=30)
	@Pattern(regexp="^[a-zA-Z]*$")
	@Column(name="Surname")
	private String surname;
	@Size(min=2, max=250)
	@Pattern(regexp="^[a-zA-Z ]*$")//Allows also a space character
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
	public Guest(String name, String surname, String description) {
		super();
		this.name = name;
		this.surname = surname;
		this.description = description;
	}
	public Guest(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
		this.description = null;
	}
	/*
	public Guest(Date entered) {
		super();
		this.name = null;
		this.surname = null;
		this.description = null;
	}
	*/
	public Guest() {
		super();
		this.name = null;
		this.surname = null;
		this.description = null;
	}
	@Override
	public String toString() {
		return "Guest [guest_id=" + guest_id + ", name=" + name + ", surname=" + surname + ", description="
				+ description + ", attendance=" + attendance + "]";
	}
	
	
	
}
