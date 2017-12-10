# Up and running

We will get you up an running in no time. 

* Install Tesseract for your system. https://github.com/tesseract-ocr/tesseract/wiki
* Create an environment variable TESSERACT_PATH pointing to your installation directory. Where the executable is.
* Create an environment variable TESSDATA_PREFIX poiting to the directory above the 'tessdata' one; should be in your installation directory but it can be downloaded from the web.

* Clone the repo (you probably already did this part ??)
* Create a local MySQL account with credentials
 ```
username: root
password: root
```
* Create a schema named: _**vir**_
* Go to File/Run SQL Script... and pick the script located at: /vir-2.0/Code/VIR-Backend/src/main/resources/schema.sql 
* Go to File/Run SQL Script... and pick the script located at: /vir-2.0/Code/VIR-Backend/src/main/resources/data.sql 

* Run: 
```
./build.sh
./start
```
* Access the application at [localhost:8080](localhost:8080)

_Note:_
If you are using a Unix like system make sure you give execution permitions to the files.
```chmod +x build.sh```


# Folder structure information

## Tree
```
.
├── Code
│   ├── .mvn
│   ├── VIR-Android
│   ├── VIR-Backend
│   ├── VIR-Frontend
│   ├── VIR-VBA
│   ├── VIR-Scripts
│   │   ├── install_image_magic_ec2.sh
│   │   └── install_tesseract_ec2.sh
│   ├── VIR-iPhone
│   ├── build.cmd
│   ├── build.sh
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   └── start
└── README.md
```

## Description

This is the decription of the files from the tree.

Folder/File | Description
--- | --- 
Code             | Contains all the code for the application.
.mvn             | Folder needed for the maven wrapper. (safely ignore, but not delete)
VIR-Android      | Contains all the code for the Android application.
VIR-Backend      | Contains all the code for the backend of the application.
VIR-Frontend     | Contains all the code for the frontend of the application.
VIR-VBA	         | Contains all the code for a VBA workbook to help gather information from the professor and convert it to sql.
VIR-Scripts      | Contains scripts for the application. (not used frequently)
VIR-iPhone       | Contains all the code for the iPhone application.
build.cmd        | Script to build the application from Windows.
build.sh         | Script to build the application from Unix/Mac.
mvnw             | File needed for the maven wrapper. (safely ignore, but not delete)
mvnw.cmd         | File needed for the maven wrapper. (safely ignore, but not delete)
pom.xml          | Configuration file to build the whole project. Read more about the build pipeline.
start            | Script to start the application
README.md        | This readme file (talking about recursion...)


# Creating AWS Environment From Scratch

### Elastic Beanstalk Dashboard
- If needed create a new Application.
- Create a new environment with all the default configurations and the sample appication.
- Under Config got ahead and create a database with the "username" and "password" (Note the password has to be long, keep in mind that this password will be ecrypted later on)
- Under Config/Software Configuration add a new Property name with the name used in the java EncryptorConfig.java for the name and the value.


### RDS Dashboard

- Go to the database associated with the beanstack above.
- Select it and click Instance Details.
- Click on the security groups: the one that starts with "rds-aws...."
- There go to the "InBound" tab at the bottom and add a new TCP protocol with port 3306 and pic your ip address.
	(This will allow to connect using workbench)
	
### Workbench

- Login with the credentials and the connection string listed on the RDS dashboard.
- Create a new database.
- Populate any data needed.

### Java IDE

- Add properties for the database, encryptor place holder, and port 5000 (needed for production)
- Compile and generate JAR.

### Elastic Beanstalk Dashboard

- Upoad the new version of the application.

### EC2 Dashboard
- If you  do not have a .pem file. Create a key to log in via ssh.
- Click 'Key pairs' to go to the section of the keys. (from here is self explainatiory...)
- Locate the EC2 intance and right click on it to get the ssh connection information.
    (for the key to work it has to be associate with the BeanStalk instance, this should be already done,
    but you can do this in the 'Instance settings' of the Beanstalk)


# Application Building and Pipelines

![Alt text](https://github.com/FIU-SCIS-Senior-Projects/VIR-2.0/blob/develop/Media/DeploymentFlow.png?raw=true "Pipe lines")