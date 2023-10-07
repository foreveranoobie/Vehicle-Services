CREATE TABLE IF NOT EXISTS test.vehicle_service (
  ID serial PRIMARY KEY,
  NAME text NOT NULL,
  COUNTRY text NOT NULL,
  CITY text NOT NULL,
  ADDRESS text NOT NULL
);