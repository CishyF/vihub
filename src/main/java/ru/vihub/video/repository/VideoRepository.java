package ru.vihub.video.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vihub.video.model.Video;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    @Override
    Optional<Video> findById(Long id);

    @Override
    List<Video> findAll();
}
