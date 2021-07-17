INSERT INTO Users (login, password) VALUES ('Clink', 'Clink');
INSERT INTO Users (login, password) VALUES ('HanSolo', 'HanSolo');
INSERT INTO Users (login, password) VALUES ('Wookiee', 'Wookiee');
INSERT INTO Users (login, password) VALUES ('Jedi', 'Jedi');
INSERT INTO Users (login, password) VALUES ('Chewbacca', 'Chewbacca');

INSERT INTO ChatRooms (name, creator) VALUES ('Jedi', 1);
INSERT INTO ChatRooms (name, creator) VALUES ('Heroes', 2);
INSERT INTO ChatRooms (name, creator) VALUES ('Fun', 3);
INSERT INTO ChatRooms (name, creator) VALUES ('Force', 4);
INSERT INTO ChatRooms (name, creator) VALUES ('Alliance', 5);

INSERT INTO Messages (author, chatRoom, message) VALUES (1, 1, 'Hello!');
INSERT INTO Messages (author, chatRoom, message) VALUES (2, 1, 'Kusmene!');
INSERT INTO Messages (author, chatRoom, message) VALUES (2, 5, 'Kusmene every one!');
INSERT INTO Messages (author, chatRoom, message) VALUES (4, 5, 'May the Force be with you!');
INSERT INTO Messages (author, chatRoom, message) VALUES (3, 3, 'Not fun!');

INSERT INTO createdChatRooms (idUser, idRoom) VALUES (1, 1);
INSERT INTO createdChatRooms (idUser, idRoom) VALUES (2, 2);
INSERT INTO createdChatRooms (idUser, idRoom) VALUES (3, 3);
INSERT INTO createdChatRooms (idUser, idRoom) VALUES (4, 4);
INSERT INTO createdChatRooms (idUser, idRoom) VALUES (5, 5);

INSERT INTO activeChatRooms (idUser, idRoom) VALUES (1, 1);
INSERT INTO activeChatRooms (idUser, idRoom) VALUES (2, 1);
INSERT INTO activeChatRooms (idUser, idRoom) VALUES (2, 5);
INSERT INTO activeChatRooms (idUser, idRoom) VALUES (4, 5);
INSERT INTO activeChatRooms (idUser, idRoom) VALUES (3, 3);

INSERT INTO chatRoomMessage (idRoom, idMessage) VALUES (1, 1);
INSERT INTO chatRoomMessage (idRoom, idMessage) VALUES (1, 2);
INSERT INTO chatRoomMessage (idRoom, idMessage) VALUES (5, 3);
INSERT INTO chatRoomMessage (idRoom, idMessage) VALUES (5, 4);
INSERT INTO chatRoomMessage (idRoom, idMessage) VALUES (3, 5);