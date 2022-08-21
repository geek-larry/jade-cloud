package com.jade.system.biz.listener;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.Date;

@Slf4j
@Component
public class AppStartupListener implements ApplicationRunner {

    public static final String APP_START_INFO = "\n" +
            "==============================================================\n" +
            "\tApp:\t{}\n" +
            "\tState:\tapp is running\n" +
            "\tPID:\t{}\n" +
            "\tDate:\tstarted at {}\n" +
            "\tURLs:\thttp://{}:{}{}\n" +
            "==============================================================";
    @Resource
    private ApplicationContext ctx;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String appName = ctx.getEnvironment().getProperty("spring.application.name");
        String appJvmName = ManagementFactory.getRuntimeMXBean().getName();
        String appHost = InetAddress.getLocalHost().getHostAddress();
        String appPort = ctx.getEnvironment().getProperty("server.port");
        String appPath = ctx.getEnvironment().getProperty("server.servlet.context-path");
        String appStartupDate = DateUtil.format(new Date(ctx.getStartupDate()), DatePattern.NORM_DATETIME_MS_PATTERN);
        log.info(APP_START_INFO, appName, appJvmName.split("@")[0], appStartupDate, appHost, appPort,
                (appPath == null ? "" : appPath));
    }
}