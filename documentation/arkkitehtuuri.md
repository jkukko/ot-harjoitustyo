<h1>Arkkitehtuurikuvaus</h1>

<h2>Rakenne</h2>

Ohjelman rakenne noudattelee kolmitasoista kerrosarkkitehtuuria, ja koodin pakkausrakenne on seuraava:

<img src="https://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/kuvat/a-1.png" width="160">

<li>Pakkaus inventoryManagement.ui sisältää JavaFX:llä toteutun käyttöliittymän</li>
<li>Pakkaus inventoryManagement.domain sisältää sovelluslogiikan</li>
<li>Pakkaus inventoryManagement.dao sisältää tietojen tallennus logiikan</li>

<h2>Päätoiminnallisuudet</h2>
<h3>Käyttäjän kirjautuminen</h3>

Kun kijautumisnäkymässä oleviin syöttökenttiin, 'username' ja 'password', on kirjattu arvot ja klikataan loginButtonia sovellus etenee seuraavista:

<img src="https://github.com/jkukko/ot-harjoitustyo/blob/master/documentation/kuvat/a-2.png" width="160">
