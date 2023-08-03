xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ContratoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Contrato/V1/Schema/ContratoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarContratoBySolicitud";
(:: import schema at "../XSD/ConsultarContratoBySolicitud.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";
declare namespace res = "http://www.bcie.org/ResultBO";

declare variable $responseContrato as element() (:: schema-element(ns1:ConsultarContratoBySolicitudOutputCollection) ::) external;

declare function local:func($responseContrato as element() (:: schema-element(ns1:ConsultarContratoBySolicitudOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarContratoBySolicitudResponse) ::) {
    <ns2:ConsultarContratoBySolicitudResponse>
        {
            for $Contrato in $responseContrato/ns1:ConsultarContratoBySolicitudOutput
            return 
            <ns2:Contrato>
                    <con:idContratoDesembolso>{fn:data($Contrato/ns1:ID_CONTRATO_DESEMBOLSO)}</con:idContratoDesembolso>           
            </ns2:Contrato>
         }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>La consulta se ha realizado exitosamente</res:message>
        </ns2:Resultado>
    </ns2:ConsultarContratoBySolicitudResponse>
};

local:func($responseContrato)