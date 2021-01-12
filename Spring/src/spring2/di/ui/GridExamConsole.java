package spring2.di.ui;

import spring2.di.entity.Exam;

public class GridExamConsole implements ExamConsole {

	private Exam exam;
	
	public GridExamConsole() {}
	
	public GridExamConsole(Exam exam) { this.exam = exam; }

	@Override
	public void print() {
		System.out.println("------------------");
		System.out.println("/--total---avg---/");
		System.out.println("/----------------/");
		System.out.printf("/----%d------%f---/", exam.total(),exam.avg());
	}

	@Override
	public void setExam(Exam exam) {
		this.exam = exam;
	}

}
