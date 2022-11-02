INSERT INTO usuarios (username, password, enabled, nombre, apellidos, email ) VALUES('paulgh00', '800', 1, 'Paul', 'Ghetea', 'paulghetea@outlook.es');
INSERT INTO usuarios (username, password, enabled, nombre, apellidos, email ) VALUES('maria', '123', 1, 'Maria', 'Menchero', 'mariamenchero@outlook.es');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_to_roles (usuario_id, role_id) VALUES(1,1);
INSERT INTO usuarios_to_roles  (usuario_id, role_id) VALUES(2,1);
INSERT INTO usuarios_to_roles  (usuario_id, role_id) VALUES(2,2);