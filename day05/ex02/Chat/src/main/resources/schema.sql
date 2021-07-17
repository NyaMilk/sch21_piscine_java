CREATE TABLE Users
(
--     id       SERIAL,
    id       BIGSERIAL,
    login    text NOT NULL,
    password text,
    PRIMARY KEY (id)
);

CREATE TABLE ChatRooms
(
--     id      SERIAL,
    id      BIGSERIAL,
    name    text NOT NULL,
    creator bigint,
    FOREIGN KEY (creator) REFERENCES Users (id),
    PRIMARY KEY (id)
);

CREATE TABLE Messages
(
--     id        SERIAL,
    id        BIGSERIAL,
    author    bigint,
    chatRoom  bigint,
    message   text,
    createdAt timestamp DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author) REFERENCES Users (id),
    FOREIGN KEY (chatRoom) REFERENCES ChatRooms (id),
    PRIMARY KEY (id)
);

CREATE TABLE createdChatRooms
(
    idUser bigint,
    idRoom bigint,
    FOREIGN KEY (idUser) REFERENCES Users (id),
    FOREIGN KEY (idRoom) REFERENCES ChatRooms (id)
);

CREATE TABLE activeChatRooms
(
    idUser bigint,
    idRoom bigint,
    FOREIGN KEY (idUser) REFERENCES Users (id),
    FOREIGN KEY (idRoom) REFERENCES ChatRooms (id)
);

CREATE TABLE chatRoomMessage
(
    idRoom    bigint,
    idMessage bigint,
    FOREIGN KEY (idRoom) REFERENCES ChatRooms (id),
    FOREIGN KEY (idMessage) REFERENCES Messages (id)
);
