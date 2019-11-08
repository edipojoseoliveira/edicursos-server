CREATE TABLE public.atividade_resposta_aluno
(
    id serial NOT NULL,
    idatividadequestao integer,
    idalunoaula integer,
    resposta character varying(100),
    certa boolean,
    PRIMARY KEY (id),
    FOREIGN KEY (idatividadequestao)
        REFERENCES public.atividade_questao (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    FOREIGN KEY (idalunoaula)
        REFERENCES public.aluno_aula (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.atividade_resposta_aluno
    OWNER to postgres;