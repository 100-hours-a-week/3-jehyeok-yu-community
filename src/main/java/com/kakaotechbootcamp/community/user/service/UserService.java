package com.kakaotechbootcamp.community.user.service;

import com.kakaotechbootcamp.community.user.dto.request.SignUpRequestDto;
import com.kakaotechbootcamp.community.user.dto.response.SignUpResponseDto;
import com.kakaotechbootcamp.community.user.entity.User;
import com.kakaotechbootcamp.community.user.exception.DuplicateException;
import com.kakaotechbootcamp.community.user.exception.UserErrorCode;
import com.kakaotechbootcamp.community.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public SignUpResponseDto createUser(SignUpRequestDto dto) {
        checkDuplicateAtUserEmail(dto.getEmail());
        checkDuplicateAtUserNickname(dto.getNickname());
        User user = User.create(dto.getEmail(), dto.getNickname(), dto.getPassword());
        userRepository.save(user);
        return new SignUpResponseDto(user.getUserId());
    }

    @Transactional(readOnly = true)
    public void checkDuplicateAtUserEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateException(UserErrorCode.DUPLICATE_ERROR);
        }
    }

    @Transactional(readOnly = true)
    public void checkDuplicateAtUserNickname(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new DuplicateException(UserErrorCode.DUPLICATE_ERROR);
        }
    }
}
