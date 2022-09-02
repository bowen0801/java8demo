package com.bowen.dtoconvert;

public interface DTOConvert<S,T> {
    T convert(S s);
}
