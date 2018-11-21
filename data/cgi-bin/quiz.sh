#!/bin/bash
echo "Content-Type: text/html\n\n"
echo ""

mysql='mysql -uroot -proot -Dquiz'

msisdn=`echo "$QUERY_STRING" | sed -n 's/^.*msisdn=\([^&]*\).*$/\1/p' | sed "s/%20/ /g"`;
action=`echo "$QUERY_STRING" | sed -n 's/^.*action=\([^&]*\).*$/\1/p' | sed "s/%20/ /g"`;
status='0'

if [[ ${action} -eq 0 ]]; then
	status=`${mysql}  <<< "select count(*) from user where msisdn='$msisdn' and status='ACTIVE'" | sed -n 2p`;
else if [[ ${action} -eq 1 ]]; then
	status=`${mysql}  <<< "select count(*) from user where msisdn='$msisdn' and status='ACTIVE'" | sed -n 2p`;
	`${mysql}  <<< "update user set status='DE_ACTIVE',deactivated_on=NOW() where msisdn='$msisdn' and status='ACTIVE'"`;
else if [[ ${action} -eq 2 ]]; then
	status=`${mysql}  <<< "select count(*) from user where msisdn='$msisdn' and status='DE_ACTIVE'" | sed -n 2p`;
	`${mysql}  <<< "update user set status='ACTIVE' where msisdn='$msisdn' and status='DE_ACTIVE'"`;
fi
fi
fi

echo "`date '+%Y-%m-%d %H:%M:%S'`|MSISDN:$msisdn|Action:$action|Response:$status" >> ./quiz.log

echo "$status"