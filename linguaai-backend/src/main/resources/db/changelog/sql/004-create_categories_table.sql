CREATE TABLE IF NOT EXISTS categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    language_code VARCHAR(5),
    CONSTRAINT categories_language_code_fk FOREIGN KEY (language_code)
    REFERENCES languages (code)
);