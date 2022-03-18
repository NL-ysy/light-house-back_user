package com.jo.user.userLogin.security;

import com.jo.user.userLogin.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {
    private static final String SECRET_KEY ="N<A8JPctFuna59f5";

    //create: Jwt 라이브러리를 이용해 Jwt 토큰 생성
    public String create(User user) {
        //기간은 지금부터 1일로 설정
        Date expirDate = Date.from(
                Instant.now().plus(1, ChronoUnit.DAYS));

        //JWT Token 생성
        return Jwts.builder()
                //header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) //HS512이거 조심하기 딴거 입력해서 에러뜸
                //payload에 들어갈 내용
                .setSubject(user.getId()) //sub
                .setIssuer("demo app") //iss
                .setIssuedAt(new Date()) //iat
                .setExpiration(expirDate) //exp
                .compact();}

    //validateAndGetUserId: 토큰을 디코딩 및 파싱하여 토큰의 위조 여부를 확인한다. 이후 우리가 원하는 사용자의 아이디를 리턴함
    public String validateAndGetUserId(String token) {
        //parseClaimsJws 메서드가 Base64로 디코딩 및 파싱
        //헤더와 페이로드를 setsigninkey로 넘어온 시크릿을 이용해 서명한 후 token의 서명과 비교
        //위조되지 않았다면 페이로드(Claims) 리턴, 위조라면 예외를 날림
        //그중 우리는 userId가 필요하므로 getbody를 부른다.

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();}
}
