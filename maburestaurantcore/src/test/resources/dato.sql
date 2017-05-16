delete from role_privilege;
delete from role;
delete from privilege;
INSERT INTO privilege(id, name)  VALUES (1,'ROLE_USER');
INSERT INTO privilege(id, name)  VALUES (2,'ROLE_MANAGER');
INSERT INTO privilege(id, name)  VALUES (3,'ROLE_ADMIN');
INSERT INTO role(id, name) VALUES (1,'ROLE_USER');
INSERT INTO role(id, name) VALUES (2,'ROLE_ADMIN');
INSERT INTO role(id, name) VALUES (3,'ROLE_MANAGER');
INSERT INTO role_privilege(role_id, privilege_id) VALUES (1,1);
INSERT INTO role_privilege(role_id, privilege_id) VALUES (3,1);
INSERT INTO role_privilege(role_id, privilege_id) VALUES (3,2);
INSERT INTO role_privilege(role_id, privilege_id) VALUES (3,3);
INSERT INTO role_privilege(role_id, privilege_id) VALUES (2,2);
commit;