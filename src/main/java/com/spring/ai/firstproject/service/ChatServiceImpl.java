package com.spring.ai.firstproject.service;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{

    private ChatClient chatClient;

    @Value("classPath:/prompts/user-message.st")
    private Resource userMessage;

    @Value("classPath:/prompts/system-message.st")
    private Resource systemMessage;

    public ChatServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

public String chatTemplate(String query){
    return chatClient
            .prompt()
//            .advisors(new SimpleLoggerAdvisor())
            .system(system->system.text(systemMessage))
            .user(user -> user.text(userMessage).param("concept", query))
            .call()
            .content();
}
}
