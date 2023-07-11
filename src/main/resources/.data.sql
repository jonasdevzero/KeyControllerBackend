-- delete from "sector";
-- delete from "key";

insert into "sector" (id, name) values
(1, 'Laborátorio'),
(2,'Sala de Aula');

insert into "key" (id,number,sector_id) values
(1, 65, 1),
(2, 67, 1),
(3, 91, 1),
(4, 92, 1);