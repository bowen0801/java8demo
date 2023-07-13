package com.chain;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class User {
    private Long id;
    private String name;
    private String sex;


}
