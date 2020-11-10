-- MySQL dump 10.13  Distrib 8.0.22, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: Raccuglia
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Ordine`
--

DROP TABLE IF EXISTS `Ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Ordine` (
  `idOrdine` int NOT NULL AUTO_INCREMENT,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `totale` decimal(6,2) NOT NULL,
  `preparato` bit(1) NOT NULL,
  `ritirato` bit(1) NOT NULL,
  `pagato` bit(1) NOT NULL,
  `Utente_idUtente` int NOT NULL,
  PRIMARY KEY (`idOrdine`),
  UNIQUE KEY `idOrdine_UNIQUE` (`idOrdine`),
  KEY `fk_Ordine_Utente_idx` (`Utente_idUtente`),
  CONSTRAINT `fk_Ordine_Utente` FOREIGN KEY (`Utente_idUtente`) REFERENCES `Utente` (`idUtente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ordine`
--

LOCK TABLES `Ordine` WRITE;
/*!40000 ALTER TABLE `Ordine` DISABLE KEYS */;
INSERT INTO `Ordine` VALUES (1,'2020-11-10 09:44:19',6.90,_binary '',_binary '',_binary '',5),(2,'2020-11-10 09:45:18',13.00,_binary '',_binary '',_binary '',5),(3,'2020-11-10 09:46:39',72.00,_binary '',_binary '',_binary '',5),(4,'2020-11-10 09:47:38',10.50,_binary '',_binary '',_binary '',5);
/*!40000 ALTER TABLE `Ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ordine_has_Prodotto`
--

DROP TABLE IF EXISTS `Ordine_has_Prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Ordine_has_Prodotto` (
  `Ordine_idOrdine` int NOT NULL,
  `Prodotto_idProdotto` int NOT NULL,
  `quantità` int NOT NULL,
  PRIMARY KEY (`Ordine_idOrdine`,`Prodotto_idProdotto`),
  KEY `fk_Ordine_has_Prodotto_Prodotto1_idx` (`Prodotto_idProdotto`),
  KEY `fk_Ordine_has_Prodotto_Ordine1_idx` (`Ordine_idOrdine`),
  CONSTRAINT `fk_Ordine_has_Prodotto_Ordine1` FOREIGN KEY (`Ordine_idOrdine`) REFERENCES `Ordine` (`idOrdine`),
  CONSTRAINT `fk_Ordine_has_Prodotto_Prodotto1` FOREIGN KEY (`Prodotto_idProdotto`) REFERENCES `Prodotto` (`idProdotto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ordine_has_Prodotto`
--

LOCK TABLES `Ordine_has_Prodotto` WRITE;
/*!40000 ALTER TABLE `Ordine_has_Prodotto` DISABLE KEYS */;
INSERT INTO `Ordine_has_Prodotto` VALUES (1,1,2),(1,56,1),(1,59,1),(1,60,1),(2,2,1),(2,7,1),(2,61,1),(2,115,2),(3,26,1),(3,32,1),(3,33,1),(3,92,1),(4,51,1),(4,106,1);
/*!40000 ALTER TABLE `Ordine_has_Prodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Postazione`
--

DROP TABLE IF EXISTS `Postazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Postazione` (
  `idPostazione` int NOT NULL AUTO_INCREMENT,
  `prezzo` decimal(6,2) NOT NULL,
  PRIMARY KEY (`idPostazione`),
  UNIQUE KEY `idPostazione_UNIQUE` (`idPostazione`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Postazione`
--

LOCK TABLES `Postazione` WRITE;
/*!40000 ALTER TABLE `Postazione` DISABLE KEYS */;
INSERT INTO `Postazione` VALUES (1,17.00),(2,17.00),(3,17.00),(4,17.00),(5,17.00),(6,17.00),(7,17.00),(8,17.00),(9,17.00),(10,17.00),(11,15.00),(12,15.00),(13,15.00),(14,15.00),(15,15.00),(16,15.00),(17,15.00),(18,15.00),(19,15.00),(20,15.00),(21,12.00),(22,12.00),(23,12.00),(24,12.00),(25,12.00),(26,12.00),(27,12.00),(28,12.00),(29,12.00),(30,12.00),(31,10.00),(32,10.00),(33,10.00),(34,10.00),(35,10.00),(36,10.00),(37,10.00),(38,10.00),(39,10.00),(40,10.00),(41,10.00),(42,10.00),(43,10.00),(44,10.00),(45,10.00),(46,10.00),(47,10.00),(48,10.00),(49,10.00),(50,10.00);
/*!40000 ALTER TABLE `Postazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Prenotazione`
--

DROP TABLE IF EXISTS `Prenotazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Prenotazione` (
  `idPrenotazione` int NOT NULL AUTO_INCREMENT,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dataPrenotazione` date NOT NULL,
  `totale` decimal(6,2) NOT NULL,
  `pagato` bit(1) NOT NULL,
  `rimborsato` bit(1) NOT NULL,
  `Utente_idUtente` int NOT NULL,
  PRIMARY KEY (`idPrenotazione`),
  UNIQUE KEY `idPrenotazione_UNIQUE` (`idPrenotazione`),
  KEY `fk_Prenotazione_Utente1_idx` (`Utente_idUtente`),
  CONSTRAINT `fk_Prenotazione_Utente1` FOREIGN KEY (`Utente_idUtente`) REFERENCES `Utente` (`idUtente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Prenotazione`
--

LOCK TABLES `Prenotazione` WRITE;
/*!40000 ALTER TABLE `Prenotazione` DISABLE KEYS */;
INSERT INTO `Prenotazione` VALUES (1,'2020-11-09 21:18:01','2020-11-12',34.00,_binary '',_binary '',5),(2,'2020-11-09 21:18:37','2020-11-13',54.00,_binary '',_binary '\0',5),(3,'2020-11-10 09:48:25','2020-11-11',48.00,_binary '',_binary '\0',5),(4,'2020-11-10 09:48:46','2020-11-14',34.00,_binary '',_binary '\0',5);
/*!40000 ALTER TABLE `Prenotazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Prenotazione_has_Postazione`
--

DROP TABLE IF EXISTS `Prenotazione_has_Postazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Prenotazione_has_Postazione` (
  `Prenotazione_idPrenotazione` int NOT NULL,
  `Postazione_idPostazione` int NOT NULL,
  PRIMARY KEY (`Prenotazione_idPrenotazione`,`Postazione_idPostazione`),
  KEY `fk_Prenotazione_has_Postazione_Postazione1_idx` (`Postazione_idPostazione`),
  KEY `fk_Prenotazione_has_Postazione_Prenotazione1_idx` (`Prenotazione_idPrenotazione`),
  CONSTRAINT `fk_Prenotazione_has_Postazione_Postazione1` FOREIGN KEY (`Postazione_idPostazione`) REFERENCES `Postazione` (`idPostazione`),
  CONSTRAINT `fk_Prenotazione_has_Postazione_Prenotazione1` FOREIGN KEY (`Prenotazione_idPrenotazione`) REFERENCES `Prenotazione` (`idPrenotazione`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Prenotazione_has_Postazione`
--

LOCK TABLES `Prenotazione_has_Postazione` WRITE;
/*!40000 ALTER TABLE `Prenotazione_has_Postazione` DISABLE KEYS */;
INSERT INTO `Prenotazione_has_Postazione` VALUES (1,5),(4,5),(1,6),(4,6),(2,12),(2,13),(2,22),(2,23),(3,24),(3,25),(3,26),(3,27);
/*!40000 ALTER TABLE `Prenotazione_has_Postazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Prodotto`
--

DROP TABLE IF EXISTS `Prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Prodotto` (
  `idProdotto` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `descrizione` varchar(300) NOT NULL,
  `prezzo` decimal(6,2) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  PRIMARY KEY (`idProdotto`),
  UNIQUE KEY `idProdotto_UNIQUE` (`idProdotto`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Prodotto`
--

LOCK TABLES `Prodotto` WRITE;
/*!40000 ALTER TABLE `Prodotto` DISABLE KEYS */;
INSERT INTO `Prodotto` VALUES (1,'Croissant vari gusti','pan brioche, confettura di albicocca o crema pasticciera o crema al pistacchio o crema alle nocciole.',1.20,'Colazione'),(2,'Caprese','pomodoro, mozzarella, olio extra vergine di oliva, basilico, origano, pane a lievitazione naturale.',4.00,'Panini'),(3,'Toast','pancarré, prosciutto cotto, sottilette.',2.00,'Panini'),(4,'Hot dog','wurstel di maiale, pane al latte, ketchup, maionese, patate fritte.',4.00,'Panini'),(5,'Delicato','lattuga, pane a lievitazione naturale, prosciutto cotto, provola.',4.00,'Panini'),(6,'Sandwich','pancarré, lattuga, maionese, prosciutto cotto, sottilette, tonno in salamoia sgocciolato.',3.00,'Panini'),(7,'Delizioso al tonno','mozzarella, pane a lievitazione naturale, pomodoro cuore di bue, tonno in salamoia sgocciolato, olio di semi di girasole.',4.00,'Panini'),(8,'Vegano alle verdure','melanzane, peperoni, olio extra vergine di oliva, olive nere, pane a lievitazione naturale, pomodoro cuore di bue, zucchine grigliate.',4.00,'Panini'),(9,'Goloso al prosciutto crudo','pane a lievitazione naturale, pomodoro cuore di bue, prosciutto crudo, provola.',4.00,'Panini'),(10,'Calzone al cotto','mozzarella, calzone, olio di semi di girasole, prosciutto cotto.',2.50,'Rustici'),(11,'Focaccia vari gusti','focaccia, acciughe sott\'olio, indivia, melanzane, olio di semi di girasole, parmigiano reggiano DOP, provola, salsa di pomodoro, ricotta di pecora.',2.00,'Rustici'),(12,'Spicchi di patate dorate','patate, olio di semi di girasole, sale.',3.00,'Rustici'),(13,'Calzone messinese','mozzarella, calzone, acciughe sott\'olio, indivia, pomodorini, olio di semi di girasole.',2.50,'Rustici'),(14,'Mista','carote, lattuga, pomodoro cuore di bue, radicchio rosso.',4.00,'Insalatone'),(15,'Estiva','lattuga, mozzarella, olio extra vergine di oliva, pomodoro cuore di bue, uova di gallina.',6.00,'Insalatone'),(16,'Salina','lattuga, mais, olio extra vergine di oliva, olio di semi di girasole, pomodorini datterini, tonno in salamoia sgocciolato.',6.00,'Insalatone'),(17,'Norvegese','lattuga, olio extra vergine di oliva, olive nere, radicchio rosso, salmone affumicato.',7.00,'Insalatone'),(18,'Mediterranea','mozzarella, olio extra vergine di oliva, olive nere, pomodoro cuore di bue, radicchio rosso.',5.00,'Insalatone'),(19,'I semifreddi','frutta a guscio (allergene), semifreddo, biscotto, fave di cacao, pan di spagna.',4.00,'Frutta e Dessert'),(20,'Tartufo gelato','tartufo bianco, tartufo nero, gelato, caffé.',2.50,'Frutta e Dessert'),(21,'Brioche siciliana','pan brioche.',0.80,'Frutta e Dessert'),(22,'Granita vari gusti','ghiaccio, cacao, chicci di caffé, destrosio, fragole, frutta a guscio (allergene), frutti di bosco, limone, melone d\'estate, panna montata, pesca, zucchero.',1.80,'Frutta e Dessert'),(23,'Frutta fresca di stagione','ananas, pere, melone, mela, uva, fragole, melone d\'estate, pesca.',5.00,'Frutta e Dessert'),(24,'Gran coppa gelato belvedere','gelato, biscotto, cioccolato, frutta a guscio (allergene), panna montata.',6.00,'Frutta e Dessert'),(25,'Sfizi fritti di mare el giorno','olio extra vergine di oliva, semola di grano duro, farina di grano tenero, sale, variabile, olio di arachidi.',10.00,'Antipasti Di Mare'),(26,'Degustazione di antipasti belvedere','racchiude tutti glli antipasti sopra elencati più degli assaggini del giorno che possono essere proposti dal cameriere.',20.00,'Antipasti Di Mare'),(27,'Insalatina di mare al vapore ai profumi mediterranei','aglio, olio extra vergine di oliva, vongole, calamari, cozze, prezzemolo, gamberi, limone, polpo.',12.00,'Antipasti Di Mare'),(28,'Crudo di gamberi di mazzara, salsa di soia e frutta fresca di stagione','olio extra vergine di oliva, soia, ananas, pere, melone, mela, uva, gamberi, sale, salsa di soia.',14.00,'Antipasti Di Mare'),(29,'Crudo di parma e mozzarella di bufala campana','prosciutto crudo, mozzarella di bufala.',10.00,'Antipasti Di Terra'),(30,'Misto di salumi, formaggi, confetture e conserve del territorio','pomodoro, formaggio stagionato, carne di maiale, olive verdi, olive nere, salame di maiale, prosciutto crudo, ricotta, pancetta, provola, pomodori secchi.',14.00,'Antipasti Di Terra'),(31,'Busiate alla belvedere','olio extra vergine di oliva, basilico, farina di grano tenero, sale, olio di semi di girasole, capperi, semi di girasole, melanzane, mentuccia, uva passa, pesce spada, pinoli, pomodorini, pasta fresca all\'uovo.',12.00,'Primi Di Mare'),(32,'Scialatielli all\'astice','pomodoro, aglio, olio extra vergine di oliva, astice, prezzemolo, olio di oliva, pasta fresca all\'uovo.',18.00,'Primi Di Mare'),(33,'Couscous di pesce, crostacei e verdure','pomodoro, aglio, olio extra vergine di oliva, vongole, basilico, semola di grano duro, calamari, cous cous, cozze, gamberi, carote, melanzane, peperoni, zucchine, pesce (allergene).',14.00,'Primi Di Mare'),(34,'Risotto mantecato al modo del pescatore','pomodoro, aglio, olio extra vergine di oliva, riso bianco, vongole, calamari, cozze, prezzemolo, gamberi.',12.00,'Primi Di Mare'),(35,'Linguine trafilate al bronzo allo scoglio','pomodoro, pasta, aglio, olio extra vergine di oliva, cannolicchio, vongole, semola di grano duro, calamari, totani, cozze, prezzemolo, fasolari, grano, gamberi, scampi.',16.00,'Primi Di Mare'),(36,'Spaghettoni alla chitarra, vongole veraci e bottarga','pasta, aglio, olio extra vergine di oliva, vongole, semola di grano duro, prezzemolo, uova di pesce spada.',14.00,'Primi Di Mare'),(37,'Gnocchi alla sorrentina','pomodoro, olio extra vergine di oliva, uova di gallina, basilico, mozzarella, gnocchi di patate.',10.00,'Primi Di Terra'),(38,'Maccheroni tipici alla norma','pomodoro, olio extra vergine di oliva, basilico, sale, olio di semi di girasole, semi di girasole, ricotta, melanzane, pasta fresca all\'uovo.',10.00,'Primi Di Terra'),(39,'Bavette integrali al pesto di pistacchi di bronte','aglio, olio extra vergine di oliva, basilico, parmigiano reggiano DOP, pistacchi, pasta integrale.',10.00,'Primi Di Terra'),(40,'Filetto di pesce del giorno all\'eoliana','pomodoro, sedano, olio extra vergine di oliva, olive verdi, pane, basilico, capperi, uva passa, tonno, salmone, branzino, orata, pinoli, variabile.',16.00,'Secondi Di Mare'),(41,'Il pesce fresco del giorno','alla griglia, al forno, gratinato alla palermitana o in zuppa.',10.00,'Secondi Di Mare'),(42,'Braciolette di pesce spada gratinate al forno','olio extra vergine di oliva, pane, prezzemolo, origano, uva passa, pecorino, pinoli, provola, pesce spada.',14.00,'Secondi Di Mare'),(43,'Fritto del golfo di tindari e crema di limoni','acciughe, latterino, triglia, semola di grano duro, calamari, farina di grano tenero, sale, gamberi, limone, variabile, maionese, olio di arachidi.',16.00,'Secondi Di Mare'),(44,'Zuppa di cozze di ganzirri e crostini di pane','pomodoro, olio extra vergine di oliva, pane, basilico, cozze, origano.',10.00,'Secondi Di Mare'),(45,'Grigliata mista di pesce al \"salmurigghiu\" siculo e misticanza fresca','aceto di vino, olio extra vergine di oliva, calamari, carote, cicoria, indivia, prezzemolo, gamberoni, limone, menta, origano, tonno, salmone, branzino, orata, pesce spada, radicchio, scampi, pepe nero, sale, lattuga, vino bianco, vino rosso, variabile.',20.00,'Secondi Di Mare'),(46,'Astice, granchio o aragosta vivi','aragosta, astice, granciporro.',15.00,'Secondi Di Mare'),(47,'Braciolette di vitello alla messinese','aglio, carne di vitello, olio extra vergine di oliva, pane, carne di manzo, prezzemolo, pecorino, provola.',10.00,'Secondi Di Terra'),(48,'Cotoletta di manzo con patatine fritte','olio extra vergine di oliva, pane, uova di gallina, carne di manzo, noci, semi di girasole, olio di semi di girasole, parmigiano reggiano DOP.',10.00,'Secondi Di Terra'),(49,'Cannolo alla ricotta','farina di grano tenero, scorza di arancia, cacao in polvere, sugna, olio di arachidi, pistacchi, zucchero, burro, brandy, cioccolato, albume d\'uovo di gallina, zucchero a velo, ricotta di pecora.',3.00,'Dessert'),(50,'Coppa di gelato vari gusti','gelato, variabile.',3.00,'Dessert'),(51,'Coppa tiramisù al marsala','liquore, uova di gallina, cacao in polvere, zucchero, marsala, mascarpone, savoiardi.',3.50,'Dessert'),(52,'Tagliata di frutta fresca di stagione','ananas, pere, melone, mela, uva, cocomero, fragole, kiwi, pesca.',5.00,'Dessert'),(53,'I semifreddi del maestro pasticciere','ananas, pere, melone, mela, uva, anacardi, mandorle, nocciole, noci, panna, semifreddo.',4.00,'Dessert'),(54,'La tradizionale torta caprese con gelato alla vaniglia','uova di gallina, burro, cacao in polvere, cioccolato, cioccolato al latte, zucchero, mandorle, latte di vacca, gelato alla vaniglia.',4.00,'Dessert'),(55,'Biscotti secchi tipici o piccola pasticceria con vino liquoroso','biscotto, vino liquoroso, burro, farina di grano tenero, frutta a guscio (allergene), latte di vacca.',4.00,'Dessert'),(56,'Caffé caldo','caffé.',1.00,'Caffetteria'),(57,'Caffé freddo','caffé.',1.50,'Caffetteria'),(58,'Caffé corretto','caffé.',1.50,'Caffetteria'),(59,'Cappuccino','latte e caffé.',2.00,'Caffetteria'),(60,'Sfogliatina','pasta sfoglia.',1.50,'Caffetteria'),(61,'Ichnusa 33cl','Birra.',3.00,'Birre'),(62,'Peroni 33cl','Birra.',2.00,'Birre'),(63,'Moretti 33cl','Birra.',2.00,'Birre'),(64,'Peroni Chill Lemon 33cl','Birra.',2.50,'Birre'),(65,'Messina Cristalli di Sale 33cl','Birra.',3.00,'Birre'),(66,'Ceres 33cl','Birra.',4.00,'Birre'),(67,'Corona 33cl','Birra.',4.00,'Birre'),(68,'Tennent\'s 33cl','Birra.',5.00,'Birre'),(69,'Bud 33cl','Birra.',4.00,'Birre'),(70,'Pilsner Urquell 33cl','Birra.',3.00,'Birre'),(71,'Forst Felsenkeller 33cl','Birra.',3.00,'Birre'),(72,'Goose Island IPA 33cl','Birra.',5.00,'Birre'),(73,'Peroni Forte 33cl','Birra.',3.50,'Birre'),(74,'Gin Tonic','gin e acqua tonica.',5.00,'Cocktails'),(75,'Campari Orange','campari.',5.00,'Cocktails'),(76,'Rum e Cola','rum e coca cola.',5.00,'Cocktails'),(77,'Jack Daniels e Coca','whiskey e coca cola.',5.00,'Cocktails'),(78,'Negroni','campari, martini rosso, gin.',5.00,'Cocktails'),(79,'Americano','campari, martini rosso, soda.',5.00,'Cocktails'),(80,'Negroni sbagliato','ampari, artini rosso, prosecco.',5.00,'Cocktails'),(81,'Aperol Spritz','aperol, prosecco, soda.',5.00,'Cocktails'),(82,'Moscow Mule','vodka, succo di lime, ginger beer.',5.00,'Cocktails'),(83,'Long Island','rum, vodka, triple sec, gin, sweet sour, coca cola.',5.00,'Cocktails'),(84,'Cosmopolitan','vodka, succo di lime, triple sec, succo di mirtillo.',5.00,'Cocktails'),(85,'Margarita','tequila, triple sec, succo di lime.',5.00,'Cocktails'),(86,'Mojito','rum scuro, lime a pezzi, zucchero di canna.',5.00,'Cocktails'),(87,'Caipirinha','cachaça, lime a pezzi, zucchero di canna.',5.00,'Cocktails'),(88,'Caipiroska','vodka fragola, lime a pezzi, zucchero di canna.',5.00,'Cocktails'),(89,'Pitti','vermentino IGP BIO torre a cenaia.',20.00,'Vini Bianchi'),(90,'Traminer Aromatico','DOC ca\'tullio.',20.00,'Vini Bianchi'),(91,'Sauvignon','DOC ca\'tullio.',20.00,'Vini Bianchi'),(92,'Chardonnay','DOC ca\'tullio.',20.00,'Vini Bianchi'),(93,'Pinot Grigio','DOC ca\'tullio.',20.00,'Vini Bianchi'),(94,'Pomino Bianco','DOC marchesi de\'frescobaldi 2016.',20.00,'Vini Bianchi'),(95,'Vernaccia di San Gimignano','DOCG sensi.',20.00,'Vini Bianchi'),(96,'Le Coste','chianti DOCG giuliano grati.',20.00,'Vini Rossi'),(97,'Granaio','chianti classico DOCG fattorie melini.',20.00,'Vini Rossi'),(98,'Calappiano','sangiovese toscana IGT villa calappiano.',20.00,'Vini Rossi'),(99,'La Pieve','morellino di scansano DOCG f.lli rossi.',20.00,'Vini Rossi'),(100,'BST','cabernet merlot IGT folonari.',20.00,'Vini Rossi'),(101,'Vodka Belvedere','vodka.',7.00,'Distillati'),(102,'Vodka Beluga','vodka.',7.00,'Distillati'),(103,'Gentleman Jack','whisky.',7.00,'Distillati'),(104,'Woodford Reserve','whisky.',7.00,'Distillati'),(105,'Jack Daniel\'s Barrel','whiskey.',7.00,'Distillati'),(106,'Lagavulin 16','whisky.',7.00,'Distillati'),(107,'Oban 14','whisky.',7.00,'Distillati'),(108,'Talisker 14','whisky.',7.00,'Distillati'),(109,'Appleton Vx','rum.',7.00,'Distillati'),(110,'Don Papa Rum','rum.',7.00,'Distillati'),(111,'Gin Malfy','gin.',7.00,'Distillati'),(112,'Gin London','gin.',7.00,'Distillati'),(113,'Gin Mare','gin.',7.00,'Distillati'),(114,'Gin Hendrick\'s','gin.',7.00,'Distillati'),(115,'Acqua 50cl','acqua.',1.00,'Analcolici'),(116,'Lemon soda 33cl','soda al limone.',2.50,'Analcolici'),(117,'Chinotto 33cl','chinotto.',2.50,'Analcolici'),(118,'Fanta 33cl','aranciata.',2.50,'Analcolici'),(119,'Sprite 33cl','soda.',2.50,'Analcolici'),(120,'Coca Cola 33cl','soda.',2.50,'Analcolici'),(121,'Succhi di frutta 33cl','succo di frutta.',2.50,'Analcolici'),(122,'Red Bull 33cl','energy drink.',3.50,'Analcolici'),(123,'Crodino 33cl','crodino',2.50,'Analcolici'),(124,'Bitter bianco/rosso 33cl','bitter.',2.50,'Analcolici');
/*!40000 ALTER TABLE `Prodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Utente`
--

DROP TABLE IF EXISTS `Utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Utente` (
  `idUtente` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `cellulare` varchar(13) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  `ruolo` varchar(45) NOT NULL,
  PRIMARY KEY (`idUtente`),
  UNIQUE KEY `idUtente_UNIQUE` (`idUtente`),
  UNIQUE KEY `cellulare_UNIQUE` (`cellulare`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Utente`
--

LOCK TABLES `Utente` WRITE;
/*!40000 ALTER TABLE `Utente` DISABLE KEYS */;
INSERT INTO `Utente` VALUES (1,'Giovanni','Raccuglia','3271212111','admin@gmail.com','c54260be5107f84087751b3331a61497','Admin'),(2,'Marco','Verdi','3272121222','lifeguard@gmail.com','c54260be5107f84087751b3331a61497','Bagnino'),(3,'Davide','Marrone','3273131333','cook@gmail.com','c54260be5107f84087751b3331a61497','Ristorazione'),(5,'Federico','Gialli','3274141444','user@gmail.com','c54260be5107f84087751b3331a61497','Cliente');
/*!40000 ALTER TABLE `Utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-10 10:57:54
