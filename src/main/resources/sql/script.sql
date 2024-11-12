-- Crear la base de datos
DROP
DATABASE IF EXISTS m6_rengifo;
CREATE
DATABASE IF NOT EXISTS m6_rengifo;
USE
m6_rengifo;
-- -----------------------------
-- CREACIONES                  |
-- -----------------------------
-- Crear la tabla Clients
CREATE TABLE Clients
(
    id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    nif VARCHAR(50)  NOT NULL UNIQUE,
    nom VARCHAR(255) NOT NULL
);


-- Crear la tabla Productes
CREATE TABLE Productes
(
    id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL UNIQUE,
    preu DOUBLE NOT NULL
);

-- Crear la tabla Facturas
CREATE TABLE Facturas
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    data      DATE NOT NULL,
    client_id BIGINT  NOT NULL,
    FOREIGN KEY (client_id) REFERENCES Clients (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Crear la tabla Linias
CREATE TABLE Linias
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    producte_id BIGINT NOT NULL,
    quantitat   INT NOT NULL,
    factura_id  BIGINT NOT NULL,
    FOREIGN KEY (producte_id) REFERENCES Productes (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (factura_id) REFERENCES Facturas (id) ON DELETE CASCADE ON UPDATE CASCADE
);
-- -------------------------
-- INSERCIONES             |
-- -------------------------
-- Rellenar la tabla Clients
INSERT INTO Clients (nif, nom)
VALUES ('X1234567A', 'Juan Pérez'),
       ('X2345678B', 'María García'),
       ('X3456789C', 'Carlos Sánchez'),
       ('X4567890D', 'Laura Fernández');

-- Rellenar la tabla Productes
INSERT INTO Productes (nom, preu)
VALUES ('Producto A', 10.50),
       ('Producto B', 25.30),
       ('Producto C', 8.99),
       ('Producto D', 15.75);

-- Rellenar la tabla Facturas
-- Las fechas pueden ser ajustadas según el contexto.
INSERT INTO Facturas (data, client_id)
VALUES ('2024-11-01', 1),
       ('2024-11-02', 2),
       ('2024-11-03', 1),
       ('2024-11-04', 3);

-- Rellenar la tabla Linias
-- Cada línea está asociada a un producto y una factura, con una cantidad específica.
INSERT INTO Linias (producte_id, quantitat, factura_id)
VALUES (1, 2, 1),
       (2, 1, 1),
       (3, 5, 2),
       (4, 3, 3),
       (2, 2, 3),
       (1, 1, 4);

