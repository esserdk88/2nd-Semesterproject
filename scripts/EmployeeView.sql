CREATE VIEW ReferenceView AS
SELECT
  ref.reference_CVR_PK,
  ref.reference_name,
  ref.reference_email,
  ref.reference_phone,
  ref.reference_contact,
  addr.address_id_PK,
  addr.address_streetname,
  addr.address_streetnumber,
  addr.address_cityname,
  addr.address_zipcode
FROM
  Reference AS ref
LEFT JOIN Address AS addr ON ref.reference_address_id_FK = addr.address_id_PK;
