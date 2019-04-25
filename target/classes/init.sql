/*
    Database initialization script that runs on every web-application redeployment.
*/
DROP TABLE IF EXISTS poems;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL
);

CREATE TABLE poems (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    context TEXT NOT NULL,
    user_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES users(id)
);


INSERT INTO users (email, password) VALUES
	('user1@user1', 'user1'), -- 1
	('user2@user2', 'user2'), -- 2
	('user2@user3', 'user3'); -- 3

INSERT INTO poems (title, context, user_id) VALUES
	('titleone', 'contone', 1),   -- 1
	('titletwo', 'conttwo', 2),  -- 2
	('titlethree', 'contthree', 3); -- 3
