/*
 * @Author: songyx 2250794569@qq.com
 * @Date: 2022-08-23 00:38:17
 * @LastEditors: songyx 2250794569@qq.com
 * @LastEditTime: 2022-08-23 22:50:51
 * @Description: 
 */
package com.jade.video.service;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.opencv.core.Mat;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

@Slf4j
@Service
public class H265Transcode {

    public void videoCode(String sourcePath, String targetPath) {
        Runtime run = null;
        try {
            run = Runtime.getRuntime();
            File ffmpegcmd = new File("D:\\soft\\ffmpeg\\bin\\ffmpeg.exe");
            Process p = run.exec(ffmpegcmd.getAbsolutePath() + " -i " + sourcePath + " -vcodec libx265 " + targetPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line;
            while ((line = br.readLine()) != null) {
                log.info("exec {}", line);
            }
            br.close();
            p.getOutputStream().close();
            p.getInputStream().close();
            p.getErrorStream().close();
            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert run != null;
            run.freeMemory();
        }
    }

    public BufferedImage matToBufferedImage(Mat frame) {       
        int type = 0;
        if (frame.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (frame.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(frame.width() ,frame.height(), type);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        frame.get(0, 0, data);
        return image;
    }

    @SuppressWarnings("all")
    public org.bytedeco.opencv.opencv_core.Mat bufferedImageToMat(BufferedImage bi) {
        try (OpenCVFrameConverter.ToMat cv = new OpenCVFrameConverter.ToMat()) {
            return cv.convertToMat(new Java2DFrameConverter().convert(bi));
        }
    }

}
