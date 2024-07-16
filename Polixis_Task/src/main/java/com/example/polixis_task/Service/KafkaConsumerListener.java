package com.example.polixis_task.Service;

import com.example.polixis_task.Model.Message;
import com.example.polixis_task.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerListener {

    private final MessageRepository messageRepository;

    @Autowired
    public KafkaConsumerListener(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @KafkaListener(topics = "my_topic", groupId = "your_group_id")
    public void listen(Message message) {
        System.out.println("Received message: " + message);
        try {
            messageRepository.save(message);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
