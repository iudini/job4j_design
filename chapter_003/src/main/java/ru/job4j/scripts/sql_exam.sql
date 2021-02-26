CREATE TABLE meeting (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);
CREATE TABLE status (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);
CREATE TABLE meetings_on_users (
    id SERIAL PRIMARY KEY,
    meeting_id INT REFERENCES meeting(id),
    users_id INT REFERENCES users(id),
    status_id INT REFERENCES status(id)
);

INSERT INTO meeting(name) VALUES ('First meeting'), ('Second meeting'), ('Third meeting'), ('Fourth meeting');
INSERT INTO users(name) VALUES ('Ivanov'), ('Petrov'), ('Vasechkin'), ('Schpak');
INSERT INTO status(name) VALUES ('Confirmed'), ('Cancelled'), ('Waiting');
INSERT INTO meetings_on_users(meeting_id, users_id, status_id) VALUES (1, 1, 1), (1, 2, 2), (1, 3, 3),
                                                                      (2, 1, 1), (2, 3, 2), (2, 4, 1),
                                                                      (3, 1, 2), (3, 2, 1), (3, 3, 3),
                                                                      (4, 1, 2), (4, 2, 3), (4, 3, 2);

--  запрос, который получает список всех встреч и количество подтвердивших участников
select name, count(name) from meeting
join (select * from meetings_on_users where status_id = 1) mou on meeting.id = mou.meeting_id
group by name;

-- получить все совещания, где не было ни одной заявки на посещения
select distinct name from meeting
join (select * from meetings_on_users where meeting_id <> all (
            select distinct meeting_id from meetings_on_users
            where status_id = 1)) mou
on meeting.id = mou.meeting_id;