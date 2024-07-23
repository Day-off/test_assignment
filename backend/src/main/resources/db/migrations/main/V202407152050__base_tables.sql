CREATE TABLE custom_filter
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE criterion_type
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE comparing_condition_type
(
    id               SERIAL PRIMARY KEY,
    criterion_type_id BIGINT,
    condition_name        VARCHAR(255) NOT NULL,
    FOREIGN KEY (criterion_type_id) REFERENCES criterion_type (id)
);

CREATE TABLE criterion
(
    id               SERIAL PRIMARY KEY,
    custom_filter_id        BIGINT,
    criterion_type_id BIGINT,
    comparing_condition_type_id     BIGINT,
    condition_value  VARCHAR(255) NOT NULL,
    FOREIGN KEY (custom_filter_id) REFERENCES custom_filter (id),
    FOREIGN KEY (criterion_type_id) REFERENCES criterion_type (id),
    FOREIGN KEY (comparing_condition_type_id) REFERENCES comparing_condition_type (id)
);