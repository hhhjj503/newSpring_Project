package spring2.di.ui;

import org.springframework.stereotype.Component;

import spring2.di.entity.Exam;

public interface ExamConsole {

	void print();
	
	void setExam(Exam exam);
	
}
