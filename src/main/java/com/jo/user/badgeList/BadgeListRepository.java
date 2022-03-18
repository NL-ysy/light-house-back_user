package com.jo.user.badgeList;

import com.jo.user.goalList.GoalListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeListRepository extends JpaRepository<BadgeListEntity, String> {
    List<BadgeListEntity> findByUserId(String userId);
//    List<BadgeListEntity> point(BadgeListEntity entity);
}
