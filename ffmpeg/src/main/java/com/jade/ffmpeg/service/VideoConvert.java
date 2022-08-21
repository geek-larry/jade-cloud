package com.jade.ffmpeg.service;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;

public class VideoConvert {
    public static void main(String[] args) throws FFmpegFrameRecorder.Exception, FFmpegFrameGrabber.Exception {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber("C:\\Users\\程勇\\Videos\\100.mp4");
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("C:\\Users\\程勇\\Videos\\100.flv",
                grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
        recorder.start(grabber.getFormatContext());
        for(;;){
            recorder.recordPacket(grabber.grabPacket());
        }
    }
}

