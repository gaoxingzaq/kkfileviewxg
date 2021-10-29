#!/bin/bash
cd /tmp

install_redhat() {
    wget https://mirrors.cloud.tencent.com/libreoffice/libreoffice/stable/7.2.1/rpm/x86_64/LibreOffice_7.2.1_Linux_x86-64_rpm.tar.gz -cO libreoffice_rpm.tar.gz && tar zxf /tmp/libreoffice_rpm.tar.gz && cd /tmp/LibreOffice_7.2.1.2_Linux_x86-64_rpm/RPMS
   if [ $? -eq 0 ];then
     yum install -y libXext.x86_64
     yum groupinstall -y  "X Window System"
     yum install -y RPMS/*.rpm
	  wget http://www.CTOHome.com/linux-vps-pack/xwindow.sh;sh ./xwindow.sh
     echo 'install finshed...'
   else
     echo 'download package error...'
   fi
}

install_ubuntu() {
   wget https://mirrors.cloud.tencent.com/libreoffice/libreoffice/stable/7.2.1/deb/x86_64/LibreOffice_7.2.1_Linux_x86-64_deb.tar.gz -cO libreoffice_deb.tar.gz && tar zxf /tmp/libreoffice_deb.tar.gz && cd /tmp/LibreOffice_7.2.1.2_Linux_x86-64_deb/DEBS
   echo $?
 if [ $? -eq 0 ];then
     apt-get install -y libxrender1
     apt-get install -y libxt6
     apt-get install -y libxext-dev
     apt-get install -y libfreetype6-dev
     dpkg -i DEBS/*.deb
     echo 'install finshed...'
  else
    echo 'download package error...'
 fi
}


if [ -f "/etc/redhat-release" ]; then
  yum install -y wget
  install_redhat
else
  apt-get install -y wget
  install_ubuntu
fi