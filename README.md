# Up and running

We will get you up an running in no time. 

* Install Tesseract for your system. https://github.com/tesseract-ocr/tesseract/wiki
* Create an environment variable TESSERACT_PATH pointing to your installation directory. Where the executable is.
* Create an environment variable TESSDATA_PREFIX poiting to the directory above the 'tessdata' one; should be in your installation directory but it can be downloaded from the web.
* Install ImageMagick on your system. https://www.imagemagick.org/script/download.php
* Create an environment variable IMAGE_MAGICK_PATH pointing to where 'image magic' is installed.

* Clone the repo (you probably already did this part ??)
* Create a local MySQL account with credentials
 ```
username: root
password: root
```
* Create a schema named: _**vir**_
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
Folder/File | Description
--- | --- 
Code             | Contains all the code for the application.
.mvn             | Folder needed for the maven wrapper. (safely ignore, but not delete)
VIR-Android      | Contains all the code for the Android application.
VIR-Backend      | Contains all the code for the backend of the application.
VIR-Frontend     | Contains all the code for the frontend of the application.
VIR-Frontend     | Contains all the code for a VBA workbook to help gather information from the professor and convert it to sql.
VIR-Scripts      | Contains scripts for the application. (not used frequently)
VIR-iPhone       | Contains all the code for the iPhone application.
build.cmd        | Script to build the application from Windows.
build.sh         | Script to build the application from Unix/Mac.
mvnw             | File needed for the maven wrapper. (safely ignore, but not delete)
mvnw.cmd         | File needed for the maven wrapper. (safely ignore, but not delete)
pom.xml          | Configuration file to build the whole project. Read more about the build pipeline.
start            | Script to start the application
README.md        | This readme file (talking about recursion...)
