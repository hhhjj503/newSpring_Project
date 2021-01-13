package spring3.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring2.di.ExamDIConfig;
import spring3.aop.entity.Exam;
import spring3.aop.entity.NewlecExam;

public class Program {

	public static void main(String[] args) {
	
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("spring3/aop/setting.xml");
		
		Exam exam = (Exam) context.getBean("exam");
		
		System.out.printf("total is %s\n", exam.total());
		System.out.printf("avg is %f\n", exam.avg());
			
		/* Exam exam = new NewlecExam(1,1,1,1);
		
		 Exam proxy = (Exam) Proxy.newProxyInstance(
				NewlecExam.class.getClassLoader() ,
				new Class[] {Exam.class} ,
				new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				long start = System.currentTimeMillis();

				Object result =	method.invoke(exam, args); //주업무
				
				Thread.sleep(200);
				long end = System.currentTimeMillis();
				String message = (end - start) + " 시간이 걸렸습니다.";
				System.out.println(message);
				
				return result;
			}
		}); */
		
	}

}
