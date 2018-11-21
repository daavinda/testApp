#!/bin/bash
echo "Content-Type: text/html\n\n"
echo ""

mysql='mysql -uroot -proot -Dcharge_manager'

msisdn=`echo "$QUERY_STRING" | sed -n 's/^.*msisdn=\([^&]*\).*$/\1/p' | sed "s/%20/ /g"`;
action=`echo "$QUERY_STRING" | sed -n 's/^.*action=\([^&]*\).*$/\1/p' | sed "s/%20/ /g"`;
status='0'

if [[ ${action} -eq 0 ]]; then
	status=`${mysql}  <<< "select count(*) from subscriber where msisdn='$msisdn' and status='ACTIVE'" | sed -n 2p`;
else if [[ ${action} -eq 1 ]]; then
	status=`${mysql}  <<< "select count(*) from subscriber where msisdn='$msisdn' and status='ACTIVE'" | sed -n 2p`;
	`${mysql}  <<< "update subscriber set status='DELETED',unregister_date=NOW() where msisdn='$msisdn' and status='ACTIVE'"`;
else if [[ ${action} -eq 2 ]]; then
	status=`${mysql}  <<< "select count(*) from subscriber where msisdn='$msisdn' and status='DELETED'" | sed -n 2p`;
	`${mysql}  <<< "update subscriber set status='ACTIVE' where msisdn='$msisdn' and status='DELETED'"`;
fi
fi
fi

echo "`date '+%Y-%m-%d %H:%M:%S'`|MSISDN:$msisdn|Action:$action|Response:$status" >> ./charge-manager.log

echo "$status"