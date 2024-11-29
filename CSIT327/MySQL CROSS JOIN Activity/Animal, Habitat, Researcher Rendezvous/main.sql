SELECT Animals.species, Habitats.name AS habitat_name
FROM Animals, Habitats;

SELECT Researchers.name AS researcher_name, Animals.species
FROM Researchers, Animals;

SELECT Researchers.name AS researcher_name, Habitats.name as habitat_name
FROM Researchers, Habitats;

SELECT Animals.species, Habitats.name AS habitat_name, Researchers.name AS researcher_name
FROM Animals, Habitats, Researchers;

SELECT Animals.species, Researchers.name AS researcher_name, Researchers.years_of_experience
FROM Animals, Researchers;