package lv.venta.AttendanceSystem.controllers;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.AttendanceSystem.models.Attendance;
import lv.venta.AttendanceSystem.models.User;
import lv.venta.AttendanceSystem.repositories._UserRepo;
import lv.venta.AttendanceSystem.services.impl.CRUDServiceImpl;
import lv.venta.AttendanceSystem.services.impl.FilterServiceImpl;
import lv.venta.AttendanceSystem.services.impl.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class EmployeeController {
	@Autowired
	FilterServiceImpl filterService;
	@Autowired
	CRUDServiceImpl crudService;
	@Autowired
	UserServiceImpl userService;
	@Autowired
	_UserRepo userRepo;
	@GetMapping("/attendances/{id}")
	public String showAllAttendances(@PathVariable(name="id") int id, Model model) {
		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		int currentYear = LocalDateTime.now().getYear();
		int currentWeek = LocalDateTime.now().get(weekFields.weekOfYear()); // <--- Might not be working correctly during Sundays: returns next weeks number
		model.addAttribute("year",currentYear);
		model.addAttribute("week",currentWeek);
		model.addAttribute("innerObj",filterService.userAttendances(id,currentYear,currentWeek));
		model.addAttribute("salaryObj",userService.calculatePay(userRepo.findById(id).get(), currentYear, currentWeek));
		return "employee-attendance";
	}
	@GetMapping("/attendances/{id}/{year}/{week}")
	public String showAllAttendances(Model model ,@PathVariable int id, @PathVariable int year, @PathVariable int week) {
		System.out.println("id year week");
		model.addAttribute("innerObj",filterService.userAttendances(id,year,week));
		model.addAttribute("salaryObj",
				userService.calculatePay(userRepo.findById(id).get(), year, week));
		model.addAttribute("year",year);
		model.addAttribute("week",week);
		return "employee-attendance";
	}
	@PostMapping("/attendances/{id}")
	public String showAllAttendances(@PathVariable int id, @RequestParam(value="newYear", required=true) int year, @RequestParam(value="newWeek", required=true) int week, Model model) {
		model.addAttribute("salaryObj",userService.calculatePay(userRepo.findById(id).get(), year, week));
		model.addAttribute("innerObj",filterService.userAttendances(id,year,week));
		return "employee-attendance";
	}
	@GetMapping("/checkin/{id}")
	public String checkIn(@PathVariable int id, Model m) {
		try {
			User temp = crudService.updateEmployee(id);
			m.addAttribute("user",temp);
			m.addAttribute("lastAttendance",filterService.findUserById(id).getLastAttendance());
			m.addAttribute("innerObj",filterService.userAttendances(id));
		}catch(Exception e){
			System.out.println("error found");
			e.printStackTrace();
			return "error";
		}
		return "check-in";
	}
	@PostMapping("/checkin/{id}")
	public String checkInPost(@PathVariable int id,Model m) {
		if(filterService.findUserById(id).updateUsersAttendance()) {
			System.out.println("true");
			Attendance temp = crudService.updateAttendance(filterService.findUserById(id).getLastAttendance().getAttendance_id());
			crudService.updateAttendanceByObject(temp.getAttendance_id(), temp);
		}else {
			System.out.println("false");
			crudService.createEmployeeAttendance(filterService.findUserById(id), LocalDateTime.now());
		}
		System.out.println(filterService.findUserById(id).getAttendance());
		
		return "redirect:/user/checkin/"+id;
	}
	
}
