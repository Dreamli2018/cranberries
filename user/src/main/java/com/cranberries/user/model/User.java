package com.cranberries.user.model;


import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class User implements Serializable {

    @ApiModelProperty(value = "用户名", required = true)
    @NotNull
    @NotEmpty
    private String name;

    @ApiModelProperty(value = "年龄")
    private String age;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "手机号", required = true)
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^\\d{11}$")
    private String phone;

    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @ApiModelProperty(value = "邮箱")
//    @Pattern(regexp = "[\\w_\\-\\.]+@[\\w_\\-\\.]+]")
    private String email;

    @ApiModelProperty(value = "地址")
    private String address;

    public User() {
    }

    public User(@NotNull @NotEmpty String name, String age, String gender, @NotNull @NotEmpty @Pattern(regexp = "^\\d{11}$") String phone, String idCard, @Pattern(regexp = "[\\w_\\-\\.]+@[\\w_\\-\\.]+]") String email, String address) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.idCard = idCard;
        this.email = email;
        this.address = address;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", idCard='" + idCard + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}
