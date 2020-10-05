drop table if exists decks;
drop table if exists cards;
drop table if exists scores;
drop table if exists users;

create table if not exists decks (
        user_id int,
        deck_id serial,
        deck_name varchar(30) not null,
        is_public boolean not null,
        
        primary key (deck_id),
        constraint fk_user_id foreign key(user_id) references users(user_id) on delete cascade
);

create table if not exists users (
        user_id serial,
        user_name varchar(30) not null unique,
        
        primary key (user_id)
);

--users
insert into users values (default, 'tester2');
select * from users;
SELECT user_id, user_name FROM users where user_name = ?;
insert into users values (default, ?);
delete from users where (user_id) = ?;
--users

create table if not exists cards(
        deck_id int not null,
        card_id serial,
        user_id int not null,
        question varchar(255) not null,
        answer varchar(255) not null,
        example varchar(255),
        
        primary key (deck_id, card_id),
        constraint fk_deck_id foreign key(deck_id) references decks(deck_id) on delete cascade,
        constraint fk_user_id foreign key (user_id) references users(user_id) on delete cascade
);

create table if not exists scores(
        score_id serial,
        deck_id int not null,
        user_id int not null,
        score int not null,
        date_inserted timestamp not null,
        
        primary key(score_id),
        constraint fk_deck_id foreign key (deck_id) references decks(deck_id),
        constraint fk_user_id foreign key (user_id) references users(user_id)
);

--scores
SELECT score_id, deck_id, user_id, score, date_inserted::date FROM scores;
INSERT INTO scores VALUES (default, 1, 1, 50, '2020-09-23');
INSERT INTO scores (score_id, deck_id, user_id, score, date_inserted) VALUES (default, ?, ?, ?, ?);

select distinct on (score) * from scores limit 10;
select * from scores join users on scores.user_id = users.user_id;
--select scores.score_id, scores.deck_id, scores.user_name, avg(scores.score) as score, scores.date_inserted::date from scores where scores.deck_id = ? group by scores.score_id order by score desc;
select scores.deck_id, scores.user_id, avg(scores.score) as score from scores join users on scores.user_id = users.user_id where scores.deck_id = ? group by scores.user_id, scores.deck_id order by score desc limit 10;
select scores.score_id, scores.deck_id, scores.user_id, scores.score, scores.date_inserted::date, users.user_name FROM scores join users on scores.user_id = users.user_id where scores.user_id = ?;
insert into scores values (default, ? , ?, ? ,?);
select * from scores;
--scores

--decks
insert into decks values (2,default,'A public deck', false);
insert into decks values (default, ?);
insert into decks values (?,default,?,?);

select * from decks;
select * from decks where is_public = true union select * from decks where is_public = false and user_id = ? order by deck_id ASC;


delete from decks where (deck_id) = ?;
delete from cards where (deck_id) = ?;

delete from decks where (deck_id) = ?;

update decks set (deck_name, is_public) = (?,?) where deck_id = ?;

select deck_id, deck_name from decks where deck_id = ?;

SELECT deck_id, deck_name FROM decks ORDER BY deck_id ASC;
--decks

--cards
insert into cards values (1, default, 1, 'question', 'answer', 'example');
INSERT INTO cards (deck_id, card_id, user_id, question, answer, example) VALUES (?, default, ?, ?, ?, ?);
insert into cards values (?, default,?,?,?,?);
delete from cards where (card_id) = ?;
update cards set question = ?, answer = ?, example = ? where card_id =?;

select count (card_id) from cards where deck_id = ?;

select * from cards;
SELECT deck_id, card_id, question, answer, example FROM cards where deck_id = ? ORDER BY deck_id ASC;
select deck_id, card_id, question, answer, example FROM cards where deck_id = ? and card_id = ?;
--cards


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




