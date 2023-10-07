CREATE TABLE IF NOT EXISTS public.vehicle_type (
  ID serial PRIMARY KEY,
  NAME text NOT NULL UNIQUE
);

INSERT INTO public.vehicle_type (NAME) VALUES ('CARS');
INSERT INTO public.vehicle_type (NAME) VALUES ('BUSES');
INSERT INTO public.vehicle_type (NAME) VALUES ('TRUCKS');
INSERT INTO public.vehicle_type (NAME) VALUES ('MOTORCYCLES');
INSERT INTO public.vehicle_type (NAME) VALUES ('EVs');
INSERT INTO public.vehicle_type (NAME) VALUES ('OTHERS');