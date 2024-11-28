SELECT Guests.guest_id, Guests.guest_name, Guests.age, Hotels.hotel_name, Hotels.city
FROM Guests RIGHT JOIN Hotels ON Guests.hotel_id = Hotels.hotel_id;

SELECT Hotels.hotel_id, Hotels.hotel_name, Hotels.city, Guests.guest_id, Guests.guest_name, Guests.age
FROM Guests RIGHT JOIN Hotels ON Guests.hotel_id = Hotels.hotel_id;