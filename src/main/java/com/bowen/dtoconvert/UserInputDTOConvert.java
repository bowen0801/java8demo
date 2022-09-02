package com.bowen.dtoconvert;

public class UserInputDTOConvert implements DTOConvert<UserInputDTO,User>{
    @Override
    public User convert(UserInputDTO userInputDTO) {
        User u = new User();
        //拷贝过程
        u.setName(userInputDTO.getName());
        u.setAge(userInputDTO.getAge());
        return u;
    }
}
