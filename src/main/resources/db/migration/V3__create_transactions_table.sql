CREATE TABLE transactions (
                              id UUID PRIMARY KEY,

                              user_id UUID NOT NULL,

                              category_id UUID NOT NULL,

                              description VARCHAR(150) NOT NULL,

                              amount NUMERIC(12,2) NOT NULL,

                              type VARCHAR(20) NOT NULL,

                              transaction_date DATE NOT NULL,

                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                              CONSTRAINT fk_transactions_user
                                  FOREIGN KEY (user_id)
                                      REFERENCES users(id)
                                      ON DELETE CASCADE,

                              CONSTRAINT fk_transactions_category
                                  FOREIGN KEY (category_id)
                                      REFERENCES categories(id)
                                      ON DELETE CASCADE
);