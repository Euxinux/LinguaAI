INSERT INTO roles (name)
VALUES
    ('ADMIN'),
    ('MANAGER'),
    ('EDITOR'),
    ('VIEWER'),
    ('AUDITOR')
ON CONFLICT (name) DO NOTHING;