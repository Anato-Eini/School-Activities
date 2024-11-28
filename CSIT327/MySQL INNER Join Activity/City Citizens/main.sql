SELECT Cities.city_name, Citizens.first_name, Citizens.last_name
FROM Cities INNER JOIN Citizens ON Cities.city_id = Citizens.city_id
ORDER BY Cities.city_name;

SELECT Citizens.first_name, Citizens.last_name, Citizens.occupation
FROM Citizens INNER JOIN Cities ON Citizens.city_id = Cities.city_id
WHERE Cities.population > 1000000
ORDER BY Citizens.last_name;

SELECT Cities.city_name, Cities.population, Citizens.first_name
FROM Cities INNER JOIN Citizens ON Cities.city_id = Citizens.city_id
WHERE Citizens.age <= 30
ORDER BY Cities.population;

SELECT Cities.city_name, Cities.country, Citizens.last_name
FROM Citizens INNER JOIN Cities ON Citizens.city_id = Cities.city_id
WHERE Citizens.occupation = 'Engineer'
ORDER BY Cities.country;