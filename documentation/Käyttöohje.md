<h1>Käyttöohje</h1>

<h2>Konfigurointi</h2>

Ohjelma olettaa, että käyttäjällä löytyy käynnistyshakemistosta konfiguraatiotiedosto _congig.properties_. Tässä filessä määritellään pysyväistallennukseen tarvittavien tiedostojen nimet.

```
userFile=users.csv
orderFile=orders.csv
productFile=products.csv

```
Jos näitä tiedostoja ei ole, nämä luodaan automaattisesti ensimmäisellä käynnistyskerralla.
<h2>Ohjelman käynnistäminen</h2>

Ohjelma käynnistyy komennolla

`java -jar InventoryManagement.jar`

<h2>Kirjautuminen</h2>

Sovellus käynnistää kirjautumisnäkymän.

<img src="https://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/kuvat/k-1.png" width="480">

Kirjautuminen onnistuu kirjoittamalla olemassa oleva käyttäjätunnus ja salasana syöttökenttään ja painamalla login. Jos tälläistä ei ole, käyttäjän pitää painaa "create new user"

<h2>Uuden käyttäjän luominen</h2>

Sovellus käynnistää uuden käyttäjän luomisnäkymän.

<img src="https://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/kuvat/k-2.png" width="480">

Uuden käyttäjän luominen onnistuu syöttämällä käyttäjätunnus ja salasana ja painamalla save buttonia

<h2>Tilauksen kirjaaminen</h2>

Valitse option valikosta "Incoming order". Tämän jälkeen voitko valita nykyisen tuotteen, tai kirjoittaa uuden tuote nimen. Tämän lisäksi syötetään haluttu määrä. Kun nämä on syötetty voidaan painaa "save", mikä tallentaa tilauksent.

<img src="https://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/kuvat/k-5.png" width=400>

<h2>Nykyisen varastotilanteen tarkastaminen</h2>

Valitse option valikosta "Current inventory". Tämä tuo esiin taulun, mistä näkyy nykyisen varastotilanteen. Taulussa on tuotteen nimi, määrä, varmuusvarastonlimitti, sekä nykyisen määrän ja  varastoarvon erostus.

<img src="https://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/kuvat/k-4.png" width=400>


<h2>Varastosta ottaminen</h2>

Valitse option valikosta "Take from Inventory". Toimii melko samalla periaatteella kuin varstoon lisääminen (tilauksen kirjaaminen). Erona on että ainoastaan varastossa olevia tuotteita voi valita. Valitse tuote alasvetovalikosta ja kirjoita haluttu määrä kentään. Tämän jälkeen paina save-buttonia.

<img src="https://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/kuvat/k-6.png" width=400>

<h2>Tuotteen varmuuslimiitin vaihtaminen</h2>

Valitse option valikosta "Edit Product". Valitse alasvetovalikosta haluttu tuote. Tämän jälkeen ohjelma tuo syöttökenttään tuotteen nykyisen varmuuslimitin. Siihen voi kirjata uuden halutun, ja tämän jälkeen painaa save-buttonia. Jos haluaa tarkistaa, että limitti vaihtui, voi tämän tarkistaa "Current Inventory" näkymästä.

<img src="https://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/kuvat/k-7.png" width=400>

<h2>Tuotteen varastohistorian tarkastaminen</h2>

Valitse history viewn alta haluttu tuote. Huom, tämä vaatii sen, että edes yksi tuote on lisätty varastoon. Tämän jälkeen käyttäjä näkee kyseisen tuotteen tilaushistorian.

<img src="https://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/kuvat/k-8.png" width=400>

<h2>Ohjelman sulkeminen</h2>

Käyttäjä voi sulkea ohjelman kahdella tavalla. Joko sulkemalla punaisesta ruksista oikeasta yläkulmasta tai option valikon alta olevasta "Close Inventory Management" valinnasta.
