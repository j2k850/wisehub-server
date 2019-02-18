-- Create the tables
CREATE ADDRESS
(
  address_id int(11), 
  city_id  int(11),
  address  varchar(255),
  address2 varchar(255),
  phone    varchar(255),
  region   varchar(255),
  postal_code varchar(255),
  created   date,
  last_mod  date 
);

insert into address (address_id, city_id, address, address2, phone, region, postal_code, created, last_mod)
values (1,1,'123 Smith St', null, '123-45-9999', 'NIG', 'Z12345', 'JAN-01-2016', 'JAN-01-2016');
