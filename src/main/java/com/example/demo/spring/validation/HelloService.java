package com.example.demo.spring.validation;


import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@Validated(Default.class)
public interface HelloService {
    Object hello(@NotNull @Min(10) Integer id, @NotNull String name);
}
