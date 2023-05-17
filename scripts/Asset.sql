CREATE TABLE Asset (
  asset_id_PK INT PRIMARY KEY,
  asset_name VARCHAR(MAX),
  asset_acquisitiondate Date,
  asset_description VARCHAR(MAX),
  asset_status VARCHAR(MAX),
  asset_manufacturer VARCHAR(MAX),
  asset_location_id_FK INT FOREIGN KEY REFERENCES Location(location_id_PK)
);