CREATE TABLE public.conta
(
    id serial NOT NULL,
    email character varying(255),
    senha character varying(10),
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.conta
    OWNER to postgres;