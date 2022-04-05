package com.jo.user.userImg;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserImgRepository extends JpaRepository<UserImg, Long> {

    List<UserImg> findAll();

}
