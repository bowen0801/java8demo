package com.bowen.openvc;

import com.bowen.opencv.converter.Converter;
import com.bowen.opencv.converter.DctConverter;
import com.bowen.opencv.converter.DftConverter;
import com.bowen.opencv.decoder.Decoder;
import com.bowen.opencv.decoder.Encoder;
import com.bowen.opencv.decoder.ImageEncoder;
import com.bowen.opencv.decoder.TextEncoder;

public class Test {
    public static void main(String[] args) {

        //png图片测试  支持
        /*String src = "D:/watermark/gakki-src.png";
        Converter converter = new DctConverter();
        Encoder encoder = new ImageEncoder(converter);
        encoder.encode(src, "D:/watermark/watermark.png", "D:/watermark/gakki-dct-img-en.png");
        Decoder decoder = new Decoder(converter);
        decoder.decode("D:/watermark/gakki-dct-img-en.png", "D:/watermark/gakki-dct-img-dec.png");*/

        //jpg图片支持 不支持，效果非常不好----调整参数后可以
        String src = "D:/watermark/card-src.jpg";
        Converter converter = new DctConverter();
        Encoder encoder = new ImageEncoder(converter);
        encoder.encode(src, "D:/watermark/logo1.png", "D:/watermark/card-cat-dct-img-en.jpg");
        Decoder decoder = new Decoder(converter);
        decoder.decode("D:/watermark/card-cat-dct-img-en.jpg", "D:/watermark/jpg-dct-img-dec.jpg");

        //jpg文字水印  dct支持 效果还行
        /*String src = "D:/watermark/w.jpg";
        Converter converter = new DctConverter();
        Encoder encoder = new TextEncoder(converter);
        encoder.encode(src, "Copyright ", "D:/watermark/jpg-dct-img-en.jpg");
        Decoder decoder = new Decoder(converter);
        decoder.decode("D:/watermark/jpg-dct-img-en.jpg", "D:/watermark/jpg-dct-img-dec.jpg");*/

        //jpg文字水印  dft支持 不行 看不到水印
        /*long start = System.currentTimeMillis();
        String src = "D:/watermark/card-cat.jpg";
        Converter converter = new DftConverter();
        Encoder encoder = new TextEncoder(converter);
        encoder.encode(src, "www", "D:/watermark/card-cat-en111.jpg");
        long end = System.currentTimeMillis();
        System.out.println("耗时ms:"+(end - start));
        Decoder decoder = new Decoder(converter);
        decoder.decode("D:/watermark/card-cat-en111.jpg", "D:/watermark/card-cat-dec111.jpg");*/

        //解析水印
        /*Converter converter = new DctConverter();
        Decoder decoder = new Decoder(converter);
        decoder.decode("D:/watermark/card-cat.jpg", "D:/watermark/card-cat-dec.jpg");*/

        /*String src = "D:/watermark/card-src.jpg";
        Converter converter = new DctConverter();
        Encoder encoder = new ImageEncoder(converter);
        encoder.encode(src, "D:/watermark/logo1.png", "D:/watermark/card-en1.jpg");
        Decoder decoder = new Decoder(converter);
        decoder.decode("D:/watermark/card-en1.jpg", "D:/watermark/card-dec1.jpg");*/


    }
}
