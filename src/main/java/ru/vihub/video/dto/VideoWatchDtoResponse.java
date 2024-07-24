package ru.vihub.video.dto;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VideoWatchDtoResponse {
    private Long id;
    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;
    private byte [] videoData;
}
