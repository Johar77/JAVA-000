package com.johar.geektime.redisdistributedlock.dto;



import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: OrderParamDto
 * @Description: TODO
 * @Author: Johar
 * @Date: 2021/1/2 10:37
 * @Since: 1.0.0
 */
@Data
@ToString
public class OrderParamDto {

    @NotNull
    @Min(value = 1, message = "productId 必须大于0")
    private Integer productId;

    @NotNull
    private OpCode type;

    @Min(value = 1, message = "num 必须大于0")
    private Integer num;
}