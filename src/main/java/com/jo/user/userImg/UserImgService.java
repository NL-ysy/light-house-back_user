package com.jo.user.userImg;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserImgService {

    private S3Service s3Service;
    private final UserImgRepository userImgRepository;

    public void saveUser(UserImgDto userImgDto){ userImgRepository.save(userImgDto.toEntity());}

    public List<UserImgDto> getUserImgList(){
        List<UserImg> userImgList = userImgRepository.findAll();
        List<UserImgDto> userImgDtoList = new ArrayList<>();

        for (UserImg userImg: userImgList) {
            userImgDtoList.add(convertEntityToDto(userImg));
        }
        return userImgDtoList;
    }
    private UserImgDto convertEntityToDto(UserImg userImg) {
        return UserImgDto.builder()
                .id(userImg.getId())
                .url(userImg.getUrl())
                .imgFullPath("https://" + s3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + userImg.getUrl()) //url 주소 변형 cloud front 주소로 변경할것
                .build();
    }

}
