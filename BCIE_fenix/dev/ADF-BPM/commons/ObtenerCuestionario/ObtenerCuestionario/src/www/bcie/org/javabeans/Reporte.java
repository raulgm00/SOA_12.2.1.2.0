package www.bcie.org.javabeans;

import java.util.List;

public class Reporte {
    private String operacion;
    private List <PreguntaEvidenciaType> preguntasLst;

     public String getOperacion() {
         return operacion;
     }

     public void setOperacion(String operacion) {
         this.operacion = operacion;
     }

     public List<PreguntaEvidenciaType> getPreguntasLst() {
         return preguntasLst;
     }

     public void setPreguntasLst(List<PreguntaEvidenciaType> preguntasLst) {
         this.preguntasLst = preguntasLst;
     }
}
