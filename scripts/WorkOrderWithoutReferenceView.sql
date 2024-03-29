CREATE VIEW WorkOrderNoReference AS
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
  wo.workorder_price,
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
  addr.address_id_PK,
  addr.address_streetname,
  addr.address_streetnumber,
  addr.address_cityname,
  addr.address_zipcode,
  loc.location_id_PK,
  loc.location_building,
  loc.location_floor,
  loc.location_room
FROM
  Workorder AS wo
JOIN Employee AS emp ON wo.workorder_employee_id_FK = emp.employee_id_PK
JOIN Asset AS a ON wo.workorder_asset_id_FK = a.asset_id_PK
JOIN Location AS loc ON a.asset_location_id_FK = loc.location_id_PK
JOIN Address AS addr ON loc.location_address_id_FK = addr.address_id_PK;