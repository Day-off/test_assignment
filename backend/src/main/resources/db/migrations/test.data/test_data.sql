-- Insert into criterion_type table
INSERT INTO criterion_type (id, name)
VALUES (1, 'Amount'),
       (2, 'Title'),
       (3, 'Date');

-- Insert into comparing_condition_type table
INSERT INTO comparing_condition_type (criterion_type_id, condition_name)
VALUES (1, 'More'),
       (1, 'Less'),
       (2, 'Contains'),
       (2, 'Starts With'),
       (3, 'From'),
       (3, 'Until');

-- Insert into custom_filter table
INSERT INTO custom_filter (name)
VALUES ('Filter 1'),
       ('Filter 2');

-- Insert into criterion table for 'Filter 1'
INSERT INTO criterion (custom_filter_id, criterion_type_id, comparing_condition_type_id, condition_value)
VALUES ((SELECT id FROM custom_filter WHERE name = 'Filter 1'), 1,
        (SELECT id FROM comparing_condition_type WHERE condition_name = 'More' AND criterion_type_id = 1), '100'),
       ((SELECT id FROM custom_filter WHERE name = 'Filter 1'), 2,
        (SELECT id FROM comparing_condition_type WHERE condition_name = 'Contains' AND criterion_type_id = 2), 'example'),
       ((SELECT id FROM custom_filter WHERE name = 'Filter 1'), 3,
        (SELECT id FROM comparing_condition_type WHERE condition_name = 'From' AND criterion_type_id = 3), '2024-07-15');

-- Insert into criterion table for 'Filter 2'
INSERT INTO criterion (custom_filter_id, criterion_type_id, comparing_condition_type_id, condition_value)
VALUES ((SELECT id FROM custom_filter WHERE name = 'Filter 2'), 1,
        (SELECT id FROM comparing_condition_type WHERE condition_name = 'Less' AND criterion_type_id = 1), '50'),
       ((SELECT id FROM custom_filter WHERE name = 'Filter 2'), 2,
        (SELECT id FROM comparing_condition_type WHERE condition_name = 'Starts With' AND criterion_type_id = 2), 'test'),
       ((SELECT id FROM custom_filter WHERE name = 'Filter 2'), 3,
        (SELECT id FROM comparing_condition_type WHERE condition_name = 'Until' AND criterion_type_id = 3), '2024-12-31');
