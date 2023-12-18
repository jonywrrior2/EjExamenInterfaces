package com.example.pruebasjavafx.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private int id;
    private String nombre;
    private String email;
}


