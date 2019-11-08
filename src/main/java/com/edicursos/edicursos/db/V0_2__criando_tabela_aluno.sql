CREATE TABLE public.aluno
(
    id serial NOT NULL,
    nome character varying(100),
    sobrenome character varying(100),
    idconta integer,
    PRIMARY KEY (id),
    FOREIGN KEY (idconta)
        REFERENCES public.conta (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.aluno
    OWNER to postgres;