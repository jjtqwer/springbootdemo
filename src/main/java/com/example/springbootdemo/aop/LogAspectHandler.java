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

    /**
     * AOP中@Pointcut的用法
     * 格式 execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern)throws-pattern?)
     *
     * 修饰符匹配（modifier-pattern?）
     * 返回值匹配（ret-type-pattern）可以为*表示任何返回值,全路径的类名等
     * 类路径匹配（declaring-type-pattern?）
     * 方法名匹配（name-pattern）可以指定方法名 或者 *代表所有, set* 代表以set开头的所有方法
     * 参数匹配（(param-pattern)）可以指定具体的参数类型，多个参数间用“,”隔开，各个参数也可以用“*”来表示匹配任意类型的参数，如(String)表示匹配一个String参数的方法；
     * (*,String) 表示匹配有两个参数的方法，第一个参数可以是任意类型，而第二个参数是String类型；可以用(..)表示零个或多个任意参数
     * 异常类型匹配（throws-pattern?）
     * 其中后面跟着“?”的是可选项
     * 例：
     * “execution(* *(..) throws Exception)”匹配所有抛出Exception的方法。
     */

    /**
     * execution为表达式主体
     * 第一个*号的位置 ： 表示返回值类型 ，*表示所有类型
     * 包名： 表示需要拦截的包名 后面的两个点 表示当前包和当前包的所有子包
     * 第二个*号的位置： 表示类名 *表示所有类
     *    *(..)：这个*号表示方法名 *表示所有方法 ，后面括号里面表示方法的参数 2个点表示任意参数
     */
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
