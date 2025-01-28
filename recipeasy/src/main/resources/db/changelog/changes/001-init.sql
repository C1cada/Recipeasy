CREATE TABLE ingredients (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    quantity VARCHAR(255) NOT NULL,
    children boolean NOT NULL
);

CREATE TABLE ingredient_children (
    id INT PRIMARY KEY,
    parent_id INT NOT NULL,
    child_id INT NOT NULL,
    FOREIGN KEY (parent_id) REFERENCES ingredients(id),
    FOREIGN KEY (child_id) REFERENCES ingredients(id)
);

CREATE TABLE instructions (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    instructions VARCHAR(255) NOT NULL,
    children boolean NOT NULL
);

CREATE TABLE instruction_children (
    id INT PRIMARY KEY,
    parent_id INT NOT NULL,
    child_id INT NOT NULL,
    FOREIGN KEY (parent_id) REFERENCES instructions(id),
    FOREIGN KEY (child_id) REFERENCES instructions(id)
);