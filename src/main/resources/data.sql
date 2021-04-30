INSERT INTO USER (name, email, password, phone)
VALUES 
('Alexander Augusto', 'alexaasf1010@gmail.com', '123', '35984529203'),
('Alysson Henrique', 'alyssonhenrique1@hotmail.com', '123', '35999999999'),
('Ariany Cristina', 'arianycristina1010@gmail.com', '123', '35999999991'),
('Ana Laura', 'analaura@gmail.com', '123', '35999999992');

INSERT INTO POST (description, media, owner_id)
VALUES 
('Olha essa música, é muito massa!!!', 'https://www.youtube.com/watch?v=kPBzTxZQG5Q', 1),
('Não encontrei nada :(', 'https://i.pinimg.com/originals/da/ac/6f/daac6f2d4d76503c7d9810e808e59e63.jpg', 1),
('Procuro pessoas para me ensinar a programar, pago bem :D', null, 3),
('Se gostou, deixa o like!', 'https://i.pinimg.com/736x/39/5c/cb/395ccb62c91e09fc7507c0e5597d7fe1.jpg', 2);

INSERT INTO REACTION (user_id, post_id)
VALUES (2, 1), (3, 1), (4, 1), (1, 2), (3, 2), (4, 3);

INSERT INTO COMMENT (description, user_id, post_id)
VALUES 
('Nossa, essa música é muito top!!!', 2, 1),
('Eu também amo essa música', 4, 1),
('Opa, se quiser posso te dar uma mãozinha, só me chamar no privado.', 1, 3),
('kkkkkkkkkk ri demais', 3, 2);

INSERT INTO FOLLOW (follower_id, following_id)
VALUES (1, 2), (2, 1), (2, 3), (3, 4);

