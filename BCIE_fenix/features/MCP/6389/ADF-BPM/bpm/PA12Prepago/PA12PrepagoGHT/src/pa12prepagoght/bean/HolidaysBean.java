package pa12prepagoght.bean;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import oracle.adf.share.logging.ADFLogger;

import org.apache.myfaces.trinidad.model.DateListProvider;

public class HolidaysBean implements DateListProvider, Serializable {
    
    @SuppressWarnings("compatibility:2306487198913803704")
    private static final long serialVersionUID = 1L;

    private static ADFLogger logger = null;
    List<Date> holiDay = new ArrayList<Date>();

    public HolidaysBean() {
        super();
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    public void setHoliDay(List<Date> holiDay) {
        this.holiDay = holiDay;
    }

    public List<Date> getHoliDay() {
        return holiDay;
    }

    @Override
    public List<Date> getDateList(FacesContext facesContext, Calendar calendar, Date date, Date date2) {
        logger.warning("inside getDateList.");
        
        logger.warning("Numero de fechas inhabiles: " + getHoliDay().size());
        
        return getHoliDay();
    }
}
