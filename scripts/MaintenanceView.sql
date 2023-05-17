CREATE VIEW MaintenanceView AS
SELECT
  wo.workorder_id_PK,
  wo.workorder_title,
  wo.workorder_type,
  wo.workorder_startdate,
  wo.workorder_enddate,
  wo.workorder_priority,
  wo.workorder_description,
  wo.workorder_finished,
  wo.workorder_interval,
  wo.workorder_repeatable,
  emp.employee_id_PK,
  emp.employee_start_date,
  emp.employee_cpr,
  emp.employee_position,
  emp.employee_phone,
  emp.employee_email,
  emp.employee_name,
  a.asset_id_PK,
  a.asset_name,
  a.asset_acquisitiondate,
  a.asset_description,
  a.asset_status,
  a.asset_manufacturer,
  loc.location_id_PK,
  loc.location_building,
  loc.location_floor,
  loc.location_room,
  adr.address_id_PK AS location_address_id_PK,
  adr.address_cityname AS location_address_cityname,
  adr.address_streetname AS location_address_streetname,
  adr.address_streetnumber AS location_address_streetnumber,
  adr.address_zipcode AS location_address_zipcode,
  adr2.address_id_PK AS employee_address_id_PK,
  adr2.address_cityname AS employee_address_cityname,
  adr2.address_streetname AS employee_address_streetname,
  adr2.address_streetnumber AS employee_address_streetnumber,
  adr2.address_zipcode AS employee_address_zipcode
FROM
  Workorder AS wo
LEFT JOIN Employee AS emp ON wo.workorder_employee_id_FK = emp.employee_id_PK
LEFT JOIN Asset AS a ON wo.workorder_asset_id_FK = a.asset_id_PK
LEFT JOIN Location AS loc ON a.asset_location_id_FK = loc.location_id_PK
LEFT JOIN Address AS adr ON loc.location_address_id_FK = adr.address_id_PK
LEFT JOIN Address AS adr2 ON emp.employee_address_id_FK = adr.address_id_PK
WHERE wo.workorder_type = 'Maintenance';
