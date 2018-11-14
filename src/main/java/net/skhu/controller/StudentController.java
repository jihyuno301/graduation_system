package net.skhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.skhu.dto.Culture;
import net.skhu.dto.Department;
import net.skhu.dto.Student;
import net.skhu.mapper.CultureMapper;
import net.skhu.mapper.DepartmentMapper;
import net.skhu.mapper.PasswordQuizMapper;
import net.skhu.mapper.StudentMapper;

@Controller
@RequestMapping("/user")
public class StudentController {
	@Autowired
	StudentMapper studentMapper;
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	PasswordQuizMapper passwordQuizMapper;
	@Autowired
	CultureMapper cultureMapper;

	@RequestMapping("studentListForAdmin")
	public String list(Model model) {
		List<Student> students = studentMapper.findAll();
		List<Department> departments = departmentMapper.findRealDept();
		model.addAttribute("students", students);
		model.addAttribute("departments", departments);
		return "user/studentListForAdmin";
	}

	@RequestMapping(value = "studentSearch", method = RequestMethod.GET)
	public String studentSearch(Model model,
			@RequestParam(value = "departmentName", required = false) String departmentName,
			@RequestParam(value = "grade", required = false) Integer grade,
			@RequestParam(value = "subjectName", required = false) String subjectName,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "id", required = false) Integer id) {

		List<Student> students = studentMapper.findByStudentInquiry(departmentName, grade, subjectName, name, id);
		/*
		 * List<Student> studentsFilter = new ArrayList<Student>(); for(int i=0;
		 * i<students.size(); i++) { Student st = students.get(i); for(int j=0;
		 * j<studentsFilter.size(); j++) { if(st.getId() !=
		 * studentsFilter.get(j).getId()) { studentsFilter.add(st); } } }
		 */
		model.addAttribute("departments", departmentMapper.findRealDept());
		model.addAttribute("students", students);
		return "user/studentListForAdmin";

	}

	@RequestMapping("studentDelete")
	public String professorDelete(@RequestParam("id") int id) {
		studentMapper.delete(id);
		return "redirect:studentListForAdmin";
	}

	@RequestMapping("myPage")
	public String myPage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int userNumber = Integer.parseInt(authentication.getName());
		Student student = studentMapper.findById(userNumber);
		Culture culture = cultureMapper.find();
		int cultureGrade = culture.getGrade();
		model.addAttribute("student", student);
		model.addAttribute("culture", cultureGrade);
		return "user/myPage";
	}

}
