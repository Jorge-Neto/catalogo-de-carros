CREATE TABLE IF NOT EXISTS make (
    id NUMBER(3) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
);

CREATE TABLE IF NOT EXISTS model (
    id NUMBER(3) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    FOREIGN KEY (make_id) REFERENCES make(id)
);