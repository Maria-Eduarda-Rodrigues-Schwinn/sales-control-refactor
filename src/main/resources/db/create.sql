USE sales_control;

CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria ENUM('FOOD', 'BEVERAGES', 'PERSONAL_HYGIENE', 'CLEANING', 'ELECTRONICS', 'CLOTHING', 'FURNITURE', 'BOOKS', 'TOYS', 'OTHERS') NOT NULL,
    precounitario DECIMAL(10,2) NOT NULL,
    unidademedida ENUM('UNIT', 'GRAMS', 'KILOS', 'LITERS', 'MILLILITRES', 'PACKAGE', 'BOX', 'METERS', 'SQUARE_METERS', 'OTHERS') NOT NULL,
    quantidade INT NOT NULL
);

CREATE TABLE venda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    datavenda DATE NOT NULL,
    valortotal DECIMAL(10,2) NOT NULL
);

CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    login VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    tipo ENUM('ADMIN', 'EMPLOYEE') NOT NULL
);

CREATE TABLE venda_produto (
    venda_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    precounitario DECIMAL(10,2) NOT NULL,
    valortotal DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (venda_id, produto_id),
    FOREIGN KEY (venda_id) REFERENCES venda(id) ON DELETE CASCADE,
    FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE CASCADE
);

CREATE INDEX idx_categoria ON produto(categoria);
CREATE INDEX idx_tipo ON usuario(tipo);