package com.raihanorium.commenttree.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class CommentDto {
    private Integer id;
    private String comment;
    private LocalDateTime time;
    private List<CommentDto> children;
}
