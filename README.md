# CC_02_Wed_12_Group-4
Group Project Assignment 1 – Tools for Agile Software Development

In order to run and build the the currency converter software application use the command “gradle build” or “gradle run” (remember to copy OG.json file into Exchange.json)
 
The user will be initially prompted whether they are an admin or a regular user. The user can select either option with 1 for admin or 2 users. If admin is selected they will be prompted to input the username (admin123) and password (admin123) in order to verify their authority. 
The program will then infinitely loop while asking for user input. 
Admins are able to select 1 for view conversion, 2 summary of conversions, 3 tables of 4 most popular currencies, 4 edit the exchange, 5 for adding a new currency to the exchange and 6 is to exit. 
Users are able to select 1 for view conversion, 2 summary of conversions, 3 tables of 4 most popular currencies and 4 to exit.
 
To test, simply run “gradle test jacocoTestReport”. Open CC_02_Wed_12_Group-4/app/build/reports/tests/test/index.html to view the JUnit tests and CC_02_Wed_12_Group-4/app/build/reports/jacoco/test/html/index.html to view the jacoco test report on code coverage
 
To test UserTest and AdminTest copy OG.json file into Exchange.json and run test
 
To contribute by uploading codebase to github use the commands
“git add filename”
“git commit -m “message about commit”
“git push”
 
To change between branches use the command “git checkout branch_name”
To check the branch you are currently on use the command “git branch”
In order to merge from one branch to another first checkout to the branch and merge the other branch. Resolve any conflicts and the merge will be successful.
E.g. git checkout branch1
git merge branch 2

