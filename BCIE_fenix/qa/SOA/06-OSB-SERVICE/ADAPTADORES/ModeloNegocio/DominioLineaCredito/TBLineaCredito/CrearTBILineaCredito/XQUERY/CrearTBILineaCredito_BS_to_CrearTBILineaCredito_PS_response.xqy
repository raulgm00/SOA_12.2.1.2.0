xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearTBILineaCredito";
(:: import schema at "../XSD/TBILineaCredito.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $TBILineaCreditoResponse as element() (:: schema-element(ns1:TbiLineaCreditoCollection) ::) external;

declare function local:func(
$TBILineaCreditoResponse as element() 
  (:: schema-element(ns1:TbiLineaCreditoCollection) ::)
) as element() (:: element(*, ns2:TBILineaCreditoResponseType) ::) 
{
    <ns2:TBLineaCreditoType>
        {
            for $TbiLineaCredito in $TBILineaCreditoResponse/ns1:TbiLineaCredito
            return 
            <ns2:TbiLineaCredito></ns2:TbiLineaCredito>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>La inserción se ha realizado correctamente</res:message>
        </ns2:Resultado>
    </ns2:TBLineaCreditoType>
};

local:func($TBILineaCreditoResponse)
