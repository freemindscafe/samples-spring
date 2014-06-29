CREATE TABLE publisher
(
    publisher_id INTEGER NOT NULL,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY(publisher_id));

CREATE TABLE author
(
    author_id INTEGER NOT NULL,
    first_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128) NOT NULL,
    PRIMARY KEY(author_id));

CREATE TABLE book
(
    book_id INTEGER NOT NULL,
    title VARCHAR(255) NOT NULL,
    isbn VARCHAR(24) NOT NULL,
    publisher_id INTEGER NOT NULL,
    author_id INTEGER NOT NULL,
    PRIMARY KEY(book_id),
    FOREIGN KEY (publisher_id) REFERENCES publisher (publisher_id)
    ,
    FOREIGN KEY (author_id) REFERENCES author (author_id)
    );