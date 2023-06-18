CREATE TABLE "users" (
id serial primary key,
email text not null unique,
password text not null,
coin int default 100,
created_at timestamp without time zone default now()
);