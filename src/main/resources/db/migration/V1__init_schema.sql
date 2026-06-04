CREATE TABLE users (
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE accounts (
    id             BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(12)    NOT NULL UNIQUE,
    balance        NUMERIC(19, 4) NOT NULL,
    user_id        BIGINT         NOT NULL REFERENCES users (id)
);

CREATE TABLE transactions (
    id                 BIGSERIAL PRIMARY KEY,
    type               VARCHAR(20)    NOT NULL,
    amount             NUMERIC(19, 4) NOT NULL,
    created_at         TIMESTAMP      NOT NULL,
    account_id         BIGINT         NOT NULL REFERENCES accounts (id),
    related_account_id BIGINT
);
