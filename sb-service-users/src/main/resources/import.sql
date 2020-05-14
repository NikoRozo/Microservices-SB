INSERT INTO `users` (username, password, enabled, nombre, apellido, email) VALUES ('NikoRozo','123456',1,'Nicolás', 'Rozo', 'niko@correo.com');
INSERT INTO `users` (username, password, enabled, nombre, apellido, email) VALUES ('DianaA','123456',1,'Diana', 'Avendaño', 'diana@correo.com');

INSERT INTO `rols` (nombre) VALUES ('ROLE_USER');
INSERT INTO `rols` (nombre) VALUES ('ROLE_ADMIN'); 

INSERT INTO `users_roles` (users_id, roles_id) VALUES (1, 1);
INSERT INTO `users_roles` (users_id, roles_id) VALUES (2, 2);
INSERT INTO `users_roles` (users_id, roles_id) VALUES (2, 1);