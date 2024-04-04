package com.fabrik.test.demo.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse {

    private String status;
    private List<String> errors;
    private Object payload;

}
