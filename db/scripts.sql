drop table if exists `order`;
create table `order` (
                         quantity integer,
                         total_value float,
                         unit_value float,
                         client_id bigint,
                         control_number bigint,
                         id bigint not null auto_increment primary key,
                         register_date datetime,
                         product_name varchar(255)
) engine=InnoDB;
