package net.skhu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.skhu.dto.DepartmentMajorRule;
import net.skhu.dto.Student;
import net.skhu.mapper.DepartmentMajorRuleMapper;
import net.skhu.mapper.StudentMapper;
import net.skhu.mapper.TotalMapper;
import net.skhu.model.StudentVO;

@Service
public class ModalService {
	@Autowired DepartmentMajorRuleMapper departmentMajorMapper;
	@Autowired StudentMapper studentMapper;
	@Autowired TotalMapper totalMapper;
	
	public StudentVO fillData(int id) {
		StudentVO stu = new StudentVO();
		Student student = studentMapper.findById2(id);
		String year = String.valueOf(id).substring(0, 4);
		int entranceYear = Integer.parseInt(year);
		List<DepartmentMajorRule> rules = departmentMajorMapper.findByDepartmentId(stu.getStudent().getDepartmentId(), entranceYear);
		stu.setStudent(student);
		stu.setRules(rules);
		stu.setTotal(totalMapper.find());
		
		return stu;
	}
	
}