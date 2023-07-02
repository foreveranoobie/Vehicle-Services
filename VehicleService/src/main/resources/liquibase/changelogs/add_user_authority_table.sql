CREATE TABLE IF NOT EXISTS public.user_authority (
  ID SERIAL PRIMARY KEY,
  USER_ID integer NOT NULL,
  AUTHORITY_ID integer NOT NULL,
  CONSTRAINT fk_authority_id FOREIGN KEY (AUTHORITY_ID) REFERENCES public.authority (ID),
  CONSTRAINT fk_user_id FOREIGN KEY (USER_ID) REFERENCES public.users (ID),
  CONSTRAINT unique_user_authority UNIQUE (USER_ID, AUTHORITY_ID)
);