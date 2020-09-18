start transaction;

rollback;

create table if not exists decks (
        deck_id serial,
        deck_name varchar(30) not null,
        
        primary key (deck_id)
);

create table if not exists cards(
        deck_id int not null,
        card_id serial,
        question varchar(255) not null,
        answer varchar(255) not null,
        example varchar(255),
        
        primary key (deck_id, card_id),
        constraint fk_deck_id foreign key(deck_id) references decks(deck_id)
);
--decks
insert into decks values (default, 'this is a test');
insert into decks values (default, ?);

delete from decks where (deck_id) = ?;
delete from cards where (deck_id) = ?;

update decks set deck_name = ? where deck_id = ?;

select deck_id, deck_name from decks where deck_id = ?;
select count (card_id) from cards where deck_id = ?;

SELECT deck_id, deck_name FROM decks;
--decks

--cards
insert into cards values (1, default, 'question', 'answer', 'example');
insert into cards values (?, default, ?,?,?);
delete from cards where (card_id) = ?;
update cards set question = ?, answer = ?, example = ? where card_id =?;

select count (card_id) from cards where deck_id = ?;

select * from cards;
SELECT deck_id, card_id, question, answer, example FROM cards where deck_id = ?;
--cards


drop table if exists decks;
drop table if exists cards;


