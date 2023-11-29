package com.raihanorium.commenttree.mapper;

import com.raihanorium.commenttree.dto.CommentDto;
import com.raihanorium.commenttree.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDto map(Comment comment);

    Comment map(CommentDto comment);
}
