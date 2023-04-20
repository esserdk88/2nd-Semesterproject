CREATE TABLE Sparepart_Used (
  sparepart_used_amount int,

  sparepart_used_workorder_id_FK INT,
  sparepart_used_sparepart_id_FK INT,

  CONSTRAINT sparepart_used_id_PK PRIMARY KEY (sparepart_used_workorder_id_FK, sparepart_used_sparepart_id_FK)
);