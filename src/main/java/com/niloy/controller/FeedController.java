package com.niloy.controller;

import com.niloy.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class FeedController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private FeedRepository postRepo;

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private RepliesOfCommentREpository replyRepo;

    @GetMapping("/feed")
    public String viewfeed(Model model) {
        model.addAttribute("posts", new Feed());

        List<Feed> feedList = postRepo.findAll();
        Collections.reverse(feedList);
        model.addAttribute("feedList", feedList);

        model.addAttribute("comments", new Comment());

        List<Comment> commentList = commentRepo.findAll();
        model.addAttribute("commentList", commentList);

        model.addAttribute("replies", new RepliesOfComment());

        List<RepliesOfComment> replyList = replyRepo.findAll();
        model.addAttribute("replyList", replyList);

        return "feed";
    }
}
