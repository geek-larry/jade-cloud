package com.jade.ffmpeg.test;

import com.jade.ffmpeg.FfmpegApplication;
import net.coobird.thumbnailator.Thumbnails;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;

@SpringBootTest
public class VedioTest {

    @Test
    public void test() throws IOException {
        String dir = "C:\\Users\\songyx\\Desktop\\vedio\\zz\\a";
        String pngPath = "21yy.png";
        String videoPath = "C:\\Users\\songyx\\Desktop\\vedio\\1.mp4";

        FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(videoPath);

        ff.start();
        int ffLength = ff.getLengthInFrames();
        Frame f;
        int i = 0;
        while (i < ffLength) {
            f = ff.grabImage();
            //截取第6帧
            if((i>20) &&  (f.image != null)){
                //生成图片的相对路径 例如：pic/uuid.png
                pngPath =  i+"yy"+".png";
                //执行截图并放入指定位置
                doExecuteFrame(f, dir+pngPath);
                break;
            }
            i++;
        }
        ff.stop();

        //压缩视频缩略图
        Thumbnails.of(new File(dir+pngPath))
                .scale(1f) //图片大小（长宽）压缩比例 从0-1，1表示原图
                .outputQuality(1f) //图片质量压缩比例 从0-1，越接近1质量越好
                .toOutputStream(new FileOutputStream("C:\\Users\\songyx\\Desktop\\vedio\\kk.png"));

        System.out.println("=============完成=============");

    }

    /**
     * 截取缩略图
     * @param f Frame
     * @param targerFilePath:封面图片存放路径
     */
    private static void doExecuteFrame(Frame f, String targerFilePath) {
        int imageHeight = f.imageHeight;
        int imageWidth = f.imageWidth;
        Buffer[] image = f.image;
        ByteBuffer data = f.data;
        BufferedImage bufferedImage = new BufferedImage(imageWidth,imageHeight,BufferedImage.TYPE_INT_BGR);
//        FFmpegFrameGrabber.updatePixel();
        String imagemat = "png";
        if (null == f || null == f.image) {
            return;
        }
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bi = converter.getBufferedImage(f);
        File output = new File(targerFilePath);
        try {
            ImageIO.write(bi, imagemat, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
