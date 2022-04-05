package com.jo.user.userImg;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserImgController {

    private S3Service s3Service;
    private UserImgService userImgService;

    @GetMapping("/userImg")
    public String dispWrite(Model model) {
        List<UserImgDto> userImgDtoList = userImgService.getUserImgList();

        model.addAttribute("userImgList", userImgDtoList);

        return "/userImg";
    }

    @PostMapping("/userImg")
    public String execWrite(UserImgDto userImgDto, MultipartFile file) throws IOException {
        String imgPath = s3Service.upload(userImgDto.getUrl(), file);
        userImgDto.setUrl(imgPath);

        userImgService.saveUser(userImgDto);

        return "redirect:/userImg";

    }
}
