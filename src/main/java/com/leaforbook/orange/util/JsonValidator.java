package com.leaforbook.orange.util;

import com.alibaba.fastjson.JSON;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JsonValidator implements ConstraintValidator<Json, CharSequence> {

    @Override
    public void initialize(Json constraintAnnotation) {

    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if(charSequence==null) {
            return true;
        }

        try{
            JSON.parse(charSequence.toString().trim());
        } catch (Throwable e) {
            return false;
        }

        return true;
    }
}
