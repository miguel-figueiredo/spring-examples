CREATE TABLE public.ocean
(
    id UUID PRIMARY KEY,
    "name" varchar(255) NULL
);

CREATE TABLE public.fish
(
    id UUID PRIMARY KEY,
    "name" varchar(255) NULL,
    ocean_id UUID,
    FOREIGN KEY(ocean_id)
    REFERENCES public.ocean(id)
);