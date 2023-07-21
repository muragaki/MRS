package mrs.app.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import mrs.domain.model.RoleName;
import mrs.domain.model.User;
import mrs.domain.service.user.ReservationUserDetailsService;

@Controller
public class LoginController {
	
	@Autowired
	ReservationUserDetailsService reservationUserDetailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("loginForm")
	String loginForm() {
		return "login/loginForm";
	}
	
	@GetMapping("signupForm")
	String signupForm(SignupForm signupForm) {
		return "login/signup";
	}
	@PostMapping("/signup")
	String signup(@Validated SignupForm signupForm, BindingResult result, Model model) {
		
	    if (result.hasErrors()) {
	        return "login/signup";
	    }
	    
        if (reservationUserDetailsService.isExistUser(signupForm.getUserId())) {
            model.addAttribute("signupError", "ユーザー名 " + signupForm.getUserId() + "は既に登録されています");
            return "login/signup";
        }
	    
		User user = new User();
		user.setUserId(signupForm.getUserId());
		user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		user.setFirstName(signupForm.getFirstName());
		user.setLastName(signupForm.getLastName());
		user.setRoleName(RoleName.USER);
        try {
        	reservationUserDetailsService.register(user);
        } catch (DataAccessException e) {
        	model.addAttribute("signupError", "ユーザー登録に失敗しました");
        	return "login/signup";
        }
		return "redirect:/";
	}
}