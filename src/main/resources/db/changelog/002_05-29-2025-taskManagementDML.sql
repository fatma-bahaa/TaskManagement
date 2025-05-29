INSERT INTO users (id, name, user_name, password) VALUES (1,'Fatma', 'fatmaUser', '$2a$10$Co3eEJPFUKzpKLz18w/Kr.pTmvFdfVAf8bsUynyVIm9L/3L7aDtua');
INSERT INTO task_management (id, title, description, status, priority, created_by, due_date)
            VALUES (1,'task1', 'task1', 'TO_DO', 'LOW', 1, now());

SELECT setval('task_management_id_seq', (SELECT MAX(id) from task_management), TRUE);
SELECT setval('users_id_seq', (SELECT MAX(id) from users), TRUE);
