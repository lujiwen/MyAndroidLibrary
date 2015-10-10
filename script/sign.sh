#! /bin/bash

cd `dirname $0`
CURPATH=$PWD
# <change>
PROJECT=Library
PACKAGE=com.seadee.library
CLASS=.HomeActivity
# </change>

signapk()
{
	echo "going to sign apk file ..."
	java -jar script/signapk/signapk.jar script/signapk/platform.x509.pem script/signapk/platform.pk8 bin/$PROJECT.apk bin/$PROJECT-Debug-Signed.apk
	UNIST=`adb uninstall $PACKAGE`
	echo "Uninstall Status is $UNIST"
	if [[ "${UNIST}" == "Fail"* ]]
	then
	    echo "going to rm $PROJECT.apk from android devices"
	    adb shell rm /system/app/$PROJECT.apk
	    adb uninstall $PACKAGE
	fi
	if [ "$1" = "--sys" -o "$1" = "-s" ]
	then
		adb push bin/$PROJECT-Debug-Signed.apk /system/app/$PROJECT.apk
		sleep 4s
	else
		adb install bin/$PROJECT-Debug-Signed.apk
	fi
	adb shell am start -n $PACKAGE/$CLASS
	rm -f bin/$PROJECT.apk
	echo "sign apk all done..."
}

goerror()
{
	echo there is no file named $PROJECT.apk...
	exit 0
}

cd ..

if [ -e bin/$PROJECT.apk ]; then
signapk $1
else
goerror
fi


