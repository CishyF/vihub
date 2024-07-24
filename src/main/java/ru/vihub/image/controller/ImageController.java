package ru.vihub.image.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.vihub.image.model.Image;
import ru.vihub.video.service.VideoService;
import java.io.ByteArrayInputStream;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ImageController {

    private final VideoService videoService;

    @GetMapping("/video/preview/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        Image preview = videoService.findById(id).getPreview();
        return ResponseEntity.ok()
                .header("fileName", preview.getOriginalFileName())
                .contentType(MediaType.valueOf(preview.getContentType()))
                .contentLength(preview.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(preview.getBytes())));
    }
}
