package bd.edu.seu.ecommerce.service;

import bd.edu.seu.ecommerce.model.User;
import bd.edu.seu.ecommerce.model.UserType;
import bd.edu.seu.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public User getUserByEmail(String email) {
        Optional<User> optional = userRepository.findUserByEmail(email);
        if (optional.isPresent()) {
            return optional.get();
        }
        else {
            return null;
        }
    }

    @Transactional
    public void deleteUser(String email) {
        userRepository.deleteUserByEmail(email);
    }
}
