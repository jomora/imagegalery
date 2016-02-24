-- Sequence: public."Customer_ID_seq"

-- DROP SEQUENCE public."Customer_ID_seq";

CREATE SEQUENCE public."Customer_ID_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public."Customer_ID_seq"
  OWNER TO postgres;

  -- Sequence: public.hibernate_sequence

-- DROP SEQUENCE public.hibernate_sequence;

CREATE SEQUENCE public.hibernate_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 13
  CACHE 1;
ALTER TABLE public.hibernate_sequence
  OWNER TO postgres;

-- Sequence: public.image_id_seq

-- DROP SEQUENCE public.image_id_seq;

CREATE SEQUENCE public.image_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.image_id_seq
  OWNER TO postgres;

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
