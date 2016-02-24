- Table: public.customer

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
