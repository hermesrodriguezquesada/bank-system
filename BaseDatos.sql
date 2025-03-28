-- BaseDatos.sql

-- Tabla Persona
CREATE TABLE persona (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    genero VARCHAR(50),
    edad INTEGER,
    identificacion VARCHAR(100),
    direccion VARCHAR(255),
    telefono VARCHAR(50)
);

-- Tabla Cliente (hereda de Persona)
CREATE TABLE cliente (
    id BIGINT PRIMARY KEY,
    cliente_id VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(255),
    estado BOOLEAN,
    CONSTRAINT fk_cliente_persona FOREIGN KEY (id) REFERENCES persona(id)
);

-- Tabla Cuentas
CREATE TABLE cuentas (
    id BIGSERIAL PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    tipo VARCHAR(50) CHECK (tipo IN ('AHORRO','CORRIENTE')),
    saldo_inicial NUMERIC(38,2),
    estado BOOLEAN,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

-- Tabla Movimientos
CREATE TABLE movimientos (
    id BIGSERIAL PRIMARY KEY,
    cuenta_id BIGINT NOT NULL,
    fecha DATE,
    tipo VARCHAR(50) CHECK (tipo IN ('DEPOSITO','RETIRO')),
    valor NUMERIC(38,2),
    saldo_disponible NUMERIC(38,2),
    FOREIGN KEY (cuenta_id) REFERENCES cuentas(id)
);

-- Datos iniciales de Persona y Cliente (ejemplo)
INSERT INTO persona (id, nombre, genero, edad, identificacion, direccion, telefono)
VALUES 
    (1, 'Jose Lema', 'Masculino', 30, '1234567890', 'Otavalo sn y principal', '098254785'),
    (2, 'Marianela Montalvo', 'Femenino', 28, '2345678901', 'Amazonas y NNUU', '097548965'),
    (3, 'Juan Osorio', 'Masculino', 32, '3456789012', '13 junio y Equinoccial', '098874587');

INSERT INTO cliente (id, cliente_id, contrasena, estado)
VALUES 
    (1, '1234', '1234', TRUE),
    (2, '5678', '5678', TRUE),
    (3, '1245', '1245', TRUE);

-- Datos iniciales de Cuentas
INSERT INTO cuentas (id, cliente_id, tipo, saldo_inicial, estado)
VALUES 
    (1, 1, 'AHORRO', 2000.00, TRUE),
    (2, 2, 'CORRIENTE', 100.00, TRUE),
    (3, 3, 'AHORRO', 0.00, TRUE),
    (4, 2, 'AHORRO', 540.00, TRUE),
    (5, 1, 'CORRIENTE', 1000.00, TRUE);

-- Datos iniciales de Movimientos
INSERT INTO movimientos (cuenta_id, fecha, tipo, valor, saldo_disponible)
VALUES 
    (1, '2022-02-10', 'RETIRO', 575.00, 1425.00),
    (2, '2022-02-10', 'DEPOSITO', 600.00, 700.00),
    (3,  '2022-02-10', 'DEPOSITO', 150.00, 150.00),
    (4, '2022-02-08', 'RETIRO', 540.00, 0.00);
