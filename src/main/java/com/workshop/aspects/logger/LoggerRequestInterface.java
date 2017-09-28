package com.workshop.aspects.logger;

import com.workshop.enums.LogType;

public interface LoggerRequestInterface {

    void writeRequestInfo(LogType logType, String msg);
}
