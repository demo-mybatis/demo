package com.example.demo.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author liuhonger
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ChooseMapper {

    Class<?> mapperClass() default Void.class;

    Class<?> selectAsClass() default Void.class;
}
