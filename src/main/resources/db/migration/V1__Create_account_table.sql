CREATE TABLE account (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    document_number VARCHAR(50) NOT NULL,
    available_food_credit NUMERIC NOT NULL,
    available_meal_credit NUMERIC NOT NULL,
    available_cash_credit NUMERIC NOT NULL,
    created_at TIMESTAMP(6) NOT NULL
);