CREATE TABLE public.greeting (
  id BIGINT CONSTRAINT greeting_pk_id PRIMARY KEY,
  name VARCHAR(255) NOT NULL CONSTRAINT greeting_uniq_name UNIQUE,
  salutation VARCHAR(255) NOT NULL
);

CREATE SEQUENCE greeting_seq_id OWNED BY public.greeting.id;
