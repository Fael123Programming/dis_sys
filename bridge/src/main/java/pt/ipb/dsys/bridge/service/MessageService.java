package pt.ipb.dsys.bridge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import pt.ipb.dsys.bridge.dto.StudentDto;

import javax.jms.TextMessage;

@Service
public class MessageService {
    private final JmsTemplate template;
    private final ObjectMapper mapper;

    public MessageService(JmsTemplate template, ObjectMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    public void sendMessage(StudentDto studentDto) {
        try {
            String json = mapper.writeValueAsString(studentDto);
            template.convertAndSend("academic", json);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }

    @JmsListener(destination = "academic")
    public void handleMessage(TextMessage msg) {
        System.out.println("Received: " + msg);
    }
}
