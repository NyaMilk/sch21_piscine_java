package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;

import java.util.Optional;

public interface UsersRepository {
    Optional<User> findByUsername(String username);

    void save(User entry);
}
