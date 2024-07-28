drop TABLE if exists watch_order_shipment;

CREATE TABLE watch_order_shipment(
    id binary(16) NOT NULL PRIMARY KEY,
    tracking_number varchar(50) unique,
    created_at timestamp,
    updated_at timestamp DEFAULT NULL,
    version int DEFAULT NULL
)ENGINE = InnoDB;

ALTER TABLE watch_order
ADD COLUMN watch_order_shipment_id binary(16);

ALTER TABLE watch_order
    ADD CONSTRAINT bos_shipment_fk FOREIGN KEY (watch_order_shipment_id) references watch_order_shipment (id);