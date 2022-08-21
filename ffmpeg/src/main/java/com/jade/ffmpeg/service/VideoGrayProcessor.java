package com.jade.ffmpeg.service;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VideoGrayProcessor {
    final static String videoFolderPath = "C:\\Users\\程勇\\Videos\\";
    final static String videoName = "100.mp4";

    public static void main(String[] args) throws Exception {
        videoProcess(videoFolderPath + videoName);
    }

    //视频水印
    public static void videoProcess(String filePath) {
        //抓取视频图像资源
        FFmpegFrameGrabber videoGrabber = new FFmpegFrameGrabber(filePath);
        //抓取视频图像资源
        FFmpegFrameGrabber audioGrabber = new FFmpegFrameGrabber(filePath);
        try {
            videoGrabber.start();
            audioGrabber.start();
            FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(videoFolderPath + "new" + videoName,
                    videoGrabber.getImageWidth(), videoGrabber.getImageHeight(), videoGrabber.getAudioChannels());
            recorder.start();
            //处理图像
            int videoSize = videoGrabber.getLengthInVideoFrames();
            for (int i = 0; i < videoSize; i++) {
                Frame videoFrame = videoGrabber.grabImage();
                if (videoFrame != null && videoFrame.image != null) {
                    System.out.println("视频共" + videoSize + "帧，正处理第" + (i + 1) + "帧图片");
                    Java2DFrameConverter converter = new Java2DFrameConverter();
                    BufferedImage bi = converter.getBufferedImage(videoFrame);
                    BufferedImage bufferedImage = grayProcess(bi);
                    recorder.record(converter.convert(bufferedImage));
                }
            }
            //处理音频
            for (int i = 0; i < audioGrabber.getLengthInAudioFrames(); i++) {
                Frame audioFrame = audioGrabber.grabSamples();
                if (audioFrame != null && audioFrame.samples != null) {
                    recorder.recordSamples(audioFrame.sampleRate, audioFrame.audioChannels, audioFrame.samples);
                }
            }
            recorder.stop();
            recorder.release();
            videoGrabber.stop();
            audioGrabber.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //灰度处理
    public static BufferedImage grayProcess(BufferedImage bufImg) {
        int width = bufImg.getWidth();
        int height = bufImg.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                bufImg.setRGB(i, j, grayRGB(bufImg.getRGB(i, j)));
            }
        }
        return bufImg;
    }

    //rgb灰度转换
    private static int grayRGB(int rgb) {
        int R = (rgb & 0xff0000) >> 16;
        int G = (rgb & 0x00ff00) >> 8;
        int B = rgb & 0x0000ff;
        //平均值
        String average = Integer.toHexString((R + G + B) / 3);
        if (average.length() == 1) {
            average = "0" + average;
        }
        //RGB都变成平均值
        return Integer.parseInt(average + average + average, 16);
    }
}

