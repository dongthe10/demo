package com.example.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author hollly
 * @date 2021/1/12 0:28
 */
@Component
@Aspect
public class CustomAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void pointCutMethod() {}

    @Before("pointCutMethod()")
    public void beforeMethod() {
        System.out.println("i am before method");
    }
}
