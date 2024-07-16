package com.forohub.foro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.forohub.foro.dto.TopicDto;
import com.forohub.foro.model.Topic;
import com.forohub.foro.repository.ITopicRepository;

@Service
public class TopicService {
    private final ITopicRepository topicRepository;

    public TopicService(ITopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<TopicDto> getAllTopics(){
        List<TopicDto> topics = topicRepository.findAll().stream()
                .map(topic -> new TopicDto(
                topic.getTopicId(),
                topic.getCourse(),
                topic.getTitle(),
                topic.getDescription(),
                topic.getComments()
        )).collect(Collectors.toList());
        return topics;
    }

    public TopicDto getTopicById(Integer id) {
        return topicRepository.findById(id)
                .map(topic -> new TopicDto(
                topic.getTopicId(),
                topic.getCourse(),
                topic.getTitle(),
                topic.getDescription(),
                topic.getComments()
        )).orElse(null);
    }


    public Topic createTopic(Topic topic) {
        System.out.println(topic.toString());
        
        return topicRepository.save(topic);
    }

}
