# Hotel Occupancy Manager #

Application to compute optimal distribution of available rooms in the hotel (economy and premium) between guests looking for a room.

Run HotelOccupancyManager.class and open in browser http://localhost:8008/occupancy?premium=3&economy=3, providing the number of free premium and economy rooms.
Port can be changed in application.properties file.

Two test classes are present:
OccupancyServiceTest.class - testing different business scenarios (combinations of rooms and prices)
OccupancyControllerIT.class - simple integration test for the REST API

The prices guests are ready to pay are hardcoded when running an app normally, but can be changed in the tests.

