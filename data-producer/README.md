# data-producer

## Getting Started

### Dependencies

* Java 1.8 or higher
* Maven 3.2 or higher
* Wisehub Platform Api 


### Build

```console
$ mvn test
$ mvn clean package
```
### Usage

#### Run from maven/console
* On the project root folder, run the following command: `mvn clean install -Dmaven.test.skip=true` . That will generate the different maven modules involved that are needed by the API.
* If the previous operation was successful, run `cd data-producer/ && mvn spring-boot:run -Drun.profiles=dev`. Will start the spring boot application


This is going to produce files for all months of 2016 year, with account number as prefix.


### Basic Configuration of profiles

Into application.properties file there are few simples parameters to define any features of customers and also to configure the account & bvn.
By the way you can set it from commandline.

The parameters for a worker as customer, the profile in spring is worker:  

wisehub.profile.worker.currency=Naira
wisehub.profile.worker.bvn=38612218715
wisehub.profile.worker.accountNumber=1004605806
wisehub.profile.worker.salary=2
wisehub.profile.worker.salary.max=60000
wisehub.profile.worker.salary.min=40000
wisehub.profile.worker.age=36
wisehub.profile.worker.house=true
wisehub.profile.worker.child=true
wisehub.profile.worker.car=true
wisehub.profile.worker.pet=false


The parameters for a restaurant as customer, the profile in spring is restaurant: 
wisehub.profile.restaurant.currency=Naira
wisehub.profile.restaurant.bvn=38612218715
wisehub.profile.restaurant.accountNumber=1004605806
wisehub.profile.restaurant.sales.min=200000
wisehub.profile.restaurant.sales.max=250000
wisehub.profile.restaurant.delivery=true
wisehub.profile.restaurant.employees=10


* the account number is required


* For run both profiles, run `cd data-producer/ && mvn spring-boot:run -Drun.profiles=dev,restaurant,worker`. 




