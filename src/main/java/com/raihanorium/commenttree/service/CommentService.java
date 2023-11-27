package com.raihanorium.commenttree.service;

import com.raihanorium.commenttree.dto.CommentDto;
import com.raihanorium.commenttree.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findTopOrderComments();

    List<CommentDto> findReplies(Comment comment);

    List<CommentDto> getCommentTree();
}
