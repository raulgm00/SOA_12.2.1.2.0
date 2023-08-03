xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearLineaCredito";
(:: import schema at "../XSD/CrearLineaCredito_table.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $LineaCreditoCollection as element() (:: schema-element(ns1:LineaCreditoCollection) ::) external;

declare function local:func($LineaCreditoCollection as element() (:: schema-element(ns1:LineaCreditoCollection) ::)) as element() (:: schema-element(ns2:CrearLineaCreditoResponse) ::) {
    <ns2:CrearLineaCreditoResponse>
    {
    for $LineaCredito in $LineaCreditoCollection/ns1:LineaCredito
    return
        <ns2:LineaCredito>
            <lin:idLineaCredito>{fn:data($LineaCredito/ns1:id)}</lin:idLineaCredito>
            <lin:idContrato>{fn:data($LineaCredito/ns1:idContrato)}</lin:idContrato>
            <lin:NumeroLineaCredito>{fn:data($LineaCredito/ns1:numeroLineaCredito)}</lin:NumeroLineaCredito>
            <lin:Descripcion>{fn:data($LineaCredito/ns1:descripcionLinea)}</lin:Descripcion>
			{
                if ($LineaCredito/ns1:idTcaTipoMoneda)
                then <lin:IdTipoMonedaMontoLinea>{fn:data($LineaCredito/ns1:idTcaTipoMoneda)}</lin:IdTipoMonedaMontoLinea>
                else ()
            }
            <lin:MontoLinea>{fn:data($LineaCredito/ns1:montoLinea)}</lin:MontoLinea>
        </ns2:LineaCredito>
      }

        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>La inserción se ha realizado correctamente</res:message>
        </ns2:Resultado>
    </ns2:CrearLineaCreditoResponse>
};

local:func($LineaCreditoCollection)