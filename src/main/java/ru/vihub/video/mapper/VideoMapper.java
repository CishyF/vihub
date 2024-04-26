package ru.vihub.video.mapper;

import ru.vihub.video.dto.VideoDtoResponse;
import ru.vihub.video.dto.VideoRecommendationDtoResponse;
import ru.vihub.video.model.Video;

public class VideoMapper {
    public static VideoRecommendationDtoResponse mapToVideoRecommendationDtoResponse(Video video){
        VideoRecommendationDtoResponse dto = VideoRecommendationDtoResponse.builder()
                .title(video.getTitle())
                .description(video.getDescription())
                .url(video.getUrl())
                .build();
        return dto;
    }
    public static VideoDtoResponse mapToVideoDtoResponse(Video video){
        return null;
    }
}

