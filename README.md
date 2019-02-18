# wisehub-platform-server

## Getting Started

### Dependencies

* Java 1.8 or higher
* Maven 3.2 or higher
* Apache Cassandra 3.11.1 (If testing locally) (or Docker image)

### Modules
-	***data-model***:
	Contains all entities o persistence models that mapping to Cassandra Source, and also there are DAO's (repositories) for them. And also the connector to Cassandra.
-	***core***
	Contains all entities o persistence models that mapping to Mysql Source, and also there are DAO's for them. 
-	***api***
	Spring boot application, with all services and controllers.
	
### Build

```console
$ mvn test
$ mvn clean package
```
### Usage

#### Run from maven/console
* On the project root folder, run the following command: `mvn clean install -Dmaven.test.skip=true` . That will generate the different maven modules involved that are needed by the API.
* If the previous operation was successful, run ` mvn spring-boot:run`. Will start the spring boot application for the API. Please, have in mind that this has a dependency on the Cassandra server (take a look at _Install and RUn Docker Cassandra_). 


#### Run from maven/eclipse
*TBD

### Docker

There is a Docker image to use Cassandra, in this case we need the last image 3.11.1:  
[Docker Library Cassandra Document|https://github.com/docker-library/docs/tree/master/cassandra]

#### Install and Run Docker Cassandra
To start the Cassandra service, Docker is needed. Once Docker is installed, a Container is needed in order to raise up the Cassandra server. Run the following command to perform that operation:
```console
docker run --name wisechoize_cassandra -d  -p 9042:9042 cassandra:3.11.1
```
Once this is finished proceed with the steps below, to create the database schema.

#### Copy cql file into docker container

```console
docker cp data-model/src/test/resources/wisehub-platform-local-schema.cql wisechoize_casandra:/wisehub-platform-local-schema.cql  
docker cp data-model/src/test/resources/wisehub-platform-local-staticinserts.cql  wisechoize_casandra:/wisehub-platform-local-staticinserts.cql  
docker cp data-model/src/test/resources/wisehub-platform-local-dynamicinserts.cql  wisechoize_casandra:/wisehub-platform-local-dynamicinserts.cql  
```

#### Populate

```console
docker exec -it wisechoize_casandra bash  
cqlsh -f wisehub-platform-local-schema.cql
cqlsh -f wisehub-platform-local-staticinserts.cql
cqlsh -f wisehub-platform-local-dynamicinserts.cql
```

### Json Web Token

* Register a simple user, it's going to generate a dashboard user with USER role. 
```console
curl 'http://...:../api/register' -XPOST -H 'content-type: application/json' --data '{"email": "username@username", "password": "username1234"}'
```

* Generate JWT
```console
curl 'http://...:../api/auth' -XPOST -H 'content-type: application/json' --data '{"email": "username@username", "password": "username1234"}'
```
Response with token:
*{"token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc3RqZXJuZV9kYXNoYm9hcmRfNUB3aXNlaHViLmlvIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA5OTM5MzIwMTU2LCJleHAiOjE1MTA1NDQxMjB9.CQ6uXjoXe20Hhz-2AykJADRVGDddQPW_2RKh8VHX1m35eUTYD_P6vFFtD8DIARDihZayqXELxTunp5o_lPH2SQ"}
*


* Use JWT with accounts resources
```console
curl 'http://...:../api/accounts' -XGET -H 'content-type: application/json' -H 'Authorization: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc3RqZXJuZV9kYXNoYm9hcmRfNUB3aXNlaHViLmlvIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA5OTM5MzIwMTU2LCJleHAiOjE1MTA1NDQxMjB9.CQ6uXjoXe20Hhz-2AykJADRVGDddQPW_2RKh8VHX1m35eUTYD_P6vFFtD8DIARDihZayqXELxTunp5o_lPH2SQ'
```

### Swagger

Now you can test it in your browser by visiting http://localhost:8080/api/swagger-ui.html

In our case, by the way, the exact URL will be: http://localhost:8080/api/v2/api-docs


### Customer Fake Data
There is a simple generator of fake data that produce 1300 customers, and every time the server is setup generated they in memory. 

Request:

```console
curl 'http://...:../api/board/customers_statistics?filter=4' -XGET -H 'content-type: application/json' -H 'Authorization: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzc3RqZXJuZV9kYXNoYm9hcmRfNUB3aXNlaHViLmlvIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA5OTM5MzIwMTU2LCJleHAiOjE1MTA1NDQxMjB9.CQ6uXjoXe20Hhz-2AykJADRVGDddQPW_2RKh8VHX1m35eUTYD_P6vFFtD8DIARDihZayqXELxTunp5o_lPH2SQ'
```

Query Param => filter:
	1=Today
	2=Current Week
	3=Current Month
	4=Current Year
	
Response eg.

```console
{
   "filter":{
      "filter":4,
      "from_date":"01.01.2017",
      "to_date":"01.01.2018"
   },
   "customers_notified":8351,
   "avg_frequency_of_notification":0.01,
   "customers_responded":4238,
   "customers_pending":463,
   "customers_newly_active":423,
   "top_customers_feedback":[
      {
         "feedback":"Identifying overlapping data",
         "hits":4
      },
      {
         "feedback":"Missing values",
         "hits":7
      },
      {
         "feedback":"Illegal values",
         "hits":9
      },
      {
         "feedback":"Common misspelling",
         "hits":10
      },
      {
         "feedback":"Duplicate entries",
         "hits":11
      },
      {
         "feedback":"Varying value representations",
         "hits":13
      },
      {
         "feedback":"For large datasets cleanse it stepwise and improve the data with each step until you achieve a good data quality",
         "hits":25
      },
      {
         "feedback":"Analyze the summary statistics for each column ( standard deviation, mean, number of missing values,",
         "hits":28
      },
      {
         "feedback":"If you have an issue with data cleanliness, arrange them by estimated frequency and attack the most common problems",
         "hits":29
      },
      {
         "feedback":"Provide support to all data analysis and coordinate with customers and staffs",
         "hits":34
      }
   ],
   "avg_customer_clv":[
      {
         "month":"Jul",
         "average":3482.91
      },
      {
         "month":"Oct",
         "average":3573.32
      },
      {
         "month":"Feb",
         "average":3548.16
      },
      {
         "month":"Apr",
         "average":3425.93
      },
      {
         "month":"Jun",
         "average":3481.62
      },
      {
         "month":"Aug",
         "average":3427.78
      },
      {
         "month":"Dec",
         "average":3500.68
      },
      {
         "month":"Nov",
         "average":3478.94
      },
      {
         "month":"May",
         "average":3538.31
      },
      {
         "month":"Jan",
         "average":3134.12
      },
      {
         "month":"Mar",
         "average":3498.05
      },
      {
         "month":"Sep",
         "average":3519.91
      }
   ],
   "top_customers_clv":[
      {
         "amount":2005.57,
         "customer_full_name":"Rita Ora",
         "customer_id":"a067e3dd-da02-4771-98c2-7b877cef8be1"
      },
      {
         "amount":2008.10,
         "customer_full_name":"Thomas Müller",
         "customer_id":"57f80839-b323-4cbf-83fd-f974cbd1a6a7"
      },
      {
         "amount":2008.50,
         "customer_full_name":"Anahi",
         "customer_id":"fda141d7-4970-42cb-af8d-7c04f92652f5"
      },
      {
         "amount":2036.30,
         "customer_full_name":"Xavi",
         "customer_id":"a7598943-cbd3-4985-9867-982fac648654"
      },
      {
         "amount":2052.81,
         "customer_full_name":"Willy",
         "customer_id":"a7aca59e-03f3-4fe3-b278-a37c0e33ef09"
      },
      {
         "amount":2063.50,
         "customer_full_name":"Ashlee Simpson Ross",
         "customer_id":"c13b9178-ed5d-4c6f-ab37-e86d7db6802a"
      },
      {
         "amount":2075.12,
         "customer_full_name":"Vincent Enyeama",
         "customer_id":"fe0644ba-40f8-40d9-9ef6-9709ce06167b"
      },
      {
         "amount":2087.64,
         "customer_full_name":"Вера Брежнева",
         "customer_id":"5c58fdb1-35ae-4c91-a876-d9c50788d78d"
      },
      {
         "amount":2098.42,
         "customer_full_name":"Sean Diddy Combs",
         "customer_id":"a5293510-4bee-4fcd-8260-46b03120dc20"
      },
      {
         "amount":2102.30,
         "customer_full_name":"Kevin De Bruyne",
         "customer_id":"8fbe75e4-dba9-4219-86e4-011bfdbffb2a"
      }
   ]
}
```

### Communications Gateway APIs
Added basic communication gateway service/API that leverages a 3rd-party APIs and based on bank's preference(only email, only sms, both):
* Can send an email to customer with their weekly account summary
* Can send an email to customer with product recommendations
* Can send an email to customer with overdraft alert
* Can send an email to customer that account is inactive
* Can send a sms to customer with their weekly account summary
* Can send a sms to customer with product recommendations
* Can send a sms to customer with overdraft alert
* Can send a sms to customer that account is inactive

As shown below, a template Id is required to call out the correct freemaker template to use in communication with customer. Also, specific data for each template is required.

#### EMAIL: WEEKLY SUMMARY
Request:

```console
curl 'http://...:.../api/sendEmail' -XPOST -H 'content-type: application/json' -H 'Authorization: eyJhbGciOiJIUzUxMiJ9.                                                                                               eyJzdWIiOiJzc3RqZXJuZV9kYXNoYm9hcmRfNUB3aXNlaHViLmlvIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA5OTM5MzIwMTU2LCJleHAiOjE1MTA1NDQxMjB9.CQ6uXjoXe20Hhz-2AykJADRVGDddQPW_2RKh8VHX1m35eUTYD_P6vFFtD8DIARDihZayqXELxTunp5o_lPH2SQ'
--data '{"id" : "7dc53df5-703e-49b3-8670-b1c468f47f1f", "fromName": "WiseHub", "fromEmail": "info@wisehub.io", "subject": "Wisehub", "recps": "<your-email>", "recpName": "Barb", "bank" : "Zenith", "templateId": "1"}'
```

Response eg.
```console
200
```

#### EMAIL: INSUFFICIENT FUNDS
Request:

```console
curl 'http://...:.../api/sendEmail' -XPOST -H 'content-type: application/json' -H 'Authorization: eyJhbGciOiJIUzUxMiJ9.                                                                                               eyJzdWIiOiJzc3RqZXJuZV9kYXNoYm9hcmRfNUB3aXNlaHViLmlvIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA5OTM5MzIwMTU2LCJleHAiOjE1MTA1NDQxMjB9.CQ6uXjoXe20Hhz-2AykJADRVGDddQPW_2RKh8VHX1m35eUTYD_P6vFFtD8DIARDihZayqXELxTunp5o_lPH2SQ' --data '{"id" :"7dc53df5-703e-49b3-8670-b1c468f47f1f", "fromName": "WiseHub", "fromEmail": "info@wisehub.io", "subject": "Wisehub", "recps": "<your-email>", "recpName": "Barb", "bank" : "Zenith", "templateId": "1"}'
```

Response eg.
```console
200
```

#### SMS: RECOMMEND INSURANCE
Request:

```console
curl 'http://...:.../api/sendSms' -XPOST -H 'content-type: application/json' -H 'Authorization: eyJhbGciOiJIUzUxMiJ9.                                                                                               eyJzdWIiOiJzc3RqZXJuZV9kYXNoYm9hcmRfNUB3aXNlaHViLmlvIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA5OTM5MzIwMTU2LCJleHAiOjE1MTA1NDQxMjB9.CQ6uXjoXe20Hhz-2AykJADRVGDddQPW_2RKh8VHX1m35eUTYD_P6vFFtD8DIARDihZayqXELxTunp5o_lPH2SQ' --data '{"id" : "7dc53df5-703e-49b3-8670-b1c468f47f1f",  "toName": "Barbara" , "toPhoneNumber": "+19738860779", "subject": "Wisehub", "bank" : "Zenith", "templateId": "1", "insuranceAmount" :"1000"}'
```

Response eg.
```console
200
```

#### SMS: RECOMMEND CREDITCARD
Request:

```console
curl 'http://...:.../api/sendSms' -XPOST -H 'content-type: application/json' -H 'Authorization: eyJhbGciOiJIUzUxMiJ9.                                                                                               eyJzdWIiOiJzc3RqZXJuZV9kYXNoYm9hcmRfNUB3aXNlaHViLmlvIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA5OTM5MzIwMTU2LCJleHAiOjE1MTA1NDQxMjB9.CQ6uXjoXe20Hhz-2AykJADRVGDddQPW_2RKh8VHX1m35eUTYD_P6vFFtD8DIARDihZayqXELxTunp5o_lPH2SQ' --data '{"id" : "7dc53df5-703e-49b3-8670-b1c468f47f1f",  "toName": "Barbara" , "toPhoneNumber": "+19738860779", "subject": "Wisehub", "bank" : "Zenith", "templateId": "2", "creditLineAmount" :"1000000 Naira"}'
```

Response eg.
```console
200
```

#### SMS: RECOMMEND INVESTMENTS
Request:

```console
curl 'http://...:..../api/sendSms' -XPOST -H 'content-type: application/json' -H 'Authorization: eyJhbGciOiJIUzUxMiJ9.                                                                                               eyJzdWIiOiJzc3RqZXJuZV9kYXNoYm9hcmRfNUB3aXNlaHViLmlvIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA5OTM5MzIwMTU2LCJleHAiOjE1MTA1NDQxMjB9.CQ6uXjoXe20Hhz-2AykJADRVGDddQPW_2RKh8VHX1m35eUTYD_P6vFFtD8DIARDihZayqXELxTunp5o_lPH2SQ' --data '{"id" :"7dc53df5-703e-49b3-8670-b1c468f47f1f",  "toName": "Barbara" , "toPhoneNumber": "+19738860779", "subject": "Wisehub", "bank" : "Zenith", "templateId": "3", "investmentAmount" :"10000 Naira", "yearlyYield" :"1000 Naira", "yield" :"10 %"}'
```

Response eg.
```console
200
```

#### SMS: RECOMMEND OVERDRAFT
Request:

```console
curl 'http://...:.../api/sendSms' -XPOST -H 'content-type: application/json' -H 'Authorization: eyJhbGciOiJIUzUxMiJ9.                                                                                               eyJzdWIiOiJzc3RqZXJuZV9kYXNoYm9hcmRfNUB3aXNlaHViLmlvIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA5OTM5MzIwMTU2LCJleHAiOjE1MTA1NDQxMjB9.CQ6uXjoXe20Hhz-2AykJADRVGDddQPW_2RKh8VHX1m35eUTYD_P6vFFtD8DIARDihZayqXELxTunp5o_lPH2SQ' --data '{"id" : "7dc53df5-703e-49b3-8670-b1c468f47f1f",  "toName": "Barbara" , "toPhoneNumber": "+19738860779", "subject": "Wisehub", "bank" : "Zenith", "templateId": "4", "account" :"1234567", "available" :"35,000 Naira"}'
```

Response eg.
```console
200
```

#### SMS: RECOMMEND LOANS
Request:

```console
curl 'http://...:.../api/sendSms' -XPOST -H 'content-type: application/json' -H 'Authorization: eyJhbGciOiJIUzUxMiJ9.                                                                                               eyJzdWIiOiJzc3RqZXJuZV9kYXNoYm9hcmRfNUB3aXNlaHViLmlvIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA5OTM5MzIwMTU2LCJleHAiOjE1MTA1NDQxMjB9.CQ6uXjoXe20Hhz-2AykJADRVGDddQPW_2RKh8VHX1m35eUTYD_P6vFFtD8DIARDihZayqXELxTunp5o_lPH2SQ' --data '{"id" : "7dc53df5-703e-49b3-8670-b1c468f47f1f",  "toName": "Barbara" , "toPhoneNumber": "+19738860779", "subject": "Wisehub", "bank" : "Zenith", "templateId": "5", "loanAmount" :"50000 Naira"}'
```

Response eg.
```console
200
```

#### SMS: RECOMMEND SAVINGS
Request:

```console
curl 'http://...:.../api/sendSms' -XPOST -H 'content-type: application/json' -H 'Authorization: eyJhbGciOiJIUzUxMiJ9.                                                                                               eyJzdWIiOiJzc3RqZXJuZV9kYXNoYm9hcmRfNUB3aXNlaHViLmlvIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA5OTM5MzIwMTU2LCJleHAiOjE1MTA1NDQxMjB9.CQ6uXjoXe20Hhz-2AykJADRVGDddQPW_2RKh8VHX1m35eUTYD_P6vFFtD8DIARDihZayqXELxTunp5o_lPH2SQ' --data '{"id" : "7dc53df5-703e-49b3-8670-b1c468f47f1f",  "toName": "Barbara" , "toPhoneNumber": "+19738860779", "subject": "Wisehub", "bank" : "Zenith", "templateId": "6", "saveAmount" :"500 Naira", "monthlyAmount" :"600 Naira", "yearlySavings" :"7,200 Naira"}'
```

Response eg.
```console
200
```
#### SMS: RECOMMEND AIRTIME
Request:

```console
curl 'http://...:.../api/sendSms' -XPOST -H 'content-type: application/json' -H 'Authorization: eyJhbGciOiJIUzUxMiJ9.                                                                                               eyJzdWIiOiJzc3RqZXJuZV9kYXNoYm9hcmRfNUB3aXNlaHViLmlvIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA5OTM5MzIwMTU2LCJleHAiOjE1MTA1NDQxMjB9.CQ6uXjoXe20Hhz-2AykJADRVGDddQPW_2RKh8VHX1m35eUTYD_P6vFFtD8DIARDihZayqXELxTunp5o_lPH2SQ' --data '{"id" : "7dc53df5-703e-49b3-8670-b1c468f47f1f",  "toName": "Barbara" , "toPhoneNumber": "+19738860779", "subject": "Wisehub", "bank" : "Zenith", "templateId": "13", "airtimeAmount" :"₦500"}'
```

Response eg.
```console
200
```


#### SMS: REMINDER INSURANCE
Request:

```console
curl 'http://...:.../api/sendSms' -XPOST -H 'content-type: application/json' -H 'Authorization: eyJhbGciOiJIUzUxMiJ9.                                                                                               eyJzdWIiOiJzc3RqZXJuZV9kYXNoYm9hcmRfNUB3aXNlaHViLmlvIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA5OTM5MzIwMTU2LCJleHAiOjE1MTA1NDQxMjB9.CQ6uXjoXe20Hhz-2AykJADRVGDddQPW_2RKh8VHX1m35eUTYD_P6vFFtD8DIARDihZayqXELxTunp5o_lPH2SQ' --data '{"id" : "7dc53df5-703e-49b3-8670-b1c468f47f1f",  "toName": "Barbara" , "toPhoneNumber": "+19738860779", "subject": "Wisehub", "bank" : "Zenith", "templateId": "7", "due" :"November 31, 2017", "account" : "1234567", "insuranceAmount" :"1000 Naira"}'
```

Response eg.
```console
200
```

#### SMS: REMINDER CREDITCARD
Request:

```console
curl 'http://...:.../api/sendSms' -XPOST -H 'content-type: application/json' -H 'Authorization: eyJhbGciOiJIUzUxMiJ9.                                                                                               eyJzdWIiOiJzc3RqZXJuZV9kYXNoYm9hcmRfNUB3aXNlaHViLmlvIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA5OTM5MzIwMTU2LCJleHAiOjE1MTA1NDQxMjB9.CQ6uXjoXe20Hhz-2AykJADRVGDddQPW_2RKh8VHX1m35eUTYD_P6vFFtD8DIARDihZayqXELxTunp5o_lPH2SQ' --data '{"id" : "7dc53df5-703e-49b3-8670-b1c468f47f1f",  "toName": "Barbara" , "toPhoneNumber": "+19738860779", "subject": "Wisehub", "bank" : "Zenith", "templateId": "8", "due" :"November 31, 2017", "account" : "1234567", "creditLineAmount" :"1000 Naira"}'
```

Response eg.
```console
200
```

#### SMS: REMINDER INVESTMENTS
Request:

```console
curl 'http://...:.../api/sendSms' -XPOST -H 'content-type: application/json' -H 'Authorization: eyJhbGciOiJIUzUxMiJ9.                                                                                               eyJzdWIiOiJzc3RqZXJuZV9kYXNoYm9hcmRfNUB3aXNlaHViLmlvIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA5OTM5MzIwMTU2LCJleHAiOjE1MTA1NDQxMjB9.CQ6uXjoXe20Hhz-2AykJADRVGDddQPW_2RKh8VHX1m35eUTYD_P6vFFtD8DIARDihZayqXELxTunp5o_lPH2SQ' --data '{"id" : "7dc53df5-703e-49b3-8670-b1c468f47f1f",  "toName": "Barbara" , "toPhoneNumber": "+19738860779", "subject": "Wisehub", "bank" : "Zenith", "templateId": "9", "due" :"November 31, 2017", "account" : "1234567", "investmentAmount" :"1000 Naira"}'
```

Response eg.
```console
200
```

#### SMS: REMINDER OVERDRAFT
Request:

```console
curl 'http://...:.../api/sendSms' -XPOST -H 'content-type: application/json' -H 'Authorization: eyJhbGciOiJIUzUxMiJ9.                                                                                               eyJzdWIiOiJzc3RqZXJuZV9kYXNoYm9hcmRfNUB3aXNlaHViLmlvIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA5OTM5MzIwMTU2LCJleHAiOjE1MTA1NDQxMjB9.CQ6uXjoXe20Hhz-2AykJADRVGDddQPW_2RKh8VHX1m35eUTYD_P6vFFtD8DIARDihZayqXELxTunp5o_lPH2SQ' --data '{"id" : "7dc53df5-703e-49b3-8670-b1c468f47f1f",  "toName": "Barbara" , "toPhoneNumber": "+19738860779", "subject": "Wisehub", "bank" : "Zenith", "templateId": "10", "due" :"November 31, 2017", "account" : "1234567"}'
```

Response eg.
```console
200
```

#### SMS: REMINDER LOANS
Request:

```console
curl 'http://...:.../api/sendSms' -XPOST -H 'content-type: application/json' -H 'Authorization: eyJhbGciOiJIUzUxMiJ9.                                                                                               eyJzdWIiOiJzc3RqZXJuZV9kYXNoYm9hcmRfNUB3aXNlaHViLmlvIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA5OTM5MzIwMTU2LCJleHAiOjE1MTA1NDQxMjB9.CQ6uXjoXe20Hhz-2AykJADRVGDddQPW_2RKh8VHX1m35eUTYD_P6vFFtD8DIARDihZayqXELxTunp5o_lPH2SQ' --data '{"id" : "7dc53df5-703e-49b3-8670-b1c468f47f1f",  "toName": "Barbara" , "toPhoneNumber": "+19738860779", "subject": "Wisehub", "bank" : "Zenith", "templateId": "11", "due" :"November 31, 2017", "account" : "1234567","loanAmount" :"1000 Naira"}'
```

Response eg.
```console
200
```
#### SMS: REMINDER SAVINGS
Request:

```console
curl 'http://...:.../api/sendSms' -XPOST -H 'content-type: application/json' -H 'Authorization: eyJhbGciOiJIUzUxMiJ9.                                                                                               eyJzdWIiOiJzc3RqZXJuZV9kYXNoYm9hcmRfNUB3aXNlaHViLmlvIiwiYXVkaWVuY2UiOiJ3ZWIiLCJjcmVhdGVkIjoxNTA5OTM5MzIwMTU2LCJleHAiOjE1MTA1NDQxMjB9.CQ6uXjoXe20Hhz-2AykJADRVGDddQPW_2RKh8VHX1m35eUTYD_P6vFFtD8DIARDihZayqXELxTunp5o_lPH2SQ' --data '{"id" : "7dc53df5-703e-49b3-8670-b1c468f47f1f",  "toName": "Barbara" , "toPhoneNumber": "+19738860779", "subject": "Wisehub", "bank" : "Zenith", "templateId": "12", "due" :"November 31, 2017", "account" : "1234567","saveAmount" :"1000 Naira"}'
```

Response eg.
```console
200
```
