package com.DocumentManagementSystem.service.impl;

import com.DocumentManagementSystem.model.User;
import com.DocumentManagementSystem.repository.UserRepository;
import com.DocumentManagementSystem.security.SecurityUtils;
import com.DocumentManagementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getById(Long id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("Пользователь не найден");
        }
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        getById(user.getId());
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
