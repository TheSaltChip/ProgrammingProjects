DROP table IF EXISTS guild.member CASCADE;
drop table if exists guild.message cascade;

set search_path to guild;

CREATE TABLE member
(
    user_id varchar(18) not null,
    username varchar(32)  NOT NULL check (length(username) >= 2),
    discriminator varchar(5) NOT NULL check (length(discriminator) = 5),
    nickname varchar(32) check (length(nickname) >= 1),
    PRIMARY KEY (user_id)
);

CREATE TABLE message
(
    message_id varchar(18) NOT NULL,
    author_id varchar(18),
    msg_content varchar(2000),
    PRIMARY KEY (message_id),
    FOREIGN KEY (author_id) references member(user_id)
);