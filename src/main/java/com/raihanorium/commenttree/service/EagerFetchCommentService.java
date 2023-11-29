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
import java.util.Objects;
import java.util.stream.Collectors;

@Service("eagerFetchCommentService")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EagerFetchCommentService implements CommentService {

    @Nonnull
    private final CommentRepository commentRepository;

    @Override
    public CommentTreeHolder getCommentTree() {
        long start = System.currentTimeMillis();
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        List<Comment> all = commentRepository.findAll();
        List<CommentDto> roots = all.stream()
                .filter(comment -> Objects.isNull(comment.getParentId()))
                .map(CommentMapper.INSTANCE::map)
                .toList();

        List<CommentDto> tree = roots.stream()
                .peek(root -> {
                    List<CommentDto> replies = all.stream()
                            .filter(comment -> Objects.equals(root.getId(), comment.getParentId()))
                            .map(CommentMapper.INSTANCE::map)
                            .collect(Collectors.toList());
                    root.setReplies(replies);
                }).toList();

        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        return CommentTreeHolder.builder()
                .comments(tree)
                .executionTime((System.currentTimeMillis() - start))
                .memoryUsed(afterUsedMem - beforeUsedMem)
                .build();
    }
}
