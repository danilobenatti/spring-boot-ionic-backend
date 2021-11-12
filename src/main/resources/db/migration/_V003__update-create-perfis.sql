-- Table: public.perfis

CREATE TABLE public.perfis
(
    cliente_id integer NOT NULL,
    perfil integer NOT NULL,
    CONSTRAINT perfis_pkey PRIMARY KEY (cliente_id, perfil),
    CONSTRAINT fk_perfil__cliente_id FOREIGN KEY (cliente_id)
        REFERENCES public.cliente(id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE;
);
