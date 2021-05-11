INSERT INTO USERS (name, email, password, phone, avatar)
VALUES 
('Alexander Augusto', 'alexaasf1010@gmail.com', '$2a$10$LTdtg0/bnYaecoZqTMjXZO1w7CyzXsd1miN59Tvl6wUCXy1VleBPa', '35984529203', 'lazy-dev/user/default-avatar.png'),
('Alysson Henrique', 'alyssonhenrique1@hotmail.com', '$2a$10$LTdtg0/bnYaecoZqTMjXZO1w7CyzXsd1miN59Tvl6wUCXy1VleBPa', '35999999999', 'lazy-dev/user/default-avatar.png'),
('Ariany Cristina', 'arianycristina1010@gmail.com', '$2a$10$LTdtg0/bnYaecoZqTMjXZO1w7CyzXsd1miN59Tvl6wUCXy1VleBPa', '35999999991', 'lazy-dev/user/default-avatar.png'),
('Catarina Fernandes', 'catarina@gmail.com', '$2a$10$LTdtg0/bnYaecoZqTMjXZO1w7CyzXsd1miN59Tvl6wUCXy1VleBPa', '35999999992', 'lazy-dev/user/default-avatar.png');

INSERT INTO POST (description, media, owner_id)
VALUES 
('Olha essa música, é muito massa!!!', 'lazy-dev/post/default-post.jpg', 1),
('Não encontrei nada :(', 'lazy-dev/post/default-post.jpg', 1),
('Procuro pessoas para me ensinar a programar, pago bem :D', 'lazy-dev/post/default-post.jpg', 3),
('Se gostou, deixa o like!', 'lazy-dev/post/default-post.jpg', 2);

INSERT INTO REACTION (user_id, post_id)
VALUES (2, 1), (3, 1), (4, 1), (1, 2), (3, 2), (4, 3), (1, 4);

INSERT INTO COMMENT (description, user_id, post_id)
VALUES 
('Nossa, essa música é muito top!!!', 2, 1),
('Eu também amo essa música', 4, 1),
('Opa, se quiser posso te dar uma mãozinha, só me chamar no privado.', 1, 3),
('kkkkkkkkkk ri demais', 3, 2);

INSERT INTO FOLLOW (follower_id, following_id)
VALUES (1, 2), (2, 1), (2, 3), (3, 4);

INSERT INTO NOTIFICATION (description, url, receiver_id, sender_id)
VALUES 
('reagiu a sua publicação', '/post/1', 1, 2),
('comentou sua publicação', '/post/1', 1, 2),
('acabou de começar a seguir você', '/profile/2', 1, 2);