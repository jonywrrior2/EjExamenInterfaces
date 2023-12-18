package com.example.pruebasjavafx.controllers;

import com.example.pruebasjavafx.Sesion;
import com.example.pruebasjavafx.models.Cliente;
import com.example.pruebasjavafx.models.Entrada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button btAnhadir;
    private ObservableList<Entrada> observableListPersonas = FXCollections.observableArrayList();
    @FXML
    private TextField tvMatricula;
    @FXML
    private ChoiceBox<String> choiceModelo;
    @FXML
    private ChoiceBox<String> choiceCliente;
    @FXML
    private DatePicker dpFechaEntrada;
    @FXML
    private DatePicker dpFechaSalida;
    @FXML
    private Label lblCoste;
    @FXML
    private Button btnSalir;
    @FXML
    private TableView <Entrada>tvEntrada;
    @FXML
    private TableColumn <Entrada, String> cMatricula;
    @FXML
    private TableColumn <Entrada, String> cModelo;
    @FXML
    private TableColumn <Entrada, LocalDate> cFechaEntrada;
    @FXML
    private TableColumn <Entrada, LocalDate> cFechaSalida;
    @FXML
    private TableColumn <Entrada, String> cCliente;
    @FXML
    private TableColumn <Entrada, String> cTarifa;
    @FXML
    private TableColumn <Entrada, Integer >cCoste;
    @FXML
    private Label lblUsuario;
    @FXML
    private RadioButton rBtnStandard;
    @FXML
    private RadioButton rBtnOferta;
    @FXML
    private RadioButton rBtnLargaDuracion;
    private LocalDate fechaActual;
    private Period diasIntervalo;
    @FXML
    private ToggleGroup tarifa;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configuración de la TableView
        cMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        cModelo.setCellValueFactory(new PropertyValueFactory<>("modeloCoche"));
        cCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        cTarifa.setCellValueFactory(new PropertyValueFactory<>("tipoTarifa"));
        cFechaEntrada.setCellValueFactory(new PropertyValueFactory<>("fechaEntrada"));
        cFechaSalida.setCellValueFactory(new PropertyValueFactory<>("fechaSalida"));
        cCoste.setCellValueFactory(new PropertyValueFactory<>("costeTotal"));

        // Configuración de la ChoiceBox
        choiceCliente.setItems(FXCollections.observableArrayList("Pepe", "Luis", "Lucia"));
        choiceCliente.getSelectionModel().selectFirst();

        choiceModelo.setItems(FXCollections.observableArrayList("Honda", "Ferrari", "Bmw"));
        choiceModelo.getSelectionModel().selectFirst();

        // Configuración de las fechas por defecto
        dpFechaEntrada.setValue(LocalDate.now());
        dpFechaSalida.setValue(LocalDate.now().plusDays(1));

        // Datos iniciales en la TableView
        tvEntrada.getItems().add(new Entrada("4920CLA", "Honda", "Paco", "Oferta", LocalDate.of(2023, 12, 19), LocalDate.of(2023, 12, 20), "6€"));
        tvEntrada.getItems().add(new Entrada("0291LLP", "Ferrari", "Luis", "Standard", LocalDate.of(2023, 12, 17), LocalDate.of(2023, 12, 20), "24€"));
        tvEntrada.getItems().add(new Entrada("4920CLA", "Bmw", "Lucia", "Larga duracion", LocalDate.of(2023, 12, 20), LocalDate.of(2023, 12, 25), "10€"));
        lblUsuario.setOnMousePressed(mouseEvent -> alertaUsuario());
    }


    @FXML
    public void insertarReceta(ActionEvent actionEvent) {
        Entrada entrada = new Entrada();
        //Parsear la fecha y meterla en la tabla.
        LocalDate fechaEntrada = dpFechaEntrada.getValue(); 

        LocalDate fechaSalida = dpFechaSalida.getValue();

        diasIntervalo = Period.between(fechaEntrada,fechaSalida);


        
        if (tvMatricula.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Debe rellenar la matricula");
            alert.show();
        } else if (!rBtnOferta.isSelected() && !rBtnStandard.isSelected() && !rBtnLargaDuracion.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Debe seleccionar tarifa");
            alert.show();
        } else if (rBtnStandard.isSelected()) {
            entrada.setTipoTarifa(rBtnStandard.getText());
            lblCoste.setText(diasIntervalo.getDays()*8 + "€");
        }else if (rBtnOferta.isSelected()) {
            entrada.setTipoTarifa(rBtnOferta.getText());
            lblCoste.setText(diasIntervalo.getDays()*6 + "€");
        }else if (rBtnLargaDuracion.isSelected()) {
            entrada.setTipoTarifa(rBtnLargaDuracion.getText());
            lblCoste.setText(diasIntervalo.getDays()*2 + "€");
        }
        entrada.setMatricula(tvMatricula.getText());
        entrada.setModeloCoche(choiceModelo.getValue());
        entrada.setCliente(choiceCliente.getSelectionModel().getSelectedItem());
        entrada.setFechaEntrada(dpFechaEntrada.getValue());
        entrada.setFechaSalida(dpFechaSalida.getValue());
        entrada.setCosteTotal((lblCoste.getText()));

        tvEntrada.getItems().add(entrada);
        
    }

    @FXML
    public void salir(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void alertaUsuario() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Cristian Bersabe Atienza del grado 2 de DAM");
        alert.show();
    }
}