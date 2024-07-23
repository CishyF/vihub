package ru.vihub.video.dto;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VideoRecommendationDtoResponse {
    private long id;
    private String title;
    private String description;

}
