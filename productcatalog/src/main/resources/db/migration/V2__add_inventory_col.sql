ALTER TABLE product
    ADD inventory_count INT NULL;

ALTER TABLE product
    MODIFY inventory_count INT NOT NULL;

DROP TABLE category_seq;

DROP TABLE jt_user_seq;

DROP TABLE orders_seq;

DROP TABLE price_seq;

DROP TABLE product_seq;

DROP TABLE st_user_seq;

DROP TABLE tpc_user_seq;

ALTER TABLE st_user
    MODIFY attendance DOUBLE NOT NULL;

ALTER TABLE st_user
    MODIFY average_rating DOUBLE NOT NULL;

ALTER TABLE st_user
    MODIFY psp DOUBLE NOT NULL;

ALTER TABLE st_user
    MODIFY user_type INT NULL;