package com.cognizant.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.entity.Contact;
import com.cognizant.entity.ForgotUserId;
import com.cognizant.model.Course;
import com.cognizant.model.Enrollment;
import com.cognizant.model.Feedback;
import com.cognizant.model.Login;
import com.cognizant.model.Person;
import com.cognizant.model.QuestionAndAnswer;
import com.cognizant.model.SecretQuestions;
import com.cognizant.model.Skill;
import com.cognizant.model.Topic;
import com.cognizant.model.UserAssessment;
import com.cognizant.repositories.CourseRepository;
import com.cognizant.repositories.EnrollmentRepository;
import com.cognizant.repositories.FeedbackRepository;
import com.cognizant.repositories.PersonRepository;
import com.cognizant.repositories.QuestionAndAnswerRepository;
import com.cognizant.repositories.SecretQuestionsRepository;
import com.cognizant.repositories.SkillRepository;
import com.cognizant.repositories.TopicRepository;
import com.cognizant.repositories.UserAssessmentRepository;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {
	@Autowired(required = true)
	private CourseRepository courseRepository;
	@Autowired(required = true)
	private SkillRepository skillRepository;
	@Autowired(required = true)
	private TopicRepository topicRepository;
	@Autowired(required = true)
	private QuestionAndAnswerRepository questionAndAnswerRepository;
	@Autowired
	PersonRepository personRepository;
	@Autowired
	SecretQuestionsRepository secretQuestionsRepository;
	@Autowired
	FeedbackRepository feedbackRepository;
	@Autowired
	EnrollmentRepository enrollmentRepository;
	@Autowired
	UserAssessmentRepository userAssessmentRepository;

	@GetMapping("courseTakenReoprt")
	public String courseTakenReoprt(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}

		String user_id = (String) session.getAttribute("userId");
		Iterable<Course> courseList = courseRepository.findByUserId(user_id);
		Map<Long, Long> noOfEnrollCourse = new HashMap<>();

		long totalUser = 0;
		for (Course c : courseList) {
			long CTakenCount = enrollmentRepository.courseTakenCount(c.getCourseId());
			noOfEnrollCourse.put(c.getCourseId(), CTakenCount);
			totalUser += CTakenCount;

		}

		model.addAttribute("totalUser", totalUser);
		model.addAttribute("courseList", courseList);
		model.addAttribute("noOfEnrollCourse", noOfEnrollCourse);
		model.addAttribute("contact", new Contact());
		return "course_taken_report";
	}

	@GetMapping("adminCourseStudentReport")
	public String adminCourseStudentReport(Model model, @RequestParam long courseId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}

		List<Enrollment> enrollments = enrollmentRepository.findByCourseId(courseId);
		String courseName = courseRepository.findById(courseId).get().getCourseName();

		List<Person> persons = new ArrayList<>();

		for (Enrollment e : enrollments) {
			persons.add(personRepository.findByUserId(e.getUserId()));
		}

		model.addAttribute("persons", persons);
		model.addAttribute("courseName", courseName);

		return "admin_course_student_report";
	}

	///////////////////////////////////////////////////

	@RequestMapping(value = "/admin_registration")
	public String showPage(@ModelAttribute("person") Person person, Model model, HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		person = new Person();
		model.addAttribute("para", "");
		model.addAttribute("paraa", "");
		model.addAttribute("par", "");
		return "admin_registration";
	}

	@RequestMapping(value = "/adminRegistration", method = RequestMethod.POST)
	public String calculateTax(@ModelAttribute("person") @Valid Person person, BindingResult result, Model model,
			HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		if (result.hasErrors()) {
			return "admin_registration";
		}
		System.out.println(person);
		Person pe = personRepository.findByUserId(person.getUserId());
		if (pe != null) {
			model.addAttribute("par", "This Email ID is already registed with us Kindly Login");
			return "admin_registration";
		}
		if (person.getQid1() == person.getQid2() || person.getQid2() == person.getQid3()
				|| person.getQid1() == person.getQid3()) {
			model.addAttribute("para", "Secret Questions Should be different");
			return "admin_registration";
		}
		personRepository.save(person);
		model.addAttribute(new Login());
		model.addAttribute(new ForgotUserId());
		model.addAttribute(new Person());
		model.addAttribute("paraa", "Admin Added Successfully !!!");
		return "admin_registration";
	}

	@ModelAttribute("secretQuestionsList")
	public Map<Integer, String> buildState1() {
		Map<Integer, String> secretQuestionsList = new HashMap<Integer, String>();

		List<SecretQuestions> list = secretQuestionsRepository.findAll();
		for (SecretQuestions secretQuestions : list) {
			secretQuestionsList.put(secretQuestions.getSecQueId(), secretQuestions.getQuestion());

		}

		return secretQuestionsList;

	}

	@GetMapping(path = "/addQuestionAndAnswer")
	public String addQuestionAndAnswer(@ModelAttribute("queAndAns") QuestionAndAnswer questionAndAnswer, Model model,
			HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		if (questionAndAnswer.isOption1IsAnswer() == false && questionAndAnswer.isOption2IsAnswer() == false
				&& questionAndAnswer.isOption3IsAnswer() == false && questionAndAnswer.isOption4IsAnswer() == false) {
			model.addAttribute("mssg", "Please select atleast one option");
			return "admin_add_question";
		}
		questionAndAnswerRepository.save(questionAndAnswer);
		model.addAttribute("mssg", "");
		List<QuestionAndAnswer> questionAndAnswerList;
		questionAndAnswerList = questionAndAnswerRepository.findBySkillId(questionAndAnswer.getSkillId());
		model.addAttribute("questionAndAnswerList", questionAndAnswerList);
		model.addAttribute("skillId", questionAndAnswer.getSkillId());
		return "admin_skills_questions";
	}

	@GetMapping(path = "/test1")
	public @ResponseBody String test(@ModelAttribute("queAndAns") QuestionAndAnswer questionAndAnswer) {
		return questionAndAnswer.toString();
	}

	@GetMapping(path = "/addQuestionAndAnswerPage")
	public String addQuestionAndAnswerPage(@ModelAttribute("queAndAns") QuestionAndAnswer questionAndAnswer,
			@RequestParam long skillId, Model model, HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		model.addAttribute("skillId", skillId);
		model.addAttribute("mssg", "");
		return "admin_add_question";
	}

	@GetMapping(path = "/adminAddTopic")
	public String addTopicPage(@ModelAttribute("topic2") Topic topic, @RequestParam long skillId, Model model,
			HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		model.addAttribute("skillId", skillId);
		return "admin_add_topic";
	}

	@GetMapping(path = "/deleteQuestionAndAnswerPage")
	public String deleteQuestionAndAnswerPage(@RequestParam long skillId, @RequestParam long questionId, Model model,
			HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////
		model.addAttribute("mssg", "");
		try {
			if (questionAndAnswerRepository.findById(questionId) != null)
				questionAndAnswerRepository.deleteById(questionId);
		} catch (Exception e) {
			System.out.println("no element found by id " + questionId + " for delete");
		}
		List<QuestionAndAnswer> questionAndAnswerList;
		questionAndAnswerList = questionAndAnswerRepository.findBySkillId(skillId);
		model.addAttribute("questionAndAnswerList", questionAndAnswerList);
		model.addAttribute("skillId", skillId);
		return "admin_skills_questions";
	}

	@GetMapping(path = "/updateQuestionAndAnswer")
	public String updateQuestionAndAnswer(@ModelAttribute("QnA") QuestionAndAnswer questionAndAnswer,
			@RequestParam long skillId, Model model, HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////
		model.addAttribute("mssg", "");
		if (questionAndAnswer.isOption1IsAnswer() == false && questionAndAnswer.isOption2IsAnswer() == false
				&& questionAndAnswer.isOption3IsAnswer() == false && questionAndAnswer.isOption4IsAnswer() == false) {
			model.addAttribute("mssg", "Please select atleast one option");
			model.addAttribute("QnA1", questionAndAnswerRepository.findById(questionAndAnswer.getQuestionAndAnswerId()).get());
			return "admin_update_question";
		}
		questionAndAnswerRepository.save(questionAndAnswer);
		List<QuestionAndAnswer> questionAndAnswerList;
		questionAndAnswerList = questionAndAnswerRepository.findBySkillId(skillId);
		model.addAttribute("questionAndAnswerList", questionAndAnswerList);
		model.addAttribute("skillId", skillId);
		return "admin_skills_questions";
	}

	@GetMapping(path = "/updateQuestionAndAnswerPage")
	public String updateQuestionAndAnswerPage(@ModelAttribute("QnA") QuestionAndAnswer questionAndAnswer,
			@RequestParam long skillId, @RequestParam long questionId, Model model, HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		model.addAttribute("mssg", "");
		System.out.println(questionAndAnswerRepository.findById(questionId).get());
		model.addAttribute("QnA1", questionAndAnswerRepository.findById(questionId).get());
		return "admin_update_question";
	}

	@GetMapping(path = "/getskill")
	public @ResponseBody Iterable<Skill> getSkill(@RequestParam long courseId) {
		return skillRepository.findByCourseId(courseId);
	}

	@GetMapping(path = "/viewcourse")
	public String viewcourse(Model model, HttpServletRequest request) {

////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		Iterable<Course> courselist = courseRepository.findByUserId((String) session.getAttribute("userId"));// put id
																												// from
																												// session
		model.addAttribute("plist", courselist);
		return "viewcourse";
	}

	@GetMapping(path = "logOut")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("userId", null);
		return "redirect:/login";
	}

	@GetMapping(path = "/viewTopicDetails")
	public String viewTopicDetails(@RequestParam long topicId, Model model, HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		model.addAttribute("topic1", topicRepository.findById(topicId).get());
		model.addAttribute("skillId", topicRepository.findById(topicId).get().getSkillId());
		return "user_topic_content";
	}

	@RequestMapping("/addTopic")
	public String addTopic(@ModelAttribute("topic2") Topic topic, Model model, HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		short topicNo = 1;
		if (topicRepository.countTopicNoOfSkill(topic.getSkillId()) > 0) {
			topicNo = (short) (topicRepository.maxTopicNoOfSkill(topic.getSkillId()) + 1);
		}
		topic.setTopicNumber(topicNo);
		topicRepository.save(topic);
		List<Topic> topicList;
		topicList = topicRepository.findBySkillId(topic.getSkillId());
		model.addAttribute("topicList", topicList);
		model.addAttribute("skillId", topic.getSkillId());
		return "user_skill_topics";

	}

	@RequestMapping("/addCourse")
	public String addCourse(@ModelAttribute Course course, @RequestParam String skills, HttpServletRequest request,
			Model m) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			m.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		List<Course> course1 = courseRepository.findByCourseNameIgnoreCase(course.getCourseName());
		if (course1.size() != 0) {
			m.addAttribute("mssg", "Course Name is already Taken");
			return "courseform";
		}
		skills = skills.toLowerCase();
		String arr[] = skills.split(";");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].trim();
		}
		HashSet<String> skillList = new HashSet<>(Arrays.asList(arr));

		for (String skill : skillList) {
			Skill skillLocal = new Skill();
			course.setUserId((String) session.getAttribute("userId"));// put id from session
			skillLocal.setCourseId(courseRepository.save(course).getCourseId());
			skillLocal.setSkillName(skill);
			skillRepository.save(skillLocal);
		}
		Iterable<Course> courselist = courseRepository.findByUserId((String) session.getAttribute("userId"));// put id
																												// from
																												// session
		m.addAttribute("plist", courselist);

		return "viewcourse";
	}

	@RequestMapping("/CourseDetails")
	public String admincoursedetails(@RequestParam long courseId, @ModelAttribute("course") Course course, Model model,
			HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		Optional<Course> pp = courseRepository.findById(courseId);
		Course course2 = pp.get();
		// model.addAttribute(course);
		model.addAttribute("xyz", course2);
		List<Skill> skillList = skillRepository.findByCourseId(courseId);
		model.addAttribute("skillList", skillList);
		model.addAttribute("courseId", courseId);

		// System.out.println(course);
		return "admincoursedetails";
	}

	@RequestMapping("/addCourseform")
	public String addCourseForm(@ModelAttribute("course") Course course, Model model, HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		model.addAttribute("mssg", "");
		return "courseform";
	}

	@ModelAttribute("competencyLevelList")
	public List<String> buildState() {
		List<String> competencyLevelList = new ArrayList<String>();
		competencyLevelList.add("Beginner");
		competencyLevelList.add("Intermediate");
		competencyLevelList.add("Advance");
		return competencyLevelList;
	}

	@RequestMapping("/updateform")
	public String updateform(@RequestParam long courseId, @ModelAttribute("course") Course course, Model model,
			HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		Optional<Course> pp = courseRepository.findById(courseId);
		course = pp.get();
		// System.out.println(course);
		model.addAttribute(course);
		model.addAttribute("xyz", course);
		String skills = "";
		for (Skill skill : skillRepository.findByCourseId(courseId)) {
			skills += skill.getSkillName() + ", ";
		}
		model.addAttribute("skills", skills);

		return "updateform";
	}

	@RequestMapping("/update")
	public String updatecourse(@ModelAttribute("course") Course course, Model model, HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		model.addAttribute(course);
		model.addAttribute("xyz", course);
		model.addAttribute("courseId", courseRepository.save(course).getCourseId());

		model.addAttribute("xyz", course);
		List<Skill> skillList = skillRepository.findByCourseId(course.getCourseId());
		model.addAttribute("skillList", skillList);
		model.addAttribute("courseId", course.getCourseId());

		// System.out.println(course);
		return "admincoursedetails";
	}

	@RequestMapping("/updateSkill")
	public String updateSkill(@RequestParam String courseIdResponse, @RequestParam String skills,
			HttpServletRequest request, Model model) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		long courseId = Long.parseLong(courseIdResponse);
		skills = skills.toLowerCase();
		String arr[] = skills.split(";");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].trim();
		}
		HashSet<String> skillList = new HashSet<>(Arrays.asList(arr));
		for (String skill : skillList) {
			Skill newSkill = null;
				newSkill = skillRepository.findByCourseIdAndSkillNameIgnoreCase(courseId,skill);
			if(newSkill == null) {
				Skill skillLocal = new Skill();
				skillLocal.setCourseId(courseId);
				skillLocal.setSkillName(skill);
				skillRepository.save(skillLocal);
				newSkill = null;
			}
			
		}
		Iterable<Course> courselist = courseRepository.findAll();
		model.addAttribute("plist", courselist);
		return "redirect:/admin/CourseDetails?courseId=" + courseId;
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam long courseId, HttpServletRequest request, Model model) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		System.out.println(courseId + "deleted");

		courseRepository.deleteById(courseId);

		Iterable<Course> courselist = courseRepository.findByUserId((String) session.getAttribute("userId"));// put id
																												// from
																												// session
		model.addAttribute("plist", courselist);

		return "redirect:/admin/viewcourse";
	}

	@RequestMapping("/deleteTopic")
	public String deleteTopic(@RequestParam long skillId, @RequestParam long topicId, Model model,
			HttpServletRequest request) {

////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		model.addAttribute("skillId", skillId);

		try {
			if (topicRepository.findById(topicId).get() != null)
				topicRepository.deleteById(topicId);
		} catch (NoSuchElementException e) {
			System.out.println("no element found by id " + topicId + " for delete");
		}
		List<Topic> topicList;
		topicList = topicRepository.findBySkillId(skillId);
		model.addAttribute("topicList", topicList);
		return "user_skill_topics";
	}

	@RequestMapping("/skillOperation")
	public String skillOperation(@ModelAttribute("topic2") Topic topic, @RequestParam String skill,
			@RequestParam long skillId, @RequestParam long courseId, Model model,
			@ModelAttribute("course") Course course, @ModelAttribute("queAndAns") QuestionAndAnswer questionAndAnswer,
			HttpServletRequest request) {

////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		if (skill.equals("showQuestions")) {

			List<QuestionAndAnswer> questionAndAnswerList;
			questionAndAnswerList = questionAndAnswerRepository.findBySkillId(skillId);
			model.addAttribute("questionAndAnswerList", questionAndAnswerList);
			model.addAttribute("skillId", skillId);
			return "admin_skills_questions";
		} else if (skill.equals("showtopics")) {
			List<Topic> topicList;
			model.addAttribute("skillId", skillId);
			topicList = topicRepository.findBySkillId(skillId);
			model.addAttribute("topicList", topicList);
			return "user_skill_topics";
		} else if (skill.equals("addQuestion")) {

			model.addAttribute("skillId", skillId);
			return "admin_add_question";
		} else if (skill.equals("addTopic")) {

			model.addAttribute("skillId", skillId);

			return "admin_add_topic";
		} else if (skill.equals("delete")) {
			skillRepository.deleteById(skillId);
			System.out.println(skillId + "deleted");
			List<Skill> skillList = skillRepository.findByCourseId(courseId);
			model.addAttribute("skillList", skillList);
			Course xyz = new Course();
			xyz.setCourseId(courseId);
			xyz = courseRepository.findByCourseId(courseId);
			model.addAttribute("xyz", xyz);
			return "redirect:/admin/CourseDetails?courseId=" + courseId;
		}
		return "viewcourse";

	}

	@GetMapping(path = "/admin_user_feedback")
	public String seeUserFeedback(@RequestParam long courseId, Model model, HttpServletRequest request) {

////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		List<Feedback> plist = feedbackRepository.findByCourseId(courseId);
		model.addAttribute("plist", plist);
		return "admin_user_feedback";
	}

	@GetMapping(path = "/adminUserReport")
	public String userReportsk(@RequestParam String user, Model model, HttpServletRequest request) {

////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////
		model.addAttribute("mssg", "");
		List<UserAssessment> userAssessmentsList = userAssessmentRepository.findByUserId(user);
		System.out.println(user);
		model.addAttribute("userAssessmentsList", userAssessmentsList);

		return "admin_assessment_report";
	}

	@RequestMapping(value = "/adminReport")
	public String userReport(@ModelAttribute("contact") Contact contact, Model model, HttpServletRequest request) {

////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			} else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("employee")) {
					return "redirect:/user/user_search";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		model.addAttribute("mssg", "");
		List<UserAssessment> userAssessmentsList = userAssessmentRepository.findByUserId(contact.getContact());
		System.out.println(contact.getContact());
		if (userAssessmentsList.size() == 0) {
			model.addAttribute("mssg", contact.getContact() + " has not taken any assessment yet !!!");
		}
		model.addAttribute("userAssessmentsList", userAssessmentsList);

		return "admin_assessment_report";
	}

}
