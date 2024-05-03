package ru.vihub.video.mapper;

import ru.vihub.video.dto.CommentDtoResponse;
import ru.vihub.video.dto.VideoDtoRequest;
import ru.vihub.video.dto.VideoDtoResponse;
import ru.vihub.video.dto.VideoRecommendationDtoResponse;
import ru.vihub.video.model.Video;

import java.util.List;
import java.util.stream.Collectors;

public class VideoMapper {
    public static VideoRecommendationDtoResponse mapToVideoRecommendationDtoResponse(Video video){
        VideoRecommendationDtoResponse dto = VideoRecommendationDtoResponse.builder()
                .id(video.getId())
                .title(video.getTitle())
                .description(video.getDescription())
                .url(video.getUrl())
                .build();
        return dto;
    }
    public static VideoDtoResponse mapToVideoDtoResponse(Video video) {
        //List<CommentDtoResponse> commentDtoResponseList = video.getComments().stream()
                //.map((CommentMapper::mapToCommentDtoResponse)).toList();
        VideoDtoResponse dto = VideoDtoResponse.builder()
                .title(video.getTitle())
                .description(video.getDescription())
                //.publisher(video.getPublisher())
                .duration(video.getDuration())
                .likesNumber(video.getLikesNumber())
                .dislikesNumber(video.getDislikesNumber())
                .viewersNumber(video.getViewersNumber())
                .url(video.getUrl())
                //.commentDtoResponses(commentDtoResponseList)
                .build();
        return dto;
    }
}

