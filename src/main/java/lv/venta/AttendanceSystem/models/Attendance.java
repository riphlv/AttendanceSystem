package lv.venta.AttendanceSystem.models;




import java.time.LocalDateTime;
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

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="Attendances")
public class Attendance {
	@Column(name="Attendance_ID")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int attendance_id;
	@Column(name="RegisterIN")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime registerIN;
	@Column(name="RegisterOUT")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime registerOUT;
	@OneToOne
	private Guest guest;
	@ManyToOne
	@JoinColumn(name="User_ID")
	private User user;
	
	public LocalDateTime getRegisterIN() {
		return registerIN;
	}
	public void setRegisterIN(LocalDateTime registerIN) {
		this.registerIN = registerIN;
	}
	public LocalDateTime getRegisterOUT() {
		return registerOUT;
	}
	public void setRegisterOUT(LocalDateTime registerOUT) {
		this.registerOUT = registerOUT;
	}
	public int getAttendance_id() {
		return attendance_id;
	}
	
	public Attendance( LocalDateTime entered, LocalDateTime left ) {
		super();
		this.registerIN = entered;
		this.registerOUT = left;
	}
	public Attendance(User user, LocalDateTime entered ) {
		super();
		this.user = user;
		this.registerIN = entered;
		this.registerOUT = null;
	}
	public Attendance(Guest guest, LocalDateTime dateFrom, LocalDateTime dateTo ) {
		super();
		this.guest = guest;
		this.registerIN = dateFrom;
		this.registerOUT = dateTo;
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
