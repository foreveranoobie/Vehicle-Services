CREATE TABLE IF NOT EXISTS public.users (
  ID SERIAL PRIMARY KEY,
  USERNAME text NOT NULL UNIQUE,
  PASSWORD text NOT NULL,
  NON_EXPIRED boolean NOT NULL DEFAULT TRUE,
  NON_LOCKED boolean NOT NULL DEFAULT TRUE,
  CREDENTIALS_NON_EXPIRED boolean NOT NULL DEFAULT TRUE,
  ENABLED boolean NOT NULL DEFAULT TRUE
);