package net.skhu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.skhu.dto.CoreSubject;
import net.skhu.dto.Major;
import net.skhu.dto.StudentSubjectGrade;
import net.skhu.mapper.CoreSubjectMapper;
import net.skhu.mapper.MajorMapper;
import net.skhu.mapper.StudentSubjectGradeMapper;

@Service
public class StudentGradeService {
	@Autowired CoreSubjectMapper coreSubjectMapper;
	@Autowired StudentSubjectGradeMapper studentSubjectGradeMapper;
	
	@Autowired MajorMapper majorMapper;
	
	//복수전공 총학점 추출
	public int addAnotherMajorGrade(int id) {
		List<StudentSubjectGrade> anotherData = studentSubjectGradeMapper.findAnotherMajor(id);
		int anotherTotal = 0;
		for(StudentSubjectGrade ssg : anotherData) 
			anotherTotal += ssg.getSubjectScore();
		System.out.println(anotherTotal);
		return anotherTotal;
	}
	
	public List<Major> anotherMajorSubjects(int deptId) {
		List<Major> subjects = majorMapper.findMajorMustSubject(deptId);
		return subjects;
	}
	
	public List<StudentSubjectGrade> anotherMustMajorList (int id) {
		return studentSubjectGradeMapper.findAnotherMustMajor(id);
	}
	
	//핵심 역량 과목 데이터 목록 추출
	public List<CoreSubject> coreSubjectList(int id) {
		return coreSubjectMapper.studentCoreSubjectData(id);
	}
}
