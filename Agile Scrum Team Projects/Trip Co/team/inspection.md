# Inspection - Team *T18* 
 
| Inspection | Details |
| ----- | ----- |
| Subjects | *GreatCircleDistance.java, TIPConfig.java, TIPFind.java, DatabaseFacade.java* |
| Meeting | *April 8 2019, 3:00 PM, Stadium 1205* |
| Checklist | *[CalPoly Code Review Checklist](http://users.csc.calpoly.edu/~jdalbey/301/Forms/CodeReviewChecklistJava.pdf)* |

### Roles

| Name | Preparation Time |
| ---- | ---- |
| Matteo Vera | 1:15 HR |
| Zachary Klausner | 1:01 HR |
| Aaron Marquez | 1 HR |
| David Sant  | 55 MIN  |


### Problems found

| file:line | problem | hi/med/low | who found | github#  |
| --- | --- | :---: | :---: | --- |
| GreatCircleDistance 42:44 | Logging format is poor (CalPoly #6) | low | mfvera | null |
| TIPFind 21 | Default constructor may cause issues (GSON) | med | mfvera | null |
| TIPFind 19 | Logger is unused but initialized | med | mfvera | #254 |
| DatabaseFacade 46:49 | Hardcoded mySQL statements are poorly laid out | low | mfvera | null |
| DatabaseFacade 54:95 | Search query input is not sanitized | high | mfvera | #228 |
| DatabaseFacade 97:107 | Generic Exceptions are thrown rather than JDBC specific exceptions | med | mfvera | #258 |
| TIPFind 42 | getLimit function is never used  | low | dsant8 | null |
| TIPFind 34:40 | build response not logged | med | dsant8 | #254 |
| TIPFind 42:52 | getters are missing comment headers | low | dsant8 | null |
| TIPFind | Class is missing a descriptive toString() | med | dsant8 | #254 |
| TIPConfig | Class is missing a descriptive toString() | med | dsant8 | #256 |
| TIPConfig 45:50 | getters are missing comment headers | low | dsant8 | null |
| DatabaseFacade | All functions are missing comment headers | med | dsant8 | null |
| DatabaseFacade 46:49 | Declared constants are not all caps | low | dsant8 | #257 |
| DatabaseFacade 61,63| Magic numbers used and no comments explaining them | low | dsant8 | null |
| GreatCircleDistance 24 | calculate function name is not descriptive | low | dsant8 | null |
| GreatCircleDistance 38 | deltaSigma calculation is very complex for one line | med | dsant8| #255 |
| GreatCircleDistance | All functions missing comment headers | low | dsant8 | null |
| GreatCircleDistance 38|deltaSigma assignment is very complex | low | zachklau | #255 |
| GreatCircleDistance 42:46| logging format is hard to read | low | zachklau | null |
| GreatCircleDistance 52:59| toString() is hard to read and never used | low | zachklau | null |
| GreatCircleDistance 61:69| storeData() seems inneficient | low | zachklau | null |
| TIPConfig 32| Request version still set to 3 | high | zachklau | #216 |
| TIPConfig 37:42| buildResponse() not updated for TIP v4 | high | zachklau | #216 |
| TIPFind 17| Attributes not updated for TIP v4 | high | zachklau | #229 |
| TIPFind 19| Logger object never used | low | zachklau | #254 |
| TIPFind 21| Constructor never used | low | zachklau | null |
| TIPFind 42| getLimit() never used | low | zachklau | null |
| DatabaseFacade 1:112| No logging besides error messages | low | zachklau | null |
| DatabaseFacade 54:76| Search string not sanitized | low | zachklau | #228 |
| DatabaseFacade 78:95| Search string not sanitized | low | zachklau | #228 |
| TIPFind 42 | Can't find where method getLimit() is used outside of code, never used in it either | low | azmarque | null |
| GreatCircleDistance 42:46 | Logging format is difficult to read, on multiple lines makes it complex | low | azmarque | null |   
| GreatCircleDistance 38 | One long giant line makes it difficult to read | Low | azmarque | #255 |   
| GreatCircleDistance 52 | ToString() long and complex for return, not sure how we would fix... | Low | azmarque | null |   
| TIPConfig 32 | Should be updated to 4 | low | azmarque | #216 |   

