CREATE TABLE `customer` (
                            `version` int DEFAULT NULL,
                            `added_at` datetime(6) DEFAULT NULL,
                            `updated_at` datetime(6) DEFAULT NULL,
                            `id` binary(16) NOT NULL,
                            `name` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `watch` (
                         `price` double DEFAULT NULL,
                         `created_at` datetime(6) DEFAULT NULL,
                         `updated_at` datetime(6) DEFAULT NULL,
                         `id` binary(16) NOT NULL,
                         `model` varchar(255) DEFAULT NULL,
                         `origin` varchar(255) DEFAULT NULL,
                         `serial_number` varchar(255) DEFAULT NULL,
                         `quantity_on_hand` int DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

