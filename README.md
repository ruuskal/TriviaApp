
# TriviaApp

TriviaApp on peli, jossa pelaajalta kysytään triviatietoutta mittaavaa kysymyssarja. Jokaiseen kysymykseen annetaan vastausvaihtoehdot ja oikea vastaus tuo pelaajalle pisteitä.

## Dokumentaatio
[Käyttöohje](https://github.com/ruuskal/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Työaikakirjanpito](https://github.com/ruuskal/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[Vaatimusmäärittely](https://github.com/ruuskal/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/ruuskal/ot-harjoitustyo/tree/master/dokumentaatio/arkkitehtuuri.md)
## Testaus

Sovelluksen testit suoritetaan komentorivillä komennollaa **mvn test** ja testikattavuusraportti
luodaan komennolla **mvn jacoco:report** ja sitä voidaan tarkastella avaamalla selaimella tiedosto *target/site/jacoco/index.html*.

## Checkstyle

Tiedostoon checkstyle.xml määrittelemät tarkastukset voidaan suorittaa komennolla 
**mvn jxr:jxr checkstyle:checkstyle** ja sitä voidaan tarkastella avaamalla selaimella tiedosto *target/site/checkstyle.html*.

## Jarin generointi

Suoritettavan jar-tiedoston voi generoida komennolla **mvn package**, jolloin sen nimeksi tulee *TriviaApp-1.0-SNAPSHOT.jar*.

## JacaDoc

Sovellukselle voidaan generoida JavaDoc komennolla **mvn javadoc:javadoc** ja sitä voi tarkastella avaamalla selaimella tiedosto *target/site/apidocs/index.html*.

## Releset
[Viikko 6](https://github.com/ruuskal/ot-harjoitustyo/releases/tag/viikko6)
