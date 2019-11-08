CREATE TABLE public.curso
(
    id serial NOT NULL,
    area character varying(100),
    nome character varying(255),
    descricao character varying(999),
    cor character varying(6),
    publicado boolean,
    url character varying(100),
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.curso
    OWNER to postgres;