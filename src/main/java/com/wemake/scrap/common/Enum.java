package com.wemake.scrap.common;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * enum validation 어노테이션
 */
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EnumValidation.class})
@Target(ElementType.FIELD)
public @interface Enum {

    String message() default "Invalid enum value.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends java.lang.Enum<?>> enumClass();

    boolean ignoreCase() default false;

}