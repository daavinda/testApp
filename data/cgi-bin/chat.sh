#!/bin/bash
echo "Content-Type: text/html\n\n"
echo ""

mysql='mysql -uroot -proot -Dquiz'

msisdn=`echo "$QUERY_STRING" | sed -n 's/^.*msisdn=\([^&]*\).*$/\1/p' | sed "s/%20/ /g"`;
action=`echo "$QUERY_STRING" | sed -n 's/^.*action=\([^&]*\).*$/\1/p' | sed "s/%20/ /g"`;
status='0'

if [[ ${action} -eq 0 ]]; then
	status=`${mysql}  <<< "select count(*) from USER_ACCOUNT where msisdn='$msisdn' and status='Active'" | sed -n 2p`;
else if [[ ${action} -eq 1 ]]; then
	status=`${mysql}  <<< "select count(*) from USER_ACCOUNT where msisdn='$msisdn' and status='Active'" | sed -n 2p`;
	`${mysql}  <<< "update USER_ACCOUNT set status='Deleted',DEACTIVATED_ON=NOW() where msisdn='$msisdn' and status='Active'"`;
else if [[ ${action} -eq 2 ]]; then
	status=`${mysql}  <<< "select count(*) from USER_ACCOUNT where msisdn='$msisdn' and status='Deleted'" | sed -n 2p`;
	`${mysql}  <<< "update USER_ACCOUNT set status='Active' where msisdn='$msisdn' and status='Deleted'"`;
fi
fi
fi

echo "`date '+%Y-%m-%d %H:%M:%S'`|MSISDN:$msisdn|Action:$action|Response:$status" >> ./chat.log

echo "$status"