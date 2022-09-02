/*
 * Copyright (c) 2019 ww23(https://github.com/ww23/BlindWatermark).
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bowen.opencv.decoder;


import com.bowen.opencv.converter.Converter;
import com.bowen.opencv.util.Utils;
import org.bytedeco.javacpp.opencv_core;

/**
 * @author ww23
 */
public class TextEncoder extends Encoder {

    public TextEncoder(Converter converter) {
        super(converter);
    }

    @Override
    public void addWatermark(opencv_core.Mat com, String watermark) {
        if (Utils.isAscii(watermark)) {
            this.converter.addTextWatermark(com, watermark);
        } else {
            this.converter.addImageWatermark(com, Utils.drawNonAscii(watermark));
        }
    }
}
