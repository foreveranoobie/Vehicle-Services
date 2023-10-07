CREATE TABLE IF NOT EXISTS test.vehicle_service_provided_services (
    ID SERIAL PRIMARY KEY,
    VEHICLE_SERVICE_ID integer NOT NULL,
    PROVIDED_SERVICE_ID integer NOT NULL,
    CONSTRAINT fk_provided_service_id FOREIGN KEY (PROVIDED_SERVICE_ID) REFERENCES test.provided_service (ID),
    CONSTRAINT fk_vehicle_service_id FOREIGN KEY (VEHICLE_SERVICE_ID) REFERENCES test.vehicle_service (ID),
    CONSTRAINT unique_vehicle_service_provided_services UNIQUE (VEHICLE_SERVICE_ID, PROVIDED_SERVICE_ID)
);