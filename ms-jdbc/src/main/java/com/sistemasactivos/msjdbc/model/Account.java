package com.sistemasactivos.msjdbc.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Long id;

    @NotBlank
    private String accalias;

    @NotBlank
    private String acctype;
}
