INSERT INTO criterion_type (id, name)
VALUES (1, 'Amount'),
       (2, 'Title'),
       (3, 'Date');

INSERT INTO comparing_condition_type (criterion_type_id, condition_name)
VALUES (1, 'More'),
       (1, 'Less'),
       (2, 'Contains'),
       (2, 'Starts With'),
       (3, 'From'),
       (3, 'Until');

INSERT INTO custom_filter (name)
VALUES ('Filter 1'),
       ('Filter 2');

INSERT INTO criterion (custom_filter_id, criterion_type_id, comparing_condition_type_id, condition_value_number, condition_value_text, condition_value_date)
VALUES ((SELECT id FROM custom_filter WHERE name = 'Filter 1'), 1,
        (SELECT id FROM comparing_condition_type WHERE condition_name = 'More' AND criterion_type_id = 1), 100, NULL, NULL),
       ((SELECT id FROM custom_filter WHERE name = 'Filter 1'), 2,
        (SELECT id FROM comparing_condition_type WHERE condition_name = 'Contains' AND criterion_type_id = 2), NULL, 'example', NULL),
       ((SELECT id FROM custom_filter WHERE name = 'Filter 1'), 3,
        (SELECT id FROM comparing_condition_type WHERE condition_name = 'From' AND criterion_type_id = 3), NULL, NULL, '2024-07-15');

INSERT INTO criterion (custom_filter_id, criterion_type_id, comparing_condition_type_id, condition_value_number, condition_value_text, condition_value_date)
VALUES ((SELECT id FROM custom_filter WHERE name = 'Filter 2'), 1,
        (SELECT id FROM comparing_condition_type WHERE condition_name = 'Less' AND criterion_type_id = 1), 50, NULL, NULL),
       ((SELECT id FROM custom_filter WHERE name = 'Filter 2'), 2,
        (SELECT id FROM comparing_condition_type WHERE condition_name = 'Starts With' AND criterion_type_id = 2), NULL, 'test', NULL),
       ((SELECT id FROM custom_filter WHERE name = 'Filter 2'), 3,
        (SELECT id FROM comparing_condition_type WHERE condition_name = 'Until' AND criterion_type_id = 3), NULL, NULL, '2024-12-31');