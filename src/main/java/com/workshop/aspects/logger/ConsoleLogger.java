package com.workshop.aspects.logger;


import com.workshop.enums.LogType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
class ConsoleLogger implements LoggerRequestInterface {

    private static Logger logger = Logger.getLogger(ConsoleLogger.class);

    @Override
    public void writeRequestInfo(LogType logType, String msg) {
        switch (logType){
            case ERROR:
                logger.error(msg);
                break;
            case INFO:
                logger.info(msg);
                break;
            case WARN:
                logger.warn(msg);
                break;
            default: break;
        }
    }
}
