package com.jo.user.PostList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostListDto {

    private String id;
    private String userId;
    private String title;
    private String category;
    private LocalDate createDate;
    private LocalDate editDate;

    public PostListDto(final PostListEntity postList){
        this.id = postList.getId();
        this.userId = postList.getUserId();
        this.title = postList.getTitle();
        this.category = postList.getCategory();
        this.createDate = postList.getCreateDate();
        this.editDate = postList.getEditDate();}

    public static PostListEntity toEntity(final PostListDto postListDto){
        return PostListEntity.builder()
                .id(postListDto.getId())
                .userId(postListDto.getUserId())
                .title(postListDto.getTitle())
                .category(postListDto.getCategory())
                .createDate(postListDto.getCreateDate())
                .editDate(postListDto.getEditDate())
                .build();}
}