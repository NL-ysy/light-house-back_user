package com.jo.user.goalList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GoalListDtoT {

    private String id;
    private String userId;
    private String goalTitle;
    private String goalDesc;
    private Date startDay;
    private Date endDay;
    private int weekCount;
    private int totalCount;
    private int count;
    private int doing;
    private int state;;

    public GoalListDtoT(final GoalListEntity goalList){
        this.id = goalList.getId();
        this.userId = goalList.getUserId();
        this.goalTitle = goalList.getGoalTitle();
        this.goalDesc = goalList.getGoalDesc();
        this.startDay = goalList.getStartDay();
        this.endDay = goalList.getEndDay();
        this.weekCount = goalList.getWeekCount();
        this.totalCount = goalList.getTotalCount();
        this.count = goalList.getCount();
        this.doing = goalList.getDoing();
        this.state = goalList.getState();
    }

    //goalEntity = toEntity 책의 이름 변경함
    public static GoalListEntity toEntity(final GoalListDtoT goalListDtoT){
        return GoalListEntity.builder()
                .id(goalListDtoT.getId())
                .userId(goalListDtoT.getUserId())
                .goalTitle(goalListDtoT.getGoalTitle())
                .goalDesc(goalListDtoT.getGoalDesc())
                .startDay(goalListDtoT.getStartDay())
                .endDay(goalListDtoT.getEndDay())
                .weekCount(goalListDtoT.getWeekCount())
                .totalCount(goalListDtoT.getTotalCount())
                .count(goalListDtoT.getCount())
                .doing(goalListDtoT.getDoing())
                .state(goalListDtoT.getState())
                .build();}
}
