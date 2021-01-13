package spring3.aop.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class LogAfterReturningAdvice implements AfterReturningAdvice{

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		//주로직이 return 된 값을 이용하려면 returnValue 를 사용하면됨!
		System.out.println("returnValue : " + returnValue + "Method : " + method.getName());
	}

	
}
