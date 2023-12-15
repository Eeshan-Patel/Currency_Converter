# CC_02_Wed_12_Group-4: Currency Converter Calculator
### University of Sydney - Group Project for Agile Software Development

#### Overview
This repository contains the currency converter calculator application developed by a team of four students at the University of Sydney. It's a robust tool designed to facilitate currency conversions with additional functionalities for both regular users and administrators.

#### Getting Started
To run and build the currency converter software application, use the command `gradle build` or `gradle run`. Ensure to copy the `OG.json` file into `Exchange.json` for proper functionality.

#### Usage Instructions
- **Initial Prompt**: Upon starting, the application prompts the user to identify as either an 'admin' or a 'regular user'. Choose `1` for admin or `2` for regular user.
- **Admin Access**: If 'admin' is selected, enter the username (`admin123`) and password (`admin123`) for authentication.
- **User Interface**:
  - **Admin Options**:
    1. View conversion rates.
    2. View summary of conversions.
    3. Access tables of the 4 most popular currencies.
    4. Edit exchange rates.
    5. Add a new currency to the exchange.
    6. Exit the application.
  - **Regular User Options**:
    1. View conversion rates.
    2. View summary of conversions.
    3. Access tables of the 4 most popular currencies.
    4. Exit the application.

#### Testing
- To run tests, execute `gradle test jacocoTestReport`.
- For JUnit test reports, open `CC_02_Wed_12_Group-4/app/build/reports/tests/test/index.html`.
- For jacoco test reports on code coverage, view `CC_02_Wed_12_Group-4/app/build/reports/jacoco/test/html/index.html`.
- To test `UserTest` and `AdminTest`, copy `OG.json` into `Exchange.json` and run the test.

#### Contributing
- To upload code to GitHub:
  - Use `git add filename`.
  - Commit changes with `git commit -m “message about commit”`.
  - Push changes using `git push`.
- For branch management:
  - Change branches with `git checkout branch_name`.
  - Check the current branch with `git branch`.
  - To merge branches, checkout to the target branch and use `git merge source_branch`. Resolve conflicts for a successful merge.
  - Example: `git checkout branch1` followed by `git merge branch2`.

#### Note
This project is an educational endeavor by students to develop a practical currency conversion tool while applying agile software development practices.
