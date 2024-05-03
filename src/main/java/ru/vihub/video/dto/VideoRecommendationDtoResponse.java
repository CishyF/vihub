package ru.vihub.video.dto;

import jakarta.persistence.Column;
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
    private String url;
    private String description;

}
