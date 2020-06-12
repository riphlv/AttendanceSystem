package lv.venta.AttendanceSystem.models;




import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
	public LocalDateTime getCurrentTime() {
		return LocalDateTime.now();
	}
	public String formatDate(LocalDateTime time) {
		if(time == null) {
			return "";
		}else {
			//format text and append zero to hours and minutes if time 
			// currently is less than 10 -- 01, 02 , 12 etc.
			String hour=""+time.getHour();
			String minute=""+time.getMinute();
			if(time.getHour()<10) {
				hour = "0"+time.getHour();
			}
			if(time.getMinute()<10) {
				minute = "0"+time.getMinute();
			}
			return hour+":"+
					minute +" - "+
					time.getDayOfMonth()+"."+
					time.getMonth()+" "+
					time.getYear();
		}
			
	}
	public float calculateWorkedHours() {
		//	Return time between registerIN and registerOUT
		// 	in case one of the times equals null, return 0
		if(this.getRegisterOUT()==null || this.getRegisterIN()==null) {
			return 0;
		}else {
			LocalDateTime temp = LocalDateTime.from(this.getRegisterIN());
			return temp.until(this.getRegisterOUT(), ChronoUnit.HOURS);
		}
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
