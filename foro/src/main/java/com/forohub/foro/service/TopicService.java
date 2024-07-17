package com.forohub.foro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.forohub.foro.dto.TopicDto;
import com.forohub.foro.dto.UserDto;
import com.forohub.foro.model.Topic;
import com.forohub.foro.model.User;
import com.forohub.foro.repository.ITopicRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TopicService {

    private final ITopicRepository topicRepository;
    private final UserService userService;

    public TopicService(ITopicRepository topicRepository, UserService userService) {
        this.topicRepository = topicRepository;
        this.userService = userService;
    }

    public List<TopicDto> getAllTopics() {
        List<TopicDto> topics = topicRepository.findAll().stream()
                .map(topic -> new TopicDto(
                topic.getTopicId(),
                topic.getUser().getUser(),
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
                topic.getUser().getUser(),
                topic.getCourse(),
                topic.getTitle(),
                topic.getDescription(),
                topic.getComments()
        )).orElse(null);
    }

    public Topic createTopic(Integer userId, Topic topic) {
        System.out.println(topic.toString());
        User userEntity = new User();
        UserDto user = userService.getUserById(userId);
        if (user == null) {
            return null;
        }
        userEntity.setUserId(user.userId());
        topic.setUser(userEntity);

        return topicRepository.save(topic);
    }

    public Topic updateTopic(Integer userId, Topic topic) {
        //Search the topic for Id
        Topic isTopicExists = topicRepository.findById(topic.getTopicId())
                .orElseThrow(() -> new EntityNotFoundException("***EL TOPICO NO EXISTE***"));

        if (isTopicExists == null) {
            return null;
        }

        User userEntity = new User();
        UserDto user = userService.getUserById(userId);
        if (user == null) {
            return null;
        }
        System.out.println("User: " + user.toString());

        if (isTopicExists.getUser().getUserId() != user.userId()) {
            throw new Error("***NO PUEDES MODIFICAR UN TOPICO QUE NO TE PERTENECE***");
        }
        userEntity.setUserId(user.userId());
        topic.setUser(userEntity);

        return topicRepository.save(topic);
    }

    public String deleteTopic(Integer userId, Integer topicId) {
        //Search the topic for Id
        Topic isTopicExists = topicRepository.findById(topicId)
                .orElseThrow(() -> new EntityNotFoundException("***EL TOPICO NO EXISTE***"));

        if (isTopicExists == null) {
            return "***EL TOPICO NO EXISTE***";
        }

        User userEntity = validateUser(userId);

        if (isTopicExists.getUser().getUserId() != userEntity.getUserId()) {
            return "***NO PUEDES ELIMINAR UN TOPICO QUE NO TE PERTENECE***";
        }

        isTopicExists.setUser(userEntity);

        topicRepository.delete(isTopicExists);
        return "***EL TOPICO HA SIDO ELIMINADO***";
    }

    private User validateUser(Integer userId) {
        UserDto user = userService.getUserById(userId);
        if (user == null) {
            throw new Error("***EL USUARIO NO EXISTE***");
        }
        return new User(user.userId(), user.user(), user.password());
    }

}
