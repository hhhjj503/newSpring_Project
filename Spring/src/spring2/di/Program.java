package spring2.di;

import java.util.ArrayList; 
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring2.di.entity.Exam;
import spring2.di.entity.NewlecExam;
import spring2.di.ui.ExamConsole;

public class Program {

	public static void main(String[] args) {
		
		/* /Exam exam = new NewlecExam();
		//ExamConsole console = new InlineExamConsole(exam); 생성자방법
		console.setExam(exam); */ //setter 방법
		
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(ExamDIConfig.class); //new 없는데도 오류안남;;
		
		//new ClassPathXmlApplicationContext("spring2/di/setting.xml"); //참조할 지시서의 위치 src 를 기준으로 시작
		
		ExamConsole console = (ExamConsole) context.getBean("console"); //id 로 꺼내기
		//ExamConsole console = context.getBean(ExamConsole.class); //class 명으로 꺼내기
		//Exam exam = context.getBean(Exam.class);
		
		//System.out.println(exam.toString());
		console.print(); 
		System.out.printf("\n\n");
		/* List<Exam> exams = (List<Exam>) context.getBean("exams");
		for(Exam e : exams)
			System.out.println(e); */
	
	}

	private static ApplicationContext AnnotationConfigApplicationContext(Class<ExamDIConfig> class1) {
		// TODO Auto-generated method stub
		return null;
	}

}
