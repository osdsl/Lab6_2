CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    login CHARACTER(30),
    password CHARACTER(60)

);

CREATE TABLE IF NOT EXISTS roles
(
    id SERIAL PRIMARY KEY ,
    name varchar(30)
);

CREATE TABLE IF NOT EXISTS roles_x_users
(
    user_id bigint not null,
    role_id bigint not null,
    PRIMARY KEY(user_id, role_id),
    FOREIGN KEY(user_id) references users(id),
    FOREIGN KEY(role_id) references roles(id)
);