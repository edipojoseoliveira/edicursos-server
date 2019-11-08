CREATE TABLE public.aluno_curso
(
    id serial NOT NULL,
    idaluno integer,
    idcurso integer,
    situacao integer,
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

ALTER TABLE public.aluno_curso
    OWNER to postgres;