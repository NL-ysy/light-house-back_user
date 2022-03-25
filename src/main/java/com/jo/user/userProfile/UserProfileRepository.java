package com.jo.user.userProfile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileEntity, String> {
    List<UserProfileEntity> findByUserId(String userId);
}