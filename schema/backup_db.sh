#!/bin/bash

set -e

export PGPASSWORD='admin_3345678!'
BACKUP_NAME=$(date +%Y%m%d_%H%M%S)
DB=covid19_db
SAVE_PATH=/home/project/backup_db/${DB}

if [[ ! -d ${SAVE_PATH} ]]
then
	mkdir ${SAVE_PATH}
fi

pg_dump -U app_admin -F c -f ${SAVE_PATH}/${BACKUP_NAME} ${DB}
