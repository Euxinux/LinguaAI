CREATE TABLE IF NOT EXISTS quizzes (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    language_code VARCHAR(5),
    category_id BIGINT,
    difficulty VARCHAR(10),
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    ai_generated BOOLEAN DEFAULT TRUE,
    CONSTRAINT quizzes_user_id_fk FOREIGN KEY (user_id)
    REFERENCES users (id),
    CONSTRAINT quizzes_language_code_fk FOREIGN KEY (language_code)
    REFERENCES languages (code),
    CONSTRAINT quizzes_category_id_fk FOREIGN KEY (category_id)
    REFERENCES categories (id)
);