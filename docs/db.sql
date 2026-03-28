CREATE TABLE t_order (
             id BIGINT AUTO_INCREMENT PRIMARY KEY,
             tx_id VARCHAR(64) NOT NULL UNIQUE,
             product_id BIGINT NOT NULL,
             status VARCHAR(20) NOT NULL,  -- TRY / CONFIRM / CANCEL
             create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
             update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE t_stock (
             product_id BIGINT PRIMARY KEY,
             total INT NOT NULL,
             frozen INT NOT NULL DEFAULT 0
);

INSERT INTO t_stock(product_id, total, frozen)
VALUES (1, 10, 0);
