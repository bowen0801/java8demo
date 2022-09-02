package com.bowen.dtoconvert;

public class ConvertTest {

    public static void main(String[] args) {
        UserInputDTO userInputDTO = new UserInputDTO();
        userInputDTO.setName("张三");
        userInputDTO.setAge("18");
        User user = userInputDTO.convertToUser();
        System.out.println(user.getName() + " " + user.getAge());
    }

}
