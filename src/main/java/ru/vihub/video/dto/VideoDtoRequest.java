package ru.vihub.video.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VideoDtoRequest {
    @Size(min = 1, message = "Title must contains at least 1 symbol!")
    @Size(max = 70, message = "Title must contains less than 70 symbols!")
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{1,100}$", message = "Title is incorrect, try another!")
    private String title;
    private String description;
}
