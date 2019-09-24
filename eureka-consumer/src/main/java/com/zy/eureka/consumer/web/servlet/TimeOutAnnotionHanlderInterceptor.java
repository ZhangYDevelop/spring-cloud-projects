package com.zy.eureka.consumer.web.servlet;

import com.zy.eureka.consumer.annotion.TimeOut;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * @Author zhangy
 * @Date 21:15 2019/9/23
 * @see HandlerInterceptor
 * @see com.zy.eureka.consumer.annotion.TimeOut
 **/
public class TimeOutAnnotionHanlderInterceptor  implements HandlerInterceptor {

    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 拦截处理方法（Spring Web MVC 内建 HandlerInterceptor）
        // 2. 得到被拦截的方法对象（handler 对象在 Spring Web MVC 注解编程中永远是 HandlerMethod）
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 3 通过 handlerMethod 获取到 Method
            Method method = handlerMethod.getMethod();
            Object bean = handlerMethod.getBean();

            // 4 通过反射拿到注解 TimeOut 并且获取到属性
            TimeOut timeOut = method.getAnnotation(TimeOut.class);
            Long value = timeOut.value();
            TimeUnit timeUnit = timeOut.timeUnit();
            String  fallback = timeOut.fallBack();

            // 5 根据参数构造 Feature 超时时间
            Future<Object> future =  executorService.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    return method.invoke(bean);
                }
            });

            Object returnValue = null;
            try{
                // 6 执行别拦截的方法
                returnValue = future.get(value,timeUnit); // 正常执行

            } catch (Exception e) {
                // 7 如果失败了，就调用fallback
                returnValue = invokeFallBackMethod(handlerMethod, bean, fallback);
            }

            // 8. 返回执行结果（当前实现是存在缺陷的，大家可以尝试通过 HandlerMethodReturnValueHandler 实现）
            response.getWriter().write(String.valueOf(returnValue));
            return false;
        }
        return true;
    }

    private Object invokeFallBackMethod(HandlerMethod handlerMethod, Object bean, String fallback) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 7.1 查找 fallback 方法
        Method method = findFallbackMethod(handlerMethod, bean, fallback);
        return method.invoke(bean);
    }

    private Method findFallbackMethod(HandlerMethod handlerMethod, Object bean, String fallbackMethodName) throws NoSuchMethodException {
        // 通过被拦截方法的参数类型列表结合方法名，从同一类中找到 fallback 方法
        Class beanClass = bean.getClass();
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
        Class[] parameterTypes = Stream.of(methodParameters)
                .map(MethodParameter::getParameterType) // Class
                .toArray(Class[]::new);                 // Stream<Class> -> Class[]
        Method fallbackMethod = beanClass.getMethod(fallbackMethodName, parameterTypes);
        return fallbackMethod;
    }
}
