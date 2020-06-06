package com.onecloud.tcmgmt.support.utils;

import com.onecloud.tcmgmt.semantic.constants.ApplicationConstants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    Properties properties;

    public PropertyLoader(Class componentClass){

        String fileName = componentClass.getName()+"."+ ApplicationConstants.CONFIG_FILE_EXTENTION;

        properties = new Properties();
        InputStream stream =
                Thread.currentThread().getContextClassLoader()
                            .getResourceAsStream(fileName);

        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
