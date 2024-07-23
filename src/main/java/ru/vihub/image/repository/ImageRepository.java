package ru.vihub.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vihub.image.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
