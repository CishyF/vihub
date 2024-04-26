package ru.vihub.video.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.vihub.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private long duration;

    @Column(name = "size")
    private long size;

    @Column(name = "viewers_number")
    private long viewersNumber;

    @Column(name = "likes_number")
    private long likesNumber;

    @Column(name = "dislikes_number")
    private long dislikesNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher")
    private User publisher;

    @CreationTimestamp
    @Column(name = "created_on")
    private LocalDateTime createdOn;

//    @JoinColumn(name = "comments", unique = true)
//    @OneToMany
//    private List<Comment> comments;

}
