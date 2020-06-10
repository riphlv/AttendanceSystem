package lv.venta.AttendanceSystem.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int attendance_id;
	@Column(name="RegisterIN")
	private Date registerIN;
	@Column(name="RegisterOUT")
	private Date registerOUT;
	@OneToOne
	private Guest guest;
	@ManyToOne
	@JoinColumn(name="User_ID")
	private User user;
	
	public Date getEntered() {
		return registerIN;
	}
	public void setEntered(Date entered) {
		this.registerIN = entered;
	}
	public Date getLeft() {
		return registerOUT;
	}
	public void setLeft(Date left) {
		this.registerOUT = left;
	}
	public int getAttendance_id() {
		return attendance_id;
	}
	public Attendance( Date entered, Date left ) {
		super();
		this.registerIN = entered;
		this.registerOUT = left;
	}
	public Attendance(User user, Date entered ) {
		super();
		this.user = user;
		this.registerIN = entered;
		this.registerOUT = null;
	}
	public Attendance(Guest guest, Date entered ) {
		super();
		this.guest = guest;
		this.registerIN = entered;
		this.registerOUT = null;
	}
	public Attendance() {
		super();
		this.registerIN = null;
		this.registerOUT = null;
	}
	@Override
	public String toString() {
		return "Attendance [attendance_id=" + attendance_id + ", entered=" + registerIN + ", left=" + registerOUT + "]";
	}
	
	
}
