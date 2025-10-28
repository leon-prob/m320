package ch.noseryoung.blj.login.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Component
@Primary
@Log4j2

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public Set<User> getUserByRole(Role role) {
        return userRepository.findByRole(role);
    }

    @Override
    public User editRole(UUID id) {
        User updatedUser = userRepository.findById(id).orElseThrow();
        if(updatedUser.getRole() == Role.ADMIN){
            updatedUser.setRole(Role.USER);
        } else {
            updatedUser.setRole(Role.ADMIN);
        }
        return userRepository.save(updatedUser);
    }

    @Override
    public User editBan(UUID userId) {
        User updatedUser = userRepository.findById(userId).orElseThrow();
        updatedUser.setBanned(!updatedUser.isBanned());
        return userRepository.save(updatedUser);
    }

}
