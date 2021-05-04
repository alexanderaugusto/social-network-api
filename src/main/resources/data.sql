INSERT INTO USER (name, email, password, phone, avatar)
VALUES 
('Alexander Augusto', 'alexaasf1010@gmail.com', '$2a$10$LTdtg0/bnYaecoZqTMjXZO1w7CyzXsd1miN59Tvl6wUCXy1VleBPa', '35984529203', 'https://www.pngkey.com/png/detail/193-1938385_-pikachu-avatar.png'),
('Alysson Henrique', 'alyssonhenrique1@hotmail.com', '$2a$10$LTdtg0/bnYaecoZqTMjXZO1w7CyzXsd1miN59Tvl6wUCXy1VleBPa', '35999999999', 'https://i.pinimg.com/originals/a9/74/7d/a9747dc7e0ec8982556a3d50d28762e7.jpg'),
('Ariany Cristina', 'arianycristina1010@gmail.com', '$2a$10$LTdtg0/bnYaecoZqTMjXZO1w7CyzXsd1miN59Tvl6wUCXy1VleBPa', '35999999991', 'https://img.favpng.com/6/22/20/charmander-pok-mon-snap-charizard-png-favpng-SE9gNBfWKmvQDHKGhpvPdYCFt.jpg'),
('Catarina Fernandes', 'catarina@gmail.com', '$2a$10$LTdtg0/bnYaecoZqTMjXZO1w7CyzXsd1miN59Tvl6wUCXy1VleBPa', '35999999992', 'https://i1.sndcdn.com/avatars-000092784112-x9jopb-t240x240.jpg');

INSERT INTO POST (description, media, owner_id)
VALUES 
('Olha essa música, é muito massa!!!', 'https://i.pinimg.com/originals/da/ac/6f/daac6f2d4d76503c7d9810e808e59e63.jpg', 1),
('Não encontrei nada :(', 'https://i.pinimg.com/originals/da/ac/6f/daac6f2d4d76503c7d9810e808e59e63.jpg', 1),
('Procuro pessoas para me ensinar a programar, pago bem :D', 'https://i.pinimg.com/originals/da/ac/6f/daac6f2d4d76503c7d9810e808e59e63.jpg', 3),
('Se gostou, deixa o like!', 'https://i.pinimg.com/736x/39/5c/cb/395ccb62c91e09fc7507c0e5597d7fe1.jpg', 2);

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
