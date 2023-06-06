package com.ybllcodes.studentapplication.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    Long id;
    Integer status;
    String name;
    String mac;
    Integer type;
}
