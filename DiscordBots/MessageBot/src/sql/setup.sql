DROP table IF EXISTS guild.user CASCADE;
drop table if exists guild.message cascade;
drop table if exists guild.info cascade;
drop table if exists guild.letter cascade;
drop table if exists guild.word cascade;

set search_path to guild;

CREATE TABLE "user"
(
    id            varchar(18) not null check (length(id) = 18),
    username      varchar(32) NOT NULL check (length(username) >= 2),
    discriminator varchar(5)  NOT NULL check (length(discriminator) = 5),
    nickname      varchar(32) check (length(nickname) >= 1),
    info_id       int,
    PRIMARY KEY (id)
);

CREATE TABLE message
(
    id          varchar(18) NOT NULL,
    author_id   varchar(18),
    msg_content varchar(2000),
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) references "user" (id)
);

create table info
(
    id         serial,
    user_id    varchar(18),
    letters_id char,
    words_id   varchar(50),
    primary key (id),
    foreign key (user_id) references "user" (id)
);

create table letter
(
    letter  char,
    times   int,
    info_id int,
    primary key (letter),
    foreign key (info_id) references info (id)
);

create table word
(
    word    varchar(50),

    times   int,
    info_id int,
    primary key (word),
    foreign key (info_id) references info (id)
);

alter table "user"
    add
        foreign key (info_id) references info (id);
alter table info
    add
        foreign key (letters_id) references letter (letter);
alter table info
    add
        foreign key (words_id) references word (word);