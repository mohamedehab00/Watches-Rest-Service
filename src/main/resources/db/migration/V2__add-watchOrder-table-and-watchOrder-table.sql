CREATE TABLE `watch_order` (
                         `created_at` datetime(6) DEFAULT NULL,
                         `updated_at` datetime(6) DEFAULT NULL,
                         `id` binary(16) NOT NULL,
                         `customer_id` binary(16) NOT NULL,
                         `customer_ref` varchar(255) DEFAULT NULL,
                         `version` BIGINT DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         CONSTRAINT FOREIGN KEY (`customer_id`) REFERENCES customer (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `watch_order_line` (
                               `id` binary(16) NOT NULL,
                               `watch_id` binary(16) NOT NULL,
                               `watch_order_id` binary(16) NOT NULL,
                               `created_at` datetime(6) DEFAULT NULL,
                               `updated_at` datetime(6) DEFAULT NULL,
                               `order_quantity` int DEFAULT NULL,
                               `quantity_allocated` int DEFAULT NULL,
                               `version` BIGINT DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               CONSTRAINT FOREIGN KEY (`watch_order_id`) REFERENCES watch_order (`id`),
                               CONSTRAINT FOREIGN KEY (`watch_id`) REFERENCES watch (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;