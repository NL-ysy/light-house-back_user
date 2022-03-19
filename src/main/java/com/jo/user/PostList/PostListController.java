package com.jo.user.PostList;

import com.jo.user.userLogin.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("post")
public class PostListController {

    @Autowired
    private PostListService service;

    @PostMapping //게시글 추가
    public ResponseEntity<ResponseDTO<PostListDto>> createPostList(@AuthenticationPrincipal String userId,
                                                                   @RequestBody PostListDto dto){
        try {PostListEntity entity = PostListDto.toEntity(dto);
            entity.setId(null);
            entity.setUserId(userId);
            List<PostListEntity> entities = service.create(entity);
            List<PostListDto> dtos = entities.stream().map(PostListDto::new).collect(Collectors.toList());
            ResponseDTO<PostListDto> response = ResponseDTO.<PostListDto>builder().build();

            return ResponseEntity.ok(response);}
        catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<PostListDto> response =ResponseDTO.<PostListDto>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);}
    }

    @PutMapping //게시글 수정
    public ResponseEntity<?> updatePosstList(@AuthenticationPrincipal String userId,
                                             @RequestBody PostListDto dto){
        PostListEntity entity = PostListDto.toEntity(dto);
        entity.setUserId(userId);
        List<PostListEntity> entities = service.update(entity);
        List<PostListDto> dtos = entities.stream().map(PostListDto::new).collect(Collectors.toList());
        ResponseDTO<PostListDto> response = ResponseDTO.<PostListDto>builder().data(dtos).build();

        return ResponseEntity.ok(response);
    }

    @GetMapping //게시글 조회
    public ResponseEntity<?> retrievePostList(@AuthenticationPrincipal String userId){
        System.out.println("UserID : " + userId);
        List<PostListEntity> entities = service.retrieve(userId);
        List<PostListDto> dtos = entities.stream().map(PostListDto::new).collect(Collectors.toList());
        ResponseDTO<PostListDto> response = ResponseDTO.<PostListDto>builder().data(dtos).build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePostList(@AuthenticationPrincipal String userId,
                                            @RequestBody PostListDto dto){
        try {PostListEntity entity = PostListDto.toEntity(dto);
            entity.setUserId(userId);
            List<PostListEntity> entities = service.delete(entity);
            List<PostListDto> dtos = entities.stream().map(PostListDto::new).collect(Collectors.toList());
            ResponseDTO<PostListDto> response = ResponseDTO.<PostListDto>builder().data(dtos).build();

            return ResponseEntity.ok(response);}
        catch (Exception e){
            String error = e.getMessage();
            ResponseDTO<PostListDto> response = ResponseDTO.<PostListDto>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);}
    }
}
