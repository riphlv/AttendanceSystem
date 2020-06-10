package lv.venta.AttendanceSystem.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="Attendances")
public class Attendance {
	@Column(name="Attendance_ID")
	@Id
	@GeneratedValue
	int attendance_id;
	@Column(name="Entered")
	Date entered;
	@Column(name="Left")
	Date left;
	@OneToOne
	Guest guest;
	@ManyToOne
	@JoinColumn(name="User_ID")
	User user;
	
	public Date getEntered() {
		return entered;
	}
	public void setEntered(Date entered) {
		this.entered = entered;
	}
	public Date getLeft() {
		return left;
	}
	public void setLeft(Date left) {
		this.left = left;
	}
	public int getAttendance_id() {
		return attendance_id;
	}
	public Attendance( Date entered, Date left ) {
		super();
		this.entered = entered;
		this.left = left;
	}
	public Attendance( Date entered ) {
		super();
		this.entered = entered;
		this.left = null;
	}
	public Attendance() {
		super();
		this.entered = null;
		this.left = null;
	}
	@Override
	public String toString() {
		return "Attendance [attendance_id=" + attendance_id + ", entered=" + entered + ", left=" + left + "]";
	}
	
	
}
