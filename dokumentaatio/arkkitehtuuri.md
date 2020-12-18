# Rakenne

Ohjelma on jäsennelty kolmeen eri pakkaukseen: ui(käyttöjärjestelmä), domain(sovelluslogiikka) ja dao(tiedostojen käsittely).

## Käyttöliittymä

Käyttöliittymä sijaitsee pakkauksessa ui ja on toteutettu JavaFX:llä. Pääluokkasta käynnistettän TriviaAppUi-luokka, joka puolestaan kutsuu ohjelman alkunäkymän sisältävää StartStage-luokkaa. Ohjelmassa on kolme erilasta näkymää, joista jokainen on toteutettu omana Stage-luokkanaan. 

Käyttöliittymä käyttää sovelluslogiikkaa GameServer-olion kautta, jonka Staget saavat parametrina. 

## Sovelluslogiikka

Sovelluslogiikka sijaitsee pakkauksessa domain ja lähes kokonaan luokassa GameServer. GameServer-olio toimii käyttöliittymän ja pysyväistallennuksen välissä ja sen tehtävänä on muokata pysyväistallennuksesta saatavaa dataa käyttöliittymälle sopivaksi ja päin vastoin. Sovelluslogiikka pitää huolen oikean kysymyksen näyttämisestä ja pelaajan pisteistä. 

## Pysyväistallennus
   
Pysyväistallennus sijaitsee pakkauksessa dao. Pakkausessa on kaksi tiedostosta lukemisesta vastaavaa luokkaa, joista toinen vastaa Question- ja toinen Player-olioiden tallentamisesta tiedostoon.

Kysymykset ovat valmiiski tallennettuna tiedostoon muodossa "Kysymys;vaihtoehtoa,vaihtoehtob,vaihtoehtoc,vaihtoehtod;oikeavastaus". Luokka FileQuestionDao lukee tiedoston ja muokkaa ne List< Question> -tyyppiseksi listaksi sovelluslogiikan käytettäväksi.

Pelaajat tallennetaan muodossa "\nNimi;pisteet". Luokka FilePlayerDao vastaa pelaajien tallentamisesta teidostoon. Se myös muokkaa tiedostossa olevat pelaajat List <Player> -tyyppiseksi listaksi.  
 
# Luokka/pakkauskaavio


![luokka/pakkauskaavio](https://github.com/ruuskal/ot-harjoitustyo/blob/master/dokumentaatio/pakkauskaavio.png)

# Sekvenssikaavio

Alla on sekvenniskaavio, jossa kuvataan tilannetta, kun käyttäjä painaa Next-nappia GameStage-näkymässä. Sekvenssi lopetetaan siihen, kun vaihtoehto A on saatu haettu GameStageen -- loput vaihtoehdot haetaan samalla tavalla. Content-muuttujalla viitataan Question-olion kysymys-osaan. 
![sekvenssikaavio](https://github.com/ruuskal/ot-harjoitustyo/blob/master/dokumentaatio/sekvenssi2.png)
