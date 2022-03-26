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
    private int result;

    public GoalListDtoT(final GoalListEntity goalList){
        this.id = goalList.getId();
        this.userId = goalList.getUserId();
        this.goalId = goalList.getGoalId();
        this.goalTitle = goalList.getGoalTitle();
        this.startDay = goalList.getStartDay();
        this.endDay = goalList.getEndDay();
        this.weekCount = goalList.getWeekCount();
        this.period = goalList.getPeriod();
        this.totalCount = goalList.getTotalCount();
        this.count = goalList.getCount();
        this.doing = goalList.getDoing();
        this.state = goalList.getState();
        this.result = goalList.getResult();
    }

    //goalEntity = toEntity 책의 이름 변경함
    public static GoalListEntity toEntity(final GoalListDtoT goalListDtoT){
        return GoalListEntity.builder()
                .id(goalListDtoT.getId())
                .userId(goalListDtoT.getUserId())
                .goalId(goalListDtoT.getGoalId())
                .goalTitle(goalListDtoT.getGoalTitle())
                .startDay(goalListDtoT.getStartDay())
                .endDay(goalListDtoT.getEndDay())
                .weekCount(goalListDtoT.getWeekCount())
                .period(goalListDtoT.getPeriod())
                .totalCount(goalListDtoT.getTotalCount())
                .count(goalListDtoT.getCount())
                .doing(goalListDtoT.getDoing())
                .state(goalListDtoT.getState())
                .result(goalListDtoT.getResult())
                .build();}
}
