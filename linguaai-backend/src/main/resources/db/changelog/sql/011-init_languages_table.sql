INSERT INTO languages (code, name)
VALUES
    ('PL','Polish'),
    ('EN','English'),
    ('ES','Spanish'),
    ('DE','German'),
    ('IT','French')
ON CONFLICT (code) DO NOTHING;