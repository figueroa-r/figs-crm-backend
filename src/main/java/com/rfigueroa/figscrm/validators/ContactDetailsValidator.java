package com.rfigueroa.figscrm.validators;

import com.rfigueroa.figscrm.entity.ContactDetail;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;

import javax.validation.*;

public class ContactDetailsValidator implements ConstraintValidator<ContactDetailsConstraint, ContactDetail> {

    private EmailValidator emailValidator;


    @Override
    public void initialize(ContactDetailsConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        // define EmailValidator
        emailValidator = new EmailValidator();
    }

    @Override
    public boolean isValid(ContactDetail contactDetail, ConstraintValidatorContext constraintValidatorContext) {

        if(contactDetail.getContactType().equalsIgnoreCase("phone")) {
            // validate phone number
            String phoneRegex = "^[+]?[0-9]{0,3}[-\\s.]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$"; // matches phone pattern
            return contactDetail.getContactDetail().matches(phoneRegex);
        } else

            // check the provided email
            return emailValidator.isValid(contactDetail.getContactDetail(), constraintValidatorContext);

    }
}
