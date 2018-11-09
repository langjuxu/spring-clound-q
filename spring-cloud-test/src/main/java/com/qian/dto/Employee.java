package com.qian.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author qian
 * @date 2018/11/2
 */
@Data
@AllArgsConstructor
public class Employee implements Serializable {

    private static final long serialVersionUID = -8127560909024426763L;

    /**
     * 员工名称
     */
    private String name;

    /**
     * 员工年龄
     */
    private Integer age;

    /**
     * 员工工资
     */
    private Integer salary;

}
