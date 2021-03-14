package com.niloy.controller;

import com.niloy.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FeedProcessController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private FeedRepository postRepo;

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private RepliesOfCommentREpository replyRepo;

    @PostMapping("/successfully_expose")
    public String processPost(Feed posts) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = repo.findByEmail(auth.getName());

        posts.setEmail(auth.getName());
        posts.setFullName(user.getFirstName() + " " + user.getLastName());

        postRepo.save(posts);

        return "redirect:feed";
    }

    @PostMapping("/successfully_comment")
    public String processComment(Comment comments, Feed posts) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = repo.findByEmail(auth.getName());

        comments.setEmail(auth.getName());
        comments.setFullName(user.getFirstName() + " " + user.getLastName());

        commentRepo.save(comments);

        return "redirect:feed";
    }

    @PostMapping("/successfully_replied")
    public String processReply(RepliesOfComment replies) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = repo.findByEmail(auth.getName());

        replies.setEmail(auth.getName());
        replies.setFullName(user.getFirstName() + " " + user.getLastName());

        replyRepo.save(replies);

        return "redirect:feed";
    }
}
