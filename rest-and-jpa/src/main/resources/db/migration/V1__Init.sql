CREATE TABLE public.fish
(
    id SERIAL PRIMARY KEY,
    "name" varchar(255) NULL
);

CREATE TABLE public.fish_prey_list
(
    fish_id NUMERIC,
    prey_list_id NUMERIC
);

CREATE TABLE public.prey
(
    id SERIAL PRIMARY KEY,
    "name" varchar(255) NULL,
    fish_id NUMERIC
);