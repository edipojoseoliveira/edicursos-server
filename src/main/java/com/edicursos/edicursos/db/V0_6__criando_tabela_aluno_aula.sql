CREATE TABLE public.aluno_aula
(
    id serial NOT NULL,
    idaluno integer,
    idaula integer,
    situacao integer,
    PRIMARY KEY (id),
    FOREIGN KEY (idaluno)
        REFERENCES public.aluno (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    FOREIGN KEY (idaula)
        REFERENCES public.aula (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.aluno_aula
    OWNER to postgres;