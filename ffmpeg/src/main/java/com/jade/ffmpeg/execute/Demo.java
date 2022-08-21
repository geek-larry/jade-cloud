//package com.jade.ffmpeg.execute;
//
//import org.bytedeco.javacv.FFmpegFrameGrabber;
//import org.bytedeco.javacv.Frame;
//import org.bytedeco.javacv.Java2DFrameConverter;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//public class Demo {
//
//    public void ss(){
//        //创建FFmpegFrameGrabber 对象
//        FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(videoPath);
//        ff.start();
//        int ffLength = ff.getLengthInFrames();
//        Frame f;
//        int i = 0;
//        while (i < ffLength) {
//            f = ff.grabImage();
//            //截取第6帧
//            if((i>5) &&  (f.image != null)){
//                //生成图片的相对路径 例如：pic/uuid.png
//                pngPath =  "来电名片mp4_缩略图"+".png";
//                //执行截图并放入指定位置
//                doExecuteFrame(f, dir+pngPath);
//                break;
//            }
//            i++;
//        }
//        ff.stop();
//    }
//    /**
//     * 截取缩略图
//     * @param f Frame
//     * @param targerFilePath:封面图片存放路径
//     */
//    private static void doExecuteFrame(Frame f, String targerFilePath) {
//        String imagemat = "png";
//        if (null == f || null == f.image) {
//            return;
//        }
//        Java2DFrameConverter converter = new Java2DFrameConverter();
//        BufferedImage bi = converter.getBufferedImage(f);
//        File output = new File(targerFilePath);
//        try {
//            ImageIO.write(bi, imagemat, output);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
