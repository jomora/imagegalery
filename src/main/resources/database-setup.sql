-- Table: public.customer

-- DROP TABLE public.customer;

CREATE TABLE public.customer
(
  id bigint NOT NULL DEFAULT nextval('"Customer_ID_seq"'::regclass),
  user_name character varying(80) NOT NULL,
  password character varying(80) NOT NULL,
  role character varying(80) NOT NULL,
  CONSTRAINT customer_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE public.customer
  OWNER TO postgres;

-- Constraint: public.customer_pk

-- ALTER TABLE public.customer DROP CONSTRAINT customer_pk;

ALTER TABLE public.customer
  ADD CONSTRAINT customer_pk PRIMARY KEY(id);  
  -- Table: public.image

-- DROP TABLE public.image;

CREATE TABLE public.image
(
  id bigint NOT NULL DEFAULT nextval('image_id_seq'::regclass),
  customer_id bigint NOT NULL,
  image bytea NOT NULL,
  name character varying(80),
  CONSTRAINT image_pk PRIMARY KEY (id),
  CONSTRAINT image_user_fk FOREIGN KEY (customer_id)
      REFERENCES public.customer (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.image
  OWNER TO postgres;

  -- Constraint: public.image_pk

-- ALTER TABLE public.image DROP CONSTRAINT image_pk;

ALTER TABLE public.image
  ADD CONSTRAINT image_pk PRIMARY KEY(id);

  -- Foreign Key: public.image_user_fk

-- ALTER TABLE public.image DROP CONSTRAINT image_user_fk;

ALTER TABLE public.image
  ADD CONSTRAINT image_user_fk FOREIGN KEY (customer_id)
      REFERENCES public.customer (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
