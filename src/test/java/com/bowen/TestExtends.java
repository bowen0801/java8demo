package com.bowen;

import java.util.LinkedList;
import java.util.List;

public class TestExtends {
    public static void main(String[] args) {
        List<? extends Father> list = new LinkedList<>();
        //list.add(new Son());
    }
}
class Human{
}
class Father extends Human{
}
class Son extends Father{
}
class LeiFeng extends Father {
}
