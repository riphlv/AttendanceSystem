package lv.venta.AttendanceSystem.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lv.venta.AttendanceSystem.enums.Gender;
import lv.venta.AttendanceSystem.enums.Occupation;
@Entity
@Table(name="Users")
public abstract class User {
	@Column(name="User_ID")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int user_id;
	@NotNull
	@Size(min=2, max=30)
	@Pattern(regexp="^[a-zA-Z]*$")
	@Column(name="Name")
	private String name;
	@NotNull
	@Size(min=2, max=30)
	@Pattern(regexp="^[a-zA-Z]*$")
	@Column(name="Surname")
	private String surname;
	@NotNull
	@Column(name="Gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@OneToMany(mappedBy="user")
	private Collection<Attendance>attendance;
	@NotNull
	@Column(name="Occupation")
	@Enumerated(EnumType.STRING)
	private Occupation occupation;
	
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
	public Collection<Attendance> getAttendance() {
		return attendance;
	}
	public void setAttendance(Collection<Attendance> attendance) {
		this.attendance = attendance;
	}
	
	public Occupation getOccupation() {
		return occupation;
	}
	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	public int getUser_id() {
		return user_id;
	}
	//Checks what type of employee it is
	// 		(note: There might be a lot better solutions to this)
	public String whatChildClass() {
		if(this instanceof HEmployee) {
			return "Hourly";
		}else if(this instanceof SEmployee) {
			return "Salary";
		}else {
			return "";
		}
		
	}
	public boolean updateUsersAttendance() {
		//Checks if user checks in or out and updates it
		//Returns false if attendance is fully filled
		if(getLastAttendance().getRegisterIN() == null) {
			getLastAttendance().setRegisterIN(LocalDateTime.now());
			return true;
		}else if(getLastAttendance().getRegisterOUT() == null) {
			getLastAttendance().setRegisterOUT(LocalDateTime.now());
			return true;
		}else {
			return false;
		}
	}
	
	public Attendance getLastAttendance() {
		//ugly way to get last attendance
		Attendance temp = null;
		//Loops through all attendances 
		//  last loop will always be the most recent
		for(Attendance attendance : this.getAttendance()) {
			temp = attendance;
		}
		return temp;
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
		this.name = "";
		this.surname = "";
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
