# Sheet 7

## Hash method
Implement a program in Python that uses a hash table with 53 entries, in which where the collisions are resolved by chained lists. Do not use any libraries of Python that provide hash functionalities. Realise the following use case:

* You manage student records with the matriculation number, surname and first name. The key field here should be the name.
* To do this, read in a file with 50 data records, which you insert into the hash table. 
Important: These 50 records must all have the same hash value.
* Immediately after reading in, delete the last record inserted and also measure time for it and output it.
* Messen Sie die Zeit, die die Schleife zum Einlesen und Eintragen der 50 Datensätze braucht,
und geben sie aus.
* Provide a menu that allows the user to insert new records, delete records, change surnames and output or save the array of records. or save the array of records.
* Your programme must not crash

Now extend your programme from sheet 4 so that it reads in the same records, deletes the last one and measures the time in each case. 
Compare the times of your two hash programs.
