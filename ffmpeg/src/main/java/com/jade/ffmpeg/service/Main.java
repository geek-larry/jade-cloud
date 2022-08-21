package com.jade.ffmpeg.service;

import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avformat.AVStream;
import org.bytedeco.javacv.*;

import javax.swing.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        String filename = "C:\\Users\\程勇\\Videos\\100.mp4";
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filename);
        grabber.start();
        AVFormatContext context = grabber.getFormatContext();
        // 视频帧率
        int frameRate = (int)grabber.getVideoFrameRate();
        // 视频时长
        int timeLen = (int)grabber.getLengthInTime() / 1000000;
        System.out.println("视频帧率:" + frameRate + ", 视频时长:" + timeLen + "s");
        // 视频高度和宽度 1920*1080
        int frameWidth = grabber.getImageWidth(), frameHeight = grabber.getImageHeight();
        System.out.println("视频宽度:" + frameWidth + ", 视频高度:" + frameHeight);
        // 流的数目（一般是两个，视频流+音频流）
        for(int i = 0; i < context.nb_streams(); i ++ ){
            AVStream stream = context.streams(i);
            System.out.println("编码器类型:" + stream.codecpar().codec_type() + ", 编码器id:" + stream.codecpar().codec_id());
        }

        Frame frame;
        // canvas可以显示图象
//        CanvasFrame canvas = new CanvasFrame("aaa");
        // 记录视频
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("C:\\Users\\程勇\\Videos\\200_new.avi", 720, 405, 2);
        recorder.setFormat("avi");

        recorder.start();
        System.out.println(grabber.getLengthInFrames());
        int cnt = 0;
        while((frame = grabber.grab()) != null){
//            canvas.showImage(frame);
            recorder.record(frame);
            cnt ++ ;
            if(cnt % 100 == 0) System.out.println(cnt);
        }
        System.out.println(cnt);
        grabber.close();
        recorder.close();
    }
}

