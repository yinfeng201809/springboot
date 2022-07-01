package com.example.demo.domain;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class QueryParam {

    @NotBlank(message = "不能是空字符串")
    private String name;

    @NotNull(message = "id 不能为空")
    private Integer id;
}
