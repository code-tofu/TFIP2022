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


## NOTES
1. load args either cartdb or db

2. create new object repository with field file, where file is db specified

3. create a new session with cart, repo, and templist, where report is current repo object

4. start session
- create new anon shopping cart. cart has user name (default anon) and List of contents (Why LinkedList?)
- read LIST, ADD, DELETE, SAVE, END operations,

LIST
- trigger a save (why)?
- call repo load method
- search file directory to match username, else FNF Exception
- create new FIS for cart load method, to load shopping cart db, read all contents into shopping cart List field.
- repo load returns cart to current cart, print cart

ADD - add to current cart and temp items list (what's the point of temp item list)
DEL - delete from current cart. saves cart? add does not save cart?
SAVE - write to file, clear temp items list