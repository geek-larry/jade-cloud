package com.jade.ffmpeg.service;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.FFmpegFrameRecorder;

import java.awt.*;
import java.io.IOException;

public class Cmd {
    public static void main(String[] args) throws IOException, InterruptedException {
        String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
        ProcessBuilder pb = new ProcessBuilder(ffmpeg, "-i", "C:\\Users\\songyx\\Desktop\\vedio\\qq.mp4",
                "-vcodec", "h265", "C:\\Users\\songyx\\Desktop\\vedio\\qq.avi");
        pb.inheritIO().start().waitFor();
    }
}
