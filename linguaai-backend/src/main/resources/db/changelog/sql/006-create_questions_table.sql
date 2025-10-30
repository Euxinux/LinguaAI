CREATE TABLE IF NOT EXISTS questions (
    id BIGSERIAL PRIMARY KEY,
    quiz_id BIGINT,
    question_text TEXT NOT NULL,
    options JSONB NOT NULL,
    correct_option VARCHAR(5) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    CONSTRAINT questions_quiz_id_fk FOREIGN KEY (quiz_id)
    REFERENCES quizzes (id) ON DELETE CASCADE
);