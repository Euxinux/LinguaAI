ALTER TABLE user_quiz_history DROP CONSTRAINT IF EXISTS user_quiz_history_user_id_fk;
ALTER TABLE user_quiz_history DROP CONSTRAINT IF EXISTS user_quiz_history_quiz_id_fk;
DROP TABLE IF EXISTS user_quiz_history;