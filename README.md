# EY Assignment

1. TODO Shortner 

Steps to run the project.

1. Download or clone the Project 
2. Open it in STS or IntelliJ
3. Update the pom.xml with all the dependencies 
4. Run as a Spring boot App
5. Testing can be done in Postman 

Note: Provided the DockerFile to generate jar files to run on any machine 

Before Running the API's we need to set security passowrd to access the api's.

The password is provide when we run the application in the local. The password is generated dynamically.
Here is an Example in my system and you will get another password in your system.

![Optional Text](https://github.com/Darshan-20310597/Ey_Assignment/blob/master/ey.jpg)

In Postman, to test the api we need to set the basic authentication
Refer the below image for the same 

username - user
password - password is dynmaically allocated.

https://github.com/Darshan-20310597/Ey_Assignment/blob/master/ey1.jpg

1. Todo API and values to test in postman 


1.1 Creating a Todo  

```
Post url : http://localhost:8080/api/generate

```

```
body : 
{
  "todoItem": "test for post method",
  "status": "test successful",
  "setReminder": true
}
```

Response :
```
{
    "id": 5,
    "todoItem": "test for post method",
    "status": "test successful",
    "updatedDate": "2021-09-20",
    "setReminder": true
}
```
1.2 : Retrive to the TODO List 

```
Get url : http://localhost:8080/api/getTodo

```

Response :
```
[
    {
        "id": 1,
        "todoItem": "Learn Spring boot",
        "status": "Not Completed",
        "updatedDate": "2021-09-20",
        "setReminder": true
    },
    {
        "id": 2,
        "todoItem": "Make an appointment with the doctor",
        "status": "Completed",
        "updatedDate": "2021-09-20",
        "setReminder": false
    },
    {
        "id": 3,
        "todoItem": "Go for a Walk",
        "status": "Not Completed",
        "updatedDate": "2021-09-20",
        "setReminder": true
    },
    {
        "id": 4,
        "todoItem": "Cook Dinner",
        "status": "Completed",
        "updatedDate": "2021-09-20",
        "setReminder": false
    }
]
```
1.3 Retrive to the TODO List using unique ID  

```
Post url : http://localhost:8080/api/getTodo/2

```

Response :
```
{
    "id": 2,
    "todoItem": "Make an appointment with the doctor",
    "status": "Completed",
    "updatedDate": "2021-09-22",
    "setReminder": false
}
```
1.4 :  Delete to the value on the TODO List using unique ID  

```
Get url : http://localhost:8080/api/deleteTodo/2

```

Response :
```
We get a 200 Ok Response 
```

1.5 :  Update using unique ID  

```
Get url : http://localhost:8080/api/update/5

```
```
body : 
{
  "todoItem": "test for post method",
  "status": " Not Completed",
  "setReminder": false
}
```
Response :
```
{
    "id": 5,
    "todoItem": "test for post method",
    "status": " Not Completed",
    "updatedDate": "2021-09-20",
    "setReminder": false
}
```

