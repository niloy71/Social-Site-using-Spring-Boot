package appcontroller.container;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegistration(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		repo.save(user);
		
		return "register_success";
	}
	

	@GetMapping("/feed")
	public String viewfeed(Model model) {
		List<postController> feedList = postRepo.findAll();
		model.addAttribute("feedList", feedList);
		
		model.addAttribute("posts", new postController());
		
		model.addAttribute("comments", new commentController());
		
		
		return "feed";
	}
	
	@PostMapping("/successfully_expose")
	public String processPost(postController posts) {
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = repo.findByEmail(auth.getName());
		
		posts.setEmail(auth.getName());
		
		posts.setFullName(user.getFirstName()+" "+user.getLastName());
		
		postRepo.save(posts);
		
		return "redirect:feed";
	}
	
	@PostMapping("/successfully_comment")
	public String processComment(commentController comments) {
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = repo.findByEmail(auth.getName());
		
		comments.setEmail(auth.getName());
		
		comments.setFullName(user.getFirstName()+" "+user.getLastName());
		comments.setPostId((long) 1);
		commentRepo.save(comments);
		
		return "redirect:feed";
	}
	
	
	
	@GetMapping("/list_users")
	public String viewUserList() {
		
		return "users";
	}
}
