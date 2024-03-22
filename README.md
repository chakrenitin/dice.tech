Problem Statement :- 
    Write a server by using SpringBoot Java and integrate Weather API from Rapid API. On the
    basis of that integration, expose your RESTful APIs as follows with JSON response.
    Authentication Method: Header-based authentication with random client id and random
    client secret.

    API 1: Get the Weather forecast summary of Any city using API
    API 2: Get hourly Weather forecast details of Any city using API


You will find the postman collection in the repository for your reference during testing of the API's.

Use Client ID :- 'dicetechuser' and Client Secret :- 'f2a1ed52710d4533bde25be6da03b6e3'

Added extra API :- /api/v1/auth/authenticate

    Which will provide you the JWT token once you authenticate your clientID and clientSecret. Use that JWT token for further authentication for API's.
    This will help you when you try to integrate the provided API's with any Automation tools (e.g. Scriptrunner, Postman etc).

