xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $CrearReporteCumplimientoCondicionResponse as element() (:: schema-element(ns1:CrearReporteCumplimientoCondicionResponse) ::) external;

declare function local:func($CrearReporteCumplimientoCondicionResponse as element() (:: schema-element(ns1:CrearReporteCumplimientoCondicionResponse) ::)) as element() (:: schema-element(ns1:ConsultarCumplimientoCondicionResponse) ::) {
    <ns1:ConsultarCumplimientoCondicionResponse>
    {
      for $Documento in $CrearReporteCumplimientoCondicionResponse/ns1:Documento
      return
        <ns1:Documento>
            {$Documento/*}
        </ns1:Documento>
    }
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
        </ns1:Resultado>
    </ns1:ConsultarCumplimientoCondicionResponse>
};

local:func($CrearReporteCumplimientoCondicionResponse)
