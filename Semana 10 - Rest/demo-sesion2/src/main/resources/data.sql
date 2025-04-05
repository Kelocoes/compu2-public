-- -- Inserts for Project
INSERT INTO project(name, date_created) VALUES ('Project Alpha', '2020-01-15');
INSERT INTO project(name, date_created) VALUES ('Project Beta', '2021-03-22');
INSERT INTO project(name, date_created) VALUES ('Project Gamma', '2022-05-10');
INSERT INTO project(name, date_created) VALUES ('Project Delta', '2020-07-30');
INSERT INTO project(name, date_created) VALUES ('Project Epsilon', '2021-09-14');
INSERT INTO project(name, date_created) VALUES ('Project Zeta', '2022-11-05');
INSERT INTO project(name, date_created) VALUES ('Project Eta', '2020-12-20');
INSERT INTO project(name, date_created) VALUES ('Project Theta', '2021-02-18');
INSERT INTO project(name, date_created) VALUES ('Project Iota', '2022-04-25');
INSERT INTO project(name, date_created) VALUES ('Project Kappa', '2020-06-12');

-- Inserts for task
INSERT INTO task(name, description, date_created, due_date, status, project_id) VALUES ('Task 1', 'Description for Task 1', '2020-01-16', '2020-02-16', 0, 1);
INSERT INTO task(name, description, date_created, due_date, status, project_id) VALUES ('Task 2', 'Description for Task 2', '2021-03-23', '2021-04-23', 1, 2);
INSERT INTO task(name, description, date_created, due_date, status, project_id) VALUES ('Task 3', 'Description for Task 3', '2022-05-11', '2022-06-11', 2, 3);
INSERT INTO task(name, description, date_created, due_date, status, project_id) VALUES ('Task 4', 'Description for Task 4', '2020-07-31', '2020-08-31', 3, 4);
INSERT INTO task(name, description, date_created, due_date, status, project_id) VALUES ('Task 5', 'Description for Task 5', '2021-09-15', '2021-10-15', 0, 5);
INSERT INTO task(name, description, date_created, due_date, status, project_id) VALUES ('Task 6', 'Description for Task 6', '2022-11-06', '2022-12-06', 1, 6);
INSERT INTO task(name, description, date_created, due_date, status, project_id) VALUES ('Task 7', 'Description for Task 7', '2020-12-21', '2021-01-21', 2, 7);
INSERT INTO task(name, description, date_created, due_date, status, project_id) VALUES ('Task 8', 'Description for Task 8', '2021-02-19', '2021-03-19', 3, 8);
INSERT INTO task(name, description, date_created, due_date, status, project_id) VALUES ('Task 9', 'Description for Task 9', '2022-04-26', '2022-05-26', 0, 9);
INSERT INTO task(name, description, date_created, due_date, status, project_id) VALUES ('Task 10', 'Description for Task 10', '2020-06-13', '2020-07-13', 1, 10);

-- Inserts for user
INSERT INTO users(username, password, email) VALUES ('user1', 'password1', 'email@email.com');
INSERT INTO users(username, password, email) VALUES ('admin1', 'password1', 'emailuser@email.com');

-- Inserts for role
INSERT INTO role(name) VALUES ('ROLE_USER');
INSERT INTO role(name) VALUES ('ROLE_ADMIN');

-- Inserts for permission
INSERT INTO permission(name) VALUES ('READ_PROJECTS');
INSERT INTO permission(name) VALUES ('WRITE_PROJECTS');
INSERT INTO permission(name) VALUES ('DELETE_PROJECTS');

-- Inserts for role_permission
INSERT INTO role_permission(role_id, permission_id) VALUES (1, 1);
INSERT INTO role_permission(role_id, permission_id) VALUES (2, 1);
INSERT INTO role_permission(role_id, permission_id) VALUES (2, 2);
INSERT INTO role_permission(role_id, permission_id) VALUES (2, 3);

-- Inserts for user_role
INSERT INTO user_role(user_id, role_id) VALUES (1, 1);
INSERT INTO user_role(user_id, role_id) VALUES (2, 2);

-- Inserts for worker
INSERT INTO worker(name, lastname, document_id, user_id) VALUES ('John', 'AdminDoe', '123456789', 1);
INSERT INTO worker(name, lastname, document_id, user_id) VALUES ('Jane', 'UserSmith', '987654321', 2);
