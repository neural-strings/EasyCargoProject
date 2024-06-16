CREATE TABLE IF NOT EXISTS public.car_model
(
    cargo_height integer,
    cargo_width integer,
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    chat_id bigint,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT car_model_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.car_model
    OWNER to "root";