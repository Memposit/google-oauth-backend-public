## The Google authentication
<img src="https://www.bezkoder.com/wp-content/uploads/2019/10/spring-boot-authentication-spring-security-architecture.png"/>
<img src="https://miro.medium.com/max/1031/1*iPM8tN6AXWx7wakCD_16-Q.png"/>
## Requirements

Building the person service:
1. Java 17+
2. Maven
3. MySql

## Installation
### These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

#### Copy the project from git repository


## Documentation ##

 ### [Full documentation](IdeaProjects/google-oath/uaa/doc/index.html)  ###

## Documentation for API Endpoints

All URIs are relative to *http://localhost:8080*
# The Auth controller.

## 1.Authenticate user response entity.
**URL:** http://localhost/api/auth/signin

**Type:** POST



**Request-parameters:**

Parameter|Type|Required|DefaultValue|Description
---|---|---|---|---
└── actionRequest|object|true|&nbsp;|the action request<br/>
&nbsp;&nbsp; ├── userID|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── providerUserId|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── imgUrl|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── displayName|string|true|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── email|string|true|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── provider|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── password|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── role|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; └── matchingPassword|string|&nbsp;|&nbsp;|&nbsp;

**Request-example:**
``` json
http://localhost/api/auth/signin
```

**Request-body-example:**
``` json
{
  "imgUrl": "SzO",
  "password": "PCk0v",
  "role": "vxY",
  "provider": "Kmd0",
  "matchingPassword": "tJywWFG",
  "displayName": "0O1V3WR",
  "userID": "7WyJOv",
  "providerUserId": "6s4",
  "email": "iGKNSb9M"
}
```

**Response-fields:**

Field|Type|Description
---|---|---
&nbsp;|object|the<br/>response entity



## 2.Register user response entity.
**URL:** http://localhost/api/auth/signup

**Type:** POST



**Request-parameters:**

Parameter|Type|Required|DefaultValue|Description
---|---|---|---|---
└── actionRequest|object|true|&nbsp;|the action request<br/>
&nbsp;&nbsp; ├── userID|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── providerUserId|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── imgUrl|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── displayName|string|true|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── email|string|true|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── provider|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── password|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── role|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; └── matchingPassword|string|&nbsp;|&nbsp;|&nbsp;

**Request-example:**
``` json
http://localhost:8080/api/auth/signup
```

**Request-body-example:**
``` json
{
  "imgUrl": "GsYTks9z5",
  "password": "I",
  "role": "LG9Os21",
  "provider": "H43i",
  "matchingPassword": "7TwX",
  "displayName": "r",
  "userID": "MVQmjVRhCR",
  "providerUserId": "Oj0B",
  "email": "x"
}
```

**Response-fields:**

Field|Type|Description
---|---|---
&nbsp;|object|the<br/>response entity


## Documentation for Models


<h3>Table name: users</h3>

|field|dataType|not null|index|primary|defaultValue|description|
|----|----|----|----|----|----|----|
|id|varchar(255)|Y|Y|Y| | |
|created_date|datetime|Y| | | | |
|email|varchar(255)|Y| | | | |
|email_verified|bit(1)|Y| | | | |
|image_url|varchar(255)| | | | | |
|modified_date|datetime| | | | | |
|name|varchar(255)|Y| | | | |
|password|varchar(255)| | | | | |
|provider|varchar(255)|Y| | | | |
|provider_id|varchar(255)| | | | | |
|role_id|varchar(255)| |Y| | | |



<h3> table name: roles </h3>

|field|dataType|not null|index|primary|defaultValue|description|
|----|----|----|----|----|----|----|
|id|varchar(255)|Y|Y|Y| | |
|name|varchar(255)|Y| | | | |



# The Auth controller.

## 1.Authenticate user response entity.
**URL:** http://localhost:8080/api/auth/signin

**Type:** POST



**Request-parameters:**

Parameter|Type|Required|DefaultValue|Description
---|---|---|---|---
└── actionRequest|object|true|&nbsp;|the action request<br/>
&nbsp;&nbsp; ├── userID|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── providerUserId|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── imgUrl|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── displayName|string|true|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── email|string|true|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── provider|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── password|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── role|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; └── matchingPassword|string|&nbsp;|&nbsp;|&nbsp;

**Request-example:**
``` json
http://localhost/api/auth/signin
```

**Request-body-example:**
``` json
{
  "imgUrl": "Zo91VMXU3G",
  "password": "X",
  "role": "hso99s",
  "provider": "R",
  "matchingPassword": "kJojmsrc",
  "displayName": "apmtEQ3G",
  "userID": "o5oLq",
  "providerUserId": "B",
  "email": "I"
}
```

**Response-fields:**

Field|Type|Description
---|---|---
&nbsp;|object|the<br/>response entity

## 2.Register user response entity.
**URL:** http://localhost:8080/api/auth/signup

**Type:** POST



**Request-parameters:**

Parameter|Type|Required|DefaultValue|Description
---|---|---|---|---
└── actionRequest|object|true|&nbsp;|the action request<br/>
&nbsp;&nbsp; ├── userID|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── providerUserId|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── imgUrl|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── displayName|string|true|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── email|string|true|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── provider|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── password|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; ├── role|string|&nbsp;|&nbsp;|&nbsp;
&nbsp;&nbsp; └── matchingPassword|string|&nbsp;|&nbsp;|&nbsp;

**Request-example:**
``` json
http://localhost/api/auth/signup
```

**Request-body-example:**
``` json
{
  "imgUrl": "WST",
  "password": "FzdP",
  "role": "8OgL4",
  "provider": "H",
  "matchingPassword": "9CioyosXj2",
  "displayName": "lL6",
  "userID": "jhSKgebz",
  "providerUserId": "JGcQYG",
  "email": "LD51"
}
```

**Response-fields:**

Field|Type|Description
---|---|---
&nbsp;|object|the<br/>response entity




## Documentation for Authorization

<h4>1. The client must be authenticated and sends a request to the server endpoint with the client application’s 
forwarding URL (used when all authentication is complete). </h4>
### Example ###
http://localhost:8080/oauth2/authorization/google/? 
<h4> redirect_uri=http://localhost:4200/login </h4>

<h4>2. The server stores the authentication request and redirects the client to the Google authentication page,
specifying the redirection URL that leads back to the server.
The authentication request that came to Google also specifies: </h4>
####  The state identifies the authentication request stored on the server. ####
####  Response type ( code type is used to obtain the exchange code for access and update token).  ####
####  The client ID (stored on the server side). ####
####  Scope (what access is requested)  ####
<h4> 5. State. The state parameter is to mitigate CSRF attacks by using a unique and non-guessable value associated with 
each authentication request about to be initiated. That value allows you to prevent the attack by confirming that the 
value coming from the response matches the one you sent.You can use the state parameter to encode an application state 
that will put the user where they were before the authentication process started.</h4>

### Example

#### https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount? ####
#### - response_type=code& ####
#### - client_id=787695643713-kof6itn0efdhrr96otqtc8qrb38bbggm.apps.googleusercontent.com& ####
#### - scope=email%20profile& ####
#### - state=boksbTJyVUtK4SGQqybn8PuTPdXSdfkD-IHfhRM_am0%3D&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Foauth2%2Fcallback%2Fgoogle&flowName=GeneralOAuthFlow  ####

<h4>3. Google adds the requested code (along with others) to query parameters of a redirect URL and redirects the client to the server.
The server uses the code to exchange it for access and refresh token. Access token is used to get access to a user information stored in Google profile.
It can expire. Refresh token is used to update the access token.</h4>

### Example 

#### state: boksbTJyVUtK4SGQqybn8PuTPdXSdfkD-IHfhRM_am0=  ####
#### code: 4/0AdQt8qihO-6rnFxZvhCL0n7uUlzPflDp6ndnIw8DnBMjWu5jQHGtZ6hYhnME7mXAumUgXw  ####
#### scope: email profile https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile openid  ####
#### authuser: 0  ####
#### prompt: consent  ####

<h4>4. When the server got the access token, it requests all the needed Google profile information, adds additional custom fields and packs it all into a JWT token.
This token is added as a query parameter to a redirect URL specified in the initial client request.</h4>

### Example  ####
#### token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2MGM1ZjJhNS02N2FjLTRmZDItODg3Ny05MmM1NmFjNmE4NGEiLCJpYXQiOjE2NTk1MTQ4NzksImV4cCI6MTY2MDM3ODg3OX0.f1K8LZ3fE7DmcZB6N62s8U55zwA3cDUUMFRYwr9eRy4 ####
