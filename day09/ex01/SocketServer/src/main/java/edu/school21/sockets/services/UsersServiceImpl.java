package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public UsersServiceImpl() {
    }

    @Autowired
    @Qualifier("encodePassword")
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    @Qualifier("usersRepository")
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean signUp(String username, String password) throws SQLException {
        Optional<User> optionalUser = usersRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            return false;
        }

        usersRepository.save(new User(0L, username, passwordEncoder.encode(password)));
        return true;
    }

    @Override
    public boolean signIn(String username, String password) throws SQLException {
        Optional<User> optionalUser = usersRepository.findByUsername(username);

        return optionalUser.isPresent() && passwordEncoder.matches(password, optionalUser.get().getPassword());
    }

    @Override
    public User getUser(String username) throws SQLException {
        return usersRepository.findByUsername(username).orElse(null);
    }
}