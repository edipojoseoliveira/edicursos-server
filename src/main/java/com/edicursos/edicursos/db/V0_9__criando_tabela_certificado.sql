CREATE TABLE public.certificado
(
    id serial NOT NULL,
    idaluno integer,
    idcurso integer,
    codigo character varying(50),
    PRIMARY KEY (id),
    FOREIGN KEY (idaluno)
        REFERENCES public.aluno (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    FOREIGN KEY (idcurso)
        REFERENCES public.curso (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.certificado
    OWNER to postgres;