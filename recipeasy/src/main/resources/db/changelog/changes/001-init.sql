CREATE TABLE ingredients (
    key VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    quantity VARCHAR(255),
    is_child boolean NOT NULL
);

CREATE TABLE ingredient_children (
    id SERIAL PRIMARY KEY,
    parent_id VARCHAR(36) NOT NULL,
    child_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (parent_id) REFERENCES ingredients(key),
    FOREIGN KEY (child_id) REFERENCES ingredients(key)
);

CREATE TABLE instructions (
    key VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    instructions VARCHAR(255) NOT NULL,
    is_child boolean NOT NULL
);

CREATE TABLE instruction_children (
    id SERIAL PRIMARY KEY,
    parent_id VARCHAR(36) NOT NULL,
    child_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (parent_id) REFERENCES instructions(key),
    FOREIGN KEY (child_id) REFERENCES instructions(key)
);