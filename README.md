# Bakery
MIPT Java client-service project
## About project
This is an app which is supposed to help clients searching for a great baker and bakers waiting for an order tofind each other
## Base functional
Three types of users:
- baker
- client
- admin
### Client functional
- registration
- looking for bakers with certain specialisation
- targetting a baker
- changing a baker
- finishing the order
### Baker functional
- registration
- choosing his/her specialisation
### Admin functional
- registration secured by password
- list of bakers view
- list of clients view
- register new admin
## Creators
[Nastya](https://github.com/dunaya),
[Eva](https://github.com/ezheviica)
## How to start the app
1. install docker-compose [for example, here](https://docs.docker.com/compose/install/)
2. copy a docker.yml file in a directory on your computer and start it (docker-compose up --build -d)
3. create a directory and clone there this repository
4. start a bash script launch.sh
5. open https://localhost:8080/login 
