package com.jo.user.badgeList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BadgeListDto {

    private String id;
    private String userId;
    private String badgeName;
    private String badgeDesc;
    private String badgeImg;
    private int badgePoint;

    public BadgeListDto(final BadgeListEntity badgeList){
        this.id = badgeList.getId();
        this.userId = badgeList.getUserId();
        this.badgeName = badgeList.getBadgeName();
        this.badgeDesc = badgeList.getBadgeDesc();
        this.badgeImg = badgeList.getBadgeImg();
        this.badgePoint = badgeList.getBadgePoint();}

    public static BadgeListEntity toEntity(final BadgeListDto badgeListDto){
        return BadgeListEntity.builder()
                .id(badgeListDto.getId())
                .userId(badgeListDto.getUserId())
                .badgeName(badgeListDto.getBadgeName())
                .badgeDesc(badgeListDto.getBadgeDesc())
                .badgeImg(badgeListDto.getBadgeImg())
                .badgePoint(badgeListDto.getBadgePoint())
                .build();}
}
