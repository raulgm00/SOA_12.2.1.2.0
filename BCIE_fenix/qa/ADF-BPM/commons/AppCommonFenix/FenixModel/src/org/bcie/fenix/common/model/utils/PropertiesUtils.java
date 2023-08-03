package org.bcie.fenix.common.model.utils;

import java.util.ResourceBundle;

import oracle.javatools.resourcebundle.BundleFactory;

public class PropertiesUtils {
    private static final String BUNDLE = "org.bcie.fenix.common.model.FenixModelBundle";
    
    private static ResourceBundle resourceBundle = BundleFactory.getBundle(BUNDLE);
    
    public PropertiesUtils() {
        super();
    }
    
    public static String getResourceString(String resource) {
        return resourceBundle.getString(resource);
    }
    
}
