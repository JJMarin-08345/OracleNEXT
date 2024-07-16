package com.forohub.foro;

import java.util.List;

import com.forohub.foro.model.Topic;
import com.forohub.foro.model.User;
import com.forohub.foro.repository.ITopicRepository;
import com.forohub.foro.repository.IUserRepository;

public class Principal {

    private IUserRepository userRepository;
    private ITopicRepository topicRepository;

    public Principal() {
    }

    public Principal(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Principal(ITopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Principal(IUserRepository userRepository, ITopicRepository topicRepository) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }

    public void showUsers() {
        List<User> usersFind = userRepository.findAll();
        for (User user : usersFind) {
            System.out.println( user.getTopics() );
            // for (Topic topic : user.getTopics()) {
            //     System.out.println(user.getUser() + " " + topic.getTitle() + " " + topic.getDescription());
            // }
        }
    }

    public void showTopics() {
        List<Topic> topicsFind = topicRepository.findAll();
        for (Topic topic : topicsFind) {
            System.out.println(topic.getTitle() + " " + topic.getDescription() + " " + topic.getUser().getUser());
        }
    }

}
