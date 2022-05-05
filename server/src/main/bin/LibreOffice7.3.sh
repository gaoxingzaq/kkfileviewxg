#!/bin/bash
cd /tmp
install_redhat() {
    wget https://mirrors.nju.edu.cn/tdf/libreoffice/_testing_/7.3.3/rpm/x86_64/LibreOffice_7.3.3.2_Linux_x86-64_rpm.tar.gz -cO libreoffice_rpm.tar.gz && tar zxf /tmp/libreoffice_rpm.tar.gz && cd /tmp/LibreOffice_7.3.3.2_Linux_x86-64_rpm/RPMS
   echo $?
   if [ $? -eq 0 ];then
     yum install -y libXext.x86_64
     yum groupinstall -y  "X Window System"
     yum installlocalho
     yum localinstall *.rpm
     echo 'install finshed...'
   else
     echo 'download package error...'
   fi
}

install_ubuntu() {
     wget https://mirrors.nju.edu.cn/tdf/libreoffice/_testing_/7.3.3/deb/x86_64/LibreOffice_7.3.3.2_Linux_x86-64_deb.tar.gz -cO libreoffice_deb.tar.gz && tar zxf /tmp/libreoffice_deb.tar.gz && cd /tmp/LibreOffice_7.3.3.2_Linux_x86-64_deb/DEBS
   echo $?
 if [ $? -eq 0 ];then
     apt-get install -y libxinerama1 libcairo2 libcups2 libx11-xcb1
     dpkg -i *.deb
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