CREATE TABLE IF NOT EXISTS public.vehicle_service_vehicle_type (
  VEHICLE_SERVICE_ID integer NOT NULL,
  VEHICLE_TYPE_ID integer NOT NULL,
  CONSTRAINT vehicle_service_vehicle_type_pkey PRIMARY KEY (VEHICLE_SERVICE_ID, VEHICLE_TYPE_ID),
  CONSTRAINT vehicle_service_vehicle_type_vehicle_service_id_fkey FOREIGN KEY (VEHICLE_SERVICE_ID)
      REFERENCES public.vehicle_service (ID)
      ON DELETE CASCADE,
  CONSTRAINT vehicle_service_vehicle_type_vehicle_type_id_fkey FOREIGN KEY (VEHICLE_TYPE_ID)
      REFERENCES public.vehicle_type (ID)
       ON DELETE CASCADE
);