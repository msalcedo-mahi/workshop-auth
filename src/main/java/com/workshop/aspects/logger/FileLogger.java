package com.workshop.aspects.logger;


import com.workshop.enums.LogType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileLogger implements LoggerRequestInterface {

    private static final String FILE_NAME="log.txt";


    @Override
    public void writeRequestInfo(LogType logType, String msg) {

        BufferedWriter bufferedWriter = null;
        try {
            String strContent = logType.getType() + " " + msg;
            File file = new File(FILE_NAME);
            // check if file exist, otherwise create the file before writing
            if (!file.exists()) {
                file.createNewFile();
            }
            Writer writer = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(strContent);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(bufferedWriter != null) bufferedWriter.close();
            } catch(Exception ex){

            }
        }
    }

}
