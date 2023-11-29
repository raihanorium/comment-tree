package com.raihanorium.commenttree.service;

import com.raihanorium.commenttree.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> getCommentTree();
}
