CREATE TABLE IF NOT EXISTS odontologos(id int auto_increment primary key,numMatricula int,nombre varchar(255),apellido varchar(255));
CREATE TABLE IF NOT EXISTS domicilios(id int auto_increment primary key,calle varchar(255),numero int,localidad varchar(255),provincia varchar(255));
CREATE TABLE IF NOT EXISTS pacientes(id int auto_increment primary key,nombre varchar(255),apellido varchar (255),email varchar (255),dni int,fecha_ingreso date,domicilio_id int);
CREATE TABLE IF NOT EXISTS turnos(id int auto_increment primary key,id_paciente int,id_odontologo int,fecha date);
