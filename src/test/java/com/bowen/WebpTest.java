package com.bowen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WebpTest {
    public static void main(String[] args) throws IOException {
        /*ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ImageIO.write(img.getImg(), "webp", bout);
        data = bout.toByteArray();*/

        // Obtain an image to encode from somewhere
        //BufferedImage image = ImageIO.read(new File("input.png"));
        long start = System.currentTimeMillis();
        //for (int i = 1;i <= 1;i++) {
            BufferedImage image = ImageIO.read(new File("D:/webp/iinput1.jpg"));

            // Encode it as webp using default settings
            ImageIO.write(image, "webp", new File("D:/webp/ooutput1.webp"));
        //}

        System.out.println("耗时："+(System.currentTimeMillis() - start));
    }
}
