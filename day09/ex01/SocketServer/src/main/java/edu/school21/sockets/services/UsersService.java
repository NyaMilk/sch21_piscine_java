package edu.school21.sockets.services;

import edu.school21.sockets.models.User;

import java.sql.SQLException;

public interface UsersService {
    boolean signUp(String username, String password) throws SQLException;

    boolean signIn(String username, String password) throws SQLException;

    User getUser(String username) throws SQLException;
}