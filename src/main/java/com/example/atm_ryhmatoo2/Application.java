package com.example.atm_ryhmatoo2;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application extends javafx.application.Application {
    private String jooksevVäärtus = null;
    private int kontojääk = 0;
    private String nimi=null;
    private String isikukood=null;
    private Failihaldur haldur = new Failihaldur();
    List<String[]> read = haldur.loeAndmed(); //Loeb iga rea listi
    private Konto konto = kontoAndmed(read,jooksevVäärtus);
    private double pikkus = 220;
    private double laius=150;

    public Application() throws FileNotFoundException {
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        BorderPane root = new BorderPane();
        BorderPane root2 = new BorderPane();
        VBox vasakpoolsedNupud = new VBox();
        VBox parempoolsedNupud = new VBox();
        Label palubSisestust = new Label("Sisestage PIN/Uue konto loomiseks sisestage 'UUS': ");
        Label teineEkraan = new Label("See on teine ekraan");
        Label test = new Label("test");
        Label toiming = new Label("Soovitud toimingu tegemiseks vajutage vastavat nuppu");
        TextField sisestus = new TextField("****");
        Scene esiEkraan = new Scene(root,420,230);
        Scene peaEkraan = new Scene(root2,420,230);
        Button sisesta = new Button("Sisesta");
        Button kontoJääk = new Button("Konto jääk");
        Button rahaArvele = new Button("Pane raha arvele");
        Button rahaArvelt = new Button("Võta raha arvelt");
        Button muudaPin = new Button("Muuda PIN koodi");
        Button kviitung = new Button("Kirjuta kviitung");
        Button lõpeta = new Button("Lõpeta");
        kontoJääk.setPrefSize(laius,pikkus);
        rahaArvele.setPrefSize(laius,pikkus);
        rahaArvelt.setPrefSize(laius,pikkus);
        muudaPin.setPrefSize(laius,pikkus);
        kviitung.setPrefSize(laius,pikkus);
        lõpeta.setPrefSize(laius,pikkus);
        lõpeta.setTextFill(Color.RED);

        sisesta.setPrefSize(420,230);
        sisesta.setOnMouseClicked(event -> {
            jooksevVäärtus=sisesta.getText();
            if (sisestus.getText().equals("1234")){
                primaryStage.setScene(peaEkraan);
            }
        });
        root.setCenter(sisestus);
        root.setTop(palubSisestust);
        root.setBottom(sisesta);
        root2.setLeft(vasakpoolsedNupud);
        root2.setRight(parempoolsedNupud);
        root2.setTop(toiming);
        sisesta.setAlignment(Pos.CENTER);

        vasakpoolsedNupud.getChildren().add(kontoJääk);
        vasakpoolsedNupud.getChildren().add(rahaArvele);
        vasakpoolsedNupud.getChildren().add(rahaArvelt);
        parempoolsedNupud.getChildren().add(muudaPin);
        parempoolsedNupud.getChildren().add(kviitung);
        parempoolsedNupud.getChildren().add(lõpeta);

        primaryStage.setScene(esiEkraan);
        root2.getChildren().addAll(test);
        primaryStage.setTitle("ATM");
        primaryStage.show();
    }
    public Konto kontoAndmed(List<String[]> elem, String pin) {
        Konto konto = null;
        for (String[] strings : read) {
            if (strings[0].equals(pin)) {
                kontojääk = Integer.parseInt(strings[3]);
                nimi = strings[1];
                isikukood = strings[2];
                konto = new Konto(nimi, kontojääk, pin, isikukood);
            }
        }
        return konto;
    }

    public static void main(String[] args) {
        launch();
    }
}