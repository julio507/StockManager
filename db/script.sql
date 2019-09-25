-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema StockManager
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema StockManager
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `StockManager` DEFAULT CHARACTER SET utf8 ;
USE `StockManager` ;

-- -----------------------------------------------------
-- Table `StockManager`.`Cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`Cidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(100) NOT NULL,
  `UF` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`Endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`Endereco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Cidade_id` INT NOT NULL,
  `Endereco` VARCHAR(150) NOT NULL,
  `Rua` VARCHAR(45) NOT NULL,
  `Numero` VARCHAR(5) NOT NULL,
  `Bairro` VARCHAR(45) NULL,
  `CEP` VARCHAR(10) NOT NULL,
  `Complementos` VARCHAR(25) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Endereco_Cidade1_idx` (`Cidade_id` ASC)  ,
  CONSTRAINT `fk_Endereco_Cidade1`
    FOREIGN KEY (`Cidade_id`)
    REFERENCES `StockManager`.`Cidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`Pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`Pessoa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Endereco_id` INT NOT NULL,
  `DenominacaoSocial` VARCHAR(90) NOT NULL,
  `Nome` VARCHAR(55) NOT NULL,
  `Email` VARCHAR(45) NULL,
  `CNPJ` VARCHAR(45) NOT NULL,
  `CPF` VARCHAR(11) NOT NULL,
  `Observacoes` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Pessoa_Endereco1_idx` (`Endereco_id` ASC)  ,
  CONSTRAINT `fk_Pessoa_Endereco1`
    FOREIGN KEY (`Endereco_id`)
    REFERENCES `StockManager`.`Endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`Funcionario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Pessoa_id` INT NOT NULL,
  `Login` VARCHAR(85) NOT NULL,
  `Senha` VARCHAR(40) NOT NULL,
  `Funcao` VARCHAR(100) NOT NULL,
  `NivelAcesso` INT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Funcionario_Pessoa1_idx` (`Pessoa_id` ASC)  ,
  CONSTRAINT `fk_Funcionario_Pessoa1`
    FOREIGN KEY (`Pessoa_id`)
    REFERENCES `StockManager`.`Pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`Categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(500) NOT NULL,
  `Descricao` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`Departamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`Departamento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(500) NOT NULL,
  `Descricao` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`Unidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`Unidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Sigla` VARCHAR(2) NOT NULL,
  `Descricao` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`Marca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`Marca` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(500) NOT NULL,
  `Descricao` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`Produto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Marca_id` INT NOT NULL,
  `Departamento_id` INT NOT NULL,
  `Categoria_id` INT NOT NULL,
  `Unidade_id` INT NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Descricao` VARCHAR(45) NOT NULL,
  `Quantidade` VARCHAR(45) NOT NULL,
  `CustoUnitario` DECIMAL(11,2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Produto_Departamento1_idx` (`Departamento_id` ASC)  ,
  INDEX `fk_Produto_Categoria1_idx` (`Categoria_id` ASC)  ,
  INDEX `fk_Produto_Unidade1_idx` (`Unidade_id` ASC)  ,
  INDEX `fk_Produto_Marca1_idx` (`Marca_id` ASC)  ,
  CONSTRAINT `fk_Produto_Departamento1`
    FOREIGN KEY (`Departamento_id`)
    REFERENCES `StockManager`.`Departamento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Categoria1`
    FOREIGN KEY (`Categoria_id`)
    REFERENCES `StockManager`.`Categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Unidade1`
    FOREIGN KEY (`Unidade_id`)
    REFERENCES `StockManager`.`Unidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Marca1`
    FOREIGN KEY (`Marca_id`)
    REFERENCES `StockManager`.`Marca` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`NFE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`NFE` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Pessoa_id` INT NOT NULL,
  `NumNF` INT NULL,
  `DataEmissao` DATE NOT NULL,
  `ValorNota` DECIMAL(13,3) NOT NULL,
  `Tipo` CHAR(1) NOT NULL,
  `observações` TEXT NULL,
  PRIMARY KEY (`id`, `Pessoa_id`),
  INDEX `fk_NFE_Pessoa1_idx` (`Pessoa_id` ASC)  ,
  CONSTRAINT `fk_NFE_Pessoa1`
    FOREIGN KEY (`Pessoa_id`)
    REFERENCES `StockManager`.`Pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`NFE_has_Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`NFE_has_Produto` (
  `NFE_id` INT NOT NULL,
  `NFE_Pessoa_id` INT NOT NULL,
  `Produto_id` INT NOT NULL,
  `Quantidade` DOUBLE NOT NULL,
  `ValorProdutos` DOUBLE NOT NULL,
  PRIMARY KEY (`NFE_id`, `NFE_Pessoa_id`, `Produto_id`),
  INDEX `fk_NFE_has_Produto_Produto1_idx` (`Produto_id` ASC)  ,
  INDEX `fk_NFE_has_Produto_NFE1_idx` (`NFE_id` ASC, `NFE_Pessoa_id` ASC)  ,
  CONSTRAINT `fk_NFE_has_Produto_Produto1`
    FOREIGN KEY (`Produto_id`)
    REFERENCES `StockManager`.`Produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NFE_has_Produto_NFE1`
    FOREIGN KEY (`NFE_id` , `NFE_Pessoa_id`)
    REFERENCES `StockManager`.`NFE` (`id` , `Pessoa_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`Estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`Estoque` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Endereco_id` INT NOT NULL,
  `Nome` VARCHAR(500) NOT NULL,
  `Descricao` TEXT NULL,
  `Telefone` VARCHAR(25) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Estoque_Endereco1_idx` (`Endereco_id` ASC)  ,
  CONSTRAINT `fk_Estoque_Endereco1`
    FOREIGN KEY (`Endereco_id`)
    REFERENCES `StockManager`.`Endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`Agendamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`Agendamento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `NFE_id` INT NOT NULL,
  `NFE_Pessoa_id` INT NOT NULL,
  `Funcionario_id` INT NOT NULL,
  `Descricao` VARCHAR(45) NULL,
  `Data` DATETIME NULL,
  `Estado` CHAR(1) NOT NULL,
  PRIMARY KEY (`id`, `NFE_id`, `NFE_Pessoa_id`, `Funcionario_id`),
  INDEX `fk_Agendamento_Funcionario1_idx` (`Funcionario_id` ASC)  ,
  INDEX `fk_Agendamento_NFE1_idx` (`NFE_id` ASC, `NFE_Pessoa_id` ASC)  ,
  CONSTRAINT `fk_Agendamento_Funcionario1`
    FOREIGN KEY (`Funcionario_id`)
    REFERENCES `StockManager`.`Funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Agendamento_NFE1`
    FOREIGN KEY (`NFE_id` , `NFE_Pessoa_id`)
    REFERENCES `StockManager`.`NFE` (`id` , `Pessoa_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`Estoque_has_Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`Estoque_has_Produto` (
  `Estoque_id` INT NOT NULL,
  `Produto_id` INT NOT NULL,
  `Quantidade` DOUBLE NOT NULL,
  PRIMARY KEY (`Estoque_id`, `Produto_id`),
  INDEX `fk_Estoque_has_Produto_Produto1_idx` (`Produto_id` ASC)  ,
  INDEX `fk_Estoque_has_Produto_Estoque1_idx` (`Estoque_id` ASC)  ,
  CONSTRAINT `fk_Estoque_has_Produto_Estoque1`
    FOREIGN KEY (`Estoque_id`)
    REFERENCES `StockManager`.`Estoque` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Estoque_has_Produto_Produto1`
    FOREIGN KEY (`Produto_id`)
    REFERENCES `StockManager`.`Produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`Telefones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`Telefones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Numero` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`Tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`Tipo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`Pessoa_has_Telefones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`Pessoa_has_Telefones` (
  `Pessoa_id` INT NOT NULL,
  `Telefones_id` INT NOT NULL,
  `descricao` TEXT NULL,
  PRIMARY KEY (`Pessoa_id`, `Telefones_id`),
  INDEX `fk_Pessoa_has_Telefones_Telefones1_idx` (`Telefones_id` ASC)  ,
  INDEX `fk_Pessoa_has_Telefones_Pessoa1_idx` (`Pessoa_id` ASC)  ,
  CONSTRAINT `fk_Pessoa_has_Telefones_Pessoa1`
    FOREIGN KEY (`Pessoa_id`)
    REFERENCES `StockManager`.`Pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pessoa_has_Telefones_Telefones1`
    FOREIGN KEY (`Telefones_id`)
    REFERENCES `StockManager`.`Telefones` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`Pessoa_has_Tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`Pessoa_has_Tipo` (
  `Pessoa_id` INT NOT NULL,
  `Tipo_id` INT NOT NULL,
  PRIMARY KEY (`Pessoa_id`, `Tipo_id`),
  INDEX `fk_Pessoa_has_Tipo_Tipo1_idx` (`Tipo_id` ASC)  ,
  INDEX `fk_Pessoa_has_Tipo_Pessoa1_idx` (`Pessoa_id` ASC)  ,
  CONSTRAINT `fk_Pessoa_has_Tipo_Pessoa1`
    FOREIGN KEY (`Pessoa_id`)
    REFERENCES `StockManager`.`Pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pessoa_has_Tipo_Tipo1`
    FOREIGN KEY (`Tipo_id`)
    REFERENCES `StockManager`.`Tipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
