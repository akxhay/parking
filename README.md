# Parking management system

## Prerequisites

- Java Development Kit (JDK) - Install Java 17 or later.
- Apache Maven - Install Maven to build and manage the project.
- PostgreSQL Database - Install and set up a PostgreSQL database. You'll need the database name, username, and password.

## Configuration

1. Clone the repository:
```shell
git clone https://github.com/akxhay/parking.git
```
2. Open the project in your favorite IDE (e.g., IntelliJ IDEA, Eclipse).

3. Configure PostgreSQL Database:
    * Create a PostgreSQL database with your preferred name (e.g., parking_management).
    * Update the database connection properties in src/main/resources/application.properties:
```shell
spring.datasource.url=jdbc:postgresql://localhost:5432/parking_management
spring.datasource.username=username
spring.datasource.password=password
```
4. Build and Run
    * Build the project using Maven:
   ```shell
    mvn clean install
   ```
    * Run the Spring Boot application:
    ```shell
    mvn spring-boot:run
    ```
5. The application should start, and you can access the following APIs.

**APIs**

1. Create 1200 dummy parking lots

 ```shell
curl --location 'http://localhost:8080/parking/lot/dummy' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data '{
  "parkingLots": 1200,
  "floorsPerParkingLot": 3,
  "smallSlotsPerFloor": 100,
  "mediumSlotsPerFloor": 100,
  "largeSlotsPerFloor": 100,
  "xlargeSlotsPerFloor": 100
}'

Response: Dummy parking lots created successfully

```

1. Create parking lot

 ```shell
curl --location 'http://localhost:8080/parking/lot' \
--header 'Content-Type: application/json' \
--data '{
    "name": "parking lot 1",
    "floors": [
        {
            "name": "floor 1",
            "smallSlots": 1,
            "mediumSlots": 1,
            "largeSlots": 1,
            "xlargeSlots": 1
        }
    ]
}'

Response: 
{
    "id": 1202,
    "name": "parking lot 1",
    "floors": [
        {
            "id": 3603,
            "name": "floor 1",
            "parkingSlots": [
                {
                    "id": 1440025,
                    "slotType": "s",
                    "slotNumber": 1,
                    "occupied": false
                },
                {
                    "id": 1440026,
                    "slotType": "m",
                    "slotNumber": 1,
                    "occupied": false
                },
                {
                    "id": 1440027,
                    "slotType": "l",
                    "slotNumber": 1,
                    "occupied": false
                },
                {
                    "id": 1440028,
                    "slotType": "xl",
                    "slotNumber": 1,
                    "occupied": false
                }
            ]
        }
    ]
}

```

3. Fetch all parking lots

 ```shell
curl --location 'http://localhost:8080/parking/lot?pageNumber=0&pageSize=1'

Response: 
{
    "totalPages": 1202,
    "totalElements": 1202,
    "parkingLots": [
        {
            "id": 1202,
            "name": "parking lot 1",
            "floors": [
                {
                    "id": 3603,
                    "name": "floor 1",
                    "parkingSlots": [
                        {
                            "id": 1440025,
                            "slotType": "s",
                            "slotNumber": 1,
                            "occupied": false
                        },
                        {
                            "id": 1440026,
                            "slotType": "m",
                            "slotNumber": 1,
                            "occupied": false
                        },
                        {
                            "id": 1440027,
                            "slotType": "l",
                            "slotNumber": 1,
                            "occupied": false
                        },
                        {
                            "id": 1440028,
                            "slotType": "xl",
                            "slotNumber": 1,
                            "occupied": false
                        }
                    ]
                }
            ]
        }
    ]
}

```

4. Delete parking lot

 ```shell
uri: lot/{parking_lot_id}
parking_lot_id: parking lot id

curl --location --request DELETE 'http://localhost:8080/parking/lot/1203'

Response:Parking lot deleted successfully

```

5. Get parking slot

 ```shell
uri: getslot/{parking_lot_id}/{size}
parking_lot_id: parking lot id
size: s/m/l/xl

curl --location 'http://localhost:8080/parking/getslot/1204/s' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

Response:
{
    "slotId": 1440033,
    "slotType": "s",
    "slotNumber": 1,
    "floorId": 3605,
    "floorName": "floor 1"
}

```

6. Release parking slot

 ```shell
uri: releaseslot/{parking_lot_id}/{slot_id}
parking_lot_id: parking lot id
slot_id: slot id

curl --location --request PUT 'http://localhost:8080/parking/releaseslot/1/1440033' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json'

Response:Parking slot freed successfully
```

Thank you