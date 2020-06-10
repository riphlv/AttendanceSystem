package lv.venta.AttendanceSystem.models;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lv.venta.AttendanceSystem.enums.Gender;
import lv.venta.AttendanceSystem.enums.Occupation;
@Entity
@Table(name="Users")
public abstract class User {
	@Column(name="User_ID")
	@Id
	@GeneratedValue
	int user_id;
	@Column(name="Name")
	String name;
	@Column(name="Surname")
	String surname;
	@Column(name="Gender")
	Gender gender;
	@OneToMany(mappedBy="user")
	ArrayList<Attendance>attendance;
	@Column(name="Occupation")
	Occupation occupation;
	
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
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public ArrayList<Attendance> getAttendance() {
		return attendance;
	}
	public void setAttendance(ArrayList<Attendance> attendance) {
		this.attendance = attendance;
	}
	public Occupation getJobTitle() {
		return occupation;
	}
	public void setJobTitle(Occupation occupation) {
		this.occupation = occupation;
	}
	public int getUser_id() {
		return user_id;
	}
	public User(String name, String surname, Gender gender, Occupation occupation) {
		super();
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.attendance = new ArrayList<>();
		this.occupation = occupation;
	}
	public User() {
		super();
		this.name = "null";
		this.surname = "null";
		this.gender = null;
		this.attendance = new ArrayList<>();
		this.occupation = null;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", name=" + name + ", surname=" + surname + ", gender=" + gender
				+ ", attendance=" + attendance + ", Occupation=" + occupation + "]";
	}
	
	
	
}
