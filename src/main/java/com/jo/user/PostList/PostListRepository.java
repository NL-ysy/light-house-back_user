package com.jo.user.PostList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostListRepository extends JpaRepository<PostListEntity, String> {
    List<PostListEntity> findByUserId(String userId);}
