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
-- Table `StockManager`.`cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`cidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `uf` VARCHAR(2) NOT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`endereco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cidade_id` INT NOT NULL,
  `endereco` VARCHAR(150) NOT NULL,
  `rua` VARCHAR(45) NOT NULL,
  `numero` VARCHAR(5) NOT NULL,
  `bairro` VARCHAR(45) NULL,
  `cep` VARCHAR(10) NOT NULL,
  `complementos` VARCHAR(25) NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Endereco_Cidade1_idx` (`cidade_id` ASC) ,
  CONSTRAINT `fk_Endereco_Cidade1`
    FOREIGN KEY (`cidade_id`)
    REFERENCES `StockManager`.`cidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`pessoa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `endereco_id` INT NOT NULL,
  `denominacaosocial` VARCHAR(90) NULL,
  `nome` VARCHAR(55) NULL,
  `email` VARCHAR(45) NULL,
  `cnpj` VARCHAR(45) NULL,
  `cpf` VARCHAR(14) NULL,
  `observacoes` TEXT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Pessoa_Endereco1_idx` (`endereco_id` ASC) ,
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) ,
  UNIQUE INDEX `cnpj_UNIQUE` (`cnpj` ASC) ,
  CONSTRAINT `fk_Pessoa_Endereco1`
    FOREIGN KEY (`endereco_id`)
    REFERENCES `StockManager`.`endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`funcionario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pessoa_id` INT NOT NULL,
  `login` VARCHAR(85) NOT NULL,
  `senha` VARCHAR(40) NOT NULL,
  `funcao` VARCHAR(100) NOT NULL,
  `nivelacesso` CHAR NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `senhaemail` VARCHAR(45) NOT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Funcionario_Pessoa1_idx` (`pessoa_id` ASC) ,
  UNIQUE INDEX `ogin_UNIQUE` (`login` ASC) ,
  CONSTRAINT `fk_Funcionario_Pessoa1`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `StockManager`.`pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NOT NULL,
  `descricao` TEXT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`departamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`departamento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NOT NULL,
  `descricao` TEXT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`unidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`unidade` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `sigla` VARCHAR(2) NOT NULL,
  `descricao` TEXT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) ,
  UNIQUE INDEX `sigla_UNIQUE` (`sigla` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`marca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`marca` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) NOT NULL,
  `descricao` TEXT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`produto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `marca_id` INT NOT NULL,
  `departamento_id` INT NOT NULL,
  `categoria_id` INT NOT NULL,
  `unidade_id` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  `quantidade` VARCHAR(45) NOT NULL,
  `custounitario` DECIMAL(11,2) NOT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Produto_Departamento1_idx` (`departamento_id` ASC) ,
  INDEX `fk_Produto_Categoria1_idx` (`categoria_id` ASC) ,
  INDEX `fk_Produto_Unidade1_idx` (`unidade_id` ASC) ,
  INDEX `fk_Produto_Marca1_idx` (`marca_id` ASC) ,
  CONSTRAINT `fk_Produto_Departamento1`
    FOREIGN KEY (`departamento_id`)
    REFERENCES `StockManager`.`departamento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `StockManager`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Unidade1`
    FOREIGN KEY (`unidade_id`)
    REFERENCES `StockManager`.`unidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Marca1`
    FOREIGN KEY (`marca_id`)
    REFERENCES `StockManager`.`marca` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`nfe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`nfe` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pessoarem_id` INT NOT NULL,
  `enderecorem_id` INT NOT NULL,
  `pessoadest_id` INT NOT NULL,
  `enderecodest_id` INT NOT NULL,
  `numnf` INT NULL,
  `dataemissao` DATE NOT NULL,
  `valornota` DECIMAL(13,3) NOT NULL,
  `tipo` CHAR NOT NULL,
  `observacoes` TEXT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_nfe_pessoa1_idx` (`pessoarem_id` ASC) ,
  INDEX `fk_nfe_pessoa2_idx` (`pessoadest_id` ASC) ,
  INDEX `fk_nfe_endereco1_idx` (`enderecorem_id` ASC) ,
  INDEX `fk_nfe_endereco2_idx` (`enderecodest_id` ASC) ,
  CONSTRAINT `fk_nfe_pessoa1`
    FOREIGN KEY (`pessoarem_id`)
    REFERENCES `StockManager`.`pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nfe_pessoa2`
    FOREIGN KEY (`pessoadest_id`)
    REFERENCES `StockManager`.`pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nfe_endereco1`
    FOREIGN KEY (`enderecorem_id`)
    REFERENCES `StockManager`.`endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nfe_endereco2`
    FOREIGN KEY (`enderecodest_id`)
    REFERENCES `StockManager`.`endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`nfe_has_produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`nfe_has_produto` (
  `nfe_id` INT NOT NULL,
  `produto_id` INT NOT NULL,
  `quantidade` DOUBLE NOT NULL,
  `valorprodutos` DOUBLE NOT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`nfe_id`, `produto_id`),
  INDEX `fk_nfe_has_produto_produto1_idx` (`produto_id` ASC) ,
  CONSTRAINT `fk_nfe_has_produto_nfe1`
    FOREIGN KEY (`nfe_id`)
    REFERENCES `StockManager`.`nfe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nfe_has_produto_produto1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `StockManager`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`estoque` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `endereco_id` INT NOT NULL,
  `nome` VARCHAR(200) NOT NULL,
  `descricao` TEXT NULL,
  `telefone` VARCHAR(25) NOT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Estoque_Endereco1_idx` (`endereco_id` ASC) ,
  CONSTRAINT `fk_Estoque_Endereco1`
    FOREIGN KEY (`endereco_id`)
    REFERENCES `StockManager`.`endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`agendamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`agendamento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `funcionario_id` INT NOT NULL,
  `descricao` TEXT NULL,
  `data` DATETIME NOT NULL,
  `estado` CHAR(1) NOT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`id`, `funcionario_id`),
  INDEX `fk_Agendamento_Funcionario1_idx` (`funcionario_id` ASC) ,
  CONSTRAINT `fk_Agendamento_Funcionario1`
    FOREIGN KEY (`funcionario_id`)
    REFERENCES `StockManager`.`funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`insercao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`insercao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `funcionario_id` INT NOT NULL,
  `data` DATETIME NOT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_insercao_funcionario1_idx` (`funcionario_id` ASC) ,
  CONSTRAINT `fk_insercao_funcionario1`
    FOREIGN KEY (`funcionario_id`)
    REFERENCES `StockManager`.`funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`estoque_has_produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`estoque_has_produto` (
  `insercao_id` INT NOT NULL,
  `Estoque_id` INT NOT NULL,
  `produto_id` INT NOT NULL,
  `quantidade` DOUBLE NOT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`insercao_id`, `Estoque_id`, `produto_id`),
  INDEX `fk_Estoque_has_Produto_Produto1_idx` (`produto_id` ASC) ,
  INDEX `fk_Estoque_has_Produto_Estoque1_idx` (`Estoque_id` ASC) ,
  INDEX `fk_estoque_has_produto_insercao1_idx` (`insercao_id` ASC) ,
  CONSTRAINT `fk_Estoque_has_Produto_Estoque1`
    FOREIGN KEY (`Estoque_id`)
    REFERENCES `StockManager`.`estoque` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Estoque_has_Produto_Produto1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `StockManager`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_estoque_has_produto_insercao1`
    FOREIGN KEY (`insercao_id`)
    REFERENCES `StockManager`.`insercao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`telefones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`telefones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(45) NOT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`tipo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` TEXT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`pessoa_has_telefones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`pessoa_has_telefones` (
  `pessoa_id` INT NOT NULL,
  `telefones_id` INT NOT NULL,
  `descricao` TEXT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`pessoa_id`, `telefones_id`),
  INDEX `fk_Pessoa_has_Telefones_Telefones1_idx` (`telefones_id` ASC) ,
  INDEX `fk_Pessoa_has_Telefones_Pessoa1_idx` (`pessoa_id` ASC) ,
  CONSTRAINT `fk_Pessoa_has_Telefones_Pessoa1`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `StockManager`.`pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pessoa_has_Telefones_Telefones1`
    FOREIGN KEY (`telefones_id`)
    REFERENCES `StockManager`.`telefones` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`pessoa_has_tipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`pessoa_has_tipo` (
  `pessoa_id` INT NOT NULL,
  `tipo_id` INT NOT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`pessoa_id`, `tipo_id`),
  INDEX `fk_Pessoa_has_Tipo_Tipo1_idx` (`tipo_id` ASC) ,
  INDEX `fk_Pessoa_has_Tipo_Pessoa1_idx` (`pessoa_id` ASC) ,
  CONSTRAINT `fk_Pessoa_has_Tipo_Pessoa1`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `StockManager`.`pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pessoa_has_Tipo_Tipo1`
    FOREIGN KEY (`tipo_id`)
    REFERENCES `StockManager`.`tipo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StockManager`.`pessoa_has_agendamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StockManager`.`pessoa_has_agendamento` (
  `pessoa_id` INT NOT NULL,
  `agendamento_id` INT NOT NULL,
  `agendamento_funcionario_id` INT NOT NULL,
  `ativo` CHAR NOT NULL,
  PRIMARY KEY (`pessoa_id`, `agendamento_id`, `agendamento_funcionario_id`),
  INDEX `fk_pessoa_has_agendamento_agendamento1_idx` (`agendamento_id` ASC, `agendamento_funcionario_id` ASC) ,
  INDEX `fk_pessoa_has_agendamento_pessoa1_idx` (`pessoa_id` ASC) ,
  CONSTRAINT `fk_pessoa_has_agendamento_pessoa1`
    FOREIGN KEY (`pessoa_id`)
    REFERENCES `StockManager`.`pessoa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pessoa_has_agendamento_agendamento1`
    FOREIGN KEY (`agendamento_id` , `agendamento_funcionario_id`)
    REFERENCES `StockManager`.`agendamento` (`id` , `funcionario_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
