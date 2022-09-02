package com.bowen.dtoconvert;

public class UserOutputConvert implements DTOConvert<User,UserOutputDTO>{
    @Override
    public UserOutputDTO convert(User user) {
        UserOutputDTO outDTO = new UserOutputDTO();
        outDTO.setName(user.getName());
        outDTO.setAge(user.getAge());
        return outDTO;
    }
}
