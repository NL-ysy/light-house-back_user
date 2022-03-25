package com.jo.user.userProfile;

import com.jo.user.userLogin.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("profile")
public class UserProfileController {

    @Autowired
    private UserProfileService service;

    @PostMapping
    public ResponseEntity<ResponseDTO<UserProfileDto>> createPostList(@AuthenticationPrincipal String userId,
                                                                      @RequestBody UserProfileDto dto){
        try {
            UserProfileEntity entity = UserProfileDto.toEntity(dto);
            entity.setId(null);
            entity.setUserId(userId);
            List<UserProfileEntity> entities = service.create(entity);
            List<UserProfileDto> dtos = entities.stream().map(UserProfileDto::new).collect(Collectors.toList());
            ResponseDTO<UserProfileDto> response = ResponseDTO.<UserProfileDto>builder().build();

            return ResponseEntity.ok(response);}
        catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<UserProfileDto> response =ResponseDTO.<UserProfileDto>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);}
    }
    @PutMapping //프로필 수정
    public ResponseEntity<?> updateUserFrofile(@AuthenticationPrincipal String userId,
                                               @RequestBody UserProfileDto dto){
        UserProfileEntity entity = UserProfileDto.toEntity(dto);
        entity.setUserId(userId);
        List<UserProfileEntity> entities = service.update(entity);
        List<UserProfileDto> dtos = entities.stream().map(UserProfileDto::new).collect(Collectors.toList());
        ResponseDTO<UserProfileDto> response = ResponseDTO.<UserProfileDto>builder().data(dtos).build();

        return ResponseEntity.ok(response);
    }
    @GetMapping //프로필 조회
    public ResponseEntity<?> retrievePostList(@AuthenticationPrincipal String userId){
        System.out.println("UserID : " + userId);
        List<UserProfileEntity> entities = service.retrieve(userId);
        List<UserProfileDto> dtos = entities.stream().map(UserProfileDto::new).collect(Collectors.toList());
        ResponseDTO<UserProfileDto> response = ResponseDTO.<UserProfileDto>builder().data(dtos).build();

        return ResponseEntity.ok(response);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteProfileList(@AuthenticationPrincipal String userId,
                                               @RequestBody UserProfileDto dto){
        try {
            UserProfileEntity entity = UserProfileDto.toEntity(dto);
            entity.setUserId(userId);
            List<UserProfileEntity> entities = service.delete(entity);
            List<UserProfileDto> dtos = entities.stream().map(UserProfileDto::new).collect(Collectors.toList());
            ResponseDTO<UserProfileDto> response = ResponseDTO.<UserProfileDto>builder().data(dtos).build();

            return ResponseEntity.ok(response);}

        catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<UserProfileDto> response = ResponseDTO.<UserProfileDto>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);}
    }
}