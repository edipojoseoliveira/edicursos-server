CREATE TABLE public.aula
(
    id serial NOT NULL,
    idcurso integer,
    nome character varying(255),
    descricao character varying(999),
    url character varying(100),
    tipo integer,
    PRIMARY KEY (id),
    FOREIGN KEY (idcurso)
        REFERENCES public.curso (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.aula
    OWNER to postgres;