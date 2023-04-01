INSERT INTO city (id, latitude, longitude) VALUES
  ('1', 40.7128, -74.0060),
  ('2', 51.5074, -0.1278),
  ('3', 35.6895, 139.6917);

INSERT INTO hotel (id, name, address, latitude, longitude, deleted, city_id) VALUES
  (1, 'Hotel A', '123 Main St', 40.7128, -74.0060, false, '1'),
  (2, 'Hotel B', '456 Oak St', 40.7033, -73.9797, false, '1'),
  (3, 'Hotel C', '789 Pine St', 51.5074, -0.1278, false, '2'),
  (4, 'Hotel D', '000 Albert St', 51.5081, -0.1297, false, '2'),
  (5, 'Hotel E', '007 King St', 35.6895, 139.6917, false, '3');