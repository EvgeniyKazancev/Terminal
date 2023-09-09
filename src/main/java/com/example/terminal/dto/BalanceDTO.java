package com.example.terminal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class BalanceDTO implements Serializable {

    private Long balance;

}
