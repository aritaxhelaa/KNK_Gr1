
<p align="center">
  <a>
    <img src="@../Images/KosovoLogo.png" alt="Logo" height="40">
  </a>

<h3 align="center">Sistemi për menaxhimin e adresave dhe vendbanimeve - KNK</h3>
</p><br>

## Përmbledhje e Projektit

Ky projekt është një sistem gjithëpërfshirës i projektuar për të menaxhuar dhe lehtësuar procesin e regjistrimit, kërkimit dhe administrimit të adresave dhe vendbanimeve për qytetarët. Ai përfshin role të ndryshme si Zyrtari Komunal dhe Qytetari, secili me funksionalitete të dedikuara. Sistemi është ndërtuar duke përdorur Java dhe JavaFX dhe përfshin disa kontrollues dhe FXML files për të trajtuar ndërfaqen grafike të përdoruesit.

## Struktura e Projektit

Struktura e projektit është e organizuar si më poshtë:

- **src/main/java/Application**: Përmban klasat fillestare të aplikacionit.
  - `Main.java`: Inicializon dhe nis aplikacionin.
  - `Main1.java`: Version alternativ i nisjes.
  - `MainTest.java`: Përdoret për testim lokal.

- **src/main/java/Controllers**: Përmban kontrolluesit për funksionalitete të ndryshme.
  - `AdresaController.java`
  - `BaseController.java`
  - `HomeQytetarController.java`
  - `KerkimetEFunditController.java`
  - `KerkoAdreseController.java`
  - `KerkoInfoController.java`
  - `ListaVendbanimeveController.java`
  - `LogInController.java`
  - `MenaxhoPerdoruesitController.java`
  - `MenaxhoVendbaniminController.java`
  - `RegisterController.java`
  - `RegjistroVendbanimController.java`
  - `StatistikaKombetareController.java`

- **src/main/java/Database**: Përmban komponentët për lidhje me bazën e të dhënave.
  - `DBConnector.java`
  - `TestConnection.java`
  - `tabelat.sql`: Skema e bazës së të dhënave

- **src/main/java/Test**: Përmban klasat e testimit.
  - `AdresaRepositoryTest.java`
  - `AdresaServiceTest.java`
  - `KodiPostarRepositoryTest.java`
  - `KodiPostarServiceTest.java`
  - `KomunaRepositoryTest.java`
  - `KomunaServiceTest.java`
  - `LagjjaRepositoryTest.java`

## Karakteristikat dhe Funksionaliteti

### Faqja e Zyrtarit Komunal

Zyrtari Komunal ka akses në funksionalitete të ndryshme përfshirë:

- **Menaxhimi i Vendbanimeve**: Shtimi, përditësimi dhe fshirja e vendbanimeve ekzistuese.
- **Menaxhimi i Përdoruesve**: Krijimi dhe administrimi i llogarive të përdoruesve.
- **Statistika Kombëtare**: Gjenerimi i statistikave mbi vendbanimet në bazë të të dhënave.

### Faqja e Qytetarit

Qytetari ka akses për të:

- **Kërkuar Adresa**: Kërkim i avancuar sipas komunës, lagjes apo rrugës.
- **Shikuar Informacione**: Vizualizim i detajeve të adresave pa të drejtë modifikimi.
- **Shfletuar Historikun e Kërkimeve**: Akses në kërkimet e fundit të realizuara.

### Pjesa e Kontrollit/Controllers

Projekti përfshin disa pamje të kontrolluesve për role të ndryshme si:

- `KerkoAdreseController.java`
- `MenaxhoVendbaniminController.java`
- `StatistikaKombetareController.java`
- `ListaVendbanimeveController.java`
- `RegisterController.java`

## Skema e Bazës së të Dhënave

Struktura e tabelave përfshihet në skedarin [`tabelat.sql`](src/main/java/Database/tabelat.sql) dhe përmban tabelat kryesore për:
- përdoruesit
- vendbanimet
- komunat
- lagjet
- adresat

## Përfundimi

### Tools të nevojshëm

- Java Development Kit (JDK) 17 ose më i lartë
- JavaFX SDK
- PostgreSQL Server
- Scene Builder
- IDE si IntelliJ IDEA

### Instalimi

1. Krijoni një databazë në MySQL dhe ekzekutoni `tabelat.sql`.
2. Vendosni të dhënat e lidhjes në `DBConnector.java`.
3. Hapeni projektin në IntelliJ dhe ekzekutoni `Main.java`.

### Punuan:

- Anita Ahmeti
- Arita Xhela
- Alba Nimani
- Erion Mehmeti

<a href="#top">Shko në fillim ↑</a>
