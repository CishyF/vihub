package ru.vihub.video.mapper;

import ru.vihub.video.dto.*;
import ru.vihub.video.model.Video;

import java.util.List;

public class VideoMapper {
    public static VideoRecommendationDtoResponse mapToVideoRecommendationDtoResponse(Video video){
        VideoRecommendationDtoResponse dto = VideoRecommendationDtoResponse.builder()
                .id(video.getId())
                .title(video.getTitle())
                .description(video.getDescription())
                .build();
        return dto;
    }
    public static VideoDtoResponse mapToVideoDtoResponse(Video video) {
//        List<CommentDtoResponse> commentDtoResponseList = video.getComments().stream()
//                .map((CommentMapper::mapToCommentDtoResponse)).toList();
        VideoDtoResponse dto = VideoDtoResponse.builder()
                .id(video.getId())
                .title(video.getTitle())
                .description(video.getDescription())
                //.publisher(video.getPublisher())
                .duration(video.getDuration())
                .likesNumber(video.getLikesNumber())
                .dislikesNumber(video.getDislikesNumber())
                .viewersNumber(video.getViewersNumber())
                //.commentDtoResponses(commentDtoResponseList)
                .preview(video.getPreview())
                .build();
        return dto;
    }

    public static VideoWatchDtoResponse mapToVideoWatchDtoResponse(Video video){
        VideoWatchDtoResponse dto = VideoWatchDtoResponse.builder()
                .id(video.getId())
                .videoData(video.getVideoData())
                .name(video.getName())
                .contentType(video.getContentType())
                .size(video.getSize())
                .originalFileName(video.getOriginalFileName())
                .build();
        return dto;
    }
}

