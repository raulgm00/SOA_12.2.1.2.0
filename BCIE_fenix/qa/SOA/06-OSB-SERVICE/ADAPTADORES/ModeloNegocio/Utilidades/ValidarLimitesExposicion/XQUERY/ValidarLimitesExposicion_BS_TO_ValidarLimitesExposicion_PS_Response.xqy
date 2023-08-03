xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://org/bcie/ws/middle/LIMITES.wsdl";
(:: import schema at "../../../../Proveedores/LimitesExposicion/XSD/XMLSchema_Limites.xsd" ::)
declare namespace ns2="http://www.bcie.org/ValidarLimitesExposicionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ValidarLimitesExposicion/V1/Schema/ValidarLimitesExposicionMO.xsd" ::)

declare namespace val = "http://www.bcie.org/ValidarLimitesExposicionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ValidarLimitesResponse as element() (:: schema-element(ns1:ValidarLimitesResponse) ::) external;

declare function local:func($ValidarLimitesResponse as element() (:: schema-element(ns1:ValidarLimitesResponse) ::)) as element() (:: schema-element(ns2:responseValidarLimitesExposicionMessage) ::) {
    <ns2:responseValidarLimitesExposicionMessage>
        {
            for $limite in $ValidarLimitesResponse/ns1:listaLimites/ns1:limite
            return 
            <ns2:limite>
                <val:idLimite>{fn:data($limite/ns1:id)}</val:idLimite>
                <val:descripcion>{fn:data($limite/ns1:descripcion)}</val:descripcion>
                <val:valor>{fn:data($limite/ns1:valor)}</val:valor>
                <val:EsPorcentaje>{fn:data($limite/ns1:EsPorcentaje)}</val:EsPorcentaje>
                <val:DiferenciaPorcentaje>{fn:data($limite/ns1:DifPorcentaje)}</val:DiferenciaPorcentaje>
                <val:DiferenciaMonto>{fn:data($limite/ns1:DifMonto)}</val:DiferenciaMonto></ns2:limite>
        }
        <ns2:Result>
            <res:result>OK</res:result>
            <res:message>Validación exitosa</res:message>
        </ns2:Result>
    </ns2:responseValidarLimitesExposicionMessage>
};

local:func($ValidarLimitesResponse)
