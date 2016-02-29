
DROP TABLE IF EXISTS public.image;
DROP TABLE IF EXISTS public.authority;
DROP TABLE IF EXISTS public.customer;
DROP SEQUENCE IF EXISTS public.hibernate_sequence;
DROP SEQUENCE IF EXISTS public.image_id_seq;
DROP SEQUENCE IF EXISTS public.authorities_id_seq;
DROP SEQUENCE IF EXISTS public."Customer_ID_seq";

-- Sequence: public."Customer_ID_seq"

-- 

CREATE SEQUENCE public."Customer_ID_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 3
  CACHE 1;

CREATE SEQUENCE public.authorities_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 11
  CACHE 1;
ALTER TABLE public.authorities_id_seq
  OWNER TO postgres;


CREATE SEQUENCE public.hibernate_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 32
  CACHE 1;


CREATE SEQUENCE public.image_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

  
CREATE TABLE public.customer
(
  id bigint NOT NULL DEFAULT nextval('"Customer_ID_seq"'::regclass),
  user_name character varying(80) NOT NULL,
  password character varying(80) NOT NULL,
  enabled boolean NOT NULL,
  CONSTRAINT customer_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.customer
  OWNER TO postgres;


CREATE TABLE public.authority
(
  id bigint NOT NULL DEFAULT nextval('authorities_id_seq'::regclass),
  user_name character varying(80),
  authority character varying(80),
  CONSTRAINT authority_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.authority
  OWNER TO postgres;


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
