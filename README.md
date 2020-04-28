<h1>Ohjelmistotekniikka, kevät 2020</h1>

<h2>Tehtävät</h2>
<Strong>Viikko1</Strong>

[gitlog.txt](http://github.com/jkukko/ot-harjoitustyo/blob/master/laskarit/viikko1/gitlog.txt)

[komentorivi.txt](https://github.com/jkukko/ot-harjoitustyo/blob/master/laskarit/viikko1/komentorivi.txt)

<h2>Dokumentaatio</h2>

[Vaatimusmäärittely](http://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/vaatimusm%C3%A4%C3%A4rittely.md)

[Työaikakirjanpito](http://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/Ty%C3%B6aikakirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/arkkitehtuuri.md)

<h2>Releaset</h2>

[Viikko 5](https://github.com/jkukko/ot-harjoitustyo/releases/tag/viikko5)

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
