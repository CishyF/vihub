package ru.vihub.video.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vihub.video.dto.VideoRecommendationDtoResponse;
import ru.vihub.video.mapper.VideoMapper;
import ru.vihub.video.service.VideoService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/recommendations")
public class RecommendationController {
    private final VideoService videoService;

    @GetMapping
    public String recommendationsPage(Model model){
        List<VideoRecommendationDtoResponse> videoRecommendationDtoResponses = videoService.findAllVideos().stream().map((VideoMapper::mapToVideoRecommendationDtoResponse)).collect(Collectors.toList());
        model.addAttribute("videoRecommendationDtoResponses", videoRecommendationDtoResponses);
        log.info("Get recommendations page");
        return "recommendations";
    }

}
