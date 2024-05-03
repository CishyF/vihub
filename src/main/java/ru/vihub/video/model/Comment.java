package ru.vihub.video.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.vihub.user.model.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description", unique = true)
    private int description;

    @Column(name = "likes_number")
    private long likesNumber;

    @Column(name = "dislikes_number")
    private long dislikesNumber;

    @JoinColumn(name = "commentator")
    @OneToOne
    private User commentator;

    @JoinColumn(name = "commented_video")
    @OneToOne
    private Video commentedVideo;

    @CreationTimestamp
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

}
