package ru.vihub.video.dto;
import lombok.*;
import ru.vihub.user.model.User;

import java.util.List;


@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VideoDtoResponse {
    private String title;
    private String url;
    private String description;
    private long duration;
    private long viewersNumber;
    private long likesNumber;
    private long dislikesNumber;
    //private User publisher;
    //private List<CommentDtoResponse> commentDtoResponses;

}
