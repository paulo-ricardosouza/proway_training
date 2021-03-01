CREATE DATABASE proway_training;

CREATE TABLE pessoas (
	id INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	sobrenome VARCHAR(255) NOT NULL
);

CREATE TABLE salas (
	id INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	lotacao INT NOT NULL    
);

CREATE TABLE espacos (
	id INT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(255) NOT NULL    
);


CREATE TABLE evento (
	id INT AUTO_INCREMENT PRIMARY KEY,
	id_salas INT NOT NULL,
	id_espacos INT NOT NULL,
    id_pessoas INT NOT NULL,
	nome VARCHAR(255) NOT NULL,
    etapa INT,
    intervalo INT
);

ALTER TABLE `evento` ADD CONSTRAINT `fk_salas_evento` FOREIGN KEY ( `id_salas` ) REFERENCES `salas` ( `id` ) ;
ALTER TABLE `evento` ADD CONSTRAINT `fk_espacos_evento` FOREIGN KEY ( `id_espacos` ) REFERENCES `espacos` ( `id` ) ;
ALTER TABLE `evento` ADD CONSTRAINT `fk_pessoas_evento` FOREIGN KEY ( `id_pessoas` ) REFERENCES `pessoas` ( `id` ) ;