package lv.venta.AttendanceSystem.controllers;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lv.venta.AttendanceSystem.models.Attendance;
import lv.venta.AttendanceSystem.models.Guest;
import lv.venta.AttendanceSystem.models.HEmployee;
import lv.venta.AttendanceSystem.models.SEmployee;
import lv.venta.AttendanceSystem.services.impl.CRUDServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	CRUDServiceImpl crudService;
	
	@GetMapping("/test")
	public String testData() {
		crudService.testData();
		return "ok";
	}
	@GetMapping("/newHourlyEmpl")
	public String createHourlyEmployee(@ModelAttribute("employee") HEmployee employee) {
		return "employee-h-form";
	}
	@PostMapping("/newHourlyEmpl")
	public String createHourlyEmployee(HEmployee hemployee, BindingResult result) {
		if(!result.hasErrors()) {
			crudService.createEmployee(hemployee);
			return "ok";
		}
		return "employee-h-form";
	}
	@GetMapping("/newSalaryEmpl")
	public String createSalaryEmployee(@ModelAttribute("employee") SEmployee employee) {
		return "employee-s-form";
	}
	@PostMapping("/newSalaryEmpl")
	public String createEmployee(SEmployee employee, BindingResult result){
		if(!result.hasErrors()) {
			crudService.createEmployee(employee);
			return "ok";
		}
		return "employee-s-form";
	}
	@GetMapping("/newGuestVisit")
	public String createGuestVisit(@ModelAttribute("guest") Guest guest) {
		
		return "guest-form";
	}
	@PostMapping("/newGuestVisit")
	public String createGuestVisit(Guest guest, BindingResult result){
		if(!result.hasErrors()) {
			crudService.createGuest(guest);
			System.out.println("<");
			//crudService.createGuestAttendance(guest, guest.getAttendance().getRegisterIN(), guest.getAttendance().getRegisterOUT());
			System.out.println("<<");
			return "ok";
		}
		System.out.println(result);
		return "guest-form";
	}
	@GetMapping("/updateHourlyEmpl/{user_id}")
	public String updateHourlyEmployee(@PathVariable(name="user_id") int user_id, Model model, HEmployee employee) {
		try {
			HEmployee temp = (HEmployee) crudService.updateEmployee(user_id);
			model.addAttribute("employee",temp);
			
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "employee-h-update";
	}
	@PostMapping("/updateHourlyEmpl/{user_id}")
	public String updateHourlyEmployee(@PathVariable(name="user_id") int user_id,@ModelAttribute HEmployee employee) {
		crudService.updateHEmployeeByObject(user_id,employee);
		return "ok";
	}
	@GetMapping("/updateSalaryEmpl/{user_id}")
	public String updateSalaryEmployee(@PathVariable(name="user_id") int user_id, Model model, SEmployee employee) {
		try {
			SEmployee temp =  (SEmployee) crudService.updateEmployee(user_id);
			model.addAttribute("employee",temp);
			
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "employee-s-update";
	}
	@PostMapping("/updateSalaryEmpl/{user_id}")
	public String updateSalaryEmployee(@PathVariable(name="user_id") int user_id,@ModelAttribute SEmployee employee) {
		crudService.updateSEmployeeByObject(user_id,employee);
		return "ok";
	}
	@GetMapping("/updateGuest/{guest_id}")
	public String updateGuest(@PathVariable(name="guest_id") int guest_id, Model model, Guest guest) {
		try {
			Guest temp = crudService.updateGuest(guest_id);
			model.addAttribute("guest",temp);
			
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "guest-update";
	}
	@PostMapping("/updateGuest/{guest_id}")
	public String updateGuest(@PathVariable(name="guest_id") int guest_id,@ModelAttribute Guest guest) {
		Guest temp = crudService.updateGuest(guest_id);
		crudService.updateGuestByObject(guest_id,temp.getAttendance().getAttendance_id(),guest);
		return "ok";
	}
}
