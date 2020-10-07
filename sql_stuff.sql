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
insert into users values (default, 'admin');
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
INSERT INTO scores VALUES (default, 1, 1, 50, '2019-09-23');
INSERT INTO scores (score_id, deck_id, user_id, score, date_inserted) VALUES (default, ?, ?, ?, ?);

select distinct on (score) * from scores limit 10;
select * from scores join users on scores.user_id = users.user_id;


select scores.deck_id, scores.user_id, avg(scores.score) as score from scores join users on scores.user_id = users.user_id where scores.deck_id = ? group by scores.user_id, scores.deck_id order by score desc limit 10;
select scores.score_id, scores.deck_id, scores.user_id, scores.score, scores.date_inserted::date, users.user_name FROM scores join users on scores.user_id = users.user_id where scores.user_id = ?;
select scores.deck_id, scores.user_id, avg(scores.score)::int as score, scores.date_inserted::date from scores where scores.user_id = ? and scores.deck_id = ? and scores.date_inserted between ? and ? group by scores.deck_id, scores.user_id,scores.date_inserted::date order by date_inserted ASC;
insert into scores values (default, ? , ?, ? ,?);
select * from scores;
--scores

--decks
insert into decks values (1,default,'Server', true);
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
insert into cards values (1, default,1, 'what is an object?', 'an object is an entity that has states and behaviors', 'a car could be defined as an object with different attributes: color - red, shift - automatic, ect');
insert into cards values (1, default,1, 'what are the three principles of OOP?', 'polymorphism, inheritance, and encapsulation', 'these are easy to remember with P.I.E.');
insert into cards values (1, default,1, 'what is encapsulation?', 'encapsulation is a process of wrapping code and data together into a single unit and helps keep variables protected', 'an example of encapsulation is a vending machine, its sole purpose is to vend and it can do nothing else');
insert into cards values (1, default,1, 'what is inheritance?', 'inheritance is the process of inheriting all behaviors or state from the parent', 'an example of this is a parent passing on their genes to their children for black hair ect');
insert into cards values (1, default,1, 'what is polymorphism?', 'polymorphism is the ability of an object to take on many forms', 'a dog is a barkable and a runnable object but it can take of different behaviors as well and extends the animal class');
insert into cards values (1, default,1, 'what is abstraction?', 'abstraction is a process of hiding the implementation details and showing only functionality to the user', 'a driver pressing the gas to accelerate, but now knowing or needing to know how the car is accelerating');
insert into cards values (1, default,1, 'what is an interface?', 'an interface is the blueprint of a class or a contract saying that it must have x,y, and z functions', 'all birds must fly function included, but a duck chooses to flap once every minute versus a bat which flaps every second');
insert into cards values (1, default,1, 'what is dependency injection?', 'one object (or static method) supplies the dependencies of another object', 'an example of this is local development with a local database versus the production database and choosing which database you want to implement');
insert into cards values (1, default,1, 'what are wrapper classes?','they wrap primitive data classes which gives said data more functionality','wrapping an int to Integer');
insert into cards values (4, default,1, 'what is a server?','a computer that is accesible over a protocol to exchange data','a web server that you can make HTTP requests which allows the user to request and receive data as a response');
insert into cards values (3, default,1, 'what is there difference bewteen an outer and inner join in sql', 'The inner join only returns rows when it finds a match in both tables. An outer join will also return unmatched rows from one table if it is a single outer join, or both tables if it is a full outer join.', '');
insert into cards values (3, default,1, 'What is the feature in SQL for writing If/Else statements?','using the case syntax allows you to return data when the condition is true','CASE WHEN condition1 THEN result1');
insert into cards values (3, default,1, 'What is the difference between Left OUTER Join and Right OUTER Join?','in a left outer join its the table in the FROM clause whose all rows are returned. Whereas, in a right outer join we are returning all rows from the table specified in the join clause','');
insert into cards values (1, default,1, 'what is an exception?','An exception is an event, which occurs during the execution of a program, that disrupts the normal flow of the programs instructions','the most common exception in java is a null pointer exception');
insert into cards values (1, default,1, 'what is the difference between a run-time error and a compile-time error?','a run-time error happens when the program is executing while a compile-time error is detected during compiling','during execution the code refers to a null value versus the compiler deteching that youre missing a ; ect.');
insert into cards values (1, default,1, 'what is a class?','A Class is like a "blueprint" for creating objects','you have a class for a sedan, however when constructing it you can choose different colors or shift-boxes and it still remains a sedan');
insert into cards values (1, default,1, 'what are integration tests?','where you use an actual external service so that you test if that service responds correctly to your input data','you insert a row into your database and youre testing whether or not it has been inserted correctly');
insert into cards values (1, default,1, 'what is the difference between encapsulation and abstraction?','encapsulation is a way to acheive information hiding which has an interface to use x while abstraction is the capability to use the same interface for x,y,and z','a cellphone uses a numpad to interact with the device like a keyboard does with a computer');
insert into cards values (1, default,1, 'what is the difference between narrowing and widening','narrowing is going from a larger datatype to a smaller while widening is the opposite, both can result in truncation or the loss of precision','going from a double, 3.5, to a int datatype truncates the .5 and youre left with 3');
insert into cards values (1, default,1, 'what are the four parts of a method signature?','access modifier, return type, method name, parameter list','public (access modifier) int (return type), multiplyBy[method name](int x, int y)[parameter list]{}');
insert into cards values (1, default,1, 'how many types of loops are there in java?','for, while, and do while','for(Integer i : intList)/for(int i = 0; i < 10; i++), while(true), do{i++}while(i<100)');
insert into cards values (3, default,1, 'what is database normalization?','Normalization is the process of organizing data in a relational database used to reduce data redundancy and increase data integrity and considered normal when it meets all reqs for 3NF','');
insert into cards values (3, default,1, 'what are attribute constraints?','a rule that limits the type of data that can be entered as a specific attribute','NOT NULL, UNIQUE, PRIMARY KEY, FOREIGN KEY');
insert into cards values (3, default,1, 'Explain the Difference Between TRUNCATE, DELETE, and DROP','TRUNCATE deletes all data from the table, DELETE you can delete some rows with a WHERE clause, DROP delete the whole table','');
insert into cards values (4, default,1, 'what does API stand for?','Application Programming Interface','');
insert into cards values (4, default,1, 'what is an API?','it is the part of the server that receives requests and sends responses','');
insert into cards values (4, default,1, 'what does HTTP and HTTPS stand for?','Hyper Text Transfer Protocol and ... Secure','');
insert into cards values (4, default,1, 'what is a restful application?','RESTful applications use HTTP requests to post data (create and/or update), read data (e.g., make queries), and delete data','the request used to pull this card was a GET request');
insert into cards values (4, default,1, 'what does CRUD stand for?','Create/Read/Update/Delete','');
insert into cards values (3, default,1, '','','');
insert into cards values (3, default,1, '','','');
insert into cards values (3, default,1, '','','');
insert into cards values (3, default,1, '','','');
insert into cards values (3, default,1, '','','');
insert into cards values (3, default,1, '','','');
insert into cards values (3, default,1, '','','');
insert into cards values (3, default,1, '','','');
insert into cards values (3, default,1, '','','');
insert into cards values (3, default,1, '','','');
insert into cards values (3, default,1, '','','');
insert into cards values (3, default,1, '','','');
insert into cards values (3, default,1, '','','');
insert into cards values (3, default,1, '','','');


--card data




