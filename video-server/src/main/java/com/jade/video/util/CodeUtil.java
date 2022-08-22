package com.jade.video.util;

import org.bytedeco.javacpp.Loader;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CodeUtil {

    public void code() throws IOException, InterruptedException {
        String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
        ProcessBuilder pb = new ProcessBuilder(ffmpeg, "-i", "C:\\Users\\songyx\\Desktop\\video\\qq.mp4",
                " -vcodec ", " libx265 ", "C:\\Users\\songyx\\Desktop\\video\\qq.flv");
        pb.inheritIO().start().waitFor();
    }
}
