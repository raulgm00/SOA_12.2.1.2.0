xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CargarDocumentoFenix";
(:: import schema at "../../../../DominioDocumento/CargarDocumentoFenix/XSD/CargarDocumentoFenix_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:NotificarValidacionCondicionResponse) ::) {
    <ns2:NotificarValidacionCondicionResponse>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Notificación Exitosa</res:message>
      
        </ns2:Resultado>
    </ns2:NotificarValidacionCondicionResponse>
};

local:func($OutputParameters)
