CREATE TABLE calculations (
    id uuid not null
        constraint calculation_pkey primary key,
    created_date timestamp DEFAULT now(),
    modified_date timestamp DEFAULT now(),
    name varchar(225) NOT NULL
        constraint calc_name_uindex unique,
    description varchar(1000) NOT NULL
);

CREATE TABLE variables (
    id uuid not null
        constraint variable_pkey primary key,
    created_date timestamp DEFAULT now(),
    modified_date timestamp DEFAULT now(),
    name varchar(225) NOT NULL
        constraint var_name_uindex unique,
    value float NOT NULL,
    calculation_id uuid references calculations(id) NOT NULL
);

CREATE TABLE operations (
    id uuid not null
        constraint user_pkey primary key,
    created_date timestamp DEFAULT now(),
    modified_date timestamp DEFAULT now(),
    name varchar(225) NOT NULL
        constraint oper_name_uindex unique,
    type varchar(225) NOT NULL,
    operand_a varchar(225) NOT NULL,
    operand_b varchar(225) NOT NULL,
    result float,
    calculation_id uuid references calculations(id) NOT NULL
);