CREATE TABLE IF NOT EXISTS test.vehicle_type (
  ID serial PRIMARY KEY,
  NAME text NOT NULL UNIQUE
);