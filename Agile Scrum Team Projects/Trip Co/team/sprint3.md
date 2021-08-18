# Sprint 3 - *T18* - *Ctrl Alt Elite*

## Goal

### Shorter trips to more places!
### Sprint Leader: *Zach Klausner*

## Definition of Done

* Version in pom.xml should be `<version>3.0.0</version>` for your final build for deployment.
* Increment release `v3.0` created on GitHub with appropriate version number and name.
* Increment `server-3.0.jar` deployed for testing and demonstration on SPRINT3 assignment.
* Sprint Review, Restrospective, and Metrics completed (team/sprint3.md).
* Tasks for planned epics this sprint are completed.


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
* Code Coverage above 40%
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

* What we left from Sprint 2
* *#69 User: Show me a map and itinerary for my trip.*
* *#70 User: The calculator data shouldn't go away when units change.*
* *#23 User: I may need distances in other units of measure.*

* New for Sprint 3
* *#146 User: Data shouldn't go away when I change tabs.*
* *#157 User: Make my trip shorter.*
* *#163 Give me a friendly message if something goes wrong.*
* *#148 User: Let me change my itinerary.*
* *#158 TripCo: validate all requests sent to the server and responses received by the client.*

Planning notes can be found [here](./images/PlanningSprint3).


## Metrics

| Statistic | # Planned | # Completed |
| --- | ---: | ---: |
| Epics | *8* | *3* |
| Tasks |  *31*   | *16* | 
| Story Points |  *46*  | *24* | 


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| *03/06/19* | *None* | *#80, #117* | *None* | 
| *03/08/19* | *#80* | *#117, #149, #151* | *None* |
| *03/11/19* | *#117, #186* | *#149, #151, #169* | *None* |
| *03/15/19* | *#169* | *#149, #151, #167* | *None* | 
| *03/25/19* | *#151* | *#149, 167* | *None* |
| *03/27/19* | *167* | *#149, #178, #203* | *None* |


## Review (focus on solution and technology)

In this sprint the goal is to be able to change the itinerary, give friendly messages for errors, validate server requests and optimize the trip distance. 

#### Completed epics in Sprint Backlog 

These Epics were completed.

* *#70 The calculator data shouldn't go away when units change. : Calculator data persists when tabs are switched.*
* *#146 Data shouldn't go away when I change tabs. : Data from other tabs persists*
* *#157 Make my trip shorter. : Trip itinerary trip can be shortened to desired optimization choice using the nearest neighbor algorithm .*


#### Incomplete epics in Sprint Backlog 

These Epics were not completed.

* *#23 I may need distances in other units of measure. : We put this in the Icebox because we lost a team member in the middle of the sprint.*
* *#148 Let me change my itinerary. : We were not able to add the ability to enter new locations. We did use a SQL database that allows the user to remove locations, reverse, rearrange and choose a new starting location.*
* *#158 validate all requests sent to the server and responses received by the client. : This was placed in the IceBox during our replanning because we lost a team member in the middle of the sprint.*
* *#163 Give me a friendly message if something goes wrong. : During replanning we decided to put it in the Icebox to finish more important epics.*


#### What went well

During this sprint Planning went well and was organized well to lay out what we need to get done. We also had to re-plan due to losing a team member in the middle of the sprint and we were able to choose what was more important to complete. 


#### Problems encountered and resolutions

The biggest problem we encountered was losing Spencer in the middle of the Sprint. We combated this by re-planning our epics and deciding what was more important to complete and what could be left out this sprint. Other problem we encountered were midterms, papers and of course Spring Break. This one was difficult to resolve since we all had these going on and some of us made use of time better than others by completing work before the heavy workload came. 


## Retrospective (focus on people, process, tools)

In this sprint, ...

#### What we changed this sprint

This sprint we had our team meeting with Dave and we learned what is the route to take on working together as a team. Although we did not do it the entire sprint, making time to actually sit down and code together proved to be more beneficial to aid each other and prevent us from spinning our wheels. 

#### What we did well

We worked well when we were working together at the same time, this allowed team members with more understanding of the code to help pick the whole team up and work efficiently. 

#### What we need to work on

We could improve communicating better, and creating more time to meet as a group to code and prevent us from doing everything seperate and hoping it all fits together. More testing will also be a thing we strive for. 

#### What we will change next sprint 

We will change how much we are actually able to complete since the last two sprints we fell short of our goals. Time efficiency is a reoccurring problem but by setting a time and place to work on it together, we will improve on getting more done incrementally. 