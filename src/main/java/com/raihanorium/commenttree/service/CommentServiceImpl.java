package com.raihanorium.commenttree.service;

import com.raihanorium.commenttree.dto.CommentDto;
import com.raihanorium.commenttree.model.Comment;
import com.raihanorium.commenttree.repository.CommentRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CommentServiceImpl implements CommentService {

    @Nonnull
    private final CommentRepository commentRepository;

    @Override
    public List<Comment> findTopOrderComments() {
        return commentRepository.findAllByParentIdIsNull();
    }

    @Override
    public List<CommentDto> findReplies(Comment comment) {
        return commentRepository.findAllByParentId(comment.getId())
                .stream().map(reply -> CommentDto.builder()
                        .id(reply.getId())
                        .comment(reply.getComment())
                        .time(reply.getTime())
                        .children(findReplies(reply))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getCommentTree() {
        return findTopOrderComments().stream()
                .map(comment ->
                        CommentDto.builder()
                                .id(comment.getId())
                                .comment(comment.getComment())
                                .time(comment.getTime())
                                .children(findReplies(comment))
                                .build())
                .collect(Collectors.toList());
    }
}
