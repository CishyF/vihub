package ru.vihub.video.service;

import org.springframework.web.multipart.MultipartFile;
import ru.vihub.video.dto.VideoDtoRequest;
import ru.vihub.video.dto.VideoDtoResponse;
import ru.vihub.video.dto.VideoRecommendationDtoResponse;
import ru.vihub.video.dto.VideoWatchDtoResponse;
import ru.vihub.video.model.Video;

import java.util.List;

public interface VideoService {
    public Video findById(Long id);

    public Video createVideo(VideoDtoRequest dto, MultipartFile videoFile, MultipartFile previewFile);

    public void addVideoToLocalStorage(MultipartFile file);

    public List<Video> findAllVideos();

}
