DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS announcement;
DROP TABLE IF EXISTS car;



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
                       user_user_id   long,
                       user_id long,
                       car_id long,
                       coment_id long,
                      coment_coment_id long,
                        best_announcement_id  long,
                       PRIMARY KEY (id),
                       FOREIGN KEY (user_id) REFERENCES user(user_id)
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
                    announcement_id long,
                    PRIMARY KEY (id),
                    FOREIGN KEY (announcement_id ) REFERENCES announcement(id )
);
CREATE TABLE coment (
                                coment_id  long NOT NULL AUTO_INCREMENT,
                                message varchar,
                                announcement_id long,
                                PRIMARY KEY ( coment_id),
                                FOREIGN KEY (announcement_id ) REFERENCES  announcement(id )
);
CREATE TABLE credit_cart (
                                id  long NOT NULL AUTO_INCREMENT,
                                 cash int,
                                 user_user_id long,
                                user_id long,
                                PRIMARY KEY (id),
                                FOREIGN KEY (user_user_id) REFERENCES  user(user_id )

);
 CREATE TABLE best_announcement (
                                 id  long NOT NULL AUTO_INCREMENT,
                                 announcement_id long,
                                 user_id long,
                                  PRIMARY KEY (id),
                                  FOREIGN KEY (announcement_id) REFERENCES  announcement (id)
);




    INSERT INTO user(  login,balance ,pasword ,announcement_id, best_announcement_id )
                                values ('pavel', 1, 22, 1,1);
    INSERT INTO announcement(  get_up , number_phone ,user_id,  car_id, coment_id,coment_coment_id ,best_announcement_id  )
                                 values (2, 22, 1,1,1,1,1);
    INSERT INTO car ( name_car , model_car , price, year_of_issue , milage,volume_engine,
    type_engine,transmission,location,custom,exchange, announcement_id )
                              values (  'opel', 'astra', 1000,2000,1000000,2,
                              'dt','avto','Brest','yes','no',1);

  INSERT INTO best_announcement (  id,announcement_id , user_id  )
                               values (  1,1,1);
 INSERT INTO coment ( coment_id,message , announcement_id  )
                          values (  1,'uuuuuu',1);