CREATE TABLE t_order (
         id BIGINT PRIMARY KEY AUTO_INCREMENT,
         user_id BIGINT,
         product_id BIGINT,
         count INT,
         status VARCHAR(20) -- TRY / CONFIRM / CANCEL
);

CREATE TABLE t_stock (
         product_id BIGINT PRIMARY KEY,
         total INT,
         frozen INT
);