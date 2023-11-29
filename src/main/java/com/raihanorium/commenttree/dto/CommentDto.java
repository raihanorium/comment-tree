package com.raihanorium.commenttree.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class CommentDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6873611286341634186L;

    private Integer id;
    private String comment;
    private LocalDateTime time;
    private List<CommentDto> replies;
}
