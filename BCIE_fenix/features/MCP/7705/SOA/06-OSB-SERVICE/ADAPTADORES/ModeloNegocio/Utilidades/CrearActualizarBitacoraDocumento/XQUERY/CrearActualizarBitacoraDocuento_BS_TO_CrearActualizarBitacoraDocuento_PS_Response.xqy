xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearActualizarBitacoraDocumentoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/CrearActualizarBitacoraDocumento/V1/Schema/CrearActualizarBitacoraDocumentoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearActualizarBitacoraDocumento";
(:: import schema at "../XSD/CrearActualizarBitacoraDocumento_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outCrearActualizar as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($outCrearActualizar as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:responseCrearActualizarBitacoraDocumentoMessage) ::) {
    <ns2:responseCrearActualizarBitacoraDocumentoMessage>
        <ns2:BitacoraDocumentoOutput>
            <id>{fn:data($outCrearActualizar/ns1:ID_BITACORA)}</id>
        </ns2:BitacoraDocumentoOutput>
        <ns2:Result>
            <res:result>{
              if (fn:data($outCrearActualizar/ns1:P_TIPO_RES)=1)
              then 'ERROR'
              else 'OK'     
            }</res:result>
            <res:message>{
              if (fn:data($outCrearActualizar/ns1:P_TIPO_RES)=1)
              then 'Ha ocurrido un error'
              else 'Se inserto o actualizo correctmente'
            }</res:message>
            <res:error>
                <err:errorCode>{fn:data($outCrearActualizar/ns1:P_CODIGO_RES)}</err:errorCode>
                <err:errorDescription>{fn:data($outCrearActualizar/ns1:P_MENSAJE)}</err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Result>
    </ns2:responseCrearActualizarBitacoraDocumentoMessage>
};

local:func($outCrearActualizar)
