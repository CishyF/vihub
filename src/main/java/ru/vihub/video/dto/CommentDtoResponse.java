package ru.vihub.video.dto;
import lombok.*;
import ru.vihub.user.model.User;

import java.time.LocalDateTime;
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDtoResponse {

    private int description;
    private long likesNumber;
    private long dislikesNumber;
    private User commentator;
    private LocalDateTime createdOn;
}
