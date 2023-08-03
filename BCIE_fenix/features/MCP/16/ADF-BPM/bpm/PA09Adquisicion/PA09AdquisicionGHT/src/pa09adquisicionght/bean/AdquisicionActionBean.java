package pa09adquisicionght.bean;

import oracle.adf.share.logging.ADFLogger;

import org.bcie.fenix.common.view.beans.FenixActionBean;

public class AdquisicionActionBean extends FenixActionBean{
    
    public static ADFLogger logger = null;

    public AdquisicionActionBean() {
        super();
        if (logger == null){
            logger = ADFLogger.createADFLogger(AdquisicionActionBean.class);
        }
    }
}
