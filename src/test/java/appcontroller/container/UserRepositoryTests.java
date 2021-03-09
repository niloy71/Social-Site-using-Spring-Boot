package appcontroller.container;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import appcontroller.container.PostRepository;
import appcontroller.container.User;
import appcontroller.container.UserRepository;
import appcontroller.container.postController;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository repo;
	

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("asdf12345@mail.com");
		user.setPassword("asdf12");
		user.setFirstName("asd");
		user.setLastName("fgh");
		
		User savedUser = repo.save(user);
		User existUser = entityManager.find(User.class, savedUser.getId());
		
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
	}
	
	@Test
	public void testFindUserByEmail() {
		String email = "ara@gmail.com";
		
		User user = repo.findByEmail(email);
		
		assertThat(user).isNotNull();
	}
	
	@Test
	public void testFeed() {
		postController posts = new postController();
		posts.setEmail("zxc1231212@gmail.com");
		posts.setFullName("zxc one");
		posts.setPosts("Hello Test");
		
		postController savedPost = postRepo.save(posts);
		postController existPost = entityManager.find(postController.class, savedPost.getId());
		
		assertThat(existPost.getEmail()).isEqualTo(posts.getEmail());

		
	}
	
	@Test
	public void testcomment() {
		commentController comments = new commentController();
		comments.setEmail("zxc1231212@gmail.com");
		comments.setFullName("zxc one");
		comments.setComment("Hello Test");
		comments.setPostId((long) 1);
		
		commentController savedPost = commentRepo.save(comments);
		commentController existPost = entityManager.find(commentController.class, savedPost.getId());
		
		assertThat(existPost.getEmail()).isEqualTo(comments.getEmail());

		
	}
	
}
