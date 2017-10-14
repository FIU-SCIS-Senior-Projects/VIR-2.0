
#################################################################
# This script has to be run only once or if for some reason the #
# ec2 instane get's damaged or lost                             #
################################################################


# Read the link information on how to install ImageMagick from source
# https://www.2daygeek.com/install-imagemagick-image-editor-convertor-on-ubuntu-centos-debian-fedora-mint-rhel-opensuse

# Become a super user
sudo su -

# Start installation
cd /opt
wget http://www.imagemagick.org/download/ImageMagick.tar.gz
tar xvzf ImageMagick.tar.gz
cd ImageMagick-7.0.7-7
touch configure
./configure
make
make install
ldconfig /usr/local/lib
/usr/local/bin/convert logo: logo.gif
make check

# Clean up
 rm -fr ImageMagick*.tar.gz