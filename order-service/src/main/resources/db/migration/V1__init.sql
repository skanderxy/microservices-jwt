CREATE TABLE t_orders
(
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    order_number VARCHAR(255) DEFAULT NULL,
    sku_code     VARCHAR(255) NOT NULL,
    price        DECIMAL(19, 2) CHECK (price >= 0),
    quantity     INT          NOT NULL CHECK (quantity >= 0),
    PRIMARY KEY (id)
);