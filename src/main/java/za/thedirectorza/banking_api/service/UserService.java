package za.thedirectorza.banking_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.thedirectorza.banking_api.dto.UserRequest;
import za.thedirectorza.banking_api.dto.UserResponse;
import za.thedirectorza.banking_api.exception.DuplicateResourceException;
import za.thedirectorza.banking_api.exception.ResourceNotFoundException;
import za.thedirectorza.banking_api.model.User;
import za.thedirectorza.banking_api.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse create(UserRequest request) {
        if (userRepository.existsByEmail(request.email()))
            throw new DuplicateResourceException("Email already in use: " + request.email());
        User user = userRepository.save(User.builder()
                .name(request.name())
                .email(request.email())
                .build());
        return toResponse(user);
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    public UserResponse findById(Long id) {
        return toResponse(getUser(id));
    }

    public UserResponse update(Long id, UserRequest request) {
        User user = getUser(id);
        if (!user.getEmail().equals(request.email()) && userRepository.existsByEmail(request.email()))
            throw new DuplicateResourceException("Email already in use: " + request.email());
        user.setName(request.name());
        user.setEmail(request.email());
        return toResponse(userRepository.save(user));
    }

    public void delete(Long id) {
        userRepository.delete(getUser(id));
    }

    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
    }

    private UserResponse toResponse(User u) {
        return new UserResponse(u.getId(), u.getName(), u.getEmail());
    }
}
