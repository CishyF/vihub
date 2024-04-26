package ru.vihub.video.service;

import ru.vihub.video.dto.VideoDtoRequest;
import ru.vihub.video.dto.VideoDtoResponse;
import ru.vihub.video.dto.VideoRecommendationDtoResponse;
import ru.vihub.video.model.Video;

import java.util.List;

public interface VideoService {
    public VideoDtoResponse findById(Long id);
    public Video createVideo(VideoDtoRequest dto);

    public List<VideoRecommendationDtoResponse> findAllVideos();

}
