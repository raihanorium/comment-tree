package com.raihanorium.commenttree.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class CommentTreeHolder implements Serializable {
    @Serial
    private static final long serialVersionUID = 3368237351208765248L;

    private List<CommentDto> comments;
    private Long executionTime;
    private Long memoryUsed;
}
