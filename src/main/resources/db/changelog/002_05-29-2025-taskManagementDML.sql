INSERT INTO users (id, name, user_name, password) VALUES (1,'Fatma', 'fatma', '$2a$10$Co3eEJPFUKzpKLz18w/Kr.pTmvFdfVAf8bsUynyVIm9L/3L7aDtua');
INSERT INTO task (id, title, description, status, priority, created_by, due_date)
            VALUES (1,'task1', 'task1', 'TO_DO', 'LOW', 1, now());

SELECT setval('task_id_seq', (SELECT MAX(id) from task), TRUE);
SELECT setval('users_id_seq', (SELECT MAX(id) from users), TRUE);
