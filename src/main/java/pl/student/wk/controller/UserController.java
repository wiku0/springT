package pl.student.wk.controller;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import pl.student.wk.domain.Tel;
import pl.student.wk.domain.User;
import pl.student.wk.service.MailService;
import pl.student.wk.service.UserService;
import pl.student.wk.utilis.CatpchaVerify;
import pl.student.wk.validator.UserValidator;

@Controller
@SessionAttributes
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Autowired
	MailService emailSend;
	@Autowired
	UserService userService;

	UserValidator userValidator = new UserValidator();
	
	@RequestMapping("/test")
	public String test(Map<String, Object> map) {
		map.put("user", new User());
		User usr = new User();
		Tel tel = new Tel();
		usr.setTel(null);
		return "home";
	};

	@RequestMapping("/Registrations")
	public String registration(Map<String, Object> map) {
		map.put("user", new User());
		return "Registration";
	};

	@RequestMapping(value = "/addUsr", method = RequestMethod.POST)
	public String addContact(@ModelAttribute User user, BindingResult result, HttpServletRequest request, Model model) {

		String captchaR = request.getParameter("g-recaptcha-response");

		System.out.println("Login: " + user.getLogin() + "\nHas這: " + user.getPass() + "\nemail: " + user.getEmail());

		userValidator.validate(user, result);
		System.out.println(result.getErrorCount());

		if (result.getErrorCount() == 0) {
			try {
				userService.getUser("login", user.getLogin());
				user.setPass("");
				result.rejectValue("login", "error.logintaken");
				return "Registration";
			} catch (IndexOutOfBoundsException e) {
				try {
					userService.getUser("email", user.getEmail());
					user.setPass("");
					result.rejectValue("email", "error.emailtaken");
					return "Registration";
				} catch (IndexOutOfBoundsException ex) {
					try {

						if (CatpchaVerify.verify(captchaR)) {
							user.setEmailToken(UUID.randomUUID().toString());
							userService.addUser(user);
							emailSend.send(user);
							System.out.println("Dodano u篡tkownika do db");
							model.addAttribute("message", "succesadd");
							return "redirect:/";
						} else {
							System.out.println("Z造 catpcha");
							user.setPass("");
							model.addAttribute("message", "Invalid captcha");
							return "Registration";
						}

					} catch (IOException exx) {
						return "redirect:/";
					}

				}
			}
		}
		user.setPass("");
		return "Registration";
	}

	@RequestMapping("/ActivationLink**")
	public String activation(@RequestParam(value = "user") String login, @RequestParam(value = "token") String token,
			Model model) {

		System.out.println("User: " + login + "\n" + "Token: " + token);

		try {
			User userCheck = userService.getUser("login", login);

			String tokenCheck = userCheck.getEmailToken();
			
			if (token.matches(tokenCheck)) {
				userCheck.setActive(true);
				userService.updateUser(userCheck);
				System.out.println("User updated!");
				model.addAttribute("message", "Konto zosta這 aktywowane<br><br>");
				return "home";
				
			}else {
				model.addAttribute("message", "Token nie jest prawid這wy");
				return "home";
			}
		} catch (Exception e) {
			model.addAttribute("message", "U篡tkownik nie istnieje<br><br>");
			return "home";
		}
	}

	@RequestMapping("/logins")
	public String zaloguj(Map<String, Object> map) {
		map.put("login", new User());

		return "login";

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("login") User user, BindingResult result) {

		return null;

	}

}
