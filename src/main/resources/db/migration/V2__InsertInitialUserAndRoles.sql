INSERT INTO "roles"
(id, description, "name")
VALUES(nextVal('role_id_seq'), 'Admin role', 'ADMIN');


INSERT INTO users
(id, email, full_name, "password", username)
VALUES(nextVal('user_id_seq'), 'bernard.briski@gmail.com', 'Bernard Briški', '$2a$09$CvggcMxFSY2lzNrcVmARCOyLIQutlvCZ4bVDkU2nmyjyWacnoxati', 'briskibe');

INSERT INTO users_roles
(user_id, role_id)
VALUES(currval('user_id_seq'), currval('role_id_seq'));

commit;
