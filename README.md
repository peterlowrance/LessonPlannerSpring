http://localhost:8080/h2-console <--- link to access DB.

create CRUD REST APIs w/
    - Repo
    - Service
    - Controller

TASKS
    1) Make a GET request for all records
    2) Make a GET request for a specific record using SK (You can make more GET request based off other parameters too
        if you want)
    3) Make a PUT request
        (after making this change all the 'list header name' to unique names)
    4) Make a POST request
    5) Make a DELETE request for a specific record based off SK

BONUS
    1) Can only update on records that are not only readable
    2) If 'hidden' is true make it so the record is not displayed to the user //for all gets?
    3) if the SK is already present in the DB, make the POST request return an error that says
        "This SK for List is not unique, please try another SK"
            Replace 'SK' with the SK being passed in. Also, for this task you'd have to remove the 'GeneratedValue'
            annotation since you wouldn't want the DB to generate value but let you insert custom SK

    SUPER BONUS
        Create test cases for the services


Let me know when you finish the Tasks before you move on to Bonus. Always ready to answer questions, so make sure to
ask if you get stuck on something for too long.