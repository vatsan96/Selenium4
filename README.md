# Selenium4
Features of Selenium4

A template project to get started with Selenium 4

```
Step 1: git clone https://github.com/abalmukund/Selenium_4.git
```

There are 6 packages in src/test/java to explain 6 different features of the Selenium4
1. Browser options
2. Selenium Grid
3. Relative Locators
4. Shadow DOM support
5. WebDriver BiDi
6. Window Management
7. Change in Action class
8. Decorating WebDriver - Highlight elements before performing any operation
9. Handle authentication pop-ups

For Selenium Grid:
For detailed features about Selenium Grid please see: https://www.selenium.dev/documentation/grid/
Here example is available with the docker. We are using testNG parallel execution for cross browser with docker.
Install docker: https://docs.docker.com/engine/install/
Pull Selenium images after installing docker. Please use following commands from command line:

selenium/hub
```
$ docker pull selenium/hub
```
Image for running Grid Hub

selenium/node-chrome
```
$ docker pull selenium/node-chrome
```
Grid Node with Chrome installed, needs to be connected to a Grid Hub

selenium/node-firefox
```
$ docker pull selenium/node-firefox
```
Grid Node with Firefox installed, needs to be connected to a Grid Hub

Note: By default, docker installs the latest available browser versions.
Verify if the images are reflected in the system by using:
```
$ docker images
```

Launch Docker Compose with following command:
```
$ docker-compose -f docker-compose-v3.yml up -d
```

Check docker process:
```
$ docker ps
```

Check on browser: http://localhost:4444/

Tear down:
```
$ docker-compose -f docker-compose-v3.yml down
```