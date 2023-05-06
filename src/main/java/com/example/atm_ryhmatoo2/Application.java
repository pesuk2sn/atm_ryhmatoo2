package com.example.atm_ryhmatoo2;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
    private String isikukood;
    private Failihaldur haldur = new Failihaldur();
    private final List<String[]> read = haldur.loeAndmed(); //Loeb iga rea listi
    private Konto konto;

    private double pikkus = 220; //default mõõdud klikitavate nuppude jaoks
    private double laius=150;

    public Application() throws FileNotFoundException {
    }

    @Override
    public void start(Stage primaryStage) throws IOException {


        for(String[] elem:read){
            System.out.println(Arrays.toString(elem));
        }


        //layoutid iga vastava stseeni jaoks, nimega viidatud
        BorderPane sisselogiminePane = new BorderPane(); //Sisselogimise ekraani layout
        BorderPane paroolPane=new BorderPane();
        BorderPane menüüPane = new BorderPane(); //Avaekraani layout
        VBox vasakpoolsedNupud = new VBox(); //Nuppude asetamine üksteisele alla, ja mis asub vasakul pool stseenis
        VBox parempoolsedNupud = new VBox();
        VBox uusKontoPane = new VBox();
        VBox kontoJääkPane = new VBox();
        VBox rahaArvelePane = new VBox();
        VBox rahaArveltPane = new VBox();
        VBox muudaPinPane = new VBox();
        VBox kviitungPane = new VBox();
        HBox rahaArveltNupud = new HBox();
        HBox rahaArveleNupud = new HBox();

        //tekstid vastava stseeni jaoks
        Label palubSisestust = new Label("Sisestage ID kood/Uue konto loomiseks sisestage 'UUS': "); //Sisselogimise ekraani tekst
        Label palubSisestustparool=new Label("Sisestage parool");
        Label toiming = new Label("Soovitud toimingu tegemiseks vajutage vastavat nuppu");
        Label info = new Label();
        Label kontoJääkInfo = new Label();
        Label kviitungInfo = new Label();

        //sisestused iga stseeni jaoks
        TextField idKoodSisestus = new TextField("ID kood"); //Siia peaks kasutaja oma ID koodi sisestama
        TextField paroolSisestus = new PasswordField();
        TextField rahaArveltSisestus = new TextField("Summa");
        TextField rahaArveleSisestus = new TextField("Summa");
        TextField uusKontoSisestus = new TextField("Uus nimi");
        TextField uusParoolSisestus = new TextField("Uus parool");

        //Stseen iga funktsiooni jaoks
        Scene sisselogimineStseen = new Scene(sisselogiminePane,420,230); //See on sisselogimise stseen, kus kasutaja saab sisse logid
        Scene paroolSisestusStseen = new Scene(paroolPane,420,230);
        Scene menüüStseen = new Scene(menüüPane,420,230); //See on põhiekraan, kus kasutaja saab teha vajalike toiminguid oma kontoga
        Scene kontoJääkStseen = new Scene(kontoJääkPane,420,230);
        Scene rahaArveleStseen = new Scene(rahaArvelePane,420,230);
        Scene rahaArveltStseen = new Scene(rahaArveltPane,420,230);
        Scene muudaPinStseen = new Scene(muudaPinPane,420,230);
        Scene kviitungStseen = new Scene(kviitungPane,420,230);
        Scene uusKontoStseen = new Scene(uusKontoPane,420,230);


        //Nupud, nende nimed ja suurused
        Button sisesta = new Button("Sisesta"); //PIN koodi sisestamise nupp
        Button sisestaParool = new Button("Sisesta");
        Button kontoJääk = new Button("Konto jääk"); //kontojäägi väljastamise fun
        Button rahaArvele = new Button("Pane raha arvele"); //raha arvele panemise fun
        Button rahaArvelt = new Button("Võta raha arvelt"); //raha arvelt võtmise fun
        Button muudaPin = new Button("Muuda PIN koodi"); //PINi muutmise fun
        Button kviitung = new Button("Kirjuta kviitung"); //kviitungi printimise fun
        Button lõpeta = new Button("Lõpeta"); //lõpetab atmi töö
        Button jääkTagasi = new Button("Tagasi"); //Jääk stseenis tagasi põhimenüüsee
        Button rahaArveltTagasi = new Button("Tagasi"); //raha arvelt stseenis tagasi põhimenüüsse
        Button rahaArveleTagasi = new Button("Tagasi"); //raha arvele stseenis tagasi põhimenüüsse
        Button kviitungTagasi = new Button("Tagasi"); // kviitung stseenist tagasi põhimenüüsse
        Button võta = new Button("Võta"); //nupp raha võtmiseks
        Button lisa = new Button("Lisa"); //nupp raha lisamiseks
        Button uuskonto = new Button("Sisesta nimi");

        kontoJääk.setPrefSize(laius,pikkus);
        rahaArvele.setPrefSize(laius,pikkus);
        rahaArvelt.setPrefSize(laius,pikkus);
        muudaPin.setPrefSize(laius,pikkus);
        kviitung.setPrefSize(laius,pikkus);
        lõpeta.setPrefSize(laius,pikkus);
        lõpeta.setTextFill(Color.RED);
        jääkTagasi.setPrefSize(laius,pikkus);
        rahaArveleTagasi.setPrefSize(laius,pikkus);
        rahaArveltTagasi.setPrefSize(laius,pikkus);
        lisa.setPrefSize(laius,pikkus);
        võta.setPrefSize(laius,pikkus);
        kviitungTagasi.setPrefSize(laius,pikkus);
        sisesta.setPrefSize(420,230);
        sisestaParool.setPrefSize(420,230);

        //lisasin peamised eventid ära
        sisesta.setOnMouseClicked(event -> {
            if((!idKoodSisestus.getText().equals("UUS"))){
                for(String[] elem:read){
                    if(elem[2].equals(idKoodSisestus.getText())){
                        konto=new Konto(elem[1],Integer.parseInt(elem[3]),elem[0],elem[2]);
                        palubSisestust.setText("Sisesta parool: ");
                        primaryStage.setScene(paroolSisestusStseen);
                        break;
                    }
                    System.out.println("siin");
                }
            }else if(idKoodSisestus.getText().equals("UUS")){
                primaryStage.setScene(uusKontoStseen);
            }
        });
        sisestaParool.setOnMouseClicked(event ->{
            if((paroolSisestus.getText().equals(konto.getPin()))){
                primaryStage.setScene(menüüStseen);
            }
        });
        kontoJääk.setOnMouseClicked(event -> {
            primaryStage.setScene(kontoJääkStseen);
        });
        kviitungTagasi.setOnMouseClicked(event -> {
            primaryStage.setScene(menüüStseen);
        });
        rahaArvele.setOnMouseClicked(event -> {
            primaryStage.setScene(rahaArveleStseen);
        });
        rahaArvelt.setOnMouseClicked(event -> {
            primaryStage.setScene(rahaArveltStseen);
        });
        kviitung.setOnMouseClicked(event -> {
            primaryStage.setScene(kviitungStseen);
            info.setText("Siia tuleb kontojääk");
        });
        rahaArveleTagasi.setOnMouseClicked(event -> {
            primaryStage.setScene(menüüStseen);
        });
        rahaArveltTagasi.setOnMouseClicked(event -> {
            primaryStage.setScene(menüüStseen);
        });
        jääkTagasi.setOnMouseClicked(event -> {
            primaryStage.setScene(menüüStseen);
        });
        lõpeta.setOnMouseClicked(event -> {
            try {
                haldur.kirjutaAndmed(read);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            primaryStage.close();
        });
        //layoutidele nuppude lisamine, iga layout eraldi
        uusKontoPane.getChildren().add(uusKontoSisestus);
        uusKontoPane.getChildren().add(sisesta);

        kontoJääkPane.getChildren().add(kontoJääkInfo);
        kontoJääkPane.getChildren().add(jääkTagasi);

        rahaArveltNupud.getChildren().add(võta);
        rahaArveltNupud.getChildren().add(rahaArveltTagasi);
        rahaArveltPane.getChildren().add(rahaArveltSisestus);
        rahaArveltPane.getChildren().add(rahaArveltNupud);

        rahaArveleNupud.getChildren().add(lisa);
        rahaArveleNupud.getChildren().add(rahaArveleTagasi);
        rahaArvelePane.getChildren().add(rahaArveleSisestus);
        rahaArvelePane.getChildren().add(rahaArveleNupud);

        kviitungPane.getChildren().add(kviitungInfo);
        kviitungPane.getChildren().add(kviitungTagasi);



        //elementide paigutus layoutidele
        paroolPane.setCenter(paroolSisestus);
        paroolPane.setTop(palubSisestustparool);
        paroolPane.setBottom(sisestaParool);
        sisselogiminePane.setCenter(idKoodSisestus);
        sisselogiminePane.setTop(palubSisestust);
        sisselogiminePane.setBottom(sisesta);
        menüüPane.setLeft(vasakpoolsedNupud);
        menüüPane.setRight(parempoolsedNupud);
        menüüPane.setTop(toiming);
        sisesta.setAlignment(Pos.CENTER);


        vasakpoolsedNupud.getChildren().add(kontoJääk);
        vasakpoolsedNupud.getChildren().add(rahaArvele);
        vasakpoolsedNupud.getChildren().add(rahaArvelt);
        parempoolsedNupud.getChildren().add(muudaPin);
        parempoolsedNupud.getChildren().add(kviitung);
        parempoolsedNupud.getChildren().add(lõpeta);

        primaryStage.setScene(sisselogimineStseen);
        primaryStage.setTitle("ATM");
        primaryStage.show();
    }
    //Konto andmete sisse lugemine
    public Konto kontoAndmed(List<String[]> elem, String pin) {
        Konto konto = null;
        for (String[] strings : read) {
            System.out.println(strings[0]);
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