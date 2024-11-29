CREATE TABLE Buses (
    bus_id INT PRIMARY KEY,
    model VARCHAR(100),
    capacity INT,
    year INT
);

CREATE TABLE Routes (
    route_id INT PRIMARY KEY,
    start_point VARCHAR(100),
    end_point VARCHAR(100),
    distance INT
);

CREATE TABLE RideHistory (
    ride_id INT PRIMARY KEY,
    bus_id INT,
    route_id INT,
    date DATE,
    passenger_count INT,
    FOREIGN KEY (bus_id) REFERENCES Buses(bus_id),
    FOREIGN KEY (route_id) REFERENCES Routes(route_id)
);

INSERT INTO Buses (bus_id, model, capacity, year) VALUES
(101, 'Volvo B7R', 50, 2018),
(102, 'Mercedes-Benz Citaro', 40, 2019),
(103, 'Blue Bird Vision', 30, 2020),
(104, 'Scania Citywide', 45, 2017),
(105, 'MAN Lion''s City', 35, 2021),
(106, 'Iveco Crossway', 50, 2016),
(107, 'Setra MultiClass', 40, 2022),
(108, 'Alexander Dennis Enviro500', 55, 2018),
(109, 'BYD K9', 25, 2019),
(110, 'Solaris Urbino', 30, 2020);

INSERT INTO Routes (route_id, start_point, end_point, distance) VALUES
(201, 'Downtown', 'Uptown', 12),
(202, 'City Center', 'Suburb', 20),
(203, 'Eastside', 'Westside', 15),
(204, 'North Plaza', 'South Plaza', 18),
(205, 'Central Station', 'Hilltop', 22),
(206, 'Harbor', 'Airport', 25),
(207, 'University', 'Tech Park', 10),
(208, 'Beachfront', 'Museum District', 17),
(209, 'Old Town', 'New Town', 14),
(210, 'Parklands', 'Industrial Zone', 20);

INSERT INTO RideHistory (ride_id, bus_id, route_id, date, passenger_count) VALUES
(301, 101, 201, '2023-03-01', 35),
(302, 102, 202, '2023-03-02', 40),
(303, 103, 203, '2023-03-03', 28),
(304, 104, 204, '2023-03-04', 42),
(305, 105, 205, '2023-03-05', 33),
(306, 106, 206, '2023-03-06', 47),
(307, 107, 207, '2023-03-07', 39),
(308, 108, 208, '2023-03-08', 50),
(309, 109, 209, '2023-03-09', 22),
(310, 110, 210, '2023-03-10', 30),
(311, 101, 201, '2023-03-11', 45),
(312, 102, 202, '2023-03-12', 38),
(313, 103, 203, '2023-03-13', 26),
(314, 104, 204, '2023-03-14', 48),
(315, 105, 205, '2023-03-15', 34),
(316, 106, 206, '2023-03-16', 49),
(317, 107, 207, '2023-03-17', 36),
(318, 108, 208, '2023-03-18', 52),
(319, 109, 209, '2023-03-19', 24),
(320, 110, 210, '2023-03-20', 31),
(321, 101, 201, '2023-03-21', 44),
(322, 102, 202, '2023-03-22', 37),
(323, 103, 203, '2023-03-23', 29),
(324, 104, 204, '2023-03-24', 41),
(325, 105, 205, '2023-03-25', 35),
(326, 106, 206, '2023-03-26', 46),
(327, 107, 207, '2023-03-27', 38),
(328, 108, 208, '2023-03-28', 51),
(329, 109, 209, '2023-03-29', 23),
(330, 110, 210, '2023-03-30', 32);