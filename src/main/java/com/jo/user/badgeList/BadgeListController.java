package com.jo.user.badgeList;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.jo.user.userLogin.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("badge")
public class BadgeListController {

    @Autowired
    private BadgeListService service;

    @PostMapping // 배지 부여
    public ResponseEntity<?> createBadgeList(@AuthenticationPrincipal String userId,
                                             @RequestBody BadgeListDto dto) {
        try {BadgeListEntity entity = BadgeListDto.toEntity(dto);
            entity.setId(null);
            entity.setUserId(userId);
            List<BadgeListEntity> entities = service.create(entity);
            List<BadgeListDto> dtos = entities.stream().map(BadgeListDto::new).collect(Collectors.toList());
            ResponseDTO<BadgeListDto> response = ResponseDTO.<BadgeListDto>builder().data(dtos).build();

            return ResponseEntity.ok(response);}
        catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<BadgeListDto> response = ResponseDTO.<BadgeListDto>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);}
    }
    @GetMapping //배지 조회
    public ResponseEntity<?> retrieveBadgeList(@AuthenticationPrincipal String userId){
        System.out.println("UserID : "+ userId);
        List<BadgeListEntity> entities = service.retrieve(userId);
        List<BadgeListDto> dtos = entities.stream().map(BadgeListDto::new).collect(Collectors.toList());
        ResponseDTO<BadgeListDto> response = ResponseDTO.<BadgeListDto>builder().data(dtos).build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> updateBadgeList(@AuthenticationPrincipal String userId,
                                             @RequestBody BadgeListDto dto){
        BadgeListEntity entity = BadgeListDto.toEntity(dto);
        entity.setUserId(userId);
        List<BadgeListEntity> entities = service.update(entity);
        List<BadgeListDto> dtos = entities.stream().map(BadgeListDto::new).collect(Collectors.toList());
        ResponseDTO<BadgeListDto> response = ResponseDTO.<BadgeListDto>builder().data(dtos).build();

        return ResponseEntity.ok(response);
    }

}
