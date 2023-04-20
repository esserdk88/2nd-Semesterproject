CREATE TABLE Sparepart (
  sparepart_id_PK INT PRIMARY KEY,
  sparepart_name VARCHAR(MAX),
  sparepart_stock_amount int,
  sparepart_price decimal,
  sparepart_supplier_CVR_FK INT FOREIGN KEY REFERENCES Supplier(supplier_CVR_PK)
);