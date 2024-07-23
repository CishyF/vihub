package ru.vihub.video.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.vihub.video.dto.VideoDtoRequest;
import ru.vihub.video.dto.VideoDtoResponse;
import ru.vihub.video.dto.VideoWatchDtoResponse;
import ru.vihub.video.mapper.VideoMapper;
import ru.vihub.video.service.VideoService;
import java.io.ByteArrayInputStream;

@Slf4j
@Controller
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/watch/{id}")
    public String videoPage(@PathVariable("id") Long id, Model model){
        log.info("GET запрос для watch video {}", id);
        VideoDtoResponse dto = VideoMapper.mapToVideoDtoResponse(videoService.findById(id));
        log.info("Dto из репозитория{}", dto);
        model.addAttribute("video", dto);
        return "video";
    }

    @GetMapping("/add_video")
    public String getAddVideoPage(){
        return "add-video";
    }

    @PostMapping("/add_video")
    public String handleFileUpload(@RequestParam("video") MultipartFile videoFile, @RequestParam("preview") MultipartFile previewFile, @ModelAttribute("videoDtoRequest") VideoDtoRequest videoDtoRequest, Model model) {
        model.addAttribute(videoDtoRequest);
        videoService.createVideo(videoDtoRequest, videoFile, previewFile);
        //videoService.addVideoToLocalStorage(videoFile);
        return "redirect:/home";
    }

    @GetMapping("/video/watch/{id}")
    private ResponseEntity<?> getVideoById(@PathVariable Long id) {
        VideoWatchDtoResponse videoWatchDtoResponse = VideoMapper.mapToVideoWatchDtoResponse(videoService.findById(id));
        return ResponseEntity.ok()
                .header("fileName", videoWatchDtoResponse.getOriginalFileName())
                .contentType(MediaType.valueOf(videoWatchDtoResponse.getContentType()))
                .contentLength(videoWatchDtoResponse.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(videoWatchDtoResponse.getVideoData())));
    }
}
