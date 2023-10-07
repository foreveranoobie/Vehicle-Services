CREATE TABLE IF NOT EXISTS public.vehicle_service_provided_services (
    ID SERIAL PRIMARY KEY,
    VEHICLE_SERVICE_ID integer NOT NULL,
    PROVIDED_SERVICE_ID integer NOT NULL,
    CONSTRAINT fk_provided_service_id FOREIGN KEY (PROVIDED_SERVICE_ID) REFERENCES public.provided_service (ID),
    CONSTRAINT fk_vehicle_service_id FOREIGN KEY (VEHICLE_SERVICE_ID) REFERENCES public.vehicle_service (ID),
    CONSTRAINT unique_vehicle_service_provided_services UNIQUE (VEHICLE_SERVICE_ID, PROVIDED_SERVICE_ID)
);