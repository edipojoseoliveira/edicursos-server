CREATE TABLE public.atividade_questao
(
    id serial NOT NULL,
    idaula integer,
    questao character varying(255),
    resposta character varying(100),
    PRIMARY KEY (id),
    FOREIGN KEY (idaula)
        REFERENCES public.aluno (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.atividade_questao
    OWNER to postgres;