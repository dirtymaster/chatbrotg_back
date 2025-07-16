package com.radmize.chatbrotg.controller;

import com.radmize.chatbrotg.api.MessageApi;
import com.radmize.chatbrotg.model.MessageResponse;
import com.radmize.chatbrotg.model.NewMessageRequest;
import com.radmize.chatbrotg.service.MessageService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MessageController extends MessageApi {
    private final MessageService messageService;

    @Override
    public List<MessageResponse> getAllMessages() {
        return messageService.getAllSorted();
    }

    @Override
    public void save(NewMessageRequest newMessageRequest) {

    }
}
