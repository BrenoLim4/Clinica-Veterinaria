/**
 * Author:  BrenoLima
 * Created: 23/09/2021
 */

CREATE TABLE IF NOT EXISTS `clinica_veterinaria`.`pessoa` (
  `nome` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `dataNascimento` DATE NOT NULL,
  `idade` INT NOT NULL,
  `sexo_id` INT NOT NULL,
  `tipo_pessoa_id` INT NOT NULL,
  PRIMARY KEY (`cpf`, `sexo_id`, `tipo_pessoa_id`),
  INDEX `fk_pessoa_sexo1_idx` (`sexo_id` ASC) VISIBLE,
  INDEX `fk_pessoa_tipo_pessoa1_idx` (`tipo_pessoa_id` ASC) VISIBLE,
  CONSTRAINT `fk_pessoa_sexo1`
    FOREIGN KEY (`sexo_id`)
    REFERENCES `clinica_veterinaria`.`sexo` (`id`),
  CONSTRAINT `fk_pessoa_tipo_pessoa1`
    FOREIGN KEY (`tipo_pessoa_id`)
    REFERENCES `clinica_veterinaria`.`tipo_pessoa` (`id`))
ENGINE = InnoDB;

-- ---------------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_veterinaria`.`sexo` (
  `id` INT NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- ---------------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_veterinaria`.`tipo_pessoa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- ---------------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_veterinaria`.`medico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pessoa_cpf` VARCHAR(14) NOT NULL,
  `especialidade` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_medico_pessoa1`
    FOREIGN KEY (`pessoa_cpf`)
    REFERENCES `clinica_veterinaria`.`pessoa` (`cpf`))
ENGINE = InnoDB;

-- ---------------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_veterinaria`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pessoa_cpf` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cliente_pessoa1_idx` (`pessoa_cpf` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_pessoa1`
    FOREIGN KEY (`pessoa_cpf`)
    REFERENCES `clinica_veterinaria`.`pessoa` (`cpf`))
ENGINE = InnoDB;

-- ---------------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_veterinaria`.`animal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cliente_id` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `raca` VARCHAR(45) NOT NULL,
  `especie` VARCHAR(45) NULL,
  `idade` INT NOT NULL,
  `animalcol` VARCHAR(45) NULL,
  `sexo_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_animal_cliente1_idx` (`cliente_id` ASC) VISIBLE,
  INDEX `fk_animal_sexo1_idx` (`sexo_id` ASC) VISIBLE,
  CONSTRAINT `fk_animal_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `clinica_veterinaria`.`cliente` (`id`),
  CONSTRAINT `fk_animal_sexo1`
    FOREIGN KEY (`sexo_id`)
    REFERENCES `clinica_veterinaria`.`sexo` (`id`))
ENGINE = InnoDB;

-- ---------------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_veterinaria`.`status_tratamento` (
  `id` INT NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
-- ---------------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_veterinaria`.`tratamento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `medico_id` INT NOT NULL,
  `cliente_id` INT NOT NULL,
  `animal_id` INT NOT NULL,
  `status_tratamento_id` INT NOT NULL,
  PRIMARY KEY (`id`, `medico_id`, `cliente_id`, `animal_id`, `status_tratamento_id`),
  INDEX `fk_tratamento_medico_idx` (`medico_id` ASC) VISIBLE,
  INDEX `fk_tratamento_cliente_idx` (`cliente_id` ASC) VISIBLE,
  INDEX `fk_tratamento_animal_idx` (`animal_id` ASC) VISIBLE,
  INDEX `fk_tratamento_status_tratamento1_idx` (`status_tratamento_id` ASC) VISIBLE,
  CONSTRAINT `fk_tratamento_medico1`
    FOREIGN KEY (`medico_id`)
    REFERENCES `clinica_veterinaria`.`medico` (`id`),
  CONSTRAINT `fk_tratamento_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `clinica_veterinaria`.`cliente` (`id`),
  CONSTRAINT `fk_tratamento_animal1`
    FOREIGN KEY (`animal_id`)
    REFERENCES `clinica_veterinaria`.`animal` (`id`),
  CONSTRAINT `fk_tratamento_status_tratamento1`
    FOREIGN KEY (`status_tratamento_id`)
    REFERENCES `clinica_veterinaria`.`status_tratamento` (`id`))
ENGINE = InnoDB;

-- ---------------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_veterinaria`.`exame` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_hora_solicitada` DATETIME NOT NULL,
  `tratamento_id` INT NOT NULL,
  `data_hora_resultado` DATETIME NULL,
  `concluido` TINYINT NOT NULL DEFAULT 0,
  `resultado` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_exame_tratamento1_idx` (`tratamento_id` ASC) VISIBLE,
  CONSTRAINT `fk_exame_tratamento1`
    FOREIGN KEY (`tratamento_id`)
    REFERENCES `clinica_veterinaria`.`tratamento` (`id`))
ENGINE = InnoDB;

-- ---------------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_veterinaria`.`status_sessao` (
  `id` INT NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- ---------------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_veterinaria`.`gravidade` (
  `id` INT NOT NULL,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- ---------------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica_veterinaria`.`sessao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(8) NOT NULL,
  `tratamento_id` INT NOT NULL,
  `status_sessao_id` INT NOT NULL,
  `sintomas` VARCHAR(255) NULL,
  `gravidade_id` INT NOT NULL,
  `diagnostico` VARCHAR(255) NULL,
  `data_Marcada` DATETIME NOT NULL,
  `data_encerramento` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sessao_tratamento1_idx` (`tratamento_id` ASC) VISIBLE,
  INDEX `fk_sessao_status_sessao1_idx` (`status_sessao_id` ASC) VISIBLE,
  INDEX `fk_sessao_gravidade1_idx` (`gravidade_id` ASC) VISIBLE,
  CONSTRAINT `fk_sessao_tratamento1`
    FOREIGN KEY (`tratamento_id`)
    REFERENCES `clinica_veterinaria`.`tratamento` (`id`),
  CONSTRAINT `fk_sessao_status_sessao1`
    FOREIGN KEY (`status_sessao_id`)
    REFERENCES `clinica_veterinaria`.`status_sessao` (`id`),
  CONSTRAINT `fk_sessao_gravidade1`
    FOREIGN KEY (`gravidade_id`)
    REFERENCES `clinica_veterinaria`.`gravidade` (`id`))
ENGINE = InnoDB;

-- ---------------------------------------------------------
INSERT INTO `clinica_veterinaria`.`sexo`
(`id`, `descricao`)
VALUES
(1,'MASCULINO'),
(2,'FEMININO');

-- ---------------------------------------------------------
INSERT INTO  clinica_veterinaria.status_sessao
(id, descricao)
VALUES
(1, 'REGISTRADA'),
(2, 'FINALIZADA'),
(3, 'CANCELADA');

-- ---------------------------------------------------------
INSERT INTO clinica_veterinaria.status_tratamento
(id,
descricao)
VALUES
(1, 'REGISTRADA'),
(2, 'FINALIZADA'),
(3, 'CANCELADA'),
(4, 'EXAME SOLICITADO');

-- ---------------------------------------------------------
INSERT INTO `clinica_veterinaria`.`tipo_pessoa`
(`id`,
`descricao`)
VALUES
(1,'MEDICO'),
(2,'CLIENTE'),
(3,'SECRETARIA'),
(4,'GESTOR_TIC');
-- =============================================================================
--                ATUALIZAÇÕES QUE PRECISAM SICRONIZAR 
-- =============================================================================