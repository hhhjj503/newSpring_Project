package spring2.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import spring2.di.entity.Exam;
import spring2.di.entity.NewlecExam;

@ComponentScan("spring2.di.ui")
@Configuration
public class ExamDIConfig {
	
	@Bean
	public Exam exam() {
		return new NewlecExam();
	} //IoC컨테이너에 저장
	
	
	
}
