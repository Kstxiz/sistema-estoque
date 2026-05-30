CREATE TABLE categories (
                            id UUID PRIMARY KEY,
                            user_id UUID NOT NULL,
                            name VARCHAR(100) NOT NULL,
                            type VARCHAR(20) NOT NULL,
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                            CONSTRAINT fk_categories_user
                                FOREIGN KEY (user_id)
                                    REFERENCES users(id)
                                    ON DELETE CASCADE
);