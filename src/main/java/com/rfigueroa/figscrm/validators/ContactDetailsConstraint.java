package com.rfigueroa.figscrm.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {ContactDetailsValidator.class})
public @interface ContactDetailsConstraint {

    String message () default "Please enter a valid contact";

    Class<?>[] groups () default { };

    Class<? extends Payload>[] payload() default  { };
}
