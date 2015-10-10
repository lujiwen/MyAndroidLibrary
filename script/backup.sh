#!  /bin/bash
cd `dirname $0`
cd ..
# <change>
WINBACKDIR=/cygdrive/g
LINUXBACKDIR=/root/Backup
# </change>
showinfo(){
	echo -e "\033[41;33m [uforgetmenot]: <I> $1 \033[0m"
}

error()
{
    echo -e "\033[47;30m [uforgetmenot]: <E> $1 \033[0m"
    exit 1
}

if [ -n $1 ] && [ -d $1 ] && [ ! "$1" == "" ];then
PROJECT=$1
else
PROJECT=$PWD
fi

showinfo "project is $PROJECT"

OS=`uname | cut -b 1-5 | tr 'A-Z' 'a-z'`
STARTTIME=`date +%s`
FILE=`basename $PROJECT`

if [ $OS = "cygwi" ];then
	showinfo "The system is windows."
	if [ ! -d $WINBACKDIR ];then
		error "The base directory $WINBACKDIR is not exists!"
	fi
	BACKUP=$WINBACKDIR/${FILE}
elif [ $OS = "linux" ];then
  showinfo "The system is linux."
  if [ ! -d $LINUXBACKDIR ];then
		error "The base directory $LINUXIR is not exists!"
	fi
  BACKUP=$LINUXBACKDIR/${FILE}
else
	error "unknown operate system!!!"
fi



showinfo "file is $FILE"
FILENAME=$FILE-`date +%Y%m%d`.tar.gz
showinfo "filename is $FILENAME"

showinfo "Backup Directory is $BACKUP"
showinfo "Project Directory is $PROJECT"

if [ ! -d $BACKUP ];then
    mkdir $BACKUP
fi

i=1
while [ -d "${BACKUP}/${i}" ];
do
   i=$(expr $i + 1)
done

mkdir -p ${BACKUP}/${i}

README=${BACKUP}/${i}/ReadMe.txt
ERROR=${BACKUP}/$i/Error.txt

cd $PROJECT
tar cvzf ${BACKUP}/${i}/$FILENAME ../$FILE 2>$ERROR

if [ $? -ne 0 ];then
	showinfo "*****************************************"
	echo "Backup ${PROJECT} error at `date +%Y/%m/%d-%H:%M:%S`!" >> $ERROR
	cat $ERROR
	showinfo "*****************************************"
	error "Tar cvzf the directory $PROJECT error!"
else
	echo "Times: $i" > $README
	echo "Time: `date +%Y/%m/%d-%H:%M:%S`" >> $README
	echo "Log:" >> $README
	if [ $# -gt 0 ];then
	    echo "Backup from $1" >> $README
	else
	    showinfo " " >> $README
	fi
	ENDTIME=`date +%s`
	showinfo "*****************************************"
	showinfo "Spend Time:`expr $ENDTIME - $STARTTIME`s"
	showinfo "Backup project from $PROJECT to $BACKUP/$i successfully!"
	showinfo "*****************************************"
fi

if [ $OS = "cygwi" ];then
	WINBACKUP=`value=$BACKUP/$i && value=${value:10};value=${value:0:1}:${value:1} && showinfo $value | tr '/' '\'`
	explorer $WINBACKUP
	uex $WINBACKUP\\ReadMe.txt
elif [ $OS = "linux" ];then
  	nautilus $BACKUP/$i
	uex $BACKUP/$i/ReadMe.txt
else
	error "unknown operate system!!!"
fi

