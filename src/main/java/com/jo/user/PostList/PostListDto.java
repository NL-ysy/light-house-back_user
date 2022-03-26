package com.jo.user.PostList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostListDto {

    private String id;
    private String userId;
    private String goalId;
    private String goalTitle;
    private Date startDay;
    private Date endDay;
    private int weekCount;
    private int period;
    private int totalCount;
    private int count;
    private int doing;
    private int state;

    public PostListDto(final PostListEntity postList){
        this.id = postList.getId();
        this.userId = postList.getUserId();
        this.goalId = postList.getGoalId();
        this.goalTitle = postList.getGoalTitle();
        this.startDay = postList.getStartDay();
        this.endDay = postList.getEndDay();
        this.weekCount = postList.getWeekCount();
        this.period = postList.getPeriod();
        this.totalCount = postList.getTotalCount();
        this.count = postList.getCount();
        this.doing = postList.getDoing();
        this.state = postList.getState();
    }

    public static PostListEntity toEntity(final PostListDto postListDto){
        return PostListEntity.builder()
                .id(postListDto.getId())
                .userId(postListDto.getUserId())
                .goalId(postListDto.getGoalId())
                .goalTitle(postListDto.getGoalTitle())
                .startDay(postListDto.getStartDay())
                .endDay(postListDto.getEndDay())
                .weekCount(postListDto.getWeekCount())
                .period(postListDto.getPeriod())
                .totalCount(postListDto.getTotalCount())
                .count(postListDto.getCount())
                .doing(postListDto.getDoing())
                .state(postListDto.getState())
                .build();}
}
