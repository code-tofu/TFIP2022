## Workshop 3 
## https://github.com/kenken64/sdf-b3-workshop3
```
java -cp target/sdfb3workshop3-1.0-SNAPSHOT.jar sg.edu.nus.iss.app.App
```

### Task 1 
Add an option to specify a directory to be used to store user’s shopping cart 
eg. 
 java shoppingcart.jar cartdb 
where cartdb is a directory to be used to store user’s cart. If the program is 
started without specifying the cart database directory, then the program will 
default to use a directory called db. If this directory does not exist, create it. 

* Take in directory from cli argument (App.java)
* Default to db if there isnt any argument specify (App.java)
* If the specify directory does not exist create the directory on the system.


