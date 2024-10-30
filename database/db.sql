CREATE DATABASE schoolSystem;

USE schoolSystem;

CREATE TABLE Professor (
    id INT PRIMARY KEY,
    nome VARCHAR(100),
    departamento VARCHAR(100)
);

CREATE TABLE Curso (
    id INT PRIMARY KEY,
    nome VARCHAR(100),
    carga_horaria INT,
    professor_id INT,
    FOREIGN KEY (professor_id) REFERENCES Professor(id)
);

CREATE TABLE Aluno (
    id INT PRIMARY KEY,
    nome VARCHAR(100),
    data_nascimento DATE,
    cpf CHAR(14),
    idade INT,
    curso_id INT,
    FOREIGN KEY (curso_id) REFERENCES Curso(id)
);