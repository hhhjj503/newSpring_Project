package spring3.aop.entity;

import org.springframework.beans.factory.annotation.Value; 
import org.springframework.stereotype.Component;

public class NewlecExam implements Exam {

	private int kor;
	private int eng;
	private int math;
	private int com;
	
	public NewlecExam() {}
	
	public NewlecExam(int kor, int eng, int math, int com) {
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.com = com;
	}

	@Override
	public String toString() {
		return "NewlecExam [kor=" + kor + ", eng=" + eng + ", math=" + math + ", com=" + com + "]";
	}

	@Override
	public int total() {
		
		//long start = System.currentTimeMillis();
		if(kor > 100)
		 throw new IllegalArgumentException("유효하지않은 국어점수");
		
		int result = kor + eng + math + com;
		//long end = System.currentTimeMillis();
		//String message = (end - start) + " 시간이 걸렸습니다.";
		//System.out.println(message);
		return result;
	}

	@Override
	public float avg() {
		float result = (float) (total()/4.0);
		return result;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getCom() {
		return com;
	}

	public void setCom(int com) {
		this.com = com;
	}
	
	

}
