package com.example.pruebasjavafx.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entrada {

    private String matricula;
    private String modeloCoche;
    private String cliente;
    private String tipoTarifa;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private String costeTotal;

}
