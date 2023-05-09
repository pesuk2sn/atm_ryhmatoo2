Rühmatöö 2
Teema - ATM graafilise liidesega

Autorid - Robert Juhkam, Joonas Tiitson

Kirjeldus - Rühmatöö on otsene edasiarendus autorite eelmisest rühmatööst, mis oli samuti ATM teemal. Autorid otsustasid siis lisada olemasolevale programmile graafilise liidese.
Programmi eesmärk on kasutajal hallata oma konto rahalist seisu. Samuti on kasutajal võimalus luua endale uus konto. 
Kasutaja saab programmis oma kontolt raha välja võtta, raha arvele panna, konto jääki vaadata, konto parooli muuta ja kviitungi välja trükkida tekst failina.
Kontode andmed on kontode tekst failis, mis on turvalisuse eesmärgil krüpteeritud.
Andmete lugemiseks programm dekrüpteerib tekstis olevad andmed ja loeb need Konto isendisse sisse.
Programmi kasutamiseks on ette antud paari konto andmed terminalis, millega saab sisse logida.

Klassid: Application - graafilise liidese klass, kus on deklareeritud kõik graafilised elemendid, stseenid ja tekstid. Samuti rakendatakse selles klassis meetodeid.
start meetod loob ja kirjeldab kogu graafilise liidese tegevust. main meetod käivitab graafilise liidese.
Konto - klass Konto isendi loomiseks ja vajadusel isendi andmete muutmiseks set meetoditega.
Failihaldur - klass failist andmete krüpteerimiseks ja dekrüpteerimiseks. Meetodite nimed klassis kirjeldavad juba, mida need meetodid teevad.
Loeandmed meetod loeb tekstifailist teksti sisse. Loo konto meetod teeb uue Konto isendi. Krüpteeri meetod krüpteerib etteantud teksti. Dekrüpteeri meetod dekrüpteerib etteantud teksti.


Protsess: Joonas tegeles rohkem programmi funktsionaalsusega, Robert rohkem programmi graafilise poolega. 
Esimene etapp oli vaja graafilise liidese ja eelmise programmi funktsionaalsused omavahel toimima panna. Nupud ja tekstiväljad said esimeste elementidena liidesesse lisatud, et katsetada programmi tööd.
Nupud said ühildatud vastavate funktsionaalsustega mis olid ka eelmises rühmatöös. Nüüd oli lihtsalt see erinevus, et tekstisisestuse asemel saab kasutaja lihtsalt soovitud nupule klikkida.
Teine etapp oli kõikvõimalike veaohtlike kohtade otsimine, mida kasutaja teha võib ja nendesse kohtadesse siis erindeid luua. 
Kolmas etapp oli programmi rohkem kasutajasõbralikumaks muutmine.


Rühmaliikmete panus: Eelmisest rühmatööst tegin Joonas failihalduri klassi, Robert konto klassi. Selles rühmatöös Joonas kasutas failihalduri klassi, et andmed sisse lugeda tekstist. Samuti lõi Joonas igale nupule
vastava algoritmi ja vajadusel erindi. Kokku läks Joonasel töö tegemiseks umbes 6h.
Robert tegeles peamisel graafiliste elementide loomisega, stseenide loomisega, paigutusega, suurustega ja tekstiga. Osade nuppude funktsioonalsuste tegemisel toetas Joonas. 
Kokku läks Robertil töö tegemiseks umbes 6h

Murekohad: Peamine murekoht oli elementide paigutus vastavalt ekraani suuruse muutmisele. Kuna kumbki rühmaliige ei osanud seda probleemi korrigeerida, jäi programmi aken fikseeritud suurusega.
Nii oli kergem elemente stseenides paigutada.


Lõpptulemus: Töö autorid jäid oma tehtuga väga rahule. Midagi katki ei jäänud, kõik nupud töötavad nii nagu peab ja võimalusi on mitmeid, mida kasutaja programmis teha saab.
Arendamist vajab veel kindlasti graafilise poole tegemine, sest see võttis natuke liiga kaua aega. Graafilise liidese tegemise puhul peab paar sammu ette mõtlema, et edaspidi tühja vaeva ei näeks, kui midagi valesti liidese tegemisel valesti läheb.


Testimine: testimine käis samm sammult. Esialgu oli vaja konto isend saada programmi sisse, mida siis lugema hakati. Sisselogimise puhul oli vaja tuvastada õige konto isend.
Kui õigete andmetega konto sai sisse loetud, sai hakata edasi tegema. Funktsioonide välja kutsumine vastavalt nupule ja selle järgi konto andmete muutmine.
Kui oli vaja kontrollida, kas uus konto sai graafilise liidesega loodud, siis sai seda kontrollida eelmise rühmatöö programmiga, mis kõik kontod ette luges.
Põhiliselt oli kogu programmi loomine katse eksitus meetodiga. Käivitasime, kui ei töötanud, parandasime vea ja käivitasime uuesti. Kogu tegevus oli üsna sirgjooneline.
