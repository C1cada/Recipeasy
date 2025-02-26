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
    step_number int NOT NULL,
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

CREATE TABLE recipes (
    key VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    instruction_id VARCHAR(36) NOT NULL,
    ingredient_id VARCHAR(36) NOT NULL,
    servings int,
    prep_time int,
    cook_time int,
    total_time int,
    ingredient_keys VARCHAR(255),
    instruction_keys VARCHAR(255),
);

CREATE TABLE tags (
    key VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE recipe_tags (
    id SERIAL PRIMARY KEY,
    recipe_id VARCHAR(36) NOT NULL,
    tag_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (recipe_id) REFERENCES recipes(key),
    FOREIGN KEY (tag_id) REFERENCES tags(key)
);