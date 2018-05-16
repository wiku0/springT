package pl.student.wk.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.student.wk.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	
	@RequestMapping(value = { "/", "wk" }, method = RequestMethod.GET)
	public String home(HttpServletRequest request, Locale locale, Model model) {
		
		System.out.println(request.getRequestURL());

		try {
			if (request.getParameter("message").matches("succesadd")) {
				model.addAttribute("message", "Wys³aliœmy link aktywuj¹cy na email");
			}
		} catch (NullPointerException e) {

		} finally {

			Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

			String formattedDate = dateFormat.format(date);

			model.addAttribute("serverTime", formattedDate);
		}

		return "home";

	}



}
