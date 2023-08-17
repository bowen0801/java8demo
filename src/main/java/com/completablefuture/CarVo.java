package com.completablefuture;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CarVo {
    private Long no;
    private String carName;

}
