package com.jk.quartz;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 任务调度
 */
@Component
public class MyQuartz {

	Logger log = Logger.getLogger(MyQuartz.class);
	/**
	 * 每隔10秒定时清理缓存
	 */
	@Scheduled(cron = "*/10 * * * * ?")
	public void cacheClear() {
		log.info("缓缓飘落的枫叶像思念……");
	}

}
