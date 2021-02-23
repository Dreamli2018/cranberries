package com.cranberries.userapi.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "user")
public class User implements Serializable {

    @Field
    @Indexed
    private Integer id;

    @ApiModelProperty(value = "用户名", required = true)
    @NotNull
    @NotEmpty
    @Field
    private String name;

    @ApiModelProperty(value = "年龄")
    @Field
    private Integer age;

    @ApiModelProperty(value = "性别, 0-女，1-男")
    @Field
    private Integer gender;

    @ApiModelProperty(value = "手机号", required = true)
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^\\d{11}$")
    @Field
    private String phone;

    @ApiModelProperty(value = "身份证号")
    @Field
    private String idCard;

    @ApiModelProperty(value = "邮箱")
//    @Pattern(regexp = "[\\w_\\-\\.]+@[\\w_\\-\\.]+]")
    @Field
    private String email;

    @ApiModelProperty(value = "地址")
    @Field
    private String address;

}
