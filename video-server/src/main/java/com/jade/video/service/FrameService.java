package com.jade.video.service;

import net.coobird.thumbnailator.Thumbnails;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FrameService {

    public void frame() throws IOException {
        String dir = "files/";

        String videoPath = "qq.mp4";
        try (FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(videoPath)) {

            ff.start();
            int ffLength = ff.getLengthInFrames();
            Frame f;
            int i = 0;
            while (i < ffLength) {
                f = ff.grabImage();
                if ((i > 5) && (f.image != null)) {
                    String pngPath;
                    pngPath = i + ".png";
                    doExecuteFrame(f, dir + pngPath);
                    break;
                }
                i++;
            }
            ff.stop();
        }
        //压缩视频缩略图
        Thumbnails.of(new File(""))
                .scale(0.5f)
                .outputQuality(0.1f)
                .toOutputStream(Files.newOutputStream(Paths.get("files/qe.png")));
    }

    private static void doExecuteFrame(Frame f, String targerFilePath) {
        String imagemat = "png";
        if (null == f || null == f.image) {
            return;
        }
        BufferedImage bi;
        try (Java2DFrameConverter converter = new Java2DFrameConverter()) {
            bi = converter.getBufferedImage(f);
        }
        File output = new File(targerFilePath);
        try {
            ImageIO.write(bi, imagemat, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
