CREATE TABLE body (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);
CREATE TABLE engine (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);
CREATE TABLE gearbox (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);
CREATE TABLE car (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    body_id INT REFERENCES body(id),
    engine_id INT REFERENCES engine(id),
    gearbox_id INT REFERENCES gearbox(id)
);
INSERT INTO body(name) VALUES ('sedan'), ('hatchback 3d'), ('hatchback 5d'), ('wagon'), ('pickup'), ('cabrio'), ('SUV');
INSERT INTO engine(name) VALUES ('2l gas'), ('3l gas'), ('5l gas'), ('2l diesel'), ('3l diesel'), ('4l diesel');
INSERT INTO gearbox(name) VALUES ('5MT'), ('6MT'), ('CVT'), ('5sp DSG'), ('6sp DSG'), ('7sp DSG'), ('8sp DSG'), ('4sp TC'), ('5sp TC'), ('6sp TC');
INSERT INTO car(name, body_id, engine_id, gearbox_id) VALUES ('bmw 320i', 1, 1, 10), ('bmw 320d', 1, 4, 10), ('bmw 530i', 2, 1, 10), ('bmw 530d', 1, 5, 2),
                                                             ('audi a3', 2, 1, 2), ('audi a3', 3, 1, 2), ('audi a4', 1, 4, 6), ('audi a8', 1, 3, 7),
                                                             ('vw jetta', 1, 4, 2), ('vw passat', 4, 4, 6), ('vw tiguan', 7, 4, 7), ('vw tuareg', 7, 6, 7);

--Вывести список всех машин и все привязанные к ним детали.
SELECT c.name, b.name as body, e.name as engine, g.name as gearbox FROM car c
                                                                            JOIN body b on c.body_id = b.id
                                                                            JOIN engine e on c.engine_id = e.id
                                                                            JOIN gearbox g on c.gearbox_id = g.id;

--Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
SELECT b.name as body FROM car
    RIGHT JOIN body b on car.body_id = b.id
WHERE car.id IS NULL;

SELECT e.name as engine FROM engine e
    LEFT JOIN car c ON c.engine_id = e.id
WHERE c.id IS NULL;

SELECT g.name as gearbox FROM gearbox g
    FULL JOIN car c on g.id = c.gearbox_id
WHERE c.id IS NULL;