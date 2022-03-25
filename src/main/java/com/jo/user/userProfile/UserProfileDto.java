package com.jo.user.userProfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserProfileDto {

    private String id;
    private String userId;
    private String name; //유저의 닉네임
    private String phoneNum; //유저의 전화번호
    private String img; //유저의 프로필 사진
    private int grade; //유저의 등급
    private int point; //유저의 포인트 점수

    public UserProfileDto(final UserProfileEntity userFrofile){
        this.id = userFrofile.getId();
        this.userId = userFrofile.getUserId();
        this.name = userFrofile.getName();
        this.phoneNum = userFrofile.getPhoneNum();
        this.img = userFrofile.getImg();
        this.grade = userFrofile.getGrade();
        this.point = userFrofile.getPoint();
    }

    public static UserProfileEntity toEntity(final UserProfileDto userFrofileDto){
        return UserProfileEntity.builder()
                .id(userFrofileDto.getId())
                .userId(userFrofileDto.getUserId())
                .name(userFrofileDto.getName())
                .phoneNum(userFrofileDto.getPhoneNum())
                .img(userFrofileDto.getImg())
                .grade(userFrofileDto.getGrade())
                .point(userFrofileDto.getPoint())
                .build();
    }
}
