<h1>Testausdokumentti</h1>
Ohjelmaa testataan sekä yksikkö- ja integraatiotestein. Tämän lisäksi järjestelmätestausta on tehty manuaalisesti.

<h2>Yksikkö- ja integraatiotestaus</h2>
Testauksen pääpaino on sovelluksen logiikassa. Tämä tapahtuu testaamalla inventoryManagement.domain luokkia, ja varsinkin InventoryService-luokkaa, mikä sisältää sovelluksen logiikan.

Integraatiotestausta varten on luotu FakeOrderDao, FakeProductDao ja FakeUserDao, jotta voisimme testata InventorySerivice-luokkaa. Näiden avulla luodaan keskusmuistitoteutettu pysyväistallennus.

Sovelluslogiikkakerroksen luokille User, Product ja Order on tehty muutama yksikkötesti.

<h2>Dao Luokat</h2>

<h2>Testauskattavuus</h2>

<h2>Järjestelmätestaus</h2>
Sovelluksen järjestelmä testaus on suoritettu manuaalisesti.

<h2>Asennus ja konfigurointi</h2>
Sovellusta on ladattu ja testattu käyttöohjeen mukaisesti Linux- ja Windows-ympäristössä. 

Soveluksen vaativien config tietojen olemassa oloa on testattu kahdella tavalla. Yksi, niin että ne on jo olemassa. Kaksi, niin että ohjelma luo ne, koska niitä ei ole ollut olemassa.

<h2>Sovellukseen jääneet laatuongelmat</h2>
Aikapaineiden takia tarpeellinen error-handling on jäänyt vähäiselle tarkastelulle.
