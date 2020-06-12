package lv.venta.AttendanceSystem.controllers;

import java.io.FilterReader;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.AttendanceSystem.models.Attendance;
import lv.venta.AttendanceSystem.models.User;
import lv.venta.AttendanceSystem.services._FilterService;
import lv.venta.AttendanceSystem.services.impl.CRUDServiceImpl;
import lv.venta.AttendanceSystem.services.impl.FilterServiceImpl;

@Controller
@RequestMapping("/user")
public class EmployeeController {
	@Autowired
	FilterServiceImpl filterService;
	@Autowired
	CRUDServiceImpl crudService;
	
	@GetMapping("/attendances/{id}")
	public String showAllAttendances(@PathVariable(name="id") int id, Model model) {
		model.addAttribute("innerObj",filterService.userAttendances(id));
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
