package com.example.terminal.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Users implements Serializable {

    private String firstName;

    private String lastName;

}
