.PHONY : backup all sign

all :

backup :
	@bash ./script/backup.sh

sign :
	@bash ./script/sign.sh
