package net.skhu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.skhu.dto.StudentSubjectGrade;
import net.skhu.mapper.StudentSubjectGradeMapper;

@Service
public class StudentGradeService {
	@Autowired StudentSubjectGradeMapper studentSubjectGradeMapper;
	
	//복수전공 총학점 추출
	public int addAnotherMajorGrade(int id) {
		List<StudentSubjectGrade> anotherData = studentSubjectGradeMapper.findAnotherMajor(id);
		int anotherTotal = 0;
		for(StudentSubjectGrade ssg : anotherData) 
			anotherTotal += ssg.getSubjectScore();
		return anotherTotal;
	}
}
