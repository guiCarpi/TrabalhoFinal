create table if not exists medicos(

    id SERIAL,
    name varchar(50) not null,
    gender char(1) not null,
    dep_id integer not null,

    constraint pk_medico primary key (id),
    constraint fk_medico_dep foreign key(dep_id) references dep(id)

    );