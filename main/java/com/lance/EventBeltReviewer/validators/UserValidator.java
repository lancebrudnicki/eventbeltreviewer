package com.lance.EventBeltReviewer.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.lance.EventBeltReviewer.models.User;
import com.lance.EventBeltReviewer.services.UserService;

@Component
public class UserValidator implements Validator {
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
		
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		if(!user.getPasswordConfirmation().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirmation", "Match");
		}
		
		if(userService.emailExist(user.getEmail())) {
			errors.rejectValue("email", "validEmail");
		}
		
	}
	
}
