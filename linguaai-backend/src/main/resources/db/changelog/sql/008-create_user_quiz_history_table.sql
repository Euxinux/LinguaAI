CREATE TABLE IF NOT EXISTS tasks (
    id BIGSERIAL PRIMARY KEY,
    quiz_id BIGINT,
    type VARCHAR(50),
    content JSONB,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    CONSTRAINT tasks_quiz_id_fk FOREIGN KEY (quiz_id)
    REFERENCES quizzes (id) ON DELETE CASCADE
);