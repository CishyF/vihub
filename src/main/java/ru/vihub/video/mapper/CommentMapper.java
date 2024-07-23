package ru.vihub.video.mapper;

import ru.vihub.video.dto.CommentDtoResponse;
import ru.vihub.video.model.Comment;

public class CommentMapper {
    public static CommentDtoResponse mapToCommentDtoResponse(Comment comment){
        CommentDtoResponse dto = CommentDtoResponse.builder()
                .description(comment.getDescription())
                .commentator(comment.getCommentator())
                .likesNumber(comment.getLikesNumber())
                .dislikesNumber(comment.getDislikesNumber())
                .createdOn(comment.getCreatedOn())
                .build();
        return dto;
    }
}
