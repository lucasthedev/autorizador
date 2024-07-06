CREATE TABLE transactions (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    account_id VARCHAR(36) REFERENCES account (id),
    mcc_code VARCHAR(6) NOT NULL,
    amount NUMERIC NOT NULL,
    merchant VARCHAR(200) NOT NULL,
    status_code VARCHAR(6) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL
);