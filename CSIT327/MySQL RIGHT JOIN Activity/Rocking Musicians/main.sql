SElECT Musicians.musician_id, Musicians.full_name, Musicians.instrument, Bands.band_name, Bands.genre
FROM Musicians RIGHT JOIN Bands ON Musicians.band_id = Bands.band_id
WHERE Musicians.instrument = "Guitar"
ORDER BY Musicians.musician_id;

SELECT Musicians.musician_id, Musicians.full_name, Musicians.instrument, Bands.band_name, Bands.genre
FROM Musicians RIGHT JOIN Bands ON Musicians.band_id = Bands.band_id
WHERE Bands.genre = "Jazz"
ORDER BY Musicians.musician_id;

SELECT Musicians.musician_id, Musicians.full_name, Musicians.instrument, Bands.band_name, Bands.genre
FROM Musicians RIGHT JOIN Bands ON Musicians.band_id = Bands.band_id;

SELECT Musicians.musician_id, Musicians.full_name, Musicians.instrument, Bands.band_name, Bands.genre
FROM Musicians RIGHT JOIN Bands ON Musicians.band_id = Bands.band_id
WHERE Bands.genre = "Rock";