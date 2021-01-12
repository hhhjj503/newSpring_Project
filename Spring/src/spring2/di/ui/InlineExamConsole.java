package spring2.di.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import spring2.di.entity.Exam;

@Component("console")
public class InlineExamConsole implements ExamConsole {

	@Autowired(required = true)
	@Qualifier("exam")
	private Exam exam;
	
	public InlineExamConsole() {
		System.out.println("default constructor");
	}
	
	public InlineExamConsole(Exam exam) { 
		this.exam = exam;
		System.out.println("doverload constructor");
	}

	@Override
	public void print() {
		if(exam == null)
			System.out.printf("total is %d", 0);
		else
		System.out.printf("total is %d and avg is %f", exam.total() , exam.avg());
	}

	@Override
	public void setExam(Exam exam) {
		this.exam = exam;
		System.out.println("setter");
	}

}
