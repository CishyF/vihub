package ru.vihub.video.service;

import org.springframework.web.multipart.MultipartFile;
import ru.vihub.video.dto.VideoDtoRequest;
import ru.vihub.video.model.Video;

import java.util.List;

public interface VideoService {
    Video findById(Long id);

    Video createVideo(VideoDtoRequest dto, MultipartFile videoFile, MultipartFile previewFile);

    void addVideoToLocalStorage(MultipartFile file);

    List<Video> findAllVideos();

}
