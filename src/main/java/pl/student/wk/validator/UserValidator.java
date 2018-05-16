package pl.student.wk.validator;

import org.apache.commons.validator.routines.EmailValidator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

	private int userMinPassLength = 5;
	EmailValidator emailValidator = EmailValidator.getInstance();

	public void validate(Object user, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "error.emptyfield");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pass", "error.emptyfield");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.emptyfield");

		String email = (String) errors.getFieldValue("email");
		String password = (String) errors.getFieldValue("pass");

		if (errors.getErrorCount() == 0) {
			if (emailValidator.isValid(email) == false) {
				errors.rejectValue("email", "error.wrongemail");
			} else if (password.length() < userMinPassLength) {
				errors.rejectValue("pass", "error.shortpass");
			}

		}

	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}
}
