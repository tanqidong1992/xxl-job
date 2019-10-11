package com.hngd.xxl.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hngd.xxl.job.config.XxlJobExecutorConfigProperties;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;

@Configuration
@EnableConfigurationProperties({ XxlJobExecutorConfigProperties.class })
public class XxlJobExecutorAutoConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(XxlJobExecutorAutoConfiguration.class);
	@Autowired
	private XxlJobExecutorConfigProperties config;

	@Bean(initMethod = "start", destroyMethod = "destroy")
	public XxlJobSpringExecutor xxlJobExecutor() {
		logger.info("xxl-job config init.");
		XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
		if (config.getAdmin() != null) {
			xxlJobSpringExecutor.setAdminAddresses(config.getAdmin().getAddresses());
		}
		if (config.getExecutor() != null) {
			XxlJobExecutorConfigProperties.Executor executor = config.getExecutor();
			xxlJobSpringExecutor.setAppName(executor.getAppName());
			xxlJobSpringExecutor.setIp(executor.getIp());
			xxlJobSpringExecutor.setPort(executor.getPort());
			xxlJobSpringExecutor.setAccessToken(config.getAccessToken());
			xxlJobSpringExecutor.setLogPath(executor.getLogPath());
			xxlJobSpringExecutor.setLogRetentionDays(executor.getLogRetentionDays());
		}
		return xxlJobSpringExecutor;
	}
}
