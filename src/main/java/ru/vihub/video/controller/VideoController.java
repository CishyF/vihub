package ru.vihub.video.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.vihub.video.dto.VideoDtoResponse;
import ru.vihub.video.service.VideoService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/watch/{id}")
    public String videoPage(@PathVariable("id") Long id, Model model){
        log.info("GET запрос для watch video {}", id);
        VideoDtoResponse dto = videoService.findById(id);
        log.info("Dto из репозитория{}", dto);
        model.addAttribute("video", dto);
        return "video";
    }

    @GetMapping("/add_video")
    public String getAddVideoPage(){
        return "add-video";
    }

    @PostMapping("/add_video")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        videoService.addVideoToLocalStorage(file);
        return "redirect:/home";
    }
}
