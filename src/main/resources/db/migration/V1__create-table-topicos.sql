create TABLE topicos (

    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(100) not null,
    fecha_creacion date not null,
    estado tinyint not null,
    autor varchar(100) not null,
    curso varchar(100) not null,

    primary key(id)

);
