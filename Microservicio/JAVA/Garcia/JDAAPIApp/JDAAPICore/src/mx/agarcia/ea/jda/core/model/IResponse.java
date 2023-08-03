package mx.agarcia.ea.jda.core.model;

import java.io.Serializable;

import java.util.HashMap;

/**
 *
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public abstract class IResponse implements Serializable {

    protected HashMap<String, RowData> dataSet = new HashMap<String, RowData>();
    
    
    @SuppressWarnings("compatibility:-391651764901682187")
    private static final long serialVersionUID = 1L;

    public abstract Boolean getSuccess();

    public abstract void setSuccess(Boolean paramBoolean);

    public abstract String getId();

    public abstract void setId(String paramString);

    public abstract String getCode();

    public abstract void setCode(String paramString);

    public abstract String getMessage();

    public abstract void setMessage(String paramString);


    public void setDataSet(HashMap<String, RowData> dataSet) {
        this.dataSet = dataSet;
    }

    public HashMap<String, RowData> getDataSet() {
        return dataSet;
    }
}
