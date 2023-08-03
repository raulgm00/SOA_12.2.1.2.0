xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)


declare namespace res = "http://www.bcie.org/ResultBO";

declare function local:func() as element() (:: schema-element(ns1:EliminarLineaCreditoByIdContratoResponse) ::) {
    <ns1:EliminarLineaCreditoByIdContratoResponse>
         <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>La eliminación se ha realizado correctamente</res:message>
        </ns1:Resultado>
    </ns1:EliminarLineaCreditoByIdContratoResponse>
};

local:func()
