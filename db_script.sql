BEGIN;

DROP TABLE IF EXISTS customer CASCADE;
CREATE TABLE customer (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customer (name) VALUES
('Igor'),
('Andrew'),
('Alexey'),
('Olga'),
('Jana');

DROP TABLE IF EXISTS product CASCADE;
CREATE TABLE product (id bigserial PRIMARY KEY, title VARCHAR(255), price numeric(8, 2));
INSERT INTO product (title, price) VALUES
('women''s dress', 35500.00),
('women''s boots', 20500.00),
('men''s shoes', 5900.00),
('electric drill', 6500.00),
('screwdriver', 12450.00),
('winter tires', 48000.00),
('perfume', 18240.00),
('shaving cream', 190.00),
('robot vacuum cleaner', 25500.00),
('Tefal frying pan', 5500.00);

DROP TABLE IF EXISTS customer_product CASCADE;
CREATE TABLE customer_product (customer_id bigint, product_id bigint,
 FOREIGN KEY (customer_id) REFERENCES customer (id),
  FOREIGN KEY (product_id) REFERENCES product (id));
INSERT INTO customer_product (customer_id, product_id) VALUES
(1, 3),
(1, 6),
(1, 8),
(2, 4),
(2, 5),
(2, 9),
(3, 3),
(3, 4),
(3, 5),
(3, 8),
(4, 1),
(4, 2),
(4, 7),
(4, 8),
(5, 1),
(5, 2),
(5, 7);

COMMIT;