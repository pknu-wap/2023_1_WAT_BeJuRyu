package com.WAT.BEJURYU.service;

import com.WAT.BEJURYU.entity.User;
import com.WAT.BEJURYU.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

}
