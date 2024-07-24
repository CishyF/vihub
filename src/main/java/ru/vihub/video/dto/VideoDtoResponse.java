package ru.vihub.video.dto;
import lombok.*;
import ru.vihub.image.model.Image;
import ru.vihub.user.model.User;

import java.util.List;


@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VideoDtoResponse {
    private Long id;
    private String title;
    private String description;
    private long duration;
    private long viewersNumber;
    private long likesNumber;
    private long dislikesNumber;
    private Image preview;
    private byte [] videoData;
//    private User publisher;
//    private List<CommentDtoResponse> commentDtoResponses;

}
