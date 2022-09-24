package com.cmc.cmc_server.application;

import com.cmc.cmc_server.domain.User;
import com.cmc.cmc_server.dto.Session.SignupReq;
import com.cmc.cmc_server.dto.Session.SignupRes;
import com.cmc.cmc_server.errors.CustomException;
import com.cmc.cmc_server.errors.ErrorCode;
import com.cmc.cmc_server.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SessionService
{
    private final UserRepository userRepository;

    @Transactional
    // 회원 가입
    public SignupRes signup(SignupReq signupReq) {
        if(userRepository.existsByEmail(signupReq.getEmail())) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        User user = userRepository.save(User.builder()
                .email(signupReq.getEmail())
                .password(signupReq.getPassword())
                .nickname(signupReq.getNickname())
                .imageUrl(signupReq.getImageUrl())
                .build());

        return SignupRes.toSignupRes(user);
    }

}
