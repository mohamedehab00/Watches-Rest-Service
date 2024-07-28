drop table if exists category;
drop table if exists watch_category;

create TABLE category(
    id binary(16) NOT NULL PRIMARY KEY ,
    description varchar(50),
    created_at timestamp,
    updated_at timestamp DEFAULT null,
    version int DEFAULT null
) ENGINE = InnoDB;

create TABLE watch_category(
    watch_id binary(16) NOT NULL,
    category_id binary(16) NOT NULL,
    primary key (watch_id, category_id),
    constraint pc_watch_id_fk FOREIGN KEY (watch_id) references watch (id),
    constraint pc_category_id_fk FOREIGN KEY (category_id) references category (id)
) ENGINE = InnoDB;