# Sprint 5 - *T18* - *Ctrl Alt Elite*

## Goal

### A Beautiful User Experience!
### Sprint Leader: *Matteo Vera*

## Definition of Done

* Version in pom.xml should be `<version>5.0.0</version>` for your final build for deployment.
* Increment release `v5.0` created on GitHub with appropriate version number and name.
* Increment `server-5.0.jar` deployed for testing and demonstration on SPRINT5 assignment.
* Sprint Review, Restrospective, and Metrics completed (team/sprint5.md).


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
* Each team member must complete Interop with another team and file an issue in the **class** repo with the results.
  * title is your team number and your name, 
  * labels should include Interop and the Team that you tested with, 
  * description should include a list of tests performed, noting any failures that occurred.


## Plan

This sprint will complete the following Epics.

* *#211 "I want to view my trip in other tools": Add functionality to allow the user to 
export their trip in a variety of formats so that they can view their trip in other tools.*
* *#212 "I would like to highlight certain places on the map": Improve the User Experience
by removing excessive markers from the map and allowing the user choose which markers to 
display.*
* *#213 "Let me plan trips world wide": Add functionality to allow the user to select
pre-defined locations from across the globe and to add and define new locations.*
* *#296 "Make the application easier to use": Improve the User Experience by cleaing up
and restructuring the User Interface.*
* *#329 "Could the planning be a bit faster?": Large trips can take a very long time to
complete their calculations. Can we speed this up? We plan to use concurrency to do so.*

Images from our planning session can be seen [here](./images/PlanningSprint5).


## Metrics

| Statistic | # Planned | # Completed |
| --- | ---: | ---: |
| Epics | *5* | *5* |
| Tasks | *35* | *32* | 
| Story Points | *42* | *39* | 


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| *4/24/2019* | *none* | *#218, #273* | *none* |
| *4/26/2019* | *none* | *#218, #273* | *none* |
| *4/29/2019* | *none* | *#218, #273, #225, #215, #299, #306* | *none* |
| *5/01/2019* | *#218, #225, #215, #217, #299* | *#273, #306, #217* | *none* |
| *5/03/2019* | *none* | *#273, #306, #217* | *none* |
| *5/06/2019* | *none* | *#273, #306, #227, #219, #280* |
| *5/08/2019* | *none* | *#273, #306, #227, #219, #280* | *none* |


## Review (focus on solution and technology)

In this sprint, we greatly improved the user experience in a number of ways. We improved the user
interface by making elements more intuitive and organized. We also managed to greatly improve the
speed of our server so the user has a snappier experience when planning large trips.

#### Completed epics in Sprint Backlog 

These Epics were completed.
* *#211 "I want to view my trip in other tools": The user can now download their trip and/or their
map for use in external tools. We support saving the map as an SVG or as a KML and we support 
saving the itinerary in our native JSON format or as a CSV*
* *#212 "I would like to highlight certain places on the map": This allows for a great dec-luttering
of the map and allows for users to mark important places on their trips. Users can highlight
everything, nothing, or some stuff in between.*
* *#213 "Let me plan trips world wide": We now have access to a database of worldwide locations
that the user can search from. Users can search for places and limit their search to specific
countries and can limit even further by specifying the type of the place.*
* *#296 "Make the application easier to use": We have greatly improved upon our existing user
experience by redesigning our interface. We have reorganized buttons and fields into groups and managed
to make everything less cluttered.*
* *#329 "Could the planning be a bit faster?": Yes it can! We have made changes to our server to
 greatly improve our response times. The main source of improvement comes from our use of 
 multiple system cores and better design.*


#### Incomplete epics in Sprint Backlog 

These Epics were not completed.

* *None that were not planned.*

#### What went well

The changes we made to the server in the last sprint allowed for us to quickly and easily
improve our new changes. Our improved layout planning also made creating and using UI elements
much easier. We managed to stay on top of our code coverage and we also managed to improve our maintainability slightly.


#### Problems encountered and resolutions

We had a few problems using external libraries but these were generally resolved quickly.

## Retrospective (focus on people, process, tools)

This sprint was a tough one for a number of reasons. We were working with our least maintainable
code, in our least familiar area (design), during the toughest weeks of the semester. We had some
struggles but we still did a great job.

In this sprint, we primarily focused on making our user experience better. As such, we planned
less total hours than in previous sprints so that we could really focus on the issues we needed
to do, and to create more tickets when needed. This helped greatly with the stress levels of
the group and helped produce something that allowed for more care to be given.

#### What we changed this sprint

Our changes for this sprint included making more in-person coding time and meeting more ofter.
This greatly improved our output and greatly improved our ability to agree on design choices.

#### What we did well

As usual, we did a great job of communicating using Slack and at using GitHub. We had a great time
when we met at the lab and we did a good job of working around this busy time of the semester.
We also had an excellent planning session that really helped us nail down our UI. Our burndown
chart may be terrible, but it does not reflect the quality of our work. 

#### What we need to work on

We could improve our responsiveness to PR's and the spread of our work time. This sprint was
unusually poor in terms of our burndown report but this can safely be chalked up to external
pressures.

#### What we will change next sprint 

By our next sprint, we will likely no longer be on the same team. We will be out in the world
using our new skills to work in professional development environments where we can be a positive
force in practicing clean code and great work ethic.
