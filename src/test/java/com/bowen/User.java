package com.bowen;
/**
 * description
 *
 * @version v1.0.0
 * @since 2019年08月28日
 */
public class User {
    private Long id;
    private String name;
    private int age;
    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public User() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
