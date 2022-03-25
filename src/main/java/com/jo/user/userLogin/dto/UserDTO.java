package com.jo.user.userLogin.dto;


import com.jo.user.userLogin.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String token;
    private String id; //유저 고유의 id  //여기서는 String이라 좀더 확인해보고 바꿀수있음 Long으로 바꿔볼것
    private String password; //유저의 비밀번호
    private String email; //유저의 이메일 주소: 여기선 아이디 대신 사용됨
}
