package spring3.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogAroundAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		//round 형 보조 AOP
		long start = System.currentTimeMillis();

		Object result = invocation.proceed(); //주업무 (핵심업무)
		
		Thread.sleep(200);
		long end = System.currentTimeMillis();
		String message = (end - start) + " 시간이 걸렸습니다.";
		System.out.println(message);
		
		return result;
	}

	
}
