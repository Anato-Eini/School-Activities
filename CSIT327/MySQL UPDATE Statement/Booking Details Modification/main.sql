UPDATE HotelBookings SET room_type = "Suite" WHERE booking_id = 5;

UPDATE HotelBookings SET booking_status = "Cancelled" WHERE guest_name = "Eric Clark";

UPDATE HotelBookings SET num_guests = num_guests + 1 WHERE room_type = "Deluxe";

UPDATE HotelBookings SET booking_status = "Checked out" WHERE booking_status = "Confirmed" AND check_in_date < "2024-03-12";

UPDATE HotelBookings SET special_requests = "No smoking" WHERE special_requests IS NULL;
