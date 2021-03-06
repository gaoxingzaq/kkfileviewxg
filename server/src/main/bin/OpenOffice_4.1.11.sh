#!/bin/bash
cd /tmp

install_redhat() {
    wget https://jaist.dl.sourceforge.net/project/openofficeorg.mirror/4.1.11/binaries/zh-CN/Apache_OpenOffice_4.1.11_Linux_x86-64_install-rpm_zh-CN.tar.gz -cO openoffice_rpm.tar.gz && tar zxf /tmp/openoffice_rpm.tar.gz && cd /tmp/zh-CN/RPMS
   if [ $? -eq 0 ];then
     yum install -y libXext.x86_64
     yum groupinstall -y  "X Window System"
     rpm -Uvih *.rpm
     echo 'install desktop service ...'
     rpm -Uvih desktop-integration/openoffice4.1.11-redhat-menus-4.1.11-9808.noarch.rpm
     echo 'install finshed...'
   else
     echo 'download package error...'
   fi
}

install_ubuntu() {
   wget  https://jaist.dl.sourceforge.net/project/openofficeorg.mirror/4.1.11/binaries/zh-CN/Apache_OpenOffice_4.1.11_Linux_x86-64_install-deb_zh-CN.tar.gz  -cO openoffice_deb.tar.gz && tar zxf /tmp/openoffice_deb.tar.gz && cd /tmp/zh-CN/DEBS
   echo $?
 if [ $? -eq 0 ];then
     dpkg -i *.deb
     echo 'install desktop service ...'
     dpkg -i desktop-integration/openoffice4.1.11-debian-menus_4.1.11-9808_all.deb
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