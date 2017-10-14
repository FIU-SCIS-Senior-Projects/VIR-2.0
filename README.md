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
* Run: ```
./build.sh
./start
```
* Access the application at [localhost:8080](localhost:8080)

_Note:_
If you are using a Unix like system make sure you give execution permitions to the files  
```chmod +x build.sh```


# Folder structure
