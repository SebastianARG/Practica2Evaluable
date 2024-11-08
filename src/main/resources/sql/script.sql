-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS m6_rengifo;
USE m6_rengifo;

-- Crear la tabla Clients
CREATE TABLE Clients (
        id INT AUTO_INCREMENT PRIMARY KEY,
        nif VARCHAR(50) NOT NULL UNIQUE,
        nom VARCHAR(255) NOT NULL
);

-- Crear la tabla Productes
CREATE TABLE Productes (
        id INT AUTO_INCREMENT PRIMARY KEY,
        nom VARCHAR(255) NOT NULL UNIQUE,
        preu DOUBLE NOT NULL
);

-- Crear la tabla Facturas
CREATE TABLE Facturas (
        id INT AUTO_INCREMENT PRIMARY KEY,
        data DATE NOT NULL,
        client_id INT NOT NULL,
        FOREIGN KEY (client_id) REFERENCES Clients(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Crear la tabla Linias
CREATE TABLE Linias (
        id INT AUTO_INCREMENT PRIMARY KEY,
        producte_id INT NOT NULL,
        quantitat INT NOT NULL,
        factura_id int NOT NULL,
        FOREIGN KEY (producte_id) REFERENCES Productes(id) ON DELETE CASCADE ON UPDATE CASCADE,
        FOREIGN KEY (factura_id) REFERENCES Facturas(id) ON DELETE CASCADE ON UPDATE CASCADE
);
