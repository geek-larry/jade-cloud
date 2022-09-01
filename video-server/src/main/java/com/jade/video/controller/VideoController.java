package com.jade.video.controller;

import com.jade.video.service.FrameService;
import com.jade.video.service.H265Transcode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("video")
@RestController
@RequiredArgsConstructor
public class VideoController {
    private final FrameService frameService;
    private final H265Transcode h265Transcode;

    @PostMapping("test")
    public String test() throws IOException {
        frameService.frame();
        h265Transcode.videoCode("","");
        return null;
    }
}
