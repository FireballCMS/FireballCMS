package com.smac89.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Logged {

    /**
     * The name of the logger. Default name is LOGGER
     */
    String name() default "LOGGER";
}
