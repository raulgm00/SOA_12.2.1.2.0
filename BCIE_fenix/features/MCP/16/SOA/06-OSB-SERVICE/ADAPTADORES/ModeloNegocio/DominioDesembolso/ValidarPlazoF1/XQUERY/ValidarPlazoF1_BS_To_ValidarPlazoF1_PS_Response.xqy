xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ValidarPlazoF1_DB";
(:: import schema at "../XSD/ValidarPlazoF1_DB_sp.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ValidarLimitesResponse) ::) {
    <ns2:ValidarLimitesResponse>
        <ns2:Regla>
            <reg:Estado>
                <cat:Id></cat:Id>
                <cat:Descripcion></cat:Descripcion>
                {if (fn:data($OutputParameters/ns1:P_CODIGO_RES/text()) = '0' ) then
                  if (contains($OutputParameters/ns1:P_ACUMULADO_MESSAGE/text(),'BHQ'))
                    then
                    <cat:DescripcionCorta>NO CUMPLIDA</cat:DescripcionCorta>
                    else
                    <cat:DescripcionCorta>CUMPLIDA</cat:DescripcionCorta>
                  else
                    <cat:DescripcionCorta>ERROR</cat:DescripcionCorta>
                  }
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </reg:Estado>
        </ns2:Regla>
        <ns2:Resultado>
            <res:result>{if (fn:data($OutputParameters/ns1:P_CODIGO_RES/text()) = '0' ) then 'OK' else 'ERROR'}</res:result>
            <res:message>
            {
              if (fn:data($OutputParameters/ns1:P_CODIGO_RES/text()) = '0') 
              then fn:data($OutputParameters/ns1:P_ACUMULADO_MESSAGE/text())
              else fn:data($OutputParameters/ns1:P_MENSAJE/text())
            }
           </res:message>
            <res:error>
                <err:errorCode>{fn:data($OutputParameters/ns1:P_CODIGO_RES)}</err:errorCode>
                <err:errorDescription>{fn:data($OutputParameters/ns1:P_TIPO_RES)}</err:errorDescription>
                <err:errorType>{fn:data($OutputParameters/ns1:P_MENSAJE)}</err:errorType>
            </res:error>
        </ns2:Resultado>
    </ns2:ValidarLimitesResponse>
};

local:func($OutputParameters)
