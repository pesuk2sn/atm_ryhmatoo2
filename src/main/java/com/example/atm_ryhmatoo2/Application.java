package com.example.atm_ryhmatoo2;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application extends javafx.application.Application {
    private int kontojääk = 0;
    private String nimi=null;
    private String isikukood;
    private Failihaldur haldur = new Failihaldur();
    private List<String[]> read = haldur.loeAndmed(); //Loeb iga rea listi
    private Konto konto;

    private double nupuPikkus = 20; //default mõõdud klikitavate nuppude jaoks
    private double nupuLaius=100;
    private double stseeniPikkus=170;
    private double stseeniLaius =330;


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
        BorderPane kontoJääkPane = new BorderPane();
        BorderPane rahaArvelePane = new BorderPane();
        BorderPane rahaArveltPane = new BorderPane();
        BorderPane muudaPinPane = new BorderPane();
        BorderPane kviitungPane = new BorderPane();
        HBox rahaArveltNupud = new HBox();
        VBox rahaArveltSisestused = new VBox();
        HBox rahaArveleNupud = new HBox();
        VBox rahaArveleSisestused = new VBox();
        HBox kviitungNupud = new HBox();
        HBox jääkNupud = new HBox();
        HBox sisestaIdNupp = new HBox();
        HBox sisestaParoolNupp = new HBox();
        VBox muudaPinSisestused = new VBox();
        HBox muudaPinNupud = new HBox();


        //tekstid vastava stseeni jaoks
        Label palubSisestust = new Label("Sisestage ID kood/Uue konto loomiseks sisesta'UUS': "); //Sisselogimise ekraani tekst
        Label palubSisestustparool=new Label("Sisesta parool");
        Label toiming = new Label("Soovitud toimingu tegemiseks vajutage vastavat nuppu");
        Label info = new Label();
        Label kontoJääkInfo = new Label();
        Label kviitungInfo = new Label();

        //sisestused iga stseeni jaoks
        TextField idKoodSisestus = new TextField("ID kood"); //Siia peaks kasutaja oma ID koodi sisestama
        TextField paroolSisestus = new PasswordField();
        TextField rahaArveltSisestus = new TextField("Summa");
        TextField rahaArveleSisestus = new TextField("Summa");
        TextField uusKontoNimi = new TextField("Uus nimi");
        TextField uusKontoIDkood=new TextField("Teie id kood");
        TextField uusKontoParool=new TextField("Teie parool");
        TextField uusParoolSisestus = new TextField("Uus parool");
        TextField vanaPinSisestus = new PasswordField();
        TextField uusPinSisestus1 = new PasswordField();
        TextField uusPinSisestus2 = new PasswordField();
        paroolSisestus.setPromptText("PIN");
        vanaPinSisestus.setPromptText("Vana PIN");
        uusPinSisestus1.setPromptText("Uus PIN");
        uusPinSisestus2.setPromptText("Uus PIN uuesti");

        //Stseen iga funktsiooni jaoks
        Scene sisselogimineStseen = new Scene(sisselogiminePane,stseeniLaius,stseeniPikkus); //See on sisselogimise stseen, kus kasutaja saab sisse logid
        Scene paroolSisestusStseen = new Scene(paroolPane,stseeniLaius,stseeniPikkus);
        Scene menüüStseen = new Scene(menüüPane,stseeniLaius,stseeniPikkus); //See on põhiekraan, kus kasutaja saab teha vajalike toiminguid oma kontoga
        Scene kontoJääkStseen = new Scene(kontoJääkPane,stseeniLaius,stseeniPikkus);
        Scene rahaArveleStseen = new Scene(rahaArvelePane,stseeniLaius,stseeniPikkus);
        Scene rahaArveltStseen = new Scene(rahaArveltPane,stseeniLaius,stseeniPikkus);
        Scene muudaPinStseen = new Scene(muudaPinPane,stseeniLaius,stseeniPikkus);
        Scene kviitungStseen = new Scene(kviitungPane,stseeniLaius,stseeniPikkus);
        Scene uusKontoStseen = new Scene(uusKontoPane,stseeniLaius,stseeniPikkus);


        //Nupud, nende nimed ja suurused
        Button sisestaID = new Button("Sisene"); //PIN koodi sisestamise nupp
        Button sisestaParool = new Button("Sisene");
        Button kontoJääk = new Button("Jääk"); //kontojäägi väljastamise fun
        Button rahaArvele = new Button("Sissemakse"); //raha arvele panemise fun
        Button rahaArvelt = new Button("Väljamakse"); //raha arvelt võtmise fun
        Button muudaPin = new Button("Muuda PIN"); //PINi muutmise fun
        Button kviitung = new Button("Kviitung"); //kviitungi printimise fun
        Button lõpeta = new Button("Lõpp"); //lõpetab atmi töö
        Button kontoJääkTagasi = new Button("Tagasi"); //Jääk stseenis tagasi põhimenüüsee
        Button rahaArveltTagasi = new Button("Tagasi"); //raha arvelt stseenis tagasi põhimenüüsse
        Button rahaArveleTagasi = new Button("Tagasi"); //raha arvele stseenis tagasi põhimenüüsse
        Button kviitungTagasi = new Button("Tagasi"); // kviitung stseenist tagasi põhimenüüsse
        Button võta = new Button("Võta"); //nupp raha võtmiseks
        Button lisa = new Button("Lisa"); //nupp raha lisamiseks
        Button uuskonto = new Button("Sisesta nimi, id kood ja salasõna");
        Button sisestaAndmed=new Button("Kinnita andmed");
        Button muuda = new Button("Muuda");
        Button muudaPinTagasi = new Button("Tagasi");

        kontoJääk.setPrefSize(nupuLaius,nupuPikkus);
        rahaArvele.setPrefSize(nupuLaius,nupuPikkus);
        rahaArvelt.setPrefSize(nupuLaius,nupuPikkus);
        muudaPin.setPrefSize(nupuLaius,nupuPikkus);
        kviitung.setPrefSize(nupuLaius,nupuPikkus);
        lõpeta.setPrefSize(nupuLaius,nupuPikkus);
        lõpeta.setTextFill(Color.RED);
        kontoJääkTagasi.setPrefSize(nupuLaius,nupuPikkus);
        rahaArveleTagasi.setPrefSize(nupuLaius,nupuPikkus);
        rahaArveltTagasi.setPrefSize(nupuLaius,nupuPikkus);
        lisa.setPrefSize(nupuLaius,nupuPikkus);
        võta.setPrefSize(nupuLaius,nupuPikkus);
        kviitungTagasi.setPrefSize(nupuLaius,nupuPikkus);


        sisestaID.setPrefSize(nupuLaius,nupuPikkus);
        sisestaParool.setPrefSize(nupuLaius,nupuPikkus);

        //lisasin peamised eventid ära
        sisestaID.setOnMouseClicked(event -> {
            if((!idKoodSisestus.getText().equals("UUS"))){
                for(String[] elem:read){
                    if(elem[2].equals(idKoodSisestus.getText())){
                        konto=new Konto(elem[1],Integer.parseInt(elem[3]),elem[0],elem[2]);
                        palubSisestust.setText("Sisesta parool: ");
                        primaryStage.setScene(paroolSisestusStseen);
                        break;
                    }
                }
            }else if(idKoodSisestus.getText().equals("UUS")){
                primaryStage.setScene(uusKontoStseen);
            }
        });
        sisestaAndmed.setOnMouseClicked(event ->{
            for(String[] elem:read) {
                if (uusKontoIDkood.getText().equals(elem[2])) {
                    primaryStage.setScene(sisselogimineStseen);
                    palubSisestust.setText("ID kood juba eksisteerib, logi sisse");
                    return;
                }
            }
            Konto konto = new Konto(uusKontoNimi.getText(),0,uusKontoParool.getText(),uusKontoIDkood.getText());
            try{
                haldur.looKonto(konto.getPin(),konto.getNimi(),konto.getIsikukood(),Integer.toString(konto.getKontoJääk()));
                read=haldur.loeAndmed();
                primaryStage.setScene(sisselogimineStseen);
                palubSisestust.setText("Sisestage ID kood/Uue konto loomiseks sisestage 'UUS': ");
            }catch(IOException e){
                System.out.println(e.getMessage());
                primaryStage.setScene(sisselogimineStseen);
                palubSisestust.setText("Tekkis viga konto loomisega");
            }
        });
        sisestaParool.setOnMouseClicked(event ->{
            if((paroolSisestus.getText().equals(konto.getPin()))){
                primaryStage.setScene(menüüStseen);
            }
        });
        kontoJääk.setOnMouseClicked(event -> {
            primaryStage.setScene(kontoJääkStseen);
            kontoJääkInfo.setText("Teie kontojääk on "+ konto.getKontoJääk()+"€");
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
            try{
                FileWriter failiKirjutaja = new FileWriter("kviitung.txt");
                int kviitungiNumber = (int)(Math.random()*((9999-1000)+1))+1000;
                failiKirjutaja.write("Kviitungi nr: "+ kviitungiNumber +", kliendi nimi: "+konto.getNimi()+", kuupäev: " + LocalDate.now());
                failiKirjutaja.close();
                kviitungInfo.setText("Kviitung on trükitud!");
            }catch(IOException e){
                kviitungInfo.setText("Kviitungi kirjutamine ebaõnnestus");
            }
        });
        rahaArveleTagasi.setOnMouseClicked(event -> {
            rahaArveleSisestus.setText("Summa");
            primaryStage.setScene(menüüStseen);

        });
        muudaPin.setOnMouseClicked(event -> {

            primaryStage.setScene(muudaPinStseen);
        });
        muuda.setOnMouseClicked(event -> {
            if (vanaPinSisestus.getText().equals(konto.getPin())&&uusPinSisestus1.getText().equals(uusPinSisestus2.getText())){
                konto.setPin(uusPinSisestus1.getText());
                for (String[] elem : read) {
                    if (elem[2].equals(konto.getIsikukood())) {
                        elem[0]=konto.getPin();
                        read.set(read.indexOf(elem), elem);
                        break;
                    }
                }
                primaryStage.setScene(menüüStseen);
                menüüPane.setCenter(new Label("PIN muudetud"));
            }
            else {
                muudaPinPane.setTop(new Label("PIN-id ei ühti"));
            }
        });
        muudaPinTagasi.setOnMouseClicked(event ->{
            primaryStage.setScene(menüüStseen);
        });
        võta.setOnMouseClicked(event -> {
            if (konto.getKontoJääk() >= Integer.parseInt(rahaArveltSisestus.getText())){
                konto.võtaRahaArvelt(Integer.parseInt(rahaArveltSisestus.getText()));
                rahaArveltPane.setTop(new Label("Palju tahad välja võtta?"));
                primaryStage.setScene(menüüStseen);
                rahaArveltSisestus.setText("Summa");
            }else{
                rahaArveltPane.setTop(new Label("Pole piisavalt raha"));
            }

        });
        lisa.setOnMouseClicked(event->{
            konto.kannaRahaArvele(Integer.parseInt(rahaArveleSisestus.getText()));
            rahaArvelePane.setTop(new Label("Sisesta raha"));
            primaryStage.setScene(menüüStseen);
            rahaArveleSisestus.setText("Summa");
        });
        rahaArveltTagasi.setOnMouseClicked(event -> {
            rahaArveltSisestus.setText("Summa");
            rahaArveltPane.setTop(new Label("Sisesta raha"));
            primaryStage.setScene(menüüStseen);
        });
        kontoJääkTagasi.setOnMouseClicked(event -> {
            primaryStage.setScene(menüüStseen);
        });
        lõpeta.setOnMouseClicked(event -> {
            for (String[] elem : read) {
                if (elem[2].equals(konto.getIsikukood())) {
                    elem[3]=Integer.toString(konto.getKontoJääk());
                    read.set(read.indexOf(elem), elem);
                    break;
                }
            }
            try {
                haldur.kirjutaAndmed(read);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            primaryStage.close();
        });
        //layoutidele nuppude lisamine, iga layout eraldi
        uusKontoPane.getChildren().add(uusKontoNimi);
        uusKontoPane.getChildren().add(uusKontoIDkood);
        uusKontoPane.getChildren().add(uusKontoParool);
        uusKontoPane.getChildren().add(sisestaAndmed);


        //elementide paigutus layoutidele
        paroolPane.setCenter(paroolSisestus);
        paroolPane.setTop(palubSisestustparool);
        paroolPane.setBottom(sisestaParoolNupp);
        sisselogiminePane.setCenter(idKoodSisestus);
        sisselogiminePane.setTop(palubSisestust);
        sisselogiminePane.setBottom(sisestaIdNupp);
        menüüPane.setLeft(vasakpoolsedNupud);
        menüüPane.setRight(parempoolsedNupud);
        menüüPane.setTop(toiming);
        rahaArveltNupud.setAlignment(Pos.CENTER);
        rahaArveleNupud.setAlignment(Pos.CENTER);

        rahaArvelePane.setCenter(new Label("Sisesta summa"));
        rahaArvelePane.setCenter(rahaArveleSisestused);
        rahaArvelePane.setBottom(rahaArveleNupud);

        rahaArveltPane.setCenter(rahaArveltSisestused);
        rahaArveltPane.setBottom(rahaArveltNupud);

        kontoJääkPane.setCenter(kontoJääkInfo);
        kontoJääkPane.setBottom(jääkNupud);

        muudaPinPane.setCenter(muudaPinSisestused);
        muudaPinPane.setBottom(muudaPinNupud);

        kviitungPane.setCenter(kviitungInfo);
        kviitungPane.setBottom(kviitungNupud);

        palubSisestustparool.setAlignment(Pos.CENTER);
        sisestaID.setAlignment(Pos.CENTER);
        toiming.setAlignment(Pos.CENTER);
        kviitungInfo.setAlignment(Pos.CENTER);
        kontoJääk.setAlignment(Pos.CENTER);
        info.setAlignment(Pos.CENTER);
        rahaArveltNupud.setAlignment(Pos.CENTER);
        rahaArveleNupud.setAlignment(Pos.CENTER);
        jääkNupud.setAlignment(Pos.CENTER);
        muudaPinSisestused.setAlignment(Pos.CENTER);
        muudaPinNupud.setAlignment(Pos.CENTER);
        kviitungNupud.setAlignment(Pos.CENTER);
        sisestaIdNupp.setAlignment(Pos.CENTER);
        sisestaParoolNupp.setAlignment(Pos.CENTER);

        sisselogiminePane.setPadding(new Insets(5,5,5,5));
        paroolPane.setPadding(new Insets(5,5,5,5));
        uusKontoPane.setSpacing(5);
        uusKontoPane.setPadding(new Insets(5,5,5,5));
        uusKontoPane.setAlignment(Pos.CENTER);
        vasakpoolsedNupud.setSpacing(5);
        parempoolsedNupud.setSpacing(5);
        rahaArveleNupud.setSpacing(5);
        rahaArveltNupud.setSpacing(5);
        menüüPane.setPadding(new Insets(5,5,5,5));
        rahaArvelePane.setPadding(new Insets(5,5,5,5));
        rahaArveleSisestused.setSpacing(5);
        rahaArveltPane.setPadding(new Insets(5,5,5,5));
        rahaArveltSisestused.setSpacing(5);
        kviitungPane.setPadding(new Insets(5,5,5,5));
        kontoJääkPane.setPadding(new Insets(5,5,5,5));
        paroolSisestus.setPadding(new Insets(5,5,5,5));
        muudaPinSisestused.setPadding(new Insets(5,5,5,5));
        muudaPinSisestused.setSpacing(5);
        muudaPinNupud.setPadding(new Insets(5,5,5,5));
        muudaPinNupud.setSpacing(5);


        sisestaIdNupp.getChildren().add(sisestaID);
        sisestaParoolNupp.getChildren().add(sisestaParool);
        vasakpoolsedNupud.getChildren().add(kontoJääk);
        vasakpoolsedNupud.getChildren().add(rahaArvele);
        vasakpoolsedNupud.getChildren().add(rahaArvelt);
        parempoolsedNupud.getChildren().add(muudaPin);
        parempoolsedNupud.getChildren().add(kviitung);
        parempoolsedNupud.getChildren().add(lõpeta);
        rahaArveleSisestused.getChildren().add(new Label("Summa"));
        rahaArveleSisestused.getChildren().add(rahaArveleSisestus);
        rahaArveltSisestused.getChildren().add(new Label("Summa"));
        rahaArveltSisestused.getChildren().add(rahaArveltSisestus);
        rahaArveleNupud.getChildren().add(lisa);
        rahaArveleNupud.getChildren().add(rahaArveleTagasi);
        rahaArveltNupud.getChildren().add(võta);
        rahaArveltNupud.getChildren().add(rahaArveltTagasi);
        jääkNupud.getChildren().add(kontoJääkTagasi);
        kviitungNupud.getChildren().add(kviitungTagasi);
        muudaPinSisestused.getChildren().add(vanaPinSisestus);
        muudaPinSisestused.getChildren().add(uusPinSisestus1);
        muudaPinSisestused.getChildren().add(uusPinSisestus2);
        muudaPinNupud.getChildren().add(muuda);
        muudaPinNupud.getChildren().add(muudaPinTagasi);

        primaryStage.setResizable(false);
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