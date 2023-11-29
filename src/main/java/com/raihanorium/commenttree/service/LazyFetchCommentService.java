package com.raihanorium.commenttree.service;

import com.raihanorium.commenttree.dto.CommentDto;
import com.raihanorium.commenttree.dto.CommentTreeHolder;
import com.raihanorium.commenttree.mapper.CommentMapper;
import com.raihanorium.commenttree.model.Comment;
import com.raihanorium.commenttree.repository.CommentRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("lazyFetchCommentService")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LazyFetchCommentService implements CommentService {

    @Nonnull
    private final CommentRepository commentRepository;

    @Override
    public CommentTreeHolder getCommentTree() {
        long start = System.currentTimeMillis();
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        List<CommentDto> comments = findTopOrderComments().stream()
                .map(comment -> {
                    CommentDto commentDto = CommentMapper.INSTANCE.map(comment);
                    commentDto.setReplies(findReplies(comment));
                    return commentDto;
                })
                .collect(Collectors.toList());
        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        return CommentTreeHolder.builder()
                .comments(comments)
                .executionTime((System.currentTimeMillis() - start))
                .memoryUsed(afterUsedMem - beforeUsedMem)
                .build();
    }

    private List<Comment> findTopOrderComments() {
        return commentRepository.findAllByParentIdIsNull();
    }

    private List<CommentDto> findReplies(Comment comment) {
        return commentRepository.findAllByParentId(comment.getId())
                .stream().map(reply -> {
                    CommentDto commentDto = CommentMapper.INSTANCE.map(reply);
                    commentDto.setReplies(findReplies(reply));
                    return commentDto;
                })
                .collect(Collectors.toList());
    }
}
