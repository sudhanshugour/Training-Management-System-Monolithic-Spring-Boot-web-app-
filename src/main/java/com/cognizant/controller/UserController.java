package com.cognizant.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

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

import com.cognizant.model.QuestionAndAnswer;
import com.cognizant.entity.Contact;
import com.cognizant.entity.ForgotUserId;
import com.cognizant.model.Course;
import com.cognizant.model.Enrollment;
import com.cognizant.model.Feedback;
import com.cognizant.model.Login;
import com.cognizant.model.Person;
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
@RequestMapping(path = "/user")
public class UserController {
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
	EnrollmentRepository enrollmentRepository;
	@Autowired(required = true)
	private UserAssessmentRepository userAssessmentRepository;
	@Autowired(required = true)
	private FeedbackRepository feedbackRepository;
	
	@GetMapping(path = "logOut")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("userId", null);
		return "redirect:/login";
	}

	@GetMapping("startLearning")
	public String startLearning(Model model, @RequestParam long skillId, HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			}
			 else {
					if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
							.equalsIgnoreCase("admin")) {
						return "redirect:/admin/viewcourse";
					}
				}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////

		Skill skill = skillRepository.findById(skillId).get();
		model.addAttribute("skill", skill);
		model.addAttribute("skillDetails", skill.getCourseId());
		long topicId = 0;
		try {
			topicId = topicRepository.firstTopicIdOfSkill(skillId);
			model.addAttribute("topicId", topicId);
		} catch (Exception e) {
			model.addAttribute("message", "Currently this skill is not avilable please visit later");
			return "prerequisite";
		}

		return "user_start_learning";
	}

	@GetMapping("userAssessment")
	public String userAssessment(Model model, @RequestParam long skillId, HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			}
			else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////
		List<QuestionAndAnswer> _questionAndAnswersList = questionAndAnswerRepository.findBySkillId(skillId);
		UserAssessment userAssessment;

		try {

			userAssessment = userAssessmentRepository.findBySkillIdAndUserId(skillId,
					(String) session.getAttribute("userId"));
			//userAssessment.setMarks((short) 0);
			//userAssessment.setTotalMarks((short) _questionAndAnswersList.size());

			if (userAssessment.getAttempts() > 4) {
				List<UserAssessment> userAssessmentsList = userAssessmentRepository
						.findByUserId((String) session.getAttribute("userId"));
				System.out.println("User Assessment---------");
				model.addAttribute("userAssessmentsList", userAssessmentsList);
				return "user_assessment_report";
			}
			userAssessment.setAttempts((short) (userAssessment.getAttempts() + 1));

		} catch (Exception e) {
			Skill skill = skillRepository.findBySkillId(skillId);
			Course course = courseRepository.findByCourseId(skill.getCourseId());
			String prerequisite[] = course.getPrerequisites().split(" ");
			List<UserAssessment> list = userAssessmentRepository.findByUserId((String) session.getAttribute("userId"));
			List<String> compl = new ArrayList<>();
			for (UserAssessment user : list) {
				compl.add(user.getTopicCompltetd().trim().toLowerCase());
			}
			boolean flag = true;
			for (int i = 0; i < prerequisite.length; i++) {

				if (prerequisite[i].equalsIgnoreCase("na") || prerequisite[i].equalsIgnoreCase("no")
						|| prerequisite[i].equalsIgnoreCase("none")) {
					flag = true;
				} else {
					if (compl.indexOf(prerequisite[i].trim().toLowerCase()) != -1) {
						flag = true;
					} else {
						model.addAttribute("message", "Please complete the Prerequisites of the course first !!!");
						flag = false;
						return "prerequisite";
					}
				}

			}

			userAssessment = new UserAssessment();
			userAssessment.setAttempts((short) 0);
			userAssessment.setMarks((short) 0);
			userAssessment.setTotalMarks((short) _questionAndAnswersList.size());
			userAssessment.setSkillId(skillId);
			userAssessment.setUserId((String) session.getAttribute("userId"));
			userAssessment.setAttempts((short) (1));
			userAssessment.setTopicCompltetd(skill.getSkillName());
			

		}
		List<QuestionAndAnswer> questionAndAnswersList = new ArrayList<QuestionAndAnswer>();
		while (_questionAndAnswersList.size() > 0) {
			int i = new Random().nextInt(_questionAndAnswersList.size());
			questionAndAnswersList.add(_questionAndAnswersList.get(i));
			_questionAndAnswersList.remove(i);
		}
		if (questionAndAnswersList.size() == 0) {
			model.addAttribute("message", "This Assessment is not available right now, please visit later !!!");
			return "prerequisite";
		}

		model.addAttribute("userAssessmentId", userAssessmentRepository.save(userAssessment).getUserAssessmentId());

		Skill skill = skillRepository.findById(skillId).get();
		model.addAttribute("skill", skill);

		
		model.addAttribute("QnAList", questionAndAnswersList);
		return "user_assessment";
	}

	@GetMapping("assessmentResult")
	public String assessmentResult(Model model, @RequestParam long skillId, @RequestParam long userAssessmentId,
			@RequestParam Map<String, String> allParams, HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			}
			else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////
		Skill skill = skillRepository.findById(skillId).get();
		model.addAttribute("skill", skill);
		int marks = 0;
		List<QuestionAndAnswer> _questionAndAnswersList = questionAndAnswerRepository.findBySkillId(skillId);

		for (QuestionAndAnswer QnA : _questionAndAnswersList) {
			long i = QnA.getQuestionAndAnswerId();
			boolean ans1 = allParams.get(i + "_" + 1) != null ? true : false;
			boolean ans2 = allParams.get(i + "_" + 2) != null ? true : false;
			boolean ans3 = allParams.get(i + "_" + 3) != null ? true : false;
			boolean ans4 = allParams.get(i + "_" + 4) != null ? true : false;
			boolean isRight = ((QnA.isOption1IsAnswer() == ans1) && (QnA.isOption2IsAnswer() == ans2)
					&& (QnA.isOption3IsAnswer() == ans3) && (QnA.isOption4IsAnswer() == ans4));
			if (isRight)
				marks++;

		}
		UserAssessment userAssessment = userAssessmentRepository.findById(userAssessmentId).get();
		System.out.println("current  total marks" + userAssessment.getTotalMarks());
		System.out.println("Current Marks" + userAssessment.getMarks());
		System.out.println("Total marls" + _questionAndAnswersList.size());
		
		if(userAssessment.getTotalMarks() == _questionAndAnswersList.size() && 
				userAssessment.getMarks() > marks) {
			
		}
		else {
		userAssessment.setMarks((short) marks);
		userAssessment.setTotalMarks((short) _questionAndAnswersList.size());
		userAssessmentRepository.save(userAssessment);
		}
		model.addAttribute("course", courseRepository.findByCourseId(
				skillRepository.findBySkillId(userAssessment.getSkillId()).getCourseId()));
		model.addAttribute("marks", marks);
		model.addAttribute("totalMarks", _questionAndAnswersList.size());
		System.out.println(marks);
		return "assessment_result";
	}

	@GetMapping("userAssessmentsList")
	public String userAssessmentsList(Model model, HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			}
			else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////
		List<UserAssessment> userAssessmentsList = userAssessmentRepository
				.findByUserId((String) session.getAttribute("userId"));

		model.addAttribute("userAssessmentsList", userAssessmentsList);

		return "user_assessment_report";
	}

	@GetMapping("topicDetails")
	public String topicDetails(Model model, @RequestParam long topicId, @RequestParam long skillId,
			HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			}
			else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////
		List<Topic> topicList = topicRepository.findBySkillId(skillId);

		long nextTopic = 0;
		long preTopic = 0;
		Topic currentTopic = topicRepository.findById(topicId).get();

		for (int i = 0; i < topicList.size(); i++) {
			if (topicList.get(i).getTopicId() == topicId) {
				if (i != 0) {
					preTopic = topicList.get(i - 1).getTopicId();
				}
				if (i != topicList.size() - 1) {
					nextTopic = topicList.get(i + 1).getTopicId();
				}
			}
		}

		model.addAttribute("topicList", topicList);
		model.addAttribute("currentTopic", currentTopic);
		model.addAttribute("preTopic", preTopic);
		model.addAttribute("nextTopic", nextTopic);

		return "user_skill_all_topics";
	}

	//////////////////////////////////////////////
	//////////////////////////////////////////////
	//////////////////////////////////////////////
	@RequestMapping(value = "/user_search")
	public String recover_user(@ModelAttribute("Contact") Contact contact, Model model, HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			}
			else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////
		contact = new Contact();
		model.addAttribute(contact);
		List<Course> plist = courseRepository.findAll();
		model.addAttribute("plist", plist);

		return "user_search";
	}

	@RequestMapping(value = "/user_enrolled_courses")
	public String enrolledCourses(Model model, HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			}
			else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////
		List<Enrollment> course = enrollmentRepository.findByUserId((String) session.getAttribute("userId"));
		List<Long> list = new ArrayList<>();
		for (Enrollment e : course) {
			list.add(e.getCourseId());
		}
		List<Course> plist = courseRepository.findByCourseIdIn(list);
		model.addAttribute("plist", plist);

		return "user_enrolled_courses";
	}

	@RequestMapping(value = "/search_result")
	public String searchResult(@ModelAttribute("contact") @Valid Contact contact, BindingResult result, Model model,
			HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			}
			else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////
		if (result.hasErrors()) {
			return "user_search";
		}
		System.out.println(contact.getContact());

		List<Skill> skills = skillRepository.findBySkillNameLikeIgnoreCase("%" + contact.getContact() + "%");
		List<Long> list = new ArrayList<Long>();
		for (Skill s : skills) {
			list.add(s.getCourseId());
		}

		List<Course> searchedCourses = courseRepository.findByCourseIdIn(list);
		List<Course> courseList = courseRepository.findByCourseNameLikeIgnoreCase("%" + contact.getContact() + "%");
		searchedCourses.addAll(courseList);
		HashSet<Course> unique = new HashSet<>(searchedCourses);
		if (searchedCourses.size() == 0) {
			model.addAttribute("message", "No course available for this skill");
			List<Course> plist = courseRepository.findAll();
			model.addAttribute("plist", plist);
			return "user_search";
		}
		searchedCourses = new ArrayList<>(unique);
		model.addAttribute("plist", searchedCourses);
		for (Course cou : searchedCourses) {
			System.out.println(cou);
		}

		// personRepository.save(person);

		return "view_search_course";
	}

	@RequestMapping("/CourseDetails")
	public String usercoursedetails(@RequestParam long courseId, @ModelAttribute("course") Course course, Model model,
			HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			}
			else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
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
		model.addAttribute("course", course2);
		List<Skill> skillList = skillRepository.findByCourseId(courseId);
		model.addAttribute("skillList", skillList);
		model.addAttribute("courseId", courseId);
		model.addAttribute("label", "");
		model.addAttribute("feedback", new Feedback());
		// System.out.println(course);
		return "usercoursedetails";
	}

	@RequestMapping("/CourseDetailsShow")
	public String userSkillsCoursedetails(@RequestParam long courseId, @ModelAttribute("course") Course course,
			HttpServletRequest request, Model model) {

////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			}
			else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
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
		Enrollment enr = enrollmentRepository.findByUserIdAndCourseId((String) session.getAttribute("userId"),
				courseId);
		if (enr != null) {
			return "redirect:/user/CourseDetails?courseId=" + courseId;
		}
		List<Skill> skillList = skillRepository.findByCourseId(courseId);
		model.addAttribute("skillList", skillList);
		model.addAttribute("courseId", courseId);
		model.addAttribute("course", course2);

		// System.out.println(course);
		return "usercourseskillsdetails";
	}

	@RequestMapping("/enroll")
	public String enroll(@RequestParam long courseId, @ModelAttribute("course") Course course,
			HttpServletRequest request, Model model) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			}
			else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////
		Enrollment enrollment = new Enrollment();
		enrollment.setCourseId(courseId);
		enrollment.setUserId((String) session.getAttribute("userId"));
		Enrollment enr = enrollmentRepository.findByUserIdAndCourseId((String) session.getAttribute("userId"),
				courseId);
		if (enr != null) {
			return "redirect:/user/CourseDetails?courseId=" + courseId;
		}
		enrollmentRepository.save(enrollment);

		List<Enrollment> ddd = enrollmentRepository.findByUserId((String) session.getAttribute("userId"));
		List<Long> list = new ArrayList<>();
		for (Enrollment e : ddd) {
			list.add(e.getCourseId());
		}
		List<Course> plist = courseRepository.findByCourseIdIn(list);
		model.addAttribute("plist", plist);

		return "user_enrolled_courses";
	}
	
	
	//***********************
	
	
	
	@RequestMapping("/userFeedback")
	public String feedBack(@ModelAttribute("feedBack") Feedback feedback,
			HttpServletRequest request, Model model) {

////////////////////////////////////////////////
		HttpSession session = request.getSession();
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			}
			else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////
		Feedback feed = feedbackRepository.findByUserIdAndCourseId(feedback.getUserId(), feedback.getCourseId());
		model.addAttribute("course", courseRepository.findByCourseId(feedback.getCourseId()));
		model.addAttribute("feedbac", new Feedback());
		model.addAttribute("course", courseRepository.findByCourseId(feedback.getCourseId()));
		if(feed != null) {
			
			feed.setFeedback(feedback.getFeedback());
			feedbackRepository.save(feed);
			model.addAttribute("label", "FeedBack Changed Successfully");
			return "redirect:/user/CourseDetails?courseId=" + feedback.getCourseId();
		}
		feedbackRepository.save(feedback);
		model.addAttribute("label", "FeedBack Changed Successfully");
		return "redirect:/user/CourseDetails?courseId=" + feedback.getCourseId();
		// System.out.println(course);
		
	}
	
	
	@GetMapping("user_feed")
	public String userFeedbackPage(@RequestParam long courseId, Model model, HttpServletRequest request) {
////////////////////////////////////////////////
		HttpSession session = request.getSession();
		model.addAttribute("feedback", new Feedback());
		try {
			if (session.getAttribute("userId") == null) {
				throw new NullPointerException();
			}
			else {
				if (personRepository.findByUserId((String) session.getAttribute("userId")).getRole()
						.equalsIgnoreCase("admin")) {
					return "redirect:/admin/viewcourse";
				}
			}
		} catch (NullPointerException ex) {
			model.addAttribute("login", new Login());
			return "redirect:/login";
		}
//////////////////////////////////////////////////////////////
		
		
		model.addAttribute("label", "");
		model.addAttribute("course", courseRepository.findByCourseId(courseId));
		
		return "user_feedback";
	}
}
