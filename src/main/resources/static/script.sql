create table comment_picture
(
    order_ID        int          not null,
    picture_address varchar(512) not null,
    constraint `PRIMARY`
        primary key (order_ID, picture_address)
);

create table customer
(
    customer_ID       int auto_increment
        constraint `PRIMARY`
        primary key,
    customer_nickname varchar(1024) not null,
    phone_number      varchar(1024) not null,
    avatar_address    varchar(1024) not null
);

create table customerorder_info
(
    customer_ID int not null,
    order_ID    int not null,
    constraint `PRIMARY`
        primary key (customer_ID, order_ID)
);

create table dish
(
    dish_ID          int auto_increment
        constraint `PRIMARY`
        primary key,
    dish_name        varchar(1024)   not null,
    dish_nuitprice   float           not null,
    dish_quantity    varchar(1024)   null,
    monthly_sales    int   default 0 not null,
    dish_rating      float default 5 not null,
    dish_description varchar(1024)   null,
    picture_address  varchar(1024)   null
);

create table dish_picture
(
    dish_ID         int          not null,
    picture_address varchar(512) not null,
    constraint `PRIMARY`
        primary key (dish_ID, picture_address),
    constraint dish_picture_dish_dish_ID_fk
        foreign key (dish_ID) references dish (dish_ID)
            on update cascade on delete cascade
);

create table dish_type
(
    related_ID int auto_increment
        constraint `PRIMARY`
        primary key,
    the_type   varchar(1024) not null,
    dish_ID    int           not null,
    constraint dish_type_dish_ID_uindex
        unique (dish_ID),
    constraint dish_type_related_ID_uindex
        unique (related_ID),
    constraint dish_type_dish_dish_ID_fk
        foreign key (dish_ID) references dish (dish_ID)
            on update cascade on delete cascade
);

create table dishsales
(
    dish_ID       int      not null,
    sales_time    datetime not null,
    monthly_sales int      not null,
    constraint `PRIMARY`
        primary key (dish_ID, sales_time),
    constraint dishsales_dish_dish_ID_fk
        foreign key (dish_ID) references dish (dish_ID)
);

create table openid
(
    openID      varchar(512) not null
        constraint `PRIMARY`
        primary key,
    customer_ID int          null,
    constraint openID_openID_uindex
        unique (openID),
    constraint openID_customer_customer_ID_fk
        foreign key (customer_ID) references customer (customer_ID)
            on update cascade on delete cascade
);

create table order_information
(
    order_ID      int auto_increment
        constraint `PRIMARY`
        primary key,
    table_ID      int                                        not null,
    total_amount  float                                      not null,
    order_time    datetime                                   not null,
    order_status  enum ('Submitted', 'settled', 'commented') not null,
    comment       text charset utf8                          null,
    item_quantity int                                        not null
);

create table orderinfo_dishes
(
    order_ID    int not null,
    dish_ID     int not null,
    dish_number int not null,
    constraint `PRIMARY`
        primary key (order_ID, dish_ID)
);

create table rating
(
    customer_ID int   not null,
    order_ID    int   not null,
    dish_ID     int   not null,
    rating      float not null,
    constraint `PRIMARY`
        primary key (customer_ID, dish_ID, order_ID)
);


