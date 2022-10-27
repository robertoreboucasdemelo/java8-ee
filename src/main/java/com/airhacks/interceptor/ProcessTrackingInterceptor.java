package com.airhacks.interceptor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.airhacks.entity.ProcessTracker;

@Interceptor
public class ProcessTrackingInterceptor {
	
	@Inject
	ProcessTracker processTracker;
	
	
	@AroundInvoke
	public Object aroundInvoke(InvocationContext context) throws Exception {
		processTracker.track();
		return context.proceed();
	}
}
