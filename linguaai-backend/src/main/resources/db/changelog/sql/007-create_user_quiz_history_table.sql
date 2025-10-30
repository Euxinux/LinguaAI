CREATE TABLE IF NOT EXISTS user_quiz_history (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    quiz_id BIGINT,
    score INTEGER,
    completed_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT user_quiz_history_user_id_fk FOREIGN KEY (user_id)
    REFERENCES users (id),
    CONSTRAINT user_quiz_history_quiz_id_fk FOREIGN KEY (quiz_id)
    REFERENCES quizzes (id) ON DELETE CASCADE
);