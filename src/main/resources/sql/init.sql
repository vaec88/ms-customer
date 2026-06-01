CREATE DATABASE bank;

CREATE TABLE customer (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(20),
    identification VARCHAR(20) NOT NULL UNIQUE,
    address VARCHAR(255),
    phone VARCHAR(20),
    "password" VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP
);