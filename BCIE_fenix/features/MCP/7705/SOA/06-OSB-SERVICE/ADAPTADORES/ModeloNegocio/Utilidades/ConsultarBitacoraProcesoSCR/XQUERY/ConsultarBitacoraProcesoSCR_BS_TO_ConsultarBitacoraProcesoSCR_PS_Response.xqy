xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CrearBitacoraProcesoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/BitacoraProceso/V1/Schema/BitacoraProcesoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarBitacoraProcesoSCR";
(:: import schema at "../XSD/ConsultarBitacoraProcesoSCR.xsd" ::)

declare namespace cre = "http://www.bcie.org/CrearBitacoraProcesoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $consultarBitacoraProcesoSCRRequest as element() (:: schema-element(ns2:consultarBitacoraProcesoSCRRequest) ::) external;
declare variable $ConsultarBitacoraProcesoSCROutputCollection as element() (:: schema-element(ns1:ConsultarBitacoraProcesoSCROutputCollection) ::) external;

declare function local:func($consultarBitacoraProcesoSCRRequest as element() (:: schema-element(ns2:consultarBitacoraProcesoSCRRequest) ::), 
                            $ConsultarBitacoraProcesoSCROutputCollection as element() (:: schema-element(ns1:ConsultarBitacoraProcesoSCROutputCollection) ::)) 
                            as element() (:: schema-element(ns2:consultarBitacoraProcesoSCRResponse) ::) {
    <ns2:consultarBitacoraProcesoSCRResponse>
    {
    for $listaBitacora in $ConsultarBitacoraProcesoSCROutputCollection/ns1:ConsultarBitacoraProcesoSCROutput
    return
        <ns2:Bitacora>
            <cre:id>{fn:data($listaBitacora/ns1:ID)}</cre:id>
            <cre:idOperacion>{fn:data($consultarBitacoraProcesoSCRRequest/ns2:idOperacion)}</cre:idOperacion>
            <cre:idTarea>{fn:data($listaBitacora/ns1:ID_TAREA)}</cre:idTarea>
            <cre:usuario>{fn:data($listaBitacora/ns1:USUARIO)}</cre:usuario>
            <cre:nombreUsuario>{fn:data($listaBitacora/ns1:NOMBRE_USUARIO)}</cre:nombreUsuario>
            <cre:fechaRegistro>{fn:data($listaBitacora/ns1:FECHA_REGISTRO)}</cre:fechaRegistro>
        </ns2:Bitacora>
    }    
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>
            {
            if (count($ConsultarBitacoraProcesoSCROutputCollection/ns1:ConsultarBitacoraProcesoSCROutput)>0)then
            ("Consulta Exitosa")
            else ("No existen registros")}</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:consultarBitacoraProcesoSCRResponse>
};

local:func($consultarBitacoraProcesoSCRRequest, $ConsultarBitacoraProcesoSCROutputCollection)
