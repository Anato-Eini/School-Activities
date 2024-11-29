SELECT Buses.model, AVG(RideHistory.passenger_count) AS average_passengers
FROM Buses JOIN RideHistory ON Buses.bus_id = RideHistory.bus_id
GROUP BY Buses.model;

SELECT Buses.bus_id, SUM(Routes.distance) AS total_distance
FROM Buses JOIN RideHistory ON Buses.bus_id = RideHistory.bus_id JOIN Routes ON RideHistory.route_id = Routes.route_id
GROUP BY Buses.bus_id;

SELECT Routes.start_point, Routes.end_point, COUNT(RideHistory.ride_id) AS ride_count
FROM Routes JOIN RideHistory ON Routes.route_id = RideHistory.route_id
GROUP BY Routes.start_point, Routes.end_point;

SELECT Routes.start_point, Routes.end_point, AVG(RideHistory.passenger_count) AS average_passengers
FROM Routes JOIN RideHistory ON Routes.route_id = RideHistory.route_id
GROUP BY Routes.start_point, Routes.end_point;

SELECT Buses.model, (SUM(RideHistory.passenger_count) / (COUNT(RideHistory.ride_id) * MAX(Buses.capacity))) * 100 AS utilization_percentage
FROM Buses JOIN RideHistory ON Buses.bus_id = RideHistory.bus_id
GROUP BY Buses.model;