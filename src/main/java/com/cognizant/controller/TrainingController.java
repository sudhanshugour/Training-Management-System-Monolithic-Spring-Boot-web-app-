package com.cognizant.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.customValidators.PasswordValidator;
import com.cognizant.entity.Contact;
import com.cognizant.entity.ForgotUserId;
import com.cognizant.entity.ResetPassword;
import com.cognizant.model.Login;
import com.cognizant.model.Person;
import com.cognizant.model.SecretQuestions;
import com.cognizant.repositories.PersonRepository;
import com.cognizant.repositories.SecretQuestionsRepository;

@Controller
@SessionAttributes("userId")

public class TrainingController {
	@Autowired
	SecretQuestionsRepository secretQuestionsRepository;
	@Autowired
	PersonRepository personRepository;
	@Autowired
	private PasswordValidator passwordValidator;

	HttpSession session;

	public TrainingController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/user_registration", method = RequestMethod.GET)
	public String showPage(@ModelAttribute("person") Person person, Model model) {
////////////////////////////////////////////////
		try {
			if (session.getAttribute("userId") != null) {
				Person person_check = personRepository.findByUserId((String) session.getAttribute("userId"));
				if (person_check.getRole().equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				} else if (person_check.getRole().equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {

		}
//////////////////////////////////////////////////////////////

		person = new Person();
		model.addAttribute("par", "");
		model.addAttribute("para", "");
		return "user_registration";
	}

	@RequestMapping(value = "/reset_password", method = RequestMethod.POST)
	public String showReset(@ModelAttribute("resetPassword") ResetPassword resetPassword, Model model) {
////////////////////////////////////////////////
		try {
			if (session.getAttribute("userId") != null) {
				Person person_check = personRepository.findByUserId((String) session.getAttribute("userId"));
				if (person_check.getRole().equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				} else if (person_check.getRole().equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {

		}
//////////////////////////////////////////////////////////////
		resetPassword = new ResetPassword();
		String attribute = (String) model.getAttribute("userId");
		model.addAttribute("userId", attribute);
		return "reset_password";
	}

	@RequestMapping(value = "/recover_user_true", method = RequestMethod.GET)
	public String recover_user_true(@ModelAttribute("forgotUserId") ForgotUserId forgotUserId, Model model) {
////////////////////////////////////////////////
		try {
			if (session.getAttribute("userId") != null) {
				Person person_check = personRepository.findByUserId((String) session.getAttribute("userId"));
				if (person_check.getRole().equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				} else if (person_check.getRole().equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {

		}
//////////////////////////////////////////////////////////////

		forgotUserId = new ForgotUserId();
		model.addAttribute(forgotUserId);

		return "recover_user_true";
	}

	@RequestMapping(value = "/recover_password_true", method = RequestMethod.GET)
	public String recover_password_true(@ModelAttribute("forgotUserId") ForgotUserId forgotUserId, Model model) {
////////////////////////////////////////////////
		try {
			if (session.getAttribute("userId") != null) {
				Person person_check = personRepository.findByUserId((String) session.getAttribute("userId"));
				if (person_check.getRole().equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				} else if (person_check.getRole().equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {

		}
//////////////////////////////////////////////////////////////
		forgotUserId = new ForgotUserId();
		model.addAttribute(forgotUserId);

		return "recover_password_true";
	}

	@RequestMapping(value = "/recover_user", method = RequestMethod.GET)
	public String recover_user(@ModelAttribute("Contact") Contact contact, Model model) {
////////////////////////////////////////////////
		try {
			if (session.getAttribute("userId") != null) {
				Person person_check = personRepository.findByUserId((String) session.getAttribute("userId"));
				if (person_check.getRole().equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				} else if (person_check.getRole().equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {

		}
//////////////////////////////////////////////////////////////
		contact = new Contact();
		model.addAttribute(contact);

		return "recover_user";
	}

	@RequestMapping(value = "/recover_password", method = RequestMethod.GET)
	public String recover_password(@ModelAttribute("Contact") Contact contact, Model model) {
////////////////////////////////////////////////
		try {
			if (session.getAttribute("userId") != null) {
				Person person_check = personRepository.findByUserId((String) session.getAttribute("userId"));
				if (person_check.getRole().equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				} else if (person_check.getRole().equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {

		}
//////////////////////////////////////////////////////////////
		contact = new Contact();
		model.addAttribute(contact);

		return "recover_password";
	}

	@RequestMapping(value = "/userRegistration", method = RequestMethod.POST)
	public String calculateTax(@ModelAttribute("person") @Valid Person person, BindingResult result, Model model) {
////////////////////////////////////////////////
		try {
			if (session.getAttribute("userId") != null) {
				Person person_check = personRepository.findByUserId((String) session.getAttribute("userId"));
				if (person_check.getRole().equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				} else if (person_check.getRole().equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {

		}
//////////////////////////////////////////////////////////////
		if (result.hasErrors()) {
			return "user_registration";
		}
		Person pe = personRepository.findByUserId(person.getUserId());
		if (pe != null) {
			model.addAttribute("par", "This Email ID is already registed with us Kindly Login");
			return "user_registration";
		}
		if (person.getQid1() == person.getQid2() || person.getQid2() == person.getQid3()
				|| person.getQid1() == person.getQid3()) {
			model.addAttribute("para", "Secret Questions Should be different");
			return "user_registration";
		}
		personRepository.save(person);
		model.addAttribute(new Login());
		model.addAttribute(new ForgotUserId());
		model.addAttribute(new Person());
		return "login";
	}

	@ModelAttribute("secretQuestionsList")
	public Map<Integer, String> buildState() {
		Map<Integer, String> secretQuestionsList = new HashMap<Integer, String>();
		List<SecretQuestions> list = secretQuestionsRepository.findAll();
		for (SecretQuestions secretQuestions : list) {
			secretQuestionsList.put(secretQuestions.getSecQueId(), secretQuestions.getQuestion());

		}

		return secretQuestionsList;

	}

	@RequestMapping(value = "loadQuestion", method = RequestMethod.POST)
	public String loadQuestions(@ModelAttribute("contact") @Valid Contact contact, BindingResult result, Model model) {
////////////////////////////////////////////////
		try {
			if (session.getAttribute("userId") != null) {
				Person person_check = personRepository.findByUserId((String) session.getAttribute("userId"));
				if (person_check.getRole().equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				} else if (person_check.getRole().equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {

		}
//////////////////////////////////////////////////////////////
		Person person = personRepository.findByContactNumber(contact.getContact());
		model.addAttribute(new ForgotUserId());
		
		if (person != null) {
			model.addAttribute(person);
			List<SecretQuestions> question = secretQuestionsRepository.findBySecQueId(person.getQid1());
			model.addAttribute("que1", question.get(0).getQuestion());
			question = secretQuestionsRepository.findBySecQueId(person.getQid2());
			model.addAttribute("que2", question.get(0).getQuestion());
			question = secretQuestionsRepository.findBySecQueId(person.getQid3());
			model.addAttribute("que3", question.get(0).getQuestion());
			// model.addAttribute("userId","Your User Id is "+person.getUserId());
			model.addAttribute("contact", person.getContactNumber());
		} else {
			
			 model.addAttribute("message"," User Not Found");
			 return "recover_user";
		}
		
		model.addAttribute("userId", "");
		
		model.addAttribute(new Person());
		return "recover_user_true";
	}

	@RequestMapping(value = "validate", method = RequestMethod.POST)
	public String validate(@ModelAttribute("forgotUserId") @Valid ForgotUserId forgotUserId, BindingResult result,
			Model model) {
////////////////////////////////////////////////
		try {
			if (session.getAttribute("userId") != null) {
				Person person_check = personRepository.findByUserId((String) session.getAttribute("userId"));
				if (person_check.getRole().equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				} else if (person_check.getRole().equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {

		}
//////////////////////////////////////////////////////////////
		Person person = personRepository.findByContactNumberAndAns1AndAns2AndAns3(forgotUserId.getContact(),forgotUserId.getAns1(), forgotUserId.getAns2(),
				forgotUserId.getAns3());
//		model.addAttribute(person);
		if (person != null) {
			List<SecretQuestions> question = secretQuestionsRepository.findBySecQueId(person.getQid1());
			model.addAttribute("que1", question.get(0).getQuestion());
			question = secretQuestionsRepository.findBySecQueId(person.getQid2());
			model.addAttribute("que2", question.get(0).getQuestion());
			question = secretQuestionsRepository.findBySecQueId(person.getQid3());
			model.addAttribute("que3", question.get(0).getQuestion());
			model.addAttribute("userId", "Your User Id is " + person.getUserId());
			model.addAttribute("contact", person.getContactNumber());
		} else {
			model.addAttribute("userId", " User Not Found");
			model.addAttribute(new ForgotUserId());
		}

		return "recover_user_true";
	}

	@RequestMapping(value = "loadQuestion1", method = RequestMethod.POST)
	public String loadQuestions1(@ModelAttribute("contact") @Valid Contact contact, BindingResult result, Model model) {
////////////////////////////////////////////////
		try {
			if (session.getAttribute("userId") != null) {
				Person person_check = personRepository.findByUserId((String) session.getAttribute("userId"));
				if (person_check.getRole().equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				} else if (person_check.getRole().equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {

		}
//////////////////////////////////////////////////////////////
		Person person = personRepository.findByUserId(contact.getContact());
		
		
		model.addAttribute(new ForgotUserId());
		if (person != null) {
			model.addAttribute(person);
			List<SecretQuestions> question = secretQuestionsRepository.findBySecQueId(person.getQid1());
			model.addAttribute("que1", question.get(0).getQuestion());
			question = secretQuestionsRepository.findBySecQueId(person.getQid2());
			model.addAttribute("que2", question.get(0).getQuestion());
			question = secretQuestionsRepository.findBySecQueId(person.getQid3());
			model.addAttribute("que3", question.get(0).getQuestion());
			model.addAttribute("user", person.getUserId());
			model.addAttribute("contact", person.getContactNumber());
		} else {
			
			model.addAttribute("message"," User Not Found");
			return "recover_password";
		}
		model.addAttribute("password", "");
		
		model.addAttribute(new Person());
		return "recover_password_true";
	}

	@RequestMapping(value = "validate1", method = RequestMethod.POST)
	public String validate1(@ModelAttribute("forgotUserId") @Valid ForgotUserId forgotUserId, BindingResult result,
			Model model) {

		Person person = personRepository.findByUserIdAndAns1AndAns2AndAns3(forgotUserId.getContact(),
				forgotUserId.getAns1(), forgotUserId.getAns2(), forgotUserId.getAns3());
//		model.addAttribute(person);
		if (person != null) {
			List<SecretQuestions> question = secretQuestionsRepository.findBySecQueId(person.getQid1());
			model.addAttribute("que1", question.get(0).getQuestion());
			question = secretQuestionsRepository.findBySecQueId(person.getQid2());
			model.addAttribute("que2", question.get(0).getQuestion());
			question = secretQuestionsRepository.findBySecQueId(person.getQid3());
			model.addAttribute("que3", question.get(0).getQuestion());
			model.addAttribute("user", person.getUserId());
			model.addAttribute(new ResetPassword());
			// model.addAttribute("password","Your Password is "+ person.getPassword());
			return "reset_password";
		} else {
			model.addAttribute("password", " User Not Found");
		}

		return "recover_password_true";
	}

	@RequestMapping(value = "reset_pass", method = RequestMethod.POST)
	public String resetPass(@ModelAttribute("resetPassword") @Valid ResetPassword resetPassword, BindingResult result,
			Model model) {
////////////////////////////////////////////////
		try {
			if (session.getAttribute("userId") != null) {
				Person person_check = personRepository.findByUserId((String) session.getAttribute("userId"));
				if (person_check.getRole().equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				} else if (person_check.getRole().equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {

		}
//////////////////////////////////////////////////////////////
		passwordValidator.validate(resetPassword, result);
		if (result.hasErrors()) {
			model.addAttribute("message", "Something went Wrong!!! Please Try Again");
			return "reset_password";
		} else {
			Person person = personRepository.findByUserId(resetPassword.getUserId());
			if (person != null) {
				person.setPassword(resetPassword.getPassword());
				personRepository.save(person);
				model.addAttribute("message", "Password is Changed Successfully");
			}

		}

		return "reset_password";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLogin(@ModelAttribute("login") Login login, Model model) {

		////////////////////////////////////////////////
		try {
			if (session.getAttribute("userId") != null) {
				Person person = personRepository.findByUserId((String) session.getAttribute("userId"));
				if (person.getRole().equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				} else if (person.getRole().equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {

		}
		//////////////////////////////////////////////////////////////
		model.addAttribute(new Contact());
		login = new Login();
		model.addAttribute("para", "");
		model.addAttribute("message","");
		// System.out.println("controller"+secretQuestionsRepository.findAll());
		// System.out.println("conPP"+personRepository.findAll());
		return "login";
	}

	@RequestMapping(value = "/loginForm", method = RequestMethod.POST)
	public String validateLogin(@ModelAttribute("login") @Valid Login login, BindingResult result,
			HttpServletRequest request, Model map) {

		session = request.getSession();
		if (result.hasErrors()) {

			return "user_registration";

		}
		List<Person> personList = personRepository.findByUserIdAndPassword(login.getUserId(), login.getPassword());
		if (personList.size() != 0) {
			Person person = personList.get(0);

			map.addAttribute("userId", person.getUserId());
			if (person != null) {
				session.setAttribute("userId", person.getUserId());
				session.setAttribute("userName", person.getFirstName() + " " + person.getLastName());
				map.addAttribute("name", person.getFirstName() + " " + person.getLastName());
				if (person.getRole().equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				} else if (person.getRole().equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}

			}
		}
		if (!personRepository.existsById(login.getUserId())) {
			System.out.println("User does not Exists");
			map.addAttribute("para", "User does not Exists");
		} else {
			map.addAttribute("para", "Invalid Credentials !!!");
		}

		return "login";

	}

}
