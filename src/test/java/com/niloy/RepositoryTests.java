package com.niloy;

import static org.assertj.core.api.Assertions.assertThat;

import com.niloy.model.*;
import com.niloy.model.dataacceessobject.CommentRepository;
import com.niloy.model.dataacceessobject.FeedRepository;
import com.niloy.model.dataacceessobject.RepliesOfCommentREpository;
import com.niloy.model.dataacceessobject.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RepositoryTests {
	
	@Autowired
	private UserRepository repo;

	@Autowired
	private FeedRepository postRepo;

	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private RepliesOfCommentREpository repliesRepo;

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
		Feed posts = new Feed();
		posts.setEmail("zxc1231212@gmail.com");
		posts.setFullName("zxc one");
		posts.setPosts("Hello Test");

		Feed savedPost = postRepo.save(posts);
		Feed existPost = entityManager.find(Feed.class, savedPost.getId());

		assertThat(existPost.getEmail()).isEqualTo(posts.getEmail());
	}

	@Test
	public void testcomment() {
		Comment comments = new Comment();
		comments.setEmail("zxc123@gmail.com");
		comments.setFullName("zxc one");
		comments.setComment("Hello Test");
		comments.setPostId((long) 4);

		Comment savedComment = commentRepo.save(comments);
		Comment existComment = entityManager.find(Comment.class, savedComment.getId());

		assertThat(existComment.getEmail()).isEqualTo(comments.getEmail());
	}

	@Test
	public void testreply() {
		RepliesOfComment replies = new RepliesOfComment();
		replies.setEmail("zxc123@gmail.com");
		replies.setFullName("zxc one");
		replies.setReply("Hello Test");
		replies.setCommentId((long) 1);

		RepliesOfComment savedReplies = repliesRepo.save(replies);
		RepliesOfComment existReplies = entityManager.find(RepliesOfComment.class, savedReplies.getId());

		assertThat(existReplies.getEmail()).isEqualTo(replies.getEmail());
	}
}
