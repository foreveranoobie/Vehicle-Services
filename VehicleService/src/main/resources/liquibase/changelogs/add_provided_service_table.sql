CREATE TABLE IF NOT EXISTS public.provided_service (
  ID SERIAL PRIMARY KEY,
  NAME text NOT NULL UNIQUE,
);

INSERT INTO public.provided_service (NAME) VALUES ('BASIC');
INSERT INTO public.provided_service (NAME) VALUES ('CHASSIS');
INSERT INTO public.provided_service (NAME) VALUES ('ELECTRICAL');
INSERT INTO public.provided_service (NAME) VALUES ('ENGINES');
