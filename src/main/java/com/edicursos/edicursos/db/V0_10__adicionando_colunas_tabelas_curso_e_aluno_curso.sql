ALTER TABLE public.curso
  ADD COLUMN cargahoraria integer;

ALTER TABLE public.aluno_curso
  ADD COLUMN datainicio date;

ALTER TABLE public.aluno_curso
  ADD COLUMN datatermino date;