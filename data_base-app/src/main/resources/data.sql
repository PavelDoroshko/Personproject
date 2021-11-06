DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS announcement;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS coment;
DROP TABLE IF EXISTS best_announcement;

CREATE TABLE user (
                     user_id long  NOT NULL AUTO_INCREMENT,
                    login varchar(40),
                     balance INT,
                     pasword INT,
                     announcement_id long,
                     best_announcement_id  long,
                     credit_cart_id long,
                     PRIMARY KEY (user_id)
);
CREATE TABLE announcement (
                       id long NOT NULL AUTO_INCREMENT,
                       get_up INT,
                       number_phone INT,
                       user_user_id long,
                       user_id long,
                       coment_id long,
                       car_id long,
                       best_announcement_id  long,
                       PRIMARY KEY (id),
                       FOREIGN KEY (user_user_id) REFERENCES user(user_id)
);

CREATE TABLE car (
                   id long not null AUTO_INCREMENT,
                    name_car varchar,
                    model_car varchar,
                    price int,
                    year_of_issue int,
                    milage int,
                    volume_engine int,
                    type_engine varchar,
                    transmission varchar,
                    location varchar,
                    custom varchar,
                    exchange varchar,
                    PRIMARY KEY (id)

);



CREATE TABLE coment (
                                coment_id  long NOT NULL AUTO_INCREMENT,
                                message varchar,
                                PRIMARY KEY ( coment_id)

);
CREATE TABLE credit_cart (
                                id  long NOT NULL AUTO_INCREMENT,
                                 cash int,
                                PRIMARY KEY (id)


);
 CREATE TABLE best_announcement (
                                 id  long NOT NULL AUTO_INCREMENT,
                                 announcement_id long,
                                 user_id long,
                                  PRIMARY KEY (id),
                                  FOREIGN KEY (announcement_id) REFERENCES  announcement (id)
);