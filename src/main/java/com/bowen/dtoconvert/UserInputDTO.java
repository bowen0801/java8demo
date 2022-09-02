package com.bowen.dtoconvert;

public class UserInputDTO {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public User convertToUser(){
        UserInputDTOConvert userInputDTOConvert = new UserInputDTOConvert();
        User convert = userInputDTOConvert.convert(this);
        return convert;
    }

    public static class UserInputDTOConvert implements DTOConvert<UserInputDTO,User>{
        @Override
        public User convert(UserInputDTO userInputDTO) {
            User u = new User();
            //拷贝过程
            u.setName(userInputDTO.getName());
            u.setAge(userInputDTO.getAge());
            return u;
        }
    }

}

