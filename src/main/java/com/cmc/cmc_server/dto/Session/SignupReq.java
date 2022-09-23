package com.cmc.cmc_server.dto.Session;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("회원가입 요청 정보")
public class SignupReq {

    @ApiModelProperty(value = "사용자 이메일", example = "test@email.com")
    @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @ApiModelProperty(value = "비밀번호", example = "abc123")
    @NotBlank(message = "비밀번호을 입력해주세요.")
    private String password;

    @ApiModelProperty(value = "닉네임", example = "움트")
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

}
