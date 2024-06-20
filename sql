
    create table matches (
       id bigint not null auto_increment,
        date_matches datetime,
        goals_local integer,
        goals_visitor integer,
        played bit,
        local_team_id bigint not null,
        visitor_team_id bigint not null,
        primary key (id)
    ) engine=InnoDB;

    create table picture (
       id bigint not null auto_increment,
        image longblob,
        primary key (id)
    ) engine=InnoDB;

    create table player (
       id bigint not null auto_increment,
        age varchar(2),
        height varchar(5),
        last_name varchar(30),
        name varchar(30) not null,
        weight varchar(5),
        team_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table team (
       id bigint not null auto_increment,
        dt varchar(30),
        name varchar(30) not null,
        picture_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table user (
       id bigint not null auto_increment,
        created_at bigint,
        date_of_birth datetime not null,
        email varchar(255),
        last_name varchar(30) not null,
        last_updated_at bigint,
        name varchar(30) not null,
        password varchar(255) not null,
        phone varchar(10),
        primary key (id)
    ) engine=InnoDB;

    create table user_bet (
       id bigint not null auto_increment,
        bet bigint not null,
        primary key (id)
    ) engine=InnoDB;

    alter table player 
       add constraint UK_5q11flfd61t4n9eepixi8ltup unique (team_id);

    alter table team 
       add constraint UK_5pcpucq34f8c3n9vfdodewou0 unique (picture_id);

    alter table matches 
       add constraint FKspxn1vq1frspxslku5n1r8vdq 
       foreign key (local_team_id) 
       references team (id);

    alter table matches 
       add constraint FKe0s0iosrfwa60996h4516b57 
       foreign key (visitor_team_id) 
       references team (id);

    alter table player 
       add constraint FKdvd6ljes11r44igawmpm1mc5s 
       foreign key (team_id) 
       references team (id);

    alter table team 
       add constraint FKfsuikgj5j47jnfwgi0dxnsbfn 
       foreign key (picture_id) 
       references picture (id);
