package com.jo.user.userLogin.repository;


import com.jo.user.badgeList.BadgeListEntity;
import com.jo.user.userLogin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
//    User findByEmail(String email);
//    boolean existByEmail(String email);
    User findByEmailAndPassword(String email, String password);
    User point(int point);

//    List<GoalList> findByUserId(String userId);
}
