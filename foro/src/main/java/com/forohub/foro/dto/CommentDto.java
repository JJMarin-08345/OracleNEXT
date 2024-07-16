package com.forohub.foro.dto;

public record CommentDto(
    String topicCreateByUser,
    String topicTitle,
    Integer commentId,
    String comment
) {
    
}
