DROP table IF EXISTS guild.user CASCADE;
drop table if exists guild.message cascade;
drop table if exists guild.letterAmount cascade;
drop table if exists guild.word cascade;
drop table if exists guild.info cascade;
drop table if exists guild.letter_amount cascade;
drop table if exists guild.word_amount cascade;
drop table if exists guild.amount cascade;

set search_path to guild;

-- USER
CREATE TABLE "user"
(
    id            varchar(18) PRIMARY KEY check (length(id) = 18),
    username      varchar(32) NOT NULL check (length(username) >= 2),
    discriminator varchar(5)  NOT NULL check (length(discriminator) = 5)
);

--MESSAGE
CREATE TABLE message
(
    id           varchar(18) PRIMARY KEY,
    author_id    varchar(18) references "user" (id),
    msg_content  varchar(2000),
    time_created timestamp
);

--INFO 1 info has many word_amount and many letter_amount
create table info
(
    user_id varchar(18) references "user" (id) primary key
);

alter table "user"
    add info_id varchar(18) references info (user_id);

--LETTER
create table letterAmount
(
    letterAmount char primary key
);

-- WORD
create table word
(
    word varchar(100) primary key
);

--AMOUNT
create table amount
(
    amount int primary key
);

--WORD_AMOUNT 1 info has many word_amount
create table word_amount
(
    collection varchar(18) references info (user_id) primary key,
    word       varchar(100) references word (word),
    amount      int references amount (amount)
);

alter table info
    add words varchar(18) references word_amount (collection);

--LETTER_AMOUNT 1 info has many letter_amount
create table letter_amount
(
    collection varchar(18) references info (user_id) primary key,
    letterAmount     char references letterAmount (letterAmount),
    amount      int references amount (amount)
);

alter table info
    add letterAmount varchar(18) references letter_amount (collection);