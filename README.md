# Zenika-Meeting-Planner Docs
Before you deep dive into the code please take a look at this readme

To get started first we need to generate the rooms in the use case(the use case is at top folder of this repo), 
to do that please hit the following endpoint: 
Get http://localhost:8080/api/v1/rooms/generated
You are good to go if you got something like:
```agsl
[
    {
        "id": 1,
        "name": "E1001",
        "capacity": 23,
        "availableTools": [
            "NEANT"
        ]
    },
    {
        "id": 2,
        "name": "E1002",
        "capacity": 10,
        "availableTools": [
            "ECRAN"
        ]
    },
]
```
You should get 12 rooms generated

Now that we have generated our 12 rooms, we are ready to start making reservations, to make a reservation
Send a post request with the following object as json body(Postman is your friend):
Post http://localhost:8080/api/v1/reservations
```agsl
{
    "startingHour": 9,
    "reservationType": "VC",
    "numOfPeople": 6
}
```
Fields:
```agsl
-> staringHour: the hour you want the meeting to start at, in our case, the meeting will start at 9 and 
will last for an hour(this could be changed afterwards for scalibility)
-> reservationType: the type of meeting, according to the use case, there are 4 types of meetings 
VC, SPEC, RS, RC(Please the use case for more details)
-> numOfPeople: the number of people the meeting was planned for
```

If the reservation went successful you should get a response in the following form:
```agsl
{
    "error": false,
    "message": "Your reservation for room E3001 has been registered successfully, we are waiting for you;)",
    "data": {
        "id": 2,
        "room": {
            "id": 21,
            "name": "E3001",
            "capacity": 13,
            "availableTools": [
                "ECRAN",
                "WEBCAM",
                "PIEUVRE"
            ]
        },
        "startingHour": 9,
        "numberOfPeople": 6,
        "reservationType": "VC"
    }
}
```

Otherwise you will get something like:

```agsl
{
    "error": true,
    "message": "We are booked right now for VC meetings and we are totally sorry try with another type (RS, RC, SPEC or VC)",
    "data": null
}
```

To get the reservations hit this endpoint:
Get http://localhost:8080/api/v1/reservations

You should get something like:
```agsl
[
    {
        "id": 1,
        "room": {
            "id": 9,
            "name": "E3001",
            "capacity": 13,
            "availableTools": [
                "ECRAN",
                "WEBCAM",
                "PIEUVRE"
            ]
        },
        "startingHour": 9,
        "numberOfPeople": 8,
        "reservationType": "VC"
    },
]
```

To get the rooms you have already generated or to just check hit this endpoint:
Get http://localhost:8080/api/v1/rooms

To help you with the tests check the image in the top folder, it showcase the the outcomes
we expect from the tests