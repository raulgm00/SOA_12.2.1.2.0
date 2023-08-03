xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarReactivarOperacion_DB";
(:: import schema at "../XSD/ConsultarReactivarOperacion_DB.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarReactivarOperacion_DBOutputCollection as element() (:: schema-element(ns1:ConsultarReactivarOperacion_DBOutputCollection) ::) external;

declare function local:func($ConsultarReactivarOperacion_DBOutputCollection as element() (:: schema-element(ns1:ConsultarReactivarOperacion_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarReactivarOperacionResponse) ::) {
    <ns2:ConsultarReactivarOperacionResponse>
      {
      for $listaProcesosOperacion in $ConsultarReactivarOperacion_DBOutputCollection/ns1:ConsultarReactivarOperacion_DBOutput
      return
        <ns2:ListaProcesosBitacora>
            <ns2:idOperacion>{fn:data($listaProcesosOperacion/ns1:ID_OPERACION)}</ns2:idOperacion>
            <ns2:idProceso>{fn:data($listaProcesosOperacion/ns1:ID_PROCESO)}</ns2:idProceso>
            <ns2:codigoProceso>{fn:data($listaProcesosOperacion/ns1:CODIGO_PROCESO)}</ns2:codigoProceso>
            <ns2:responsableOperacion>{fn:data($listaProcesosOperacion/ns1:RESPONSABLE_OPERACION)}</ns2:responsableOperacion>
        </ns2:ListaProcesosBitacora>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Operación Exitosa</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:ConsultarReactivarOperacionResponse>
};

local:func($ConsultarReactivarOperacion_DBOutputCollection)
