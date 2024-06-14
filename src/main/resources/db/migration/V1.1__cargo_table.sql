CREATE TABLE IF NOT EXISTS public.cargo_model
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name text COLLATE pg_catalog."default" NOT NULL,
    chat_id integer NOT NULL,
    scheme text NOT NULL,
    car_id integer NOT NULL,
    CONSTRAINT cargo_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.cargo_model
    OWNER to root;