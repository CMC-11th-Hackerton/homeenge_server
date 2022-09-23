package com.cmc.cmc_server.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 사용자의 HTTP 요청을 처리하는 클래스입니다.
 */
@Api(tags = "테스트 출력 API")
@RestController
@RequestMapping("/hello")
public class HelloController {

    @ApiOperation(value= "테스트 확인", notes= "테스트 예제를 출력하는 api")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public String sayHello() { return "Hello world!"; }

}
