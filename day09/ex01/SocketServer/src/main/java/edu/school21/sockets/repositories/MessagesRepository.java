package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.responses.MessageResponse;

import java.util.List;

public interface MessagesRepository {
    List<MessageResponse> findAll();

    void save(Message entry);
}
