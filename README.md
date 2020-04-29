## Staxter Task2 for User REST Controller

![Staxter](https://images.prismic.io/staxter/05d507f2-e0b1-4e12-9040-8d9fec7363a0_get-a-staxter-mastercard.svg?auto=compress,format)

### Task Implementation

The project contains the necessary end-points to 

- create a member
- login

I decided to use a ConcurrentHashMap to keep users since not allowed to use a database

### Build and run

#### Prerequisites

- Java 8
- Maven > 3.0

## Build
Change directory to the project root folder (where the pom.xml exists)

And run 

 `$ mvn clean install`

## Run

#### From Terminal

 `$ mvn spring-boot:run`


#### From Eclipse (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.


## Testing

Tests will execute automatically

#### Example JSON to create a user

```json
{
"firstName":"Arda",
"lastName" : "Bakan",
"userName" : "user1",
"plainTextPassword" : "12345"
}
```
