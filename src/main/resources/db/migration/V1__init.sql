CREATE TABLE user
(
  id         INT AUTO_INCREMENT
    PRIMARY KEY,
  email      VARCHAR(45)  NOT NULL,
  login      VARCHAR(45)  NOT NULL,
  password   VARCHAR(45)  NOT NULL,
  name       VARCHAR(45)  NOT NULL,
  last_name  VARCHAR(45)  NOT NULL,
  active     TINYINT      NOT NULL,
  created_at DATETIME     NOT NULL,
  gender     VARCHAR(45)  NOT NULL,
  uuid       VARCHAR(255) NOT NULL
);

CREATE TABLE role
(
  id      INT AUTO_INCREMENT
    PRIMARY KEY,
  role    VARCHAR(45) NOT NULL,
  user_id INT         NOT NULL,
  CONSTRAINT fk_this_users
  FOREIGN KEY (user_id) REFERENCES user (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);
CREATE INDEX fk_this_users_idx
  ON role (user_id);
