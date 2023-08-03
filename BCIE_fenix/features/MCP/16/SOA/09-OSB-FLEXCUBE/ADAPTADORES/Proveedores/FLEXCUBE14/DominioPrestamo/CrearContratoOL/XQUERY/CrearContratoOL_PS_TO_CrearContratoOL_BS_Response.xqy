xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/FLEXCUBE14/PrestamoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/Flexcube14/DominioPrestamo/Prestamo/V1/Schema/PrestamoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/crearContratoOL_DB";
(:: import schema at "../XSD/crearContratoOL_DB_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/FLEXCUBE14/ResultBO";

declare namespace err = "http://www.bcie.org/FLEXCUBE14/ErrorBO";

declare variable $ContratoOLResponse as element() (:: schema-element(ns2:OutputParameters) ::) external;

declare function local:func($ContratoOLResponse as element() (:: schema-element(ns2:OutputParameters) ::)) as element() (:: schema-element(ns1:CreaContratoOLResponse) ::) {
    <ns1:CreaContratoOLResponse>
    {
      if($ContratoOLResponse/ns2:PVCODIGO_CONTRATO/text() != '')
        then
        <ns1:Response>
            <ns1:Codigo_Contrato>{fn:data($ContratoOLResponse/ns2:PVCODIGO_CONTRATO)}</ns1:Codigo_Contrato>
          <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>{fn:concat('La inserción se ha realizado correctamente, Codigo_Contrato:', xs:string($ContratoOLResponse/ns2:PVCODIGO_CONTRATO))}</res:message>
          </ns1:Resultado>
        </ns1:Response>
        else
        <ns1:Response>
          <ns1:Codigo_Contrato/>
          <ns1:Resultado>
            <res:result>ERROR</res:result>
                <res:message>{fn:data($ContratoOLResponse/ns2:PVMENSAJE_SALIDA)}</res:message>
                <res:error>
                    <err:errorCode>{fn:data($ContratoOLResponse/ns2:PVCODIGO_RESULTADO)}</err:errorCode>
                    <err:errorDescription>{fn:data($ContratoOLResponse/ns2:PVMENSAJE_SALIDA)}</err:errorDescription>
                    <err:errorType>{fn:data($ContratoOLResponse/ns2:PVTIPO_RESULTADO)}</err:errorType>
                </res:error>
          </ns1:Resultado>
        </ns1:Response>
      }
    </ns1:CreaContratoOLResponse>
};

local:func($ContratoOLResponse)