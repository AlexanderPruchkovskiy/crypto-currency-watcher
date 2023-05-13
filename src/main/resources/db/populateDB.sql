
DELETE FROM users;
DELETE FROM currencies;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO currencies ( currency_code, symbol, currentPrice) VALUES
(90, 'BTC', 26000),
(80, 'ETH', 1760),
(48543, 'SOL', 20);



INSERT INTO users (currency_id, userName, regPrice)
VALUES (100000, 'User1', 26000),
       (100000, 'User2', 26000),
       (100001, 'User3', 1760),
       (100002, 'User1', 20);

