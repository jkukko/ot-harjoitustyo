<h1>Ohjelmistotekniikka, kevät 2020</h1>


<h2>Tehtävät</h2>
<Strong>Viikko1</Strong>

[gitlog.txt](http://github.com/jkukko/ot-harjoitustyo/blob/master/laskarit/viikko1/gitlog.txt)

[komentorivi.txt](https://github.com/jkukko/ot-harjoitustyo/blob/master/laskarit/viikko1/komentorivi.txt)


<h2>InventoryManagement</h2>
Sovelluksen avulla käyttäjillä on mahdollista pitää kirjaa varastotilanteesta. Sovelluksessa on mahdollista kirjata varastoon tavaraa. Tämän lisäksi käyttäjä voi ottaa varastosta tuotteita. Varastotilanteen tilanteen ylläpitämiseksi varastossa voi lisätä tuotteille varmuuslimittejä, ja siten nähdä mitkä tuotteet ovat loppumassa. Tämän lisäksi ohjelmasta voi tutkia tuotteet varastohistoriaa.

<h2>Dokumentaatio</h2>

[Vaatimusmäärittely](http://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/vaatimusm%C3%A4%C3%A4rittely.md)

[Työaikakirjanpito](http://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/Ty%C3%B6aikakirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/arkkitehtuuri.md)

[Käyttöohje](https://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/K%C3%A4ytt%C3%B6ohje.md)

[Testausdokumentti](https://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/testaus.md)

<h2>Releaset</h2>

[Viikko 5](https://github.com/jkukko/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/jkukko/ot-harjoitustyo/releases/tag/Viikko6)

<h2>Komentorivitoiminnot</h2>

<h3>Testaus</h3>

Testit suoritetaan komennolla

`mvn test`

Testikattavuusraportti luodaan komennolla

`mvn jacoco:report`

<h3>Suoritettavan jarin generointi</h3>

`mvn package`

<h3>Koodi projektin suorittamiseen</h3>

`mvn compile exec:java -Dexec.mainClass=inventoryManagement.Main`

<h3>Checkstyle</h3>

`mvn jxr:jxr checkstyle:checkstyle`

<h3>JavaDoc</h3>

JavaDoc luodaan komennolla

`mvn javadoc:javadoc`
