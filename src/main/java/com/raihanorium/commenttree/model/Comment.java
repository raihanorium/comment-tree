package com.raihanorium.commenttree.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
public class Comment implements Serializable {

    @Serial
    private static final long serialVersionUID = 5077709966796771984L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer parentId;

    private String comment;

    private LocalDateTime time;
}
