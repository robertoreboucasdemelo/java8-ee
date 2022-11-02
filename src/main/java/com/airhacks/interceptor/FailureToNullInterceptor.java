package com.airhacks.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
public class FailureToNullInterceptor {
	
	@AroundInvoke
	public Object aroundInvoke(InvocationContext context) {
		try {
			return context.proceed();
		} catch (Exception e) {
			// ..
			return null;
		}
	}
}
