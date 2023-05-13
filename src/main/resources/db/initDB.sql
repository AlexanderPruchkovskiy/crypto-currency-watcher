DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS currencies;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;


CREATE TABLE currencies (
                       id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
                       currency_code    INTEGER NOT NULL,
                       symbol           VARCHAR                NOT NULL,
                       currentPrice     DOUBLE PRECISION NOT NULL
);

CREATE UNIQUE INDEX currencies_unique_currency_code_idx ON currencies (currency_code);

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    currency_id     INTEGER                  NOT NULL,
    userName         VARCHAR                 NOT NULL,
    regPrice         DOUBLE PRECISION        NOT NULL,
        FOREIGN KEY (currency_id) REFERENCES currencies (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX users_unique_currency_userName_idx ON users (currency_id,userName);
