package com.radmize.chatbrotg.mapper;

import com.radmize.chatbrotg.entity.Message;
import com.radmize.chatbrotg.model.MessageResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Mapper
@Component
public interface MessageMapper {
    @Transactional
    MessageResponse toMessageResponse(Message message);
}
