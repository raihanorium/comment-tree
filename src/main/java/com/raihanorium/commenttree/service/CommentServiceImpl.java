package com.raihanorium.commenttree.service;

import com.raihanorium.commenttree.dto.CommentDto;
import com.raihanorium.commenttree.mapper.CommentMapper;
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
    public List<CommentDto> getCommentTree() {
        return findTopOrderComments().stream()
                .map(comment -> {
                    CommentDto commentDto = CommentMapper.INSTANCE.map(comment);
                    commentDto.setChildren(findReplies(comment));
                    return commentDto;
                })
                .collect(Collectors.toList());
    }

    private List<Comment> findTopOrderComments() {
        return commentRepository.findAllByParentIdIsNull();
    }

    private List<CommentDto> findReplies(Comment comment) {
        return commentRepository.findAllByParentId(comment.getId())
                .stream().map(reply -> {
                    CommentDto commentDto = CommentMapper.INSTANCE.map(reply);
                    commentDto.setChildren(findReplies(reply));
                    return commentDto;
                })
                .collect(Collectors.toList());
    }
}
