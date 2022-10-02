/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.common;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author anthonydonx
 * @see java.lang.annotation.Annotation
    interface in the Java programming language is an abstract type that is used to specify a behavior that classes must implement. They are similar to protocols. Interfaces are declared using the interface keyword
    @interface is used to create your own (custom) Java annotations. Annotations are defined in their own file, just like a Java class or interface. Here is custom Java annotation example:
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface  ValueOfEnum {
    Class<? extends Enum<?>> enumClass();

    String message() default "must be any of enum {enum}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
