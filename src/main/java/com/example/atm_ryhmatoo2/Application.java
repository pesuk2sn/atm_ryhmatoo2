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

    private Failihaldur haldur = new Failihaldur(); //failide lugemise isend
    private List<String[]> read = haldur.loeAndmed(); //Loeb iga rea listi
    private Konto konto;

    private double nupuPikkus = 20; //default mõõdud klikitavate nuppude jaoks
    private double nupuLaius=100;
    private double stseeniPikkus=170;// default mõõdud stseenidele
    private double stseeniLaius =330;


    @Override
    public void start(Stage primaryStage) throws IOException {


        for(String[] elem:read){
            System.out.println(Arrays.toString(elem)); //Väljastab kontode andmed
        }


        //layoutid koos elementide layoutiga iga vastava stseeni jaoks, nimega viidatud
        BorderPane sisselogiminePane = new BorderPane(); //ID sisestamise layout
        VBox sisseLogimineElemendid = new VBox();
        BorderPane paroolPane=new BorderPane(); //parooli sisestamise layout
        VBox paroolElemendid = new VBox();
        BorderPane menüüPane = new BorderPane(); //Avaekraani layout
        VBox vasakpoolsedNupud = new VBox(); //Nuppude asetamine üksteisele alla, ja mis asub vasakul pool stseenis
        VBox parempoolsedNupud = new VBox();
        BorderPane uusKontoPane = new BorderPane(); //uue konto loomise layout
        VBox uusKontoElemendid = new VBox();
        BorderPane kontoJääkPane = new BorderPane(); //konto jäägi layout
        VBox kontoJääkElemendid = new VBox();
        BorderPane rahaArvelePane = new BorderPane(); //sissemakse layout
        VBox rahaArveleElemendid = new VBox();
        BorderPane rahaArveltPane = new BorderPane(); //väljamakse layout
        VBox rahaArveltElemendid = new VBox();
        BorderPane muudaParoolPane = new BorderPane(); //parooli muutmise layout
        VBox muudaParoolElemendid =  new VBox();
        BorderPane kviitungPane = new BorderPane(); //kviitungi layout
        VBox kviitungElemendid = new VBox();
        HBox rahaArveltNupud = new HBox(); //kui stseenis on vaja rohkem kui ühte nuppu, siis kasutasin Hboxi
        HBox rahaArveleNupud = new HBox();
        HBox muudaParoolNupud = new HBox();



        //tekstid vastava stseeni jaoks. Kasutatav siis, kui oli vaja muuta teksti mingi tegevuse järel
        Label palubSisestust = new Label("Sisesta ID, uue konto loomiseks kirjuta 'UUS'");
        Label kontoJääkInfo = new Label();
        Label kviitungInfo = new Label();

        //sisestused iga stseeni jaoks
        TextField idKoodSisestus = new TextField(); //ID koodi sisestus
        idKoodSisestus.setPromptText("ID");
        TextField paroolSisestus = new PasswordField();  //parooli sisestus
        paroolSisestus.setPromptText("parool");
        TextField rahaArveltSisestus = new TextField(); //väljamakse sisestus
        rahaArveltSisestus.setPromptText("summa");
        TextField rahaArveleSisestus = new TextField(); //sissemakse sisestus
        rahaArveleSisestus.setPromptText("summa");
        TextField uusKontoNimi = new TextField(); //uue konto andmete sisestused
        uusKontoNimi.setPromptText("konto nimi");
        TextField uusKontoIDkood=new TextField();
        uusKontoIDkood.setPromptText("konto ID");
        TextField uusKontoParool=new PasswordField();
        uusKontoParool.setPromptText("konto parool");
        TextField vanaParoolSisestus = new PasswordField(); //parooli muutmise sisestused
        TextField uusParoolSisestus1 = new PasswordField();
        TextField uusParoolSisestus2 = new PasswordField();
        paroolSisestus.setPromptText("Parool");
        vanaParoolSisestus.setPromptText("Vana parool");
        uusParoolSisestus1.setPromptText("Uus parool");
        uusParoolSisestus2.setPromptText("Uus parool uuesti");

        //Stseen iga funktsiooni jaoks
        Scene sisselogimineStseen = new Scene(sisselogiminePane,stseeniLaius,stseeniPikkus); //See on sisselogimise stseen, kus kasutaja saab sisse logida
        Scene paroolSisestusStseen = new Scene(paroolPane,stseeniLaius,stseeniPikkus);
        Scene menüüStseen = new Scene(menüüPane,stseeniLaius,stseeniPikkus); //See on põhiekraan, kus kasutaja saab teha vajalike toiminguid oma kontoga
        Scene kontoJääkStseen = new Scene(kontoJääkPane,stseeniLaius,stseeniPikkus); //konto jäägi stseen
        Scene rahaArveleStseen = new Scene(rahaArvelePane,stseeniLaius,stseeniPikkus); //sissemakse stseen
        Scene rahaArveltStseen = new Scene(rahaArveltPane,stseeniLaius,stseeniPikkus); //väljamakse stseen
        Scene muudaParoolStseen = new Scene(muudaParoolPane,stseeniLaius,stseeniPikkus); //parooli muutmise stseen
        Scene kviitungStseen = new Scene(kviitungPane,stseeniLaius,stseeniPikkus); //kviitungi stseen
        Scene uusKontoStseen = new Scene(uusKontoPane,stseeniLaius,stseeniPikkus); //uue konto stseen


        //Nupud, nende nimed ja suurused
        Button sisestaID = new Button("Sisene"); //ID sisestamise nupp
        Button sisestaParool = new Button("Sisene");
        Button kontoJääk = new Button("Jääk"); //kontojäägi väljastamise fun
        Button rahaArvele = new Button("Sissemakse"); //raha arvele panemise fun
        Button rahaArvelt = new Button("Väljamakse"); //raha arvelt võtmise fun
        Button muudaParool = new Button("Muuda parool"); //parooli muutmise fun
        Button kviitung = new Button("Kviitung"); //kviitungi printimise fun
        Button lõpeta = new Button("Lõpp"); //lõpetab atmi töö
        Button kontoJääkTagasi = new Button("Tagasi"); //Jääk stseenis tagasi põhimenüüsee
        Button rahaArveltTagasi = new Button("Tagasi"); //raha arvelt stseenis tagasi põhimenüüsse
        Button rahaArveleTagasi = new Button("Tagasi"); //raha arvele stseenis tagasi põhimenüüsse
        Button kviitungTagasi = new Button("Tagasi"); // kviitung stseenist tagasi põhimenüüsse
        Button võta = new Button("Võta"); //nupp raha võtmiseks
        Button lisa = new Button("Lisa"); //nupp raha lisamiseks
        Button sisestaAndmed=new Button("Kinnita andmed"); //uue konto loomise kinnitamine
        Button muuda = new Button("Muuda"); //parooli muutmine
        Button muudaParoolTagasi = new Button("Tagasi"); //parooli stseenis tagasi

        //igale nupule default suurus
        kontoJääk.setPrefSize(nupuLaius,nupuPikkus);
        rahaArvele.setPrefSize(nupuLaius,nupuPikkus);
        rahaArvelt.setPrefSize(nupuLaius,nupuPikkus);
        muudaParool.setPrefSize(nupuLaius,nupuPikkus);
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

        //igale layoutile oma parameetrid ja layouti sisse elementide layout, iga layout on siit eraldatud teistest
        sisseLogimineElemendid.setSpacing(5);
        sisseLogimineElemendid.setPadding(new Insets(5,5,5,5));
        sisseLogimineElemendid.setAlignment(Pos.CENTER_LEFT);
        sisseLogimineElemendid.getChildren().addAll(palubSisestust,idKoodSisestus,sisestaID);
        sisselogiminePane.setCenter(sisseLogimineElemendid);

        uusKontoElemendid.setSpacing(5);
        uusKontoElemendid.setPadding(new Insets(5,5,5,5));
        uusKontoElemendid.setAlignment(Pos.CENTER_LEFT);
        uusKontoElemendid.getChildren().addAll(new Label("Sisesta uue konto andmed"),uusKontoNimi,uusKontoIDkood,uusKontoParool, sisestaAndmed);
        uusKontoPane.setCenter(uusKontoElemendid);

        paroolElemendid.setSpacing(5);
        paroolElemendid.setPadding(new Insets(5,5,5,5));
        paroolElemendid.setAlignment(Pos.CENTER_LEFT);
        paroolElemendid.getChildren().addAll(new Label("Sisesta parool"),paroolSisestus,sisestaParool);
        paroolPane.setCenter(paroolElemendid);

        vasakpoolsedNupud.setSpacing(15);
        parempoolsedNupud.setSpacing(15);
        vasakpoolsedNupud.setAlignment(Pos.CENTER);
        parempoolsedNupud.setAlignment(Pos.CENTER);
        vasakpoolsedNupud.getChildren().addAll(kontoJääk,rahaArvele,rahaArvelt);
        parempoolsedNupud.getChildren().addAll(muudaParool,kviitung,lõpeta);
        menüüPane.setPadding(new Insets(5,5,5,5));
        menüüPane.setLeft(vasakpoolsedNupud);
        menüüPane.setRight(parempoolsedNupud);
        menüüPane.setTop(new Label("Soovitud toimingu tegemiseks vajutage vastavat nuppu"));

        rahaArvelePane.setPadding(new Insets(5,5,5,5));
        rahaArveleElemendid.setSpacing(5);
        rahaArveleNupud.getChildren().addAll(lisa,rahaArveleTagasi);
        rahaArveleElemendid.getChildren().addAll(new Label("Sisesta summa"),rahaArveleSisestus,rahaArveleNupud);
        rahaArveleElemendid.setAlignment(Pos.CENTER);
        rahaArveleNupud.setAlignment(Pos.CENTER);
        rahaArvelePane.setCenter(rahaArveleElemendid);
        rahaArveleNupud.setSpacing(5);


        rahaArveltNupud.setAlignment(Pos.CENTER);
        rahaArveltNupud.setSpacing(5);
        rahaArveltNupud.getChildren().addAll(võta,rahaArveltTagasi);
        rahaArveltElemendid.getChildren().addAll(new Label("Sisesta summa"),rahaArveltSisestus,rahaArveltNupud);
        rahaArveltElemendid.setAlignment(Pos.CENTER);
        rahaArveltPane.setCenter(rahaArveltElemendid);
        rahaArveltPane.setPadding(new Insets(5,5,5,5));
        rahaArveltElemendid.setSpacing(5);


        kontoJääkElemendid.getChildren().addAll(kontoJääkInfo,kontoJääkTagasi);
        kontoJääkElemendid.setSpacing(5);
        kontoJääkElemendid.setAlignment(Pos.CENTER);
        kontoJääkPane.setPadding(new Insets(5,5,5,5));
        kontoJääkPane.setCenter(kontoJääkElemendid);


        muudaParoolNupud.getChildren().addAll(muuda,muudaParoolTagasi);
        muudaParoolElemendid.getChildren().addAll(vanaParoolSisestus,uusParoolSisestus1,uusParoolSisestus2, muudaParoolNupud);
        muudaParoolElemendid.setSpacing(5);
        muudaParoolElemendid.setAlignment(Pos.CENTER);
        muudaParoolPane.setCenter(muudaParoolElemendid);
        muudaParoolNupud.setAlignment(Pos.CENTER);
        muudaParoolElemendid.setPadding(new Insets(5,5,5,5));


        kviitungElemendid.setSpacing(5);
        kviitungElemendid.getChildren().addAll(kviitungInfo,kviitungTagasi);
        kviitungElemendid.setAlignment(Pos.CENTER);
        kviitungPane.setPadding(new Insets(5,5,5,5));
        kviitungPane.setCenter(kviitungElemendid);

        //sündmused, mis nupudele vajutades toimuvad
        sisestaID.setOnMouseClicked(event -> {
            if((!idKoodSisestus.getText().equals("UUS"))){ //kui leiab konto sisestatud id koodi järgi
                for(String[] elem:read){
                    if(elem[2].equals(idKoodSisestus.getText())){
                        konto=new Konto(elem[1],Integer.parseInt(elem[3]),elem[0],elem[2]);
                        primaryStage.setScene(paroolSisestusStseen);
                        break;
                    }
                }
            }else if(idKoodSisestus.getText().equals("UUS")){ //kui kasutaja teeb uue konto
                primaryStage.setScene(uusKontoStseen);
            }
        });
        sisestaAndmed.setOnMouseClicked(event ->{
            for(String[] elem:read) {
                if (uusKontoIDkood.getText().equals(elem[2])) { //kui konto loomisel sisestatakse olemasolev ID kood
                    primaryStage.setScene(sisselogimineStseen);
                    palubSisestust.setText("ID kood juba eksisteerib, logi sisse");
                    return;
                }
            }
            Konto konto = new Konto(uusKontoNimi.getText(),0,uusKontoParool.getText(),uusKontoIDkood.getText()); //konto loomine
            try{
                haldur.looKonto(konto.getPin(),konto.getNimi(),konto.getIsikukood(),Integer.toString(konto.getKontoJääk()));
                read=haldur.loeAndmed();
                primaryStage.setScene(sisselogimineStseen);
            }catch(IOException e){
                System.out.println(e.getMessage());
                primaryStage.setScene(sisselogimineStseen);
                palubSisestust.setText("Tekkis viga konto loomisega");
            }
        });
        sisestaParool.setOnMouseClicked(event ->{ //kontrollib kas, parool on õige
            if((paroolSisestus.getText().equals(konto.getPin()))){
                primaryStage.setScene(menüüStseen);
            }
        });
        kontoJääk.setOnMouseClicked(event -> { //kontojäägi vaatamine
            primaryStage.setScene(kontoJääkStseen);
            kontoJääkInfo.setText("Teie kontojääk on "+ konto.getKontoJääk()+"€");
        });
        kviitungTagasi.setOnMouseClicked(event -> { //viib kviitungi stseenilt peamenüüsse
            primaryStage.setScene(menüüStseen);
        });
        rahaArvele.setOnMouseClicked(event -> { //viib sissemakse stseeni
            primaryStage.setScene(rahaArveleStseen);
        });
        rahaArvelt.setOnMouseClicked(event -> { //viib väljamakse stseeni
            primaryStage.setScene(rahaArveltStseen);
        });
        kviitung.setOnMouseClicked(event -> { //viib kviitungi trükkimise stseeni
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
        rahaArveleTagasi.setOnMouseClicked(event -> { //sissemakse stseenist peamenüüsse
            primaryStage.setScene(menüüStseen);

        });
        muudaParool.setOnMouseClicked(event -> { //viib parooli muutmise stseeni
            primaryStage.setScene(muudaParoolStseen);
        });
        muuda.setOnMouseClicked(event -> { //muudab konto parooli, kui tingimused on täidetud
            if (vanaParoolSisestus.getText().equals(konto.getPin())&&uusParoolSisestus1.getText().equals(uusParoolSisestus2.getText())){
                konto.setPin(uusParoolSisestus1.getText());
                for (String[] elem : read) {
                    if (elem[2].equals(konto.getIsikukood())) {
                        elem[0]=konto.getPin();
                        read.set(read.indexOf(elem), elem);
                        break;
                    }
                }
                primaryStage.setScene(menüüStseen);
                menüüPane.setCenter(new Label("Parool muudetud"));
            }
            else {
                muudaParoolPane.setTop(new Label("Paroolid ei ühti"));
            }
        });
        muudaParoolTagasi.setOnMouseClicked(event ->{ //viib parooli stseenist peamenüüsse
            primaryStage.setScene(menüüStseen);
        });
        võta.setOnMouseClicked(event -> { //väljamakse kinnituse nupp koos kontrolliga
            if (konto.getKontoJääk() >= Integer.parseInt(rahaArveltSisestus.getText())){
                konto.võtaRahaArvelt(Integer.parseInt(rahaArveltSisestus.getText()));
                rahaArveltPane.setTop(new Label("Palju tahad välja võtta?"));
                primaryStage.setScene(menüüStseen);
                menüüPane.setCenter(new Label("Raha arvelt võetud"));
            }else{
                rahaArveltPane.setTop(new Label("Pole piisavalt raha"));
            }

        });
        lisa.setOnMouseClicked(event->{ //sissemakse kinnitus
            konto.kannaRahaArvele(Integer.parseInt(rahaArveleSisestus.getText()));
            primaryStage.setScene(menüüStseen);
            menüüPane.setCenter(new Label("Raha arvele lisatud"));
        });
        rahaArveltTagasi.setOnMouseClicked(event -> { //väljamaksest peamenüüsse
            primaryStage.setScene(menüüStseen);
        });
        kontoJääkTagasi.setOnMouseClicked(event -> { //kontojäägist peamenüüsse
            primaryStage.setScene(menüüStseen);
        });
        lõpeta.setOnMouseClicked(event -> { //lõpeta nuppu vajutades kirjutab konto andmed faili ja  lõpetab programmi töö
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


        primaryStage.setResizable(false);
        primaryStage.setScene(sisselogimineStseen);
        primaryStage.setTitle("ATM");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}