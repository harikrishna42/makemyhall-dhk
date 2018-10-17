package makemyhall.validator;

import org.apache.commons.validator.routines.EmailValidator;
import makemyhall.dao.MyUserAccountDAO;
import makemyhall.form.MyUserAccountForm;
import makemyhall.model.MyUserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
 
@Component
public class MyUserAccountValidator implements Validator {
 
    private EmailValidator emailValidator = EmailValidator.getInstance();
 
    @Autowired
    private MyUserAccountDAO myUserAccountDAO;
 
    //@Override
    public boolean supports(Class<?> clazz) {
        return clazz == MyUserAccountForm.class;
    }
 
    //@Override
    public void validate(Object target, Errors errors) {
        
        MyUserAccountForm form = (MyUserAccountForm) target;
          
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "", "User name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phonenumber", "", "phonenumber is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password is required");
 
        if (errors.hasErrors()) {
            return;
        }
 
        if (!emailValidator.isValid(form.getEmail())) {
              
            errors.rejectValue("email", "", "Email is not valid");
            return;
        }
 
        MyUserAccount userAccount = myUserAccountDAO.findByEmail(form.getEmail());
        if (userAccount != null) {
            if (form.getEmail() == null) {
                errors.rejectValue("userName", "", "User name is not available");
                return;
            } else if (!form.getEmail().equals(userAccount.getEmail())) {
                errors.rejectValue("userName", "", "User name is not available");
                return;
            }
        }
 
        userAccount = myUserAccountDAO.findByEmail(form.getEmail());
        if (userAccount != null) {
            if (form.getEmail() == null) {
                errors.rejectValue("email", "", "Email is not available");
                return;
            } else if (!form.getEmail().equals(userAccount.getEmail())) {
                errors.rejectValue("email", "", "Email is not available");
                return;
            }
        }
    }
 
}