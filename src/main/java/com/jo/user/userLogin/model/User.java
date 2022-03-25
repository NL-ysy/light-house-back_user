package com.jo.user.userLogin.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor //파라미터가 없는 기본 생성자 생성
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자를 만듬
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email")})

public class User {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id; //유저 고유의 id  //여기서는 String이라 좀더 확인해보고 바꿀수있음 Long으로 바꿔볼것
    @Column(nullable = false)
    private String password; //유저의 비밀번호
    @Column(nullable = false)
    private String email; //유저의 이메일 주소: 여기선 아이디 대신 사용됨
}
