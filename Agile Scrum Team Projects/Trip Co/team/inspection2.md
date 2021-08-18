# Inspection #2- Team *T18* 
 
| Inspection | Details |
| ----- | ----- |
| Subject | *`Home.js` All Lines* |
| Meeting | *15 April 2019, 3:00 PM, Stadium 1205* |
| Checklist | *[JustinHillsJohnson's Inspection Sheet](https://gist.github.com/justinhillsjohnson/5503121)* |

### Roles

| Name | Preparation Time |
| ---- | ---- |
| Matteo Vera | 0:55 HRS |
| Aaron Marquez | 0:48 HR |
| David Sant | 0:55 HRS|
| Zachary Klausner | 1.02 HRS |

### Problems found

| file:line | problem | hi/med/low | who found | github#  |
| --- | --- | :---: | :---: | --- |
| Home.js: [233:238]| `Intro` is taking up too much space. | LOW | mfvera | #281 |
| Home.js: [37:59]| Column widths are too narrow on mobile view. | MED | mfvera | #281 |
| Home.js: [68:83]| Items should be compared with a triple equals instead of a double equals. | MED | mfvera | #282 |
| Home.js: [85:164]| Tons of duplicated code. | HIGH | mfvera | #280 |
| Home.js: [85:164]| Popups should have unique keys | MED | mfvera | null |
| Home.js: [196]| Should use triple equals. | MED | mfvera | #282 |
| Home.js: [196:211]| Logic could be simplified using modulo operator. | MED | mfvera | #282 |
| Home.js: [201,209]| Polyline tag should be self-terminated rather than empty. A la: \<Polyline\/\> | LOW | mfvera | #282 |
| Home.js: [228:229]| Variable is declared and immediately returned. | LOW | mfvera | #282 |
| Home.js: [252:253]| Else branch is unused and blank. This should just be removed. | MED | mfvera | #282 |
| Home.js: [37:62] | Columns should make better use of space on both mobile and desktop. | MED | azmarque | #281 |
| Home.js: [68:164]  |  Repetitive code, Can be simplified | HIGH | azmarque | #280 |
| Home.js: [194,273:274] | Unnecessary comment | LOW | azmarque | #282 |
| Home.js: [85:164]  | Console error for not having unique key id's | MED | azmarque | null |
| Home.js: [253:254] | Else condition is left empty remove if nothing will be added | MED | azmarque | #282 |
| Home.js: [226]  | Is console log necessary here? | LOW | azmarque | #282 | 
| Home.js: [201,209]  | Poly tag should be self closing for no body "/>" | MED | azmarque | #282 |
| Home.js: [71,74,196]  | Use triple equals for comparison | MED | azmarque | #282 |
| Home.js: [85:165]| 3(render maps) functions are nearly the same  | HIGH | dsant8 | #280 |
| Home.js: [71,74, 196]| use of == may match incorrect types. Use === (I hate javascript so much) | dsant8 | #282 |
| Home.js: [1:302]| Comments are nearly non-existant  | LOW | dsant8 | null |
| Home.js: [103,126,180,202,210,263,242]| unterminated statement  | LOW  | dsant8 | #282 |
| Home.js: [252]| empty else statement  | LOW | dsant8 | #282 |
| Home.js: [226]| debug print statement left in code  | LOW | dsant8 | #282 |
| Home.js: [267]| newValue could have a better name. (toggledValue?)  | LOW | dsant8 | null |
| Home.js: [N/A]| Map render and scripts may not belong to Home.js (maybe new class?) | LOW | dsant8 | #280 |
| Home.js: [26] | Current location set to arbitrary point | LOW | zachklau | null |
| Home.js: [69] | Unnecessary creation and use of variable | LOW | zachklau | #282 |
| Home.js: [85:185] | Duplicate code could possible be refactored | LOW | zachklau | #280 |
| Home.js: [85: 185] | Maps and  some child components missing id's | MED | zachklau | null |
| Home.js: [97:98] | Marker id and key should be centered under position and icon (same for other maps) | LOW | zachklau | null |
| Home.js: [120] | Id doesn't  hint at component type (same for markers in other maps)| LOW | zachklau | null |
| Home.js: [129:144] | Unnecessary code (renderMapOnePlace) can be used for same purpose | MED | zachklau | #280 |
| Home.js: [201] | Polylines missing unique id's | LOW | zachklau | null |
| Home.js: [188:214] | Could possible be refactored by giving points to positions in Polyline rather than two points at a time | LOW | zachklau | #282 |
| Home.js: [226] | Unnecessary console output | MED | zachklau | #282 |
| Home.js: [233:238] | Intro should contain information about options page and using planner | MED | zachklau | #281 |
| Home.js: [249: 254] | geoLocation() doesn't handle instance where geo location can't be gathered | MED | zachklau | null |
| Home.js: [289:301] | getPoints() is not needed after minor refactoring of renderPlacesAreSame and renderMapOnePlace | LOW | zachklau | #280 |