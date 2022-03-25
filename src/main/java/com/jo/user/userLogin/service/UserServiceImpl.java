package com.jo.user.userLogin.service;

import com.jo.user.badgeList.BadgeListEntity;
import com.jo.user.badgeList.BadgeListRepository;
import com.jo.user.userLogin.dto.UserDTO;
import com.jo.user.userLogin.model.User;
import com.jo.user.userLogin.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
//@RequiredArgsConstructor
@Service
@Repository
public class UserServiceImpl implements UserService{
    @Autowired //여기서 오토와이어드를 사용함.
    private final UserRepository userRepository;
//    private final BadgeListRepository badgeListRepository;

    @Override
    public User create(final User user) {

        if (user == null || user.getEmail() == null) {
            throw new RuntimeException("Invalid arguments");}

        final String email = user.getEmail();
// *에러가 떠서 주석처리중* 빈생성 오류라고 뜨던데 아직 해결점을 못찾음 Repository랑 같이 에러가 뜨더이다...
//        if (userRepository.existByEmail(email)){
//            log.warn("Email already exists {}", email); //이메일이 이미 있습니다
//            throw new RuntimeException("Email already exists");}
        return userRepository.save(user);}

    @Override
    public User getbyCredentials(final String email, final String password, PasswordEncoder passwordEncoder){
        return userRepository.findByEmailAndPassword(email,password);}

    @Override
    public User update(final UserDTO userDTO){
        User user = User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();

        return userRepository.save(user);
    }
}


