package ru.vihub.video.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.vihub.image.model.Image;
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

    private String name;

    private String originalFileName;

    private String contentType;

    @Column(name = "title")
    private String title;

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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "comments")
    private List<Comment> comments;

    @Column(name = "videoData")
    @Lob
    private byte[] videoData;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Image preview;

}
