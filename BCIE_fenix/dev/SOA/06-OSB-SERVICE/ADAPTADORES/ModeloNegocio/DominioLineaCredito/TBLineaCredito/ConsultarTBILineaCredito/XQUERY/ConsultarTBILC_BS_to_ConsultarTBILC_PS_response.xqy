xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTBLineaCredito";
(:: import schema at "../XSD/TBILineaCredito.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $response as element()* (:: schema-element(ns1:ConsultarTBLineaCreditoResponse)* ::) external;

declare function local:func($response as element()* (:: schema-element(ns1:ConsultarTBLineaCreditoResponse)* ::)) as element() (:: element(*, ns2:TBILineaCreditoResponseType) ::) {
    <ns2:TBILineaCreditoResponseType>
        <ns2:TbiLineaCredito>
            {
                for $ConsultarTBLineaCreditoResponse in $response/ns1:ConsultarTBLineaCreditoResponse
                return 
                <lin:TbiLineaCredito> 
                    <lin:id>{fn:data($ConsultarTBLineaCreditoResponse/ns1:ID)}</lin:id>
                    <lin:idContrato>{fn:data($ConsultarTBLineaCreditoResponse/ns1:ID_CONTRATO)}</lin:idContrato>
                    <lin:idLineaCredito>{fn:data($ConsultarTBLineaCreditoResponse/ns1:ID_LINEA_CREDITO)}</lin:idLineaCredito>
                    <lin:idTareaBpm>{fn:data($ConsultarTBLineaCreditoResponse/ns1:ID_TAREA_BPM)}</lin:idTareaBpm>
                    <lin:instanciaProceso>{fn:data($ConsultarTBLineaCreditoResponse/ns1:INSTANCIA_PROCESO)}</lin:instanciaProceso>
                    <lin:estadoLineaCredito>{fn:data($ConsultarTBLineaCreditoResponse/ns1:ESTADO_LINEA_CREDITO)}</lin:estadoLineaCredito>
                    <lin:usuario>{fn:data($ConsultarTBLineaCreditoResponse/ns1:USUARIO)}</lin:usuario>
                    <lin:fechaRegistro>{fn:data($ConsultarTBLineaCreditoResponse/ns1:FECHA_REGISTRO)}</lin:fechaRegistro>
                    <lin:banEstatus>{fn:data($ConsultarTBLineaCreditoResponse/ns1:BAN_ESTATUS)}</lin:banEstatus>
                    <lin:numeroLineaCredito>{fn:data($ConsultarTBLineaCreditoResponse/ns1:NUMERO_LINEA_CREDITO)}</lin:numeroLineaCredito>
              </lin:TbiLineaCredito>
            } 
        </ns2:TbiLineaCredito>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Correcta</res:message>
        </ns2:Resultado>
    </ns2:TBILineaCreditoResponseType>
};

local:func($response)
