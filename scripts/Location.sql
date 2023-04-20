CREATE TABLE Location (
  location_id_PK INT PRIMARY KEY,
  location_building VARCHAR(MAX),
  location_floor VARCHAR(MAX),
  location_room VARCHAR(MAX),
  location_address_id_FK INT FOREIGN KEY REFERENCES Address(address_id_PK)
);