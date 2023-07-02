CREATE TABLE IF NOT EXISTS public.vehicle_service (
  ID serial PRIMARY KEY,
  NAME text NOT NULL,
  COUNTRY text NOT NULL,
  CITY text NOT NULL,
  ADDRESS text NOT NULL
);