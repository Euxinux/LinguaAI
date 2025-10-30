ALTER TABLE quizzes DROP CONSTRAINT IF EXISTS quizzes_user_id_fk;
ALTER TABLE quizzes DROP CONSTRAINT IF EXISTS quizzes_language_code_fk;
ALTER TABLE quizzes DROP CONSTRAINT IF EXISTS quizzes_category_id_fk;
DROP TABLE IF EXISTS quizzes;