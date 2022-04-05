package com.jo.user.userImg;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class UserImgDto {
    private Long id;
//    private String userProfile;
    private String url;
    private String imgFullPath;

    public UserImg toEntity(){
        UserImg build = UserImg.builder()
                .id(id)
//                .userProfile(userProfile)
                .url(url)
                .build();
        return build;
    }

    @Builder
    public UserImgDto(Long id, String url, String imgFullPath) {
        this.id = id;
//        this.userProfile = userProfile;
        this.url = url;
        this.imgFullPath = imgFullPath;
    }
}
