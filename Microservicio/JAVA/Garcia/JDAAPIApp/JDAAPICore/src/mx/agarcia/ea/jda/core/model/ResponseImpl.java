package mx.agarcia.ea.jda.core.model;

/**
 *
 * @author Sol4IT Mexico 2019 - Todos los derechos reservados
 * @category Almacenes Garcia / Ceteris- JDA API
 */
public class ResponseImpl extends IResponse {

    private static final long serialVersionUID = 1L;

    public static final String SUCESS_CODE = "JAPI-0000";
    public static final String FAIL_CODE = "JAPI-0001";


    private Boolean success;
    private String id;
    private String code;
    private String message;

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String toString(){
        return "Response{success:" + success +", code:"+code+", message: " + message + "   }"    ;
    }
    
}
