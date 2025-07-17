package com.radmize.chatbrotg.controller;

import com.radmize.chatbrotg.api.ApiApi;
import com.radmize.chatbrotg.model.MessageResponse;
import com.radmize.chatbrotg.model.NewMessageRequest;
import com.radmize.chatbrotg.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RequiredArgsConstructor
public class MessageController implements ApiApi {
    private final MessageService messageService;

    @Override
    public ResponseEntity<List<MessageResponse>> getAllMessages() {
        return ResponseEntity.ok().body(messageService.getAllSorted());
    }

    @Override
    public ResponseEntity<Void> save(NewMessageRequest newMessageRequest) {
        return ResponseEntity.ok().build();
    }
}
