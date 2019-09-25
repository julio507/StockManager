-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: stockmanager
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `agendamento`
--

DROP TABLE IF EXISTS `agendamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agendamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NFE_id` int(11) NOT NULL,
  `NFE_Pessoa_id` int(11) NOT NULL,
  `Funcionario_id` int(11) NOT NULL,
  `Descricao` varchar(45) DEFAULT NULL,
  `Data` datetime DEFAULT NULL,
  `Estado` char(1) NOT NULL,
  PRIMARY KEY (`id`,`NFE_id`,`NFE_Pessoa_id`,`Funcionario_id`),
  KEY `fk_Agendamento_Funcionario1_idx` (`Funcionario_id`),
  KEY `fk_Agendamento_NFE1_idx` (`NFE_id`,`NFE_Pessoa_id`),
  CONSTRAINT `fk_Agendamento_Funcionario1` FOREIGN KEY (`Funcionario_id`) REFERENCES `funcionario` (`id`),
  CONSTRAINT `fk_Agendamento_NFE1` FOREIGN KEY (`NFE_id`, `NFE_Pessoa_id`) REFERENCES `nfe` (`id`, `Pessoa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agendamento`
--

LOCK TABLES `agendamento` WRITE;
/*!40000 ALTER TABLE `agendamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `agendamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(500) NOT NULL,
  `Descricao` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cidade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(100) NOT NULL,
  `UF` varchar(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade`
--

LOCK TABLES `cidade` WRITE;
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
INSERT INTO `cidade` VALUES (1,'Lajeado','RS');
/*!40000 ALTER TABLE `cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamento`
--

DROP TABLE IF EXISTS `departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(500) NOT NULL,
  `Descricao` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` VALUES (1,'Limpeza','departamento de produtos de limpeza'),(2,'ferragens','ferragens');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `endereco` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Cidade_id` int(11) NOT NULL,
  `Endereco` varchar(150) NOT NULL,
  `Rua` varchar(45) NOT NULL,
  `Numero` varchar(5) NOT NULL,
  `Bairro` varchar(45) DEFAULT NULL,
  `CEP` varchar(10) NOT NULL,
  `Complementos` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Endereco_Cidade1_idx` (`Cidade_id`),
  CONSTRAINT `fk_Endereco_Cidade1` FOREIGN KEY (`Cidade_id`) REFERENCES `cidade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (1,1,'ahsdhas','rua a','0','centro','95555000','jsadhgashda');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estoque` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Endereco_id` int(11) NOT NULL,
  `Nome` varchar(500) NOT NULL,
  `Descricao` text,
  `Telefone` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Estoque_Endereco1_idx` (`Endereco_id`),
  CONSTRAINT `fk_Estoque_Endereco1` FOREIGN KEY (`Endereco_id`) REFERENCES `endereco` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estoque_has_produto`
--

DROP TABLE IF EXISTS `estoque_has_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estoque_has_produto` (
  `Estoque_id` int(11) NOT NULL,
  `Produto_id` int(11) NOT NULL,
  `Quantidade` double NOT NULL,
  PRIMARY KEY (`Estoque_id`,`Produto_id`),
  KEY `fk_Estoque_has_Produto_Produto1_idx` (`Produto_id`),
  KEY `fk_Estoque_has_Produto_Estoque1_idx` (`Estoque_id`),
  CONSTRAINT `fk_Estoque_has_Produto_Estoque1` FOREIGN KEY (`Estoque_id`) REFERENCES `estoque` (`id`),
  CONSTRAINT `fk_Estoque_has_Produto_Produto1` FOREIGN KEY (`Produto_id`) REFERENCES `produto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque_has_produto`
--

LOCK TABLES `estoque_has_produto` WRITE;
/*!40000 ALTER TABLE `estoque_has_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `estoque_has_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Pessoa_id` int(11) NOT NULL,
  `Login` varchar(85) NOT NULL,
  `Senha` varchar(40) NOT NULL,
  `Funcao` varchar(100) NOT NULL,
  `NivelAcesso` int(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Funcionario_Pessoa1_idx` (`Pessoa_id`),
  CONSTRAINT `fk_Funcionario_Pessoa1` FOREIGN KEY (`Pessoa_id`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (1,1,'root','root','master',1);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca`
--

DROP TABLE IF EXISTS `marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marca` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(500) NOT NULL,
  `Descricao` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca`
--

LOCK TABLES `marca` WRITE;
/*!40000 ALTER TABLE `marca` DISABLE KEYS */;
/*!40000 ALTER TABLE `marca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nfe`
--

DROP TABLE IF EXISTS `nfe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nfe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Pessoa_id` int(11) NOT NULL,
  `NumNF` int(11) DEFAULT NULL,
  `DataEmissao` date NOT NULL,
  `ValorNota` decimal(13,3) NOT NULL,
  `Tipo` char(1) NOT NULL,
  `observações` text,
  PRIMARY KEY (`id`,`Pessoa_id`),
  KEY `fk_NFE_Pessoa1_idx` (`Pessoa_id`),
  CONSTRAINT `fk_NFE_Pessoa1` FOREIGN KEY (`Pessoa_id`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nfe`
--

LOCK TABLES `nfe` WRITE;
/*!40000 ALTER TABLE `nfe` DISABLE KEYS */;
/*!40000 ALTER TABLE `nfe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nfe_has_produto`
--

DROP TABLE IF EXISTS `nfe_has_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nfe_has_produto` (
  `NFE_id` int(11) NOT NULL,
  `NFE_Pessoa_id` int(11) NOT NULL,
  `Produto_id` int(11) NOT NULL,
  `Quantidade` double NOT NULL,
  `ValorProdutos` double NOT NULL,
  PRIMARY KEY (`NFE_id`,`NFE_Pessoa_id`,`Produto_id`),
  KEY `fk_NFE_has_Produto_Produto1_idx` (`Produto_id`),
  KEY `fk_NFE_has_Produto_NFE1_idx` (`NFE_id`,`NFE_Pessoa_id`),
  CONSTRAINT `fk_NFE_has_Produto_NFE1` FOREIGN KEY (`NFE_id`, `NFE_Pessoa_id`) REFERENCES `nfe` (`id`, `Pessoa_id`),
  CONSTRAINT `fk_NFE_has_Produto_Produto1` FOREIGN KEY (`Produto_id`) REFERENCES `produto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nfe_has_produto`
--

LOCK TABLES `nfe_has_produto` WRITE;
/*!40000 ALTER TABLE `nfe_has_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `nfe_has_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Endereco_id` int(11) NOT NULL,
  `DenominacaoSocial` varchar(90) NOT NULL,
  `Nome` varchar(55) NOT NULL,
  `Telefone` varchar(45) NOT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `CNPJ` varchar(45) NOT NULL,
  `CPF` varchar(11) NOT NULL,
  `Observacoes` text,
  PRIMARY KEY (`id`),
  KEY `fk_Pessoa_Endereco1_idx` (`Endereco_id`),
  CONSTRAINT `fk_Pessoa_Endereco1` FOREIGN KEY (`Endereco_id`) REFERENCES `endereco` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,1,'  ','joão','51999999999','joão@joão','1165165151115','0','sdahsdjha');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa_has_telefones`
--

DROP TABLE IF EXISTS `pessoa_has_telefones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa_has_telefones` (
  `Pessoa_id` int(11) NOT NULL,
  `Telefones_id` int(11) NOT NULL,
  `descricao` text,
  PRIMARY KEY (`Pessoa_id`,`Telefones_id`),
  KEY `fk_Pessoa_has_Telefones_Telefones1_idx` (`Telefones_id`),
  KEY `fk_Pessoa_has_Telefones_Pessoa1_idx` (`Pessoa_id`),
  CONSTRAINT `fk_Pessoa_has_Telefones_Pessoa1` FOREIGN KEY (`Pessoa_id`) REFERENCES `pessoa` (`id`),
  CONSTRAINT `fk_Pessoa_has_Telefones_Telefones1` FOREIGN KEY (`Telefones_id`) REFERENCES `telefones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa_has_telefones`
--

LOCK TABLES `pessoa_has_telefones` WRITE;
/*!40000 ALTER TABLE `pessoa_has_telefones` DISABLE KEYS */;
/*!40000 ALTER TABLE `pessoa_has_telefones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa_has_tipo`
--

DROP TABLE IF EXISTS `pessoa_has_tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa_has_tipo` (
  `Pessoa_id` int(11) NOT NULL,
  `Tipo_id` int(11) NOT NULL,
  PRIMARY KEY (`Pessoa_id`,`Tipo_id`),
  KEY `fk_Pessoa_has_Tipo_Tipo1_idx` (`Tipo_id`),
  KEY `fk_Pessoa_has_Tipo_Pessoa1_idx` (`Pessoa_id`),
  CONSTRAINT `fk_Pessoa_has_Tipo_Pessoa1` FOREIGN KEY (`Pessoa_id`) REFERENCES `pessoa` (`id`),
  CONSTRAINT `fk_Pessoa_has_Tipo_Tipo1` FOREIGN KEY (`Tipo_id`) REFERENCES `tipo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa_has_tipo`
--

LOCK TABLES `pessoa_has_tipo` WRITE;
/*!40000 ALTER TABLE `pessoa_has_tipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `pessoa_has_tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Marca_id` int(11) NOT NULL,
  `Departamento_id` int(11) NOT NULL,
  `Categoria_id` int(11) NOT NULL,
  `Unidade_id` int(11) NOT NULL,
  `Nome` varchar(45) NOT NULL,
  `Descricao` varchar(45) NOT NULL,
  `Quantidade` varchar(45) NOT NULL,
  `CustoUnitario` decimal(11,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Produto_Departamento1_idx` (`Departamento_id`),
  KEY `fk_Produto_Categoria1_idx` (`Categoria_id`),
  KEY `fk_Produto_Unidade1_idx` (`Unidade_id`),
  KEY `fk_Produto_Marca1_idx` (`Marca_id`),
  CONSTRAINT `fk_Produto_Categoria1` FOREIGN KEY (`Categoria_id`) REFERENCES `categoria` (`id`),
  CONSTRAINT `fk_Produto_Departamento1` FOREIGN KEY (`Departamento_id`) REFERENCES `departamento` (`id`),
  CONSTRAINT `fk_Produto_Marca1` FOREIGN KEY (`Marca_id`) REFERENCES `marca` (`id`),
  CONSTRAINT `fk_Produto_Unidade1` FOREIGN KEY (`Unidade_id`) REFERENCES `unidade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefones`
--

DROP TABLE IF EXISTS `telefones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telefones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Numero` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefones`
--

LOCK TABLES `telefones` WRITE;
/*!40000 ALTER TABLE `telefones` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo`
--

DROP TABLE IF EXISTS `tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo`
--

LOCK TABLES `tipo` WRITE;
/*!40000 ALTER TABLE `tipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidade`
--

DROP TABLE IF EXISTS `unidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) NOT NULL,
  `Sigla` varchar(2) NOT NULL,
  `Descricao` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidade`
--

LOCK TABLES `unidade` WRITE;
/*!40000 ALTER TABLE `unidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `unidade` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-25 20:46:14
