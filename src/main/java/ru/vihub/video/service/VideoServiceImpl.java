package ru.vihub.video.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.vihub.user.model.User;
import ru.vihub.video.dto.VideoDtoRequest;
import ru.vihub.video.dto.VideoDtoResponse;
import ru.vihub.video.dto.VideoRecommendationDtoResponse;
import ru.vihub.video.mapper.VideoMapper;
import ru.vihub.video.model.Video;
import ru.vihub.video.repository.VideoRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService{

    private final VideoRepository repository;
    @Override
    public VideoDtoResponse findById(Long id) {
        if(repository.findById(id).isPresent()){
            return VideoMapper.mapToVideoDtoResponse(repository.findById(id).get());
        }
        return null;
//throw new Exception;
//        Video video = repository.findById(id).isPresent() ? repository.findById(id).get();
//        return VideoMapper.mapToVideoDtoResponse(video);
    }

    @Override
    public Video createVideo(VideoDtoRequest dto) {
        User publisher = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Video video = Video.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .url(dto.getUrl())
                .dislikesNumber(0)
                .likesNumber(0)
                .duration(countDuration(dto.getUrl()))
                .publisher(publisher)
                .viewersNumber(0)
                .build();
        return repository.save(video);
    }

    @Override
    public List<VideoRecommendationDtoResponse> findAllVideos() {
        return repository.findAll().stream().map((VideoMapper::mapToVideoRecommendationDtoResponse)).collect(Collectors.toList());
    }
    private int countDuration(String videoUrl){
        //todo
        return 0;
    }

    @Override
    public void addVideoToLocalStorage(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            //byte[] fileData = file.getBytes();


            //закгрузка в файл
            File convertFile = new File("D:\\VihubFiles\\" + fileName);
            convertFile.createNewFile();
            Files.write(convertFile.toPath(), file.getBytes());


            //загрузка в бд
            //videoService.createVideo()
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
