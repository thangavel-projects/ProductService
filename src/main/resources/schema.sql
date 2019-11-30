CREATE TABLE PRODUCT (
  id            INT AUTO_INCREMENT PRIMARY KEY,
  name          VARCHAR(64) NOT NULL,
  currentPrice  DOUBLE NOT NULL,
  lastUpdate    TIMESTAMP NOT NULL
);