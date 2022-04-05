package com.jo.user.userImg;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class UserImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private String userProfile;
    @Column(columnDefinition = "TEXT")
    private String url;

    @Builder
    public UserImg(Long id, String url) {
        this.id = id;
//        this.userProfile = userProfile;
        this.url = url;
    }
}
