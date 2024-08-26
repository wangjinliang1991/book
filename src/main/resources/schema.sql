create table if not exists product_item (
    id int primary key,
    name varchar(8) not null,
    pid int not null
);
create table if not exists business_launch (
    id int auto_increment primary key not null,
    business_detail varchar(32) not null,
    target_city varchar(32),
    target_sex varchar(8),
    target_product varchar(32)
    );
create table if not exists products (
   id int auto_increment primary key not null,
   product_id varchar(8) not null,
   send_red_bag int not null
    );
