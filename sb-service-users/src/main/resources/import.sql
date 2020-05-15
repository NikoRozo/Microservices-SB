INSERT INTO `users` (username, password, enabled, nombre, apellido, email) VALUES ('NikoRozo','$2a$10$APrTWcd6GU3r1DafbfizKeOiobqmdYdQwwfcsleohXdRVJx1/yhva',1,'Nicolás', 'Rozo', 'niko@correo.com');
INSERT INTO `users` (username, password, enabled, nombre, apellido, email) VALUES ('DianaA','$2a$10$JZLBUjqSCnR6LxkLnMhr/ejgca54meB.4gv6T2uFVD/MM1S7VKOOm',1,'Diana', 'Avendaño', 'diana@correo.com');

INSERT INTO `rols` (nombre) VALUES ('ROLE_USER');
INSERT INTO `rols` (nombre) VALUES ('ROLE_ADMIN'); 

INSERT INTO `users_roles` (users_id, roles_id) VALUES (1, 1);
INSERT INTO `users_roles` (users_id, roles_id) VALUES (2, 2);
INSERT INTO `users_roles` (users_id, roles_id) VALUES (2, 1);