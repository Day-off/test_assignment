ALTER TABLE criterion
    DROP COLUMN condition_value;

ALTER TABLE criterion
    ADD COLUMN condition_value_number BIGINT,
    ADD COLUMN condition_value_text VARCHAR(255) ,
    ADD COLUMN condition_value_date DATE;