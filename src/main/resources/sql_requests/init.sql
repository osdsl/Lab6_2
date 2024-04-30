-- Create table if it doesn't exist
CREATE TABLE IF NOT EXISTS tv (
                                  id SERIAL PRIMARY KEY,
                                  brand VARCHAR(255),
                                  type VARCHAR(255),
                                  product_name VARCHAR(255),
                                  screen_diagonal INT,
                                  price DECIMAL,
                                  quantity INT
);

-- Insert sample data
INSERT INTO tv (brand, type, product_name, screen_diagonal, price, quantity)
VALUES
    ('Panasonic', 'LCD', 'TH-55HX750M', 55, 70500, 5),
    ('Sony', 'LCD', 'KD-50X75K', 50, 69000, 10),
    ('Samsung', 'LCD', 'UE65CU7100UXRU', 65, 79000, 12),
    ('Xiaomi', 'LCD', 'Mi-LED-TV-A2', 55, 43670, 4),
    ('LG', 'LCD', 'LG65QNED816RA', 65, 119990, 7),
    ('Haier', 'LCD', 'Smart-TV-S5', 58, 56000, 14),
    ('Hisense', 'LCD', '55E7HQ', 55, 59600, 6),
    ('Skyworth', 'LCD', '40E10', 40, 16500, 25);