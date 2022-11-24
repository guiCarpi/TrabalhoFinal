create table if not exists dep (

    id SERIAL not null,
    name varchar(50) not null,

    constraint pk_dep primary key(id)

    );