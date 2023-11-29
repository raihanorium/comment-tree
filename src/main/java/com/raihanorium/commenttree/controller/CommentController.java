package com.raihanorium.commenttree.controller;

import com.raihanorium.commenttree.service.CommentService;
import com.raihanorium.commenttree.util.Views;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CommentController {

    private static final String ATTR_LAZY_COMMENTS = "lazyComments";
    private static final String ATTR_EAGER_COMMENTS = "eagerComments";

    @Nonnull
    private final CommentService lazyFetchCommentService;
    @Nonnull
    private final CommentService eagerFetchCommentService;

    @RequestMapping
    public String showComments(ModelMap modelMap) {
        modelMap.addAttribute(ATTR_LAZY_COMMENTS, lazyFetchCommentService.getCommentTree());
        modelMap.addAttribute(ATTR_EAGER_COMMENTS, eagerFetchCommentService.getCommentTree());
        return Views.COMMENT;
    }
}
