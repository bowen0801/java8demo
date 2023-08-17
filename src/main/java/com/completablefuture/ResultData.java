package com.completablefuture;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResultData {
    private UserVo user;
    private CarVo car;

}
