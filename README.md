# Up and running

We will get you up an running in no time. 

* Install Tesseract for your system. https://github.com/tesseract-ocr/tesseract/wiki
* Create an environment variable TESSERACT_PATH pointing to your installation directory. Where the executable is.
* Create an environment variable TESSDATA_PREFIX poiting to the directory above the 'tessdata' one; should be in your installation directory but it can be downloaded from the web.
* Install ImageMagick for your system. https://www.imagemagick.org/script/download.php
* Create an environment variable IMAGE_MAGICK_PATH pointing to your installation directory. Where the executable is.


* Clone the repo (you probably already did this part ??)
* Create a local MySQL account with credentials
 ```
username: root
password: root
```
* Create a schema named: _**vir**_
* Run: ```./build.sh``` 
* Run: ```./start```
* Access the application at [localhost:8080](localhost:8080)

_Note:_
If you are using a Unix like system make sure you give execution permitions to the files  
```chmod +x build.sh```



# Installing Tesseract in the AWS EC2 Server.

- Become a super user

```sudo su -```

- Install dependencies

```
yum -y update
yum -y install libstdc++ autoconf automake libtool autoconf-archive pkg-config gcc gcc-c++ make libjpeg-devel libpng-devel libtiff-devel zlib-devel
```

- Change to home

```cd ~```

- Install AutoConf-Archive
```
yum install rpm-build rpmdevtools yum-utils make git
git clone https://github.com/meltwater/autoconf-archive-rpm.git
cd autoconf-archive-rpm
mkdir SOURCES
spectool -g -R -C SOURCES autoconf-archive.spec
yum-builddep SRPMS/autoconf-archive-2014.10.15-1.amzn1.src.rpm
rpmbuild --define "_topdir `pwd`" --rebuild SRPMS/autoconf-archive-2014.10.15-1.amzn1.src.rpm 
```

- Install Leptonica from Source
```
wget  https://github.com/DanBloomberg/leptonica/releases/download/1.74.4/leptonica-1.74.4.tar.gz
tar -zxvf leptonica-1.74.4.tar.gz
cd leptonica-1.74.4
./autobuild
./configure
make
make install
cd ..
```

- Install Tesseract from Source
```
wget https://github.com/tesseract-ocr/tesseract/archive/3.05.01.tar.gz
tar -zxvf tesseract-3.05.01.tar.gz
cd tesseract-3.05.01
./autogen.sh
PKG_CONFIG_PATH=/usr/local/lib/pkgconfig LIBLEPT_HEADERSDIR=/usr/local/include ./configure --with-extra-includes=/usr/local/include --with-extra-libraries=/usr/local/lib
LDFLAGS="-L/usr/local/lib" CFLAGS="-I/usr/local/include"
```

*_ Sit, grab coffee, watch a movie, take a nap and then come back... it might be finished but
 not sure... 
 This part takes some time :) _*
```
make
make install
ldconfig
cd ..
```

- Download and install tesseract language files
 A list of languages can be get here: https://github.com/tesseract-ocr/tesseract/blob/master/doc/tesseract.1.asc#languages
```
wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/eng.traineddata #English
wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/fas.traineddata #Farsi
wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/spa.traineddata #Spanish
wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/osd.traineddata #Non standard language, might be needed, do not leave out

wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/ben.traineddata #Bengali
wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/hin.traineddata #Hindi
wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/tha.traineddata #Thai
wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/ara.traineddata #Arabic
wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/ell.traineddata #Greek Modern
wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/equ.traineddata #Mathematical Equations
wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/heb.traineddata #Hebrew
wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/rus.traineddata #Russian

```
- Move into the data location. Note this location is in OS Environment variable, if changed change that too.
```
mv -f *.traineddata /usr/local/share/tessdata
```

- Download Hindi Cube data
```
wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/hin.cube.bigrams
wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/hin.cube.fold
wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/hin.cube.lm
wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/hin.cube.nn
wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/hin.cube.params
wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/hin.cube.word-freq
wget https://github.com/tesseract-ocr/tessdata/raw/3.04.00/hin.tesseract_cube.nn
```

- Move into the data location. Note this location is in OS Environment variable, if changed change that too.
```
mv -f hin.* /usr/local/share/tessdata
```

- Clean up all the mess
```
rm -rf autoconf-archive-rpm
rm -rf leptonica-*
rm -rf tesseract-*
rm -f *.traineddata
rm -f hin.*
```

