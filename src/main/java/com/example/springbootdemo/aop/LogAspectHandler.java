package com.example.springbootdemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class LogAspectHandler {

    @Pointcut("execution(* com.example.springbootdemo.controller..*.*(..))")
    public void pointCut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void annotationCut() {
    }

    /**
     * 在上面定义的切面方法执行之前 执行
     *
     * @param joinPoint
     */
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("===doBefore方法进入了====");
        //获取签名
        Signature signature = joinPoint.getSignature();
        //获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();
        //获取即将执行的方法名
        String name = signature.getName();
        log.info("即将执行的方法为 {} , 属于 {} 包", name, declaringTypeName);

        //获取请求的url 和ip
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取请求url
        String url = request.getRequestURL().toString();
        //获取请求ip
        String ip = request.getRemoteAddr();
        log.info("用户请求的url为 {} , ip地址为{}", url, ip);
    }

    /**
     * 在上面定义的切面方法执行之后 执行
     *
     * @param joinPoint
     */
    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("===进入doAfter方法===");
        //获取签名
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        log.info("方法{} 已经执行完成", name);
    }

    /**
     * 在上面定义的切面方法执行后 执行该方法 可以捕获返回对象或对返回对象进行增强
     * @param joinPoint
     * @param result
     */
    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        log.info("方法{} 执行完毕 ，返回参数为 {}", name, result);
        //根据业务做具体的返回增强
        log.info("对方法返回参数进行业务上的增强{}", result + "增强");
    }

    /**
     * 在上面定义的切面方法执行 遇到异常时 执行此方法
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "pointCut()",throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint,Throwable ex){
        Signature signature = joinPoint.getSignature();
        log.info("执行方法{} 出错，异常为{} ",signature.getName(),ex);
    }
}
