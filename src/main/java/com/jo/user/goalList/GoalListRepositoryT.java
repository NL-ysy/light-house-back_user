package com.jo.user.goalList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalListRepositoryT extends JpaRepository<GoalListEntity, String> {
    List<GoalListEntity> findByUserId(String userId);
}
