package www.bcie.org.javabeans;

public class ResporteAvisoCobro {
    byte[] reporteDocx;
    String tipoDoc;
    byte[] reportePdf;
    String tipoPdf;


    public void setReporteDocx(byte[] reporteDocx) {
        this.reporteDocx = reporteDocx;
    }

    public byte[] getReporteDocx() {
        return reporteDocx;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setReportePdf(byte[] reportePdf) {
        this.reportePdf = reportePdf;
    }

    public byte[] getReportePdf() {
        return reportePdf;
    }

    public void setTipoPdf(String tipoPdf) {
        this.tipoPdf = tipoPdf;
    }

    public String getTipoPdf() {
        return tipoPdf;
    }
}
