package com.forohub.foro.dto;

import java.util.List;

public record TopicDto(
    Integer topicId,
    String course,
    String title,
    String description,
    List<CommentDto> comments
) {
    
}
