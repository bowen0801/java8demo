package com.bowen;



import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

public class ImgTest {
    public static void main(String[] args) throws IOException {

        //ImageInputStream iis = ImageIO.createImageInputStream(new File());
        BufferedImage bufferedImage = ImageIO.read(new URL("http://192.168.89.37/c/product/541051/800_mid/z_1.jpg?t=9898"));

        //ImageInputStream iis = ImageIO.createImageInputStream(new File("D:/watermark/gakki-src.png"));

        
        Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(bufferedImage);
        while (imageReaders.hasNext()) {
            ImageReader reader = (ImageReader) imageReaders.next();
            System.out.printf("formatName: %s%n", reader.getFormatName());
        }
    }
}
