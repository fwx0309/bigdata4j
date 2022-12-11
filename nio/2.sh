#!/bin/bash
# 0 1 * * * sh /home/omm/uploadDataApp/reportData.sh  >/dev/null 2>&1
source /opt/hadoopclient/bigdata_env
kinit -kt /home/omm/FI_KEY/user.keytab xasjrun
#日期：daytime=20200401
daytime=`date +%Y%m%d`

#前一天日期
shift=$[0+0$1]
beforeday_1=`date -d "$[-1+$shift] days" +%Y%m%d`
#beforeday_1=20220414

echo "当前日期： " $daytime
echo "打包数据日期： "$beforeday_1

# s003 备份数据
s003Path=/resource/backup/${beforeday_1}*/S003
datasetArr=$(hdfs dfs -ls ${s003Path}|awk '{print $8}'|awk -F / '{print $6}'|sort |uniq)

# 需要过滤掉的数据集
# 核酸检测记录			RGA_SOURCE_Z002_7646
# 疫苗接种记录表		RGA_SOURCE_Z002_7647
# 一场两站登记信息		RGA_SOURCE_Z002_7648
# 一码通人员信息表		RGA_SOURCE_Z002_7649
# 一码通人员码色信息	RGA_SOURCE_Z002_7650
# 一码通轨迹数据		RGA_SOURCE_Z002_7651
excludeDataSets="RGA_SOURCE_Z002_7646 RGA_SOURCE_Z002_7647 RGA_SOURCE_Z002_7648 RGA_SOURCE_Z002_7649 RGA_SOURCE_Z002_7650 RGA_SOURCE_Z002_7651"
#excludeDataSets="RGA_SOURCE_Z002_0061 RGA_SOURCE_Z002_0076"
echo "过滤数据集列表: " ${excludeDataSets}

echo "============== 开始处理数据 =============="
for dataset in "${datasetArr[@]}"
do 
	#echo "**dataset: "${dataset}
	excludeDataSet=`echo ${excludeDataSets}|grep ${dataset}`
	if [ ! -z "${excludeDataSet}" ]; then
		echo "不输出该数据集: " ${dataset}
		echo "------------------------ 分隔符 ----------------------------"
		continue
	fi

	hdfs dfs -test -e /resource/backup/${beforeday_1}*/S00