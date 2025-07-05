package com.mkr.app.ecomm;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository; // it is final coz we are using constructor injection
    private List<User> users = new ArrayList<>();
    private Long nextUserId = 1L;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
//        user.setId(nextUserId++);
        userRepository.save(user);
//        users.add(user);
//        return users;
    }

    public Optional<User> fetchUser(Long id) {
        return userRepository.findById(id);
    }

    public boolean editUser(Long id, User user) {
//        return users.stream()
//                .filter(u -> u.getId().equals(id))
//                        .findFirst()
//                .map(existingUser -> {
//                    existingUser.setFirstName(user.getFirstName());
//                    existingUser.setLastName(user.getLastName());
//                    return true;
//                }).orElse(false);

        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(user.getFirstName());
                    existingUser.setLastName(user.getLastName());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }
}
