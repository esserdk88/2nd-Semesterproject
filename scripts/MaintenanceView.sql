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
  loc.location_room
FROM
  Workorder AS wo
LEFT JOIN Employee AS emp ON wo.workorder_employee_id_FK = emp.employee_id_PK
LEFT JOIN Asset AS a ON wo.workorder_asset_id_FK = a.asset_id_PK
LEFT JOIN Location AS loc ON a.asset_location_id_FK = loc.location_id_PK
WHERE wo.workorder_type = 'Maintenance';
