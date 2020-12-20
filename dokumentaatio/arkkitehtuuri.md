# Rakenne

Ohjelma noudattelee kerrosarkkitehtuuria ja on jäsennelty kolmeen eri pakkaukseen:
*  ui (käyttöjärjestelmä)
*  domain (sovelluslogiikka) 
*  dao (tiedostojen käsittely)

## Käyttöliittymä

Käyttöliittymä sijaitsee pakkauksessa ui ja on toteutettu JavaFX:llä. Se on pyritty eriyttämään sovelluslogiikasta. Ui sisältää kolme stage-luokkaa, joiden avulla näytetään sovelluksen eri osat:

* StartStage
	* Näyttää sovelluksen alkunäkymän, eli napit tulostaulun tarkastelemiseen, pelin aloittamiseen ja sovelluksen sulkemiseen.
* ScoreStage
	* Avaa erillisen ikkunan pistetilaston tutkailua varten. ScoreStage-näkymän sulkeminen ei sammuta ohjelmaa, toisin kuin muiden stage-olioiden sulkeminen.  
* GameStage
	* Sisältää pelinäkymän. Tulee näkyville, kun käyttäjä painaa Start-nappia alkunäkymässä.
* EndStage  
	* Sisältää loppunäkymän. Loppunäkymässä  näytetään käyttäjän ansaitsemat pisteet ja annetaan käyttäjälle mahdollisuus tallentaa oma nimensä pistetilastoon. 

Käyttöliittymä käyttää sovelluslogiikkaa GameServer-olion kautta, jonka Staget saavat parametrina. 

## Sovelluslogiikka

Sovelluslogiikka sijaitsee pakkauksessa domain ja lähes kokonaan luokassa GameServer. GameServer-olio toimii käyttöliittymän ja pysyväistallennuksen välissä ja sen tehtävänä on muokata pysyväistallennuksesta saatavaa dataa käyttöliittymälle sopivaksi ja päin vastoin. Sovelluslogiikka pitää myös huolen oikean kysymyksen näyttämisestä ja pelaajan pisteistä. 

## Pysyväistallennus
   
Pysyväistallennus sijaitsee pakkauksessa dao. Pakkausessa on kaksi tiedostosta lukemisesta vastaavaa luokkaa, joista toinen vastaa Question- ja toinen Player-olioiden tallentamisesta.

Kysymykset ovat valmiiski tallennettuna tiedostoon muodossa "Kysymys;vaihtoehtoa,vaihtoehtob,vaihtoehtoc;oikeavastaus". Vaihtoehtojen määrää ei ole rajattu. Luokka FileQuestionDao lukee tiedoston ja muokkaa ne List <Question> -tyyppiseksi listaksi sovelluslogiikan käytettäväksi.

Pelaajat tallennetaan muodossa "\nNimi;pisteet". Luokka FilePlayerDao vastaa pelaajien tallentamisesta teidostoon. Se myös muokkaa tiedostossa olevat pelaajat List <Player> -tyyppiseksi listaksi.  
 
# Luokka/pakkauskaavio

![luokka/pakkauskaavio](https://github.com/ruuskal/ot-harjoitustyo/blob/master/dokumentaatio/lpkaavio.png)

# Toimintalogiikka

Sovelluksen avaaminen luo uuden GameService-olennon ja StartStage-näkymän. Kun käyttäjä painaa start-nappia, luodaan uusi GameStage-näkymä, joka saa parametrinä edellä luodun GameService-olennon. GameServicen tarkoitus on hallinnoida pelin toimintaa, jota kuvataan hieman tarkemmin seuraavissa kaavioissa.

Alla oleva sekvenssikaavio alkaa tilanteesta, jossa käyttäjälle näytetään GameStage, jolla on esitettynä kysymys ja vastasvaihtoehdot. Käyttäjä painaa nappia, johon on asetettu tekstiksi oikea vastausvaihtoehto. GameService tarkistaa, onko kysymykseen jo vastattu ja vertaa sitten käyttäjän vastausta oikeaan vaihtoehtoon. Tässä tapauksessa vaihtoehto oli oikea, joten GameService lisää privaattiin playersPoints muuttujaansa 10 pistettä.
![sekvenssikaavio](https://github.com/ruuskal/ot-harjoitustyo/blob/master/dokumentaatio/SekvenssiOikeaVastaus.png)


Alla on sekvenniskaavio, jossa kuvataan tilannetta, kun käyttäjä painaa Next-nappia GameStage-näkymässä. Sekvenssi lopetetaan siihen, kun ensimmäinen vaihtoehto on saatu haettu GameStageen -- loput vaihtoehdot haetaan samalla tavalla. Content-muuttujalla viitataan Question-olion kysymys-osaan. 
![sekvenssikaavio](https://github.com/ruuskal/ot-harjoitustyo/blob/master/dokumentaatio/sekvenssiNext.png)

Jos edellisessä tilanteessa kysymyksiä ei oliskaan ollut enempää, siirryttäisiin GameStage-näkymästä uuteen EndStage-näkymään, joka niin ikään saa parametrinään GameService-olennon. EndStage kysyy käyttäjältä nimeä ja antaa sen eteenpäin GameServicen kautta PlayerDao-oliolle, joka huolehtii nimen (ja pisteiden, joista GameService on pitänyt kirjaa pelin ajan)  lisäämisetä pelaajien listaan. PlayerDao myös palauttaa listan kaikista pelaajista, jonka GameService järjestää halutulla tavalla.
![sekvenssikaavio](https://github.com/ruuskal/ot-harjoitustyo/blob/master/dokumentaatio/SekvenssiEndStage.png)



# Muita huomioita

Soellus on tehty vain yhtä kysymyssarjaa varten ja on siten hyvin pieni. Eräs harmillinen ominaisuus on se, että kysymyssarjaan ei voi vastata kahta kertaa peräkkäin, vaan sovellus täytyy sulkea välissä. Tämä ongelma voitaisiin korjata luomalla GameService-olio vasta GameStage-näkymään siirryttäessä tai sitten luomalla erikseen Player-olio, joka vastaa pisteiden kirjaamisesta. 
 
Useampien kysymyssarjojen esittäminen vaatisi muutoksia sovelluksen konfiguraatioon. Melko helppoa puolestaan olisi lisätä muita ominaisuuksia, kuten erilaisia ehtoja pisteiden laskua varten (ajastin tai miinuksia vääristä vastauksista) tai oljenkorsitoiminnallisuus. Tulostilastointi voitaisiin myös toteuttaa erimerkiksi siten, että vain kymmenen parasta pelaajaa saavat nimensä listalle.  

Dao-pakkaukseen päätettiin toteuttaa erikseen PlayerDao- ja QuestionDao-rajapinnat ja nämä toteuttavat FilePlayerDao ja FileQuestionDao -luokat lähinnä testausta varten. Valitettavasti dao-luokan testaukset jäivät lopullisesta sovelluksesta puuttumaan, joten tämä toteutus jäi hieman turhaksi. 
 
Kaikkiaan sovellus on jäänyt toiminnallisuuksiltaan jokseenkin suppeaksi, ja jos sitä haluttaisiin kehittää eteenpäin, olisi syytä miettiä muita ratkaisuja kysymyssarjojen pysyväistallennuksesta hakemiseen ja pelaajan pisteiden kirjaamiseen. 
