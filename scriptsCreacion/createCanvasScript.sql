DROP TABLE TAREA CASCADE CONSTRAINTS;
DROP TABLE USUARIO CASCADE CONSTRAINTS;
--CREACION TABLAS
CREATE TABLE TAREA (
    idTarea NUMBER(10) PRIMARY KEY,
    duracion NUMBER(10),
    ESTADO VARCHAR2(30),
    idUsuario NUMBER(10),
    nombre VARCHAR2(60)
);

CREATE TABLE USUARIO (

    idUsuario NUMBER(10) PRIMARY KEY,
    apodo VARCHAR(20),
    contraseña VARCHAR(6)

);

--APLICAR RESTRICCIONES
ALTER TABLE TAREA ADD CONSTRAINT fk_usuario FOREIGN KEY (idUsuario) REFERENCES USUARIO(idUsuario) ON DELETE CASCADE ;
ALTER TABLE TAREA ADD CONSTRAINT check_estados CHECK(ESTADO in ('PENDIENTE','REALIZANDO','FINALIZADA'));
ALTER TABLE TAREA ADD CONSTRAINT unique_nombre unique(nombre);
ALTER TABLE USUARIO ADD CONSTRAINT unique_apodo unique(apodo);

--INSERTAR VALORES POR DEFECTO (necesario para el funcionamiento del programa)
INSERT INTO USUARIO (idUsuario, apodo, contraseña)
VALUES(1,'admin','admin');
INSERT INTO TAREA (idTarea, duracion, ESTADO, idUsuario, nombre)
VALUES(1,1,'FINALIZADA',1,'InitialTask');

commit;
