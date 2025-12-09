package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> retrieveAllUsers() {
        log.info("Retrieving all users");
        return userRepository.findAll();
    }

    @Override
    public User addUser(User u) {
        log.info("Starting addUser()");
        try {
            User saved = userRepository.save(u);
            log.info("User added successfully: {}", saved.getId());
            return saved;
        } catch (Exception e) {
            log.error("Error in addUser(): {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public User updateUser(User u) {
        log.info("Starting updateUser() for id: {}", u.getId());
        try {
            if (!userRepository.existsById(u.getId())) {
                log.warn("User with id {} not found", u.getId());
                return null;
            }

            User updated = userRepository.save(u);
            log.info("User updated successfully: {}", updated.getId());
            return updated;
        } catch (Exception e) {
            log.error("Error in updateUser(): {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void deleteUser(String id) {
        long userId;

        try {
            userId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            log.error("Invalid user id format '{}'", id);
            return;
        }

        log.info("Starting deleteUser() for id: {}", userId);

        try {
            if (!userRepository.existsById(userId)) {
                log.warn("User with id {} not found", userId);
                return;
            }

            userRepository.deleteById(userId);
            log.info("User deleted successfully: {}", userId);
        } catch (Exception e) {
            log.error("Error in deleteUser(): {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public User retrieveUser(String id) {
        long userId;

        try {
            userId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            log.error("Invalid user id format '{}'", id);
            return null;
        }

        log.info("Retrieving user with id: {}", userId);

        try {
            Optional<User> optional = userRepository.findById(userId);

            if (optional.isPresent()) {
                log.info("User found: {}", userId);
                return optional.get();
            } else {
                log.warn("User with id {} not found", userId);
                return null;
            }
        } catch (Exception e) {
            log.error("Error in retrieveUser(): {}", e.getMessage(), e);
            throw e;
        }
    }
}
