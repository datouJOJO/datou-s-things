package com.datou.twice.blog.myblog.common.aop;

import java.lang.annotation.*;

/**
 * 日志注解
 */
//放在方法之上
@Target(ElementType.METHOD)
//运行有效
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String module() default "";
    String operation() default "";
}
