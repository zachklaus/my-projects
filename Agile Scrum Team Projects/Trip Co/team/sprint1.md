# Sprint 1 - *18* - *Ctrl Alt Elite*

## Goal

### Distance Calculator!
### Sprint Leader: *Matteo Vera*

## Definition of Done

* Web application deployed on the production server (server).
* Version in server/pom.xml should be `<version>1.0</version>`.
* Product Increment release `v1.0` created on GitHub.
* Sprint Review and Restrospectives completed (team/sprint#.md).

## Policies

* All commits include a task/issue number.
* Someone else approves and merges commits, you may not merge your own changes.
* No commits to master

## Plan

Epics planned for this release.

* *#25 I want to compute the distance between two locations on the planet*
* *#19 I want to know where I am on the map*
* *#20 Use a standard logging system on the server*
* *#21 The application should identify the client and server currently in use*
* *#22 I'd like to know who to thank for this tool*
* *#23 I may need distances in other units of measure*


## Review

#### Completed epics in Sprint Backlog 
* *#25 I want to compute the distance between two locations on the planet*:  We successfully updated the client to
include *kilometers* and *nautical miles* as default unit options. The server now includes methods for calculating
distances using the Haversine Equation and can return this distance to the client.
* *#21 The application should identify the client and server currently in use*:  The Settings page uses
the server config data returned by the server. Our server also returns the proper server name.
* *#22 I'd like to know who to thank for this tool*:  We have an 'About' page that features 
the team, our photos, and our short biographies. The page uses ReactStrap Cards to display the
content for each member.
* *#20 Use a standard logging system on the server*: Our added classes are fitted to utilize SLF4J logging which can be 
seen at the *debug* logging level.

#### Incomplete epics in Sprint Backlog 
* *#23 I may need distances in other units of measure*: We do not have adequate knowledge of React or
JavaScript to create the custom units form or to save the data in a cookie. This will be rectified with
future classes and tutorials
* *#19 I want to know where I am on the map*: We have connected to the Geolocation API but we have not sent
the data to the Leaflet map. At present we do not know how to automatically re-render the map with the user coordinates.
This will be rectified with more learning.

#### What went well
*  We were able to jump in to understanding the codebase with little hesitation.
*  We have also been able to learn elementary React so that we could build our About page
to be responsive on mobile devices.
*  We can easily understand each others code.

#### Problems encountered and resolutions
*  Our code needs more tests. These tests will be added as everyone becomes more comfortable with Unit Testing.
*  Even though we are familiar with the codebase, we still do not know where to look instinctually. This will be
improved with time.
## Retrospective

#### What went well
*  We communicated well and maintained consistent talks on Slack.
*  We were able to resolve disagreements and were able to compromise on solutions.
*  Everyone participated and we had **no hitch-hikers** or any behavior of the sort.
*  We used the TripCo Guides effectively and were able to help each other when we had questions.
*  We reviewed and merged Pull Requests in a very timely fashion.
#### Potential improvements
*  We need to break down our epics into more usable chunks instead of trying to specify exactly what needs to be done
from the start.
*  We need to avoid any further commits directly to master.
*  We need to better identify which issues belong in the icebox.
*  We need to work more during the week and avoid large bursts.
#### What we will change next time
*  In the future we will have hindsight to guide us in the creation of issues and their estimates.
*  We will always check which branch we are on before pushing/committing.
*  With better time estimates we can better understand what things we do not have time for.
*  We will start work earlier in the sprint in order to get a better understanding of what needs to be done. This will 
allow us to work in smaller chunks that can be done throughout the week.