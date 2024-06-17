INSERT INTO watchesdb.watch
(`id`,`model`,  `origin`, `serial_number`, `price`, `created_at`, `updated_at`)
    VALUES('f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454','Casio','Spain','1531295132',1500,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
    ('018b2f19-e79e-7d6a-a56d-29feb6211b04','Rolex','Holland','1548795132',1400,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);

INSERT INTO watchesdb.customer
(`id`,`name`,  `version`, `added_at`, `updated_at`)
VALUES('f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454','Mohamed Ehab',1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
      ('018b2f19-e79e-7d6a-a56d-29feb6211b04','Ahmed Ali',2,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);