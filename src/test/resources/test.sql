delete
from item_category;
delete
from category;
delete
from item;

--

insert into category(id, name)
values (1, 'Super'),
       (2, 'Light');

--

insert into item(id, name, weight, price)
values (1, 'Item A', 100, 10),
       (2, 'Item B', 200, 20),
       (3, 'Item C', 300, 30);

--

insert into item_category(item_id, category_id, is_main)
values (1, 1, true),
       (1, 2, false),
       (2, 1, true),
       (3, 2, true);
