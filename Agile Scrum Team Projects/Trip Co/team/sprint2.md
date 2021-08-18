# Sprint 2 - *18* - *Ctrl Alt Elite*

## Goal

### A map and itinerary!
### Support for multiple latitude and longitude formats
### 
### Sprint Leader: *Aaron Marquez*

## Definition of Done

* Version in pom.xml should be `<version>2.0.0</version>` for your final build for deployment.
* Increment release `v2.0` created on GitHub with appropriate version number and name.
* Increment deployed for testing and demonstration on SPRINT2 assignment.
* Sprint Review and Restrospectives completed (team/sprint2.md).
* The user's location is shown on the map.
* The user is able to configure and save a custom distance unit.
* An itinerary is displayed with stages shown on the map.
* Calculator data persists when units are changed.
* More formats for latitude and longitude are supported.
* A map is displayed with the distance calculator.


## Policies

#### Mobile First Design!
* Design for mobile, tablet, laptop, desktop (in that order).
* Use ReactStrap for a consistent interface (no HTML, CSS, style, etc.).
* Must adhere to the TripCo Interchange Protocol (TIP) for interoperability and testing.
#### Clean Code
* Code Climate maintainability of A or B.
* Code adheres to Google style guides for Java and JavaScript.
#### Test Driven Development
* Write method headers, unit tests, and code in that order.
* Unit tests are fully automated.
#### Configuration Management
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* All commits include a task/issue number.
* All commits include tests for the added or modified code.
* All tests pass.
#### Continuous Integration / Delivery 
* Master is never broken.  If broken, it is fixed immediately.
* Continuous integration successfully builds and tests all pull requests for master branch.
* All Java dependencies in pom.xml.  Do not load external libraries in your repo. 


## Plan

This sprint will complete the following Epics.

* *#19 I want to know where I am on the map*
* *#23 I may need distances in other units of measure*
* *#69 Show me a map and itinerary for my trip*
* *#70 The calculator data shouldn't go away when units change*
* *#71 Enter latitudes and longitudes in the calculator using degree-minute-second and other formats*
* *#72 It would be nice to see a map with the calculator*


Key planning decisions for this sprint include breaking things apart in order of difficulty. Focusing more time and resources to more difficult and important epics. We will also be drawing out our UI changes to visualize the layout of the map and itinerary for both mobile and desktop view. 


Planning session notes can be found [here](./images/Planning). These show how we prioritized what epics we need to do and the difficulty of getting them done. 


## Metrics

| Statistic | # Planned | # Completed |
| --- | ---: | ---: |
| Epics | *6* | *3* |
| Tasks |  *14*   | *30* | 
| Story Points |  *28*  | *29.4* | 


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| *2/15/19* | *#77* | *#83, #97, #80, #96* | *None* | 
| *2/18/19* | *#77* | *#83, #97, #80, #96* | *None* |
| *2/20/19* | *#77, #97, #73, #96* | *#83, #80, #88* | *None* |
| *2/22/19* | *#77, #97, #73, #96, #88, #97, #102* | *#80, #117, #83* | *None* |
| *2/25/19* | *#77, #97, #73, #96, #88, #97, #102, #114* | *#80, #117, #89* | *None* |
| *2/27/19* | *#77, #97, #73, #96, #88, #97, #102, #114, #83* | *#80, #117, #75 #72, #89* | *None*| 


## Review (focus on solution and technology)

In this sprint, we sought out to add a trip itinerary that uses our Distance calculator and map to plan out a trip. A user can upload their own itinerary file and map it out. We do this by using React state and props to pass data to various components that will use it. We also have added support for multiple Latitude and Longitude formats and a map for the calculator. 

#### Completed epics in Sprint Backlog 

These Epics were completed.

* *##71 Enter latitudes and longitudes in the calculator using degree-minute-second and other formats.: We successfully updated the client to handle multiple Latitudes and longitudes in various formats using Coordinate Parser to put it into a single form for us to calculate.*
* *##72 It would be nice to see a map with the calculator: We were able to implement the same visual map used in home to the calculator page.*
* *##19 I want to know where I am on the map: We were able to complete geolocation on the map to know where the user is.*

#### Incomplete epics in Sprint Backlog 

These Epics were not completed.

* *##69 Show me a map and itinerary for my trip: We were able to get many parts completed for this epic, but we were not able to choose what to display for each column in the itinerary table. We weren't able to mark points on the map and draw lines in between.*
* *##70 The calculator data shouldn't go away when units change: Currently we have minimal knowledge on how cookeis work to keep the data fields stored. This will be fixed with more understanding and learning of cookies and storing data in them.*
* *##23  I may need distances in other units of measure: We were able to take custom units but were not able to keep them saved.*

#### What went well

* We were able to keep the code working together nicely, this had many parts using state which makes passing data and using it much more uniform and clean. 
* We also furthered our understanding of React making more use of state, props and UI elements to make use of the map, Table and points on the map. 
* We explained each others code and made it easy to read. 


#### Problems encountered and resolutions

* *Time managment was a hit for us this time around. With the larger epics, we underestimated the complexity and time it would take us to complete each of the tasks and how they relied on each other.*
* *There is also still lots to learn with react elements and with more time we'll get more comfortable with implementing them*
* *We made a strive to test our code more as well as including tests with our commits but we would also like to see more testing methods to guarantee working results in both client and server.*



## Retrospective (focus on people, process, tools)

In this sprint, we worked well in communicating through slack, resolve disagreements by explaining solutions and compromising on why it could or could not be beneficial. We had great participation by everyone but we all lacked in participation during the first week. We were able to read the TripCo guides to help us along the way as well as understanding the TIP.md for the requirements in our implementation. 

#### What we changed this sprint

* *Our changes for this sprint included, testing individual codes and making it a habit to pull branches and individually test others pull requests to ensure that they are working properly.*
* *We made no commits to master.*
* *We made efforts to understand and explain our implementations to better understand new methods.*

#### What we did well

* *We communicated well in both slack and scrums during class.*
* *We managed our branches well with only a few merge conflicts towards the end.*
* *We completed, reviewed and tested pull requests in a timely fashion.*
* *Everyone worked together having no hitch-hikers or Lone-Wolf's.*
* *We were able to resolve conflicts efficiently making sure the proper implementation is being carried out.*

#### What we need to work on

* **Time managment** - *We need to spread out our tasks even more and work on it from the beginning. This will allow us to properly address and upcoming issues without being overwhelmed.*
* *Estimate your task time more effectively and reinstating what we have time to complete with the given time.*
* *We will continue to knock out smaller tasks to begin with so they don't pile up.*

#### What we will change next sprint 

* *We will properly address the correct amount of time for our epics and tasks now that we have a better understanding on how it all plays out.*
* *Provide more test cases for our server.*
* *We will provide more rigid testing to our individual contributions to avoid any failed builds.*
