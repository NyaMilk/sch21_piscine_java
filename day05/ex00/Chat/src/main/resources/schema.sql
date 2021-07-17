CREATE TABLE Users
(
    id       SERIAL,
    login    text NOT NULL,
    password text,
    PRIMARY KEY (id)
);

CREATE TABLE ChatRooms
(
    id      SERIAL,
    name    text NOT NULL,
    creator int,
    FOREIGN KEY (creator) REFERENCES Users (id),
    PRIMARY KEY (id)
);

CREATE TABLE Messages
(
    id        SERIAL,
    author    int,
    chatRoom  int,
    message   text,
    createdAt timestamp DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author) REFERENCES Users (id),
    FOREIGN KEY (chatRoom) REFERENCES ChatRooms (id),
    PRIMARY KEY (id)
);

CREATE TABLE createdChatRooms
(
    idUser int,
    idRoom int,
    FOREIGN KEY (idUser) REFERENCES Users (id),
    FOREIGN KEY (idRoom) REFERENCES ChatRooms (id)
);

CREATE TABLE activeChatRooms
(
    idUser int,
    idRoom int,
    FOREIGN KEY (idUser) REFERENCES Users (id),
    FOREIGN KEY (idRoom) REFERENCES ChatRooms (id)
);

CREATE TABLE chatRoomMessage
(
    idRoom    int,
    idMessage int,
    FOREIGN KEY (idRoom) REFERENCES ChatRooms (id),
    FOREIGN KEY (idMessage) REFERENCES Messages (id)
);
