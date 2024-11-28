CREATE table usuarios (
    id UUID NOT NULL PRIMARY KEY,
    login VARCHAR(20) NOT NULL UNIQUE,
    senha VARCHAR(300) NOT NULL,
    roles VARCHAR[]
)