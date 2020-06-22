package lv.venta.AttendanceSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.AttendanceSystem.models.Guest;
import lv.venta.AttendanceSystem.models.HEmployee;
import lv.venta.AttendanceSystem.models.SEmployee;
import lv.venta.AttendanceSystem.services.impl.CRUDServiceImpl;
import lv.venta.AttendanceSystem.services.impl.FilterServiceImpl;
import lv.venta.AttendanceSystem.services.impl.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	CRUDServiceImpl crudService;
	@Autowired
	FilterServiceImpl filterService;
	@Autowired
	UserServiceImpl userService;
	
	@GetMapping("/test")
	public String testData() {
		crudService.testData();
		//userService.calculatePay(employee, year, week)
		return "ok";
	}
	@GetMapping("/newHourlyEmpl")
	public String createHourlyEmployee(@ModelAttribute("employee") HEmployee employee, Model model) {
		model.addAttribute("innerObj",filterService.selectAllUsers());
		return "employee-h-form";
	}
	@PostMapping("/newHourlyEmpl")
	public String createHourlyEmployee(HEmployee hemployee, BindingResult result) {
		if(!result.hasErrors()) {
			crudService.createEmployee(hemployee);
			return "redirect:/admin/newHourlyEmpl";
		}
		return "employee-h-form";
	}
	@GetMapping("/newSalaryEmpl")
	public String createSalaryEmployee(@ModelAttribute("employee") SEmployee employee,Model model) {
		model.addAttribute("innerObj",filterService.selectAllUsers());
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
			if(crudService.verifyDate(guest.getAttendance())) {
				crudService.createGuest(guest);
			}else {
				return "guest-form";
			}
			return "ok";
		}
		return "guest-form";
	}
	@GetMapping("/updateHourlyEmpl")
	public String updateHourlyEmployee(Model model) {
		model.addAttribute("innerObj",filterService.selectAllHEmployees());
		return "employeeTable";
	}
	@GetMapping("/updateHourlyEmpl/{user_id}")
	public String updateHourlyEmployee(@PathVariable(name="user_id") int user_id, Model model, HEmployee employee) {
		try {
			HEmployee temp = (HEmployee) crudService.updateEmployee(user_id);
			model.addAttribute("employee",temp);
			model.addAttribute("innerObj",filterService.selectAllHEmployees());
			
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
	@GetMapping("/updateSalaryEmpl")
	public String updateSalaryEmployee(Model model) {
		model.addAttribute("innerObj",filterService.selectAllSEmployees());
		return "employeeTable";
	}
	@GetMapping("/updateSalaryEmpl/{user_id}")
	public String updateSalaryEmployee(@PathVariable(name="user_id") int user_id, Model model, SEmployee employee) {
		try {
			SEmployee temp =  (SEmployee) crudService.updateEmployee(user_id);
			model.addAttribute("employee",temp);
			model.addAttribute("innerObj",filterService.selectAllSEmployees());
			
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
	@GetMapping("/updateGuest")
	public String updateGuest(Model model) {
		model.addAttribute("guestObj",filterService.selectAllGuests());
		//Guest guest = new Guest();
		
		return "guestTable";
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
		//Guest temp = crudService.updateGuest(guest_id);
		if(crudService.verifyDate(guest.getAttendance())) {
			crudService.updateGuestByObject(guest_id,guest);
		}else {
			return "redirect:/admin/updateGuest/"+guest_id;
		}
		return "ok";
	}
	@GetMapping("/deleteGuest/{guest_id}")
	public String deleteGuest(@PathVariable(name="guest_id") int guest_id, Model model) {
		//TODO Use model to redirect
		if(crudService.deleteGuest(guest_id)) {
			return "ok";
		}else {
			return "error";
		}
	}
	@GetMapping("/deleteEmployee/{user_id}")
	public String deleteEmployee(@PathVariable(name="user_id") int user_id, Model model) {
		if(crudService.deleteEmployee(user_id)) {
			return "ok";
		}else {
			return "error";
		}
	}
	
	
}
