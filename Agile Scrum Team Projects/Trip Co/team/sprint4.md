# Sprint 4 - *T18* - *Ctrl Alt Elite*

## Goal

### Worldwide!
### Sprint Leader: *Aaron Marquez*

## Definition of Done

* Version in pom.xml should be `<version>4.0.0</version>` for your final build for deployment.
* Increment release `v4.0` created on GitHub with appropriate version number and name.
* Increment `server-3.5.jar` deployed for testing and demonstration on CHECK4 assignment.
* Increment `server-4.0.jar` deployed for testing and demonstration on SPRINT4 assignment.
* Sprint Review, Restrospective, and Metrics completed (team/sprint4.md).


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
* Code Coverage above 50%
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

* *#148 Let me change my itinerary*
* *#158 validate all requests sent to the server and responses received by the client.*
* *#23 I may need distances in other units of measure*
* *#163 Give me a friendly message if something goes wrong.*
* *#212 I would like to highlight certain places on the map*
* *#213 Let me plan trips world wide.*
* *#211 I want to view my trip in other tools.*
* *#214 Can trips be shorter?*

Planning notes can be found [here](./images/PlanningSprint4).
* With the addition of two teammates, we are now a team of five and have added more to complete for this sprint. We will complete all the remaining epics from sprint 3 and piecing together components with aid of their code base to get the current issues ironed out. 


## Metrics

| Statistic | # Planned | # Completed |
| --- | ---: | ---: |
| Epics | *8* | *value* |
| Tasks |  *30*   | *value* | 
| Story Points |  *39*  | *value* | 


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| *4/1/19* | *None* | *#160* | *None* | 
| *4/3/19* | *#160* | *#233, #236* | *None* |
| *4/5/19* | *#236* | *#233, #168* | *None* |
| *4/8/19* | *#233, #242* | *#166, #168, #240, #174, #235* | *None* |
| *4/12/19* | *#166, #168* | *#223,#240,#162* | *None* |
| *4/15/19* | *#265* | *#240, #174, #273, #162, #226* | *None* |
| *4/17/19* | *#226, #162* | *#240, #174, #239, #215, #228, #230* | *None* |

## Review (focus on solution and technology)

In this sprint, we implement a shorter 2 opt algorithm as well as support for a world wide database. We also have validation of both server and client. Many UI updates for Itinerary, units and Search have been added. 

#### Completed epics in Sprint Backlog 

These Epics were completed.

* *#158 validate all requests sent to the server and responses received by the client:Sever and client responses are validated, JSON schemas updated for client and server.*
* *#148 Let me change my itinerary:Ability to add, remove, reverse, set starting location and rearrange the order of locations is now implemented.*
* *#23 I may need distances in other units of measure: With a new UI the user can specify units and it is saved so user does not have to reenter them.*
* *#212 I would like to highlight certain places on the map: Markers are no longer displayed by default, they are now selectable, clearable and show useful information.*
* *#213 Let me plan trips world wide: Data base has been updated to handle world wide locations.*
* *#214 Can trips be shorter?: We implemented 2opt using nearest neighbor making the trip even shorter and not allow crossing.*

#### Incomplete epics in Sprint Backlog 

These Epics were not completed.

* *#163 Give me a friendly message if something goes wrong: We never got around to it in this sprint.*
* *#211 I want to view my trip in other tools: We were able to write files to CSV but not SVG or KML*
* *#212 I would like to highlight certain places on the map: Merge conflicts prevented this. Markers are no longer displayed by default, they are now selectable, clearable and show useful information.*

#### What went well

During the sprint we were able to meet multiple times to work together on code and proved to be beneficial we got the majority of the epics we wanted and made sure that they were in proper working condition. 


#### Problems encountered and resolutions

Some problems we encountered had to do with not having as much time as we would've liked to work on the project. Mostly having other classes increase in workload this time of year. We had some hiccups in communication as far as changing configurations and UI. Waiting till the last minute also proves to be a reoccurring problem. 


## Retrospective (focus on people, process, tools)


#### What we changed this sprint

Our changes for this sprint included meeting together to work on projects and help each other with any clarifications when trying to accomplish something. We also accommodated the team based on what they felt comfortable and moved tasks around to get things done more efficiently. 

#### What we did well

We worked well together and helped each other past road blocks or understanding part of code that someone else wrote. We communicated well and didnt leave PR open for too long. 

#### What we need to work on

We could improve our testing to cover more code and be more beneficial to providing useful test cases. We could also work on time management especially when it comes to finishing the final part where many things could go wrong. 

#### What we will change next sprint 

We will continue to move team members to work on their strong suits as well as help those who aren't as comfortable to make better progress. We will also accommodate the amount of epics we choose due to having a team member contributing nothings. 
