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
        constraint fk_deck_id foreign key(deck_id) references decks(deck_id) on delete cascade
);

create table if not exists scores(
        score_id serial,
        deck_id int not null,
        user_name varchar(30) not null,
        score int not null,
        date_inserted timestamp not null,
        
        primary key(score_id),
        constraint fk_deck_id foreign key (deck_id) references decks(deck_id)
);

--scores
SELECT score_id, deck_id, user_name, score, date_inserted::date FROM scores;
INSERT INTO scores VALUES (default, 1, 'testname', 100, '2020-09-23');
select distinct on (score) * from scores limit 10;
--select scores.score_id, scores.deck_id, scores.user_name, avg(scores.score) as score, scores.date_inserted::date from scores where scores.deck_id = ? group by scores.score_id order by score desc;
select scores.deck_id, scores.user_name, avg(scores.score) as score from scores where deck_id = ? group by scores.user_name, scores.deck_id order by score desc limit 10;
select score_id, deck_id, user_name, score, date_inserted::date FROM scores where user_name = ?;
insert into scores values (default, ? , ?, ? ,?);
--scores


--decks
insert into decks values (default, 'DO NOT DELETE');
insert into decks values (default, ?);

delete from decks where (deck_id) = ?;
delete from cards where (deck_id) = ?;

delete from decks where (deck_id) = ?;

update decks set deck_name = ? where deck_id = ?;

select deck_id, deck_name from decks where deck_id = ?;
select count (card_id) from cards where deck_id = ?;

SELECT deck_id, deck_name FROM decks ORDER BY deck_id ASC;
--decks

--cards
insert into cards values (10, default, 'question', 'answer', 'example');
insert into cards values (?, default, ?,?,?);
delete from cards where (card_id) = ?;
update cards set question = ?, answer = ?, example = ? where card_id =?;

select count (card_id) from cards where deck_id = ?;

select * from cards;
SELECT deck_id, card_id, question, answer, example FROM cards where deck_id = ? ORDER BY deck_id ASC;
select deck_id, card_id, question, answer, example FROM cards where deck_id = ? and card_id = ?;
--cards


drop table if exists decks;
drop table if exists cards;
drop table if exists scores;

--card data
insert into cards values (1, default, 'what is an object?', 'an object is an entity that has states and behaviors', 'a car could be defined as an object with different attributes: color - red, shift - automatic, ect');
insert into cards values (1, default, 'what are the three principles of OOP?', 'polymorphism, inheritance, and encapsulation', 'these are easy to remember with P.I.E.');
insert into cards values (1, default, 'what is encapsulation?', 'encapsulation is a process of wrapping code and data together into a single unit and helps keep variables protected', 'an example of encapsulation is a vending machine, its sole purpose is to vend and it can do nothing else');
insert into cards values (1, default, 'what is inheritance?', 'inheritance is the process of inheriting all behaviors or state from the parent', 'an example of this is a parent passing on their genes to their children for black hair ect');
insert into cards values (1, default, 'what is polymorphism?', 'polymorphism is the ability of an object to take on many forms', 'a dog is a barkable and a runnable object but it can take of different behaviors as well and extends the animal class');
insert into cards values (1, default, 'what is abstraction?', 'abstraction is a process of hiding the implementation details and showing only functionality to the user', 'a driver pressing the gas to accelerate, but now knowing or needing to know how the car is accelerating');
insert into cards values (1, default, 'what is an interface?', 'an interface is the blueprint of a class or a contract saying that it must have x,y, and z functions', 'all birds must fly function included, but a duck chooses to flap once every minute versus a bat which flaps every second');
insert into cards values (1, default, 'what is dependency injection?', 'when you decide to take in something as an argument', 'an example of this is local development with a local database versus the production database and choosing which database you want to implement');
insert into cards values (1, default, 'what are wrapper classes?','they wrap primitive data classes which gives said data more functionality','wrapping an int to Integer');
insert into cards values (1, default, 'what is a server?','a computer that is accesible over a protocol to exchange data','a web server that you can make HTTP requests which allows the user to request and receive data as a response');
insert into cards values (1, default, 'what is there difference bewteen an outer and inner join in sql', 'inner join connects via a column and an outer join doesnt care about if it has shared columns and just returns what it can', '');
insert into cards values (1, default, '','','');
insert into cards values (77, default, '','','');
insert into cards values (77, default, '','','');
insert into cards values (77, default, '','','');
insert into cards values (77, default, '','','');
insert into cards values (77, default, '','','');
insert into cards values (77, default, '','','');
insert into cards values (77, default, '','','');
insert into cards values (77, default, '','','');

--card data

