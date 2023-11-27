package com.raihanorium.commenttree.controller;

import com.raihanorium.commenttree.dto.CommentDto;
import com.raihanorium.commenttree.service.CommentService;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CommentController {

    @Nonnull
    private final CommentService commentService;

    @RequestMapping
    public String showComments(ModelMap modelMap) {
        List<CommentDto> topOrderComments = commentService.getCommentTree();
        modelMap.addAttribute("comments", topOrderComments);
        return "comments";
    }
}
