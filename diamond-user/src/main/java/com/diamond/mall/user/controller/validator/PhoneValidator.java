package com.diamond.mall.user.controller.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.diamond.mall.user.controller.validator.annotation.Phone;

/**
 * 自定义校验
 * @author wu_hong_yan 
 * @Date   2019-03-17
 *
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

	private Pattern pattern = Pattern.compile("1(([38]\\d)|(5[^4&&\\d])|(4[579])|(7[0135678]))\\d{8}");
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(value==null)
			return true;
		return pattern.matcher(value).matches();
	}

}
