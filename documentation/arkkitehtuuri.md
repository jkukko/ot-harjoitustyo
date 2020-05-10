<h1>Arkkitehtuurikuvaus</h1>

<h2>Rakenne</h2>

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:

<img src="https://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/kuvat/a-1.png" width="320">

<li>Pakkaus inventoryManagement.ui sisältää JavaFX:llä toteutun käyttöliittymän</li>
<li>Pakkaus inventoryManagement.domain sisältää sovelluslogiikan</li>
<li>Pakkaus inventoryManagement.dao sisältää tietojen tallennus logiikan</li>

<h2>Käyttöliittymä</h2>

Käyttöliittymä koostuu teknisesti kolmesta eri näkymästä: loginScene, newUserScene ja mainScene. Nämä on toteutettu omina Scene-olioina. Näkymät sijoitetaan sovelluksen stageen yksi kerrallaan.

Kuitenkin mainScenen elementit vaihtuvat riippuen käyttäjän valinnoista. Näitä valintoja/"näkymiä" ovat current inventory, incoming order, take from inventory, edit product ja tuotekohtainen tilaushistoria. Näihin liittyviä elementtejä määritellään Menu-Olion ja MenuItem-Olion avulla.
Pääsääntöisesti sovelluslogiikka on pyritty erittämään täysin käyttöliittymästä. Tarkoituksena on, että käyttöliittymä kutsuu erilaisilla parametreilla inventoryService olion metodeja.


<h2>Sovelluslogiikka</h2>

Sovelluksen loogisen datamallin muodostavat Product ja Order. User-luokaa käytetään tällä hetkellä ainoastaan kirjautumiseen. 
Toiminnalisuudesta vastaa InventoryService luokka. Sen metodeja ovat

<li>Order incomingOrder(String name, int amount)</li>
<li>Order OutGoingOrder(String name, int amount)</li>
<li>List<String> getListOfProductNames()</li>
<li>List<Product> getProducts()</li>
<li>int getSafetyStockLimit(String name)</li>
<li>void changeSafetyStock(String name, int amount</li>
<li>Boolean login(String username, String password)</li>
<li>void create(String username, String password)</li>
<li>Boolean checkUsername(String username)</li>
<li>List<Order> getListOfOrderByProductName(String name)</li> 


InventoryService pääsee käsiksi user, product ja order inventoryManagement.dao oleviin luokkiin, millä toteutetaan pitkäaikaistallennus. 
Nämä inventoryManagement.dao olevat luokat toteuttavat ProductDao, UserDao ja orderDao rajapinnat. Luokkien toteutuksen injektoidaan sovelluslogiikalle konstruktorikutsun yhteydessä.

<h2>Tietojen pysyväistallennus</h2>

Pakkauksessa inventoryMangement.dao olevat luokat FileUserDao, FileProductDao ja FileOrderDao toteuttavat tietojen tallentamisesta tiedostoihin.
Luokat noudattavat Data Access Object -suunnittelumallia ja tarvittaessa ne voidaan korvata, vaikka SQL-pohjaisella tallennuksella. Testasin tämän toimivuutta kehitysvaiheessa, kun minulla oli ArrayList-pohjainen tallennus. DAO toiminnallisuus toimi hyvin tässä. Tätä hyödynnetään testauksessa, kun tiedot tallennetaan keskusmuistiin pitkäaikaisen tallennuksen sijasta.

<h3>Tiedostot</h3>

Sovellus tallentaa tiedot eri tiedostoihin: order, product ja user. Nämä tiedostot ovat csv formaattia. Sovelluksen juuressa oleva konfikuraatiotiedosto (config.properties) määrittelee näiden tiedostojen nimet.

User:

`
Test,Test
Test1,Test1
`

Ensimmäisenä käyttäjätunnus (username) ja salasana (password)

Order:

`
0,Product A,2020-05-09,true,10
1,Product A,2020-05-09,false,5
`

Ensimmäisenä id, tuote (product), päivä (date), onko sisääntuleva (isInComing) ja määrä (amount).

Product:

`
Product A,10,35
`

Ensimmäisenä nimi (name), varmuuslimiitti (safetylimit) ja nykyinen varastomäärä (current inventory)

<h2>Päätoiminnallisuudet</h2>
<h3>Käyttäjän kirjautuminen</h3>

Kun kijautumisnäkymässä oleviin syöttökenttiin, 'username' ja 'password', on kirjattu arvot ja klikataan loginButtonia sovellus etenee seuraavista:

<img src="https://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/kuvat/a-2.png" width="480">
