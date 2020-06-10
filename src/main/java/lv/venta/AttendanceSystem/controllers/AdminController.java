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
import org.springframework.web.servlet.ModelAndView;

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
}
