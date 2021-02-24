-- 1. В одном запросе получить
-- - имена всех person, которые не состоят в компании с id = 5;
-- - название компании для каждого человека.
select p.name, c.name from person p
    join company c on p.company_id = c.id
where c.id <> 5;

--2. Необходимо выбрать название компании с максимальным количеством человек
-- + количество человек в этой компании.
select c.name, count(p.company_id) as count from company c
    join person p on c.id = p.company_id
group by c.name
order by count desc
limit 1;