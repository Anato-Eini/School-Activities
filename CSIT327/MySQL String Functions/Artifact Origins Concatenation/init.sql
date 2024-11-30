CREATE TABLE ArtifactsCollection (
    artifact_id INT PRIMARY KEY,
    artifact_name VARCHAR(255),
    origin VARCHAR(100),
    material VARCHAR(50),
    era VARCHAR(50)
);

INSERT INTO ArtifactsCollection (artifact_id, artifact_name, origin, material, era) VALUES
(1, 'great sphinx of giza', 'Egypt', 'Limestone', 'Ancient'),
(2, 'Mona Lisa', 'Italy', 'Oil Paint', 'Renaissance'),
(3, 'Rosetta Stone', 'Egypt', 'Granodiorite', 'Ancient'),
(4, 'Terracotta Army', 'China', 'Terracotta', 'Ancient'),
(5, 'Venus de Milo', 'Greece', 'Marble', 'Classical'),
(6, 'The Thinker', 'France', 'Bronze', 'Modern'),
(7, 'Tutankhamun''s Mask', 'Egypt', 'Gold', 'Ancient'),
(8, 'The Colosseum', 'Italy', 'Concrete, Travertine', 'Ancient'),
(9, 'The Great Wall of China', 'China', 'Stone, Earth', 'Ancient'),
(10, 'Stonehenge', 'England', 'Stone', 'Ancient');