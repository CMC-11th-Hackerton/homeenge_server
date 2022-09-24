package com.cmc.cmc_server.controllers;

import com.cmc.cmc_server.application.UserService;
import com.cmc.cmc_server.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 사용자의 HTTP 요청을 처리하는 클래스입니다.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Api(tags = "유저 API")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ApiOperation(value = "사용자 등록", notes = "사용자의 정보를 받아 등록합니다.")
    public User user(@RequestBody User user) {
        return userService.create(user);
    }
}
