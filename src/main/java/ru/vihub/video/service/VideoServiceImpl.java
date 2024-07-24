package ru.vihub.video.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.vihub.exception.EntityNotFoundException;
import ru.vihub.user.model.User;
import ru.vihub.video.dto.VideoDtoRequest;
import ru.vihub.image.model.Image;
import ru.vihub.video.model.Video;
import ru.vihub.video.repository.VideoRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideoService{


    private final VideoRepository videoRepository;
    @Override
    public Video findById(Long id) {
        return videoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Сущности с данным id не существует"));
    }

    @Override
    public Video createVideo(VideoDtoRequest dto, MultipartFile videoFile, MultipartFile previewFile) {
        User publisher = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Image preview = null;
        byte [] videoData = null;
        try {
            preview = toImageEntity(previewFile);
            videoData = videoFile.getBytes();
        }
        catch (IOException e){
            log.info("File was not save");
        }
        Video video = Video.builder()
                .title(dto.getTitle())
                .contentType(videoFile.getContentType())
                .name(videoFile.getName())
                .originalFileName(videoFile.getOriginalFilename())
                .size(videoFile.getSize())
                .videoData(videoData)
                .description(dto.getDescription())
                .videoData(videoData)
                .preview(preview)
                .dislikesNumber(0)
                .likesNumber(0)
                .duration(countDuration(preview))
                .publisher(publisher)
                .viewersNumber(0)
                .build();
        return videoRepository.save(video);
    }

    @Override
    public List<Video> findAllVideos() {
        return videoRepository.findAll();
    }
    private int countDuration(Image image){
        //todo
        return 0;
    }

    @Override
    public void addVideoToLocalStorage(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            //закгрузка в файл
            File convertFile = new File("D:\\VihubFiles\\" + fileName);
            convertFile.createNewFile();
            Files.write(convertFile.toPath(), file.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = Image.builder()
            .name(file.getName())
            .originalFileName(file.getOriginalFilename())
            .contentType(file.getContentType())
            .size(file.getSize())
            .bytes(file.getBytes())
            .build();
        return image;
    }
}
