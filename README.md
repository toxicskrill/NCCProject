start-up instruction 
- log in mysql server then run code in the .sql files follow this order:
        1.database.sql
        2.data.sql
        3.Lenh Tao StoredProcedure.sql
- to deploy client, open command line window in folder Project-Management then run these commands by order :(you must have installed nodejs and angular@cli before take this step)
        1. npm install 
        2. ng build
        3. ng serve --open
- change the password and user to access mysql database NCCProjectManager/NCCProjectManager/src/main/resources/application.properties
- run NCCProjectManager/src/main/java/com/example/NccManagerApplication.java to deploy server.
