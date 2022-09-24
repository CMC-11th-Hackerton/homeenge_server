package com.cmc.cmc_server.controllers;

import com.cmc.cmc_server.application.SessionService;
import com.cmc.cmc_server.dto.Session.LoginReq;
import com.cmc.cmc_server.dto.Session.SignupReq;
import com.cmc.cmc_server.dto.Session.SignupRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/session")
@Api(tags = "세션 API")
public class SessionController {
    private final SessionService sessionService;

    @ApiOperation(value = "회원가입", notes = "회원가입 요청 데이터를 받아 회원가입을 합니다.")
    @PostMapping("/signup")
    public SignupRes signup(@RequestBody @Valid SignupReq signupReq) {
        return sessionService.signup(signupReq);
    }

}
