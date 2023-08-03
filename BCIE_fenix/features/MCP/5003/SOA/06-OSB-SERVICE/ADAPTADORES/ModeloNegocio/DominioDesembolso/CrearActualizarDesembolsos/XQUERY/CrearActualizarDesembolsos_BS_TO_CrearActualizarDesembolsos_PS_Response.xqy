xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearActualizarDesembolsos_DB";
(:: import schema at "../XSD/CrearActualizarDesembolsos_DB_table.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ContratoDesembolsoCollection as element() (:: schema-element(ns1:ContratoDesembolsoCollection) ::) external;

declare function local:func($ContratoDesembolsoCollection as element() (:: schema-element(ns1:ContratoDesembolsoCollection) ::)) as element() (:: schema-element(ns2:CrearActualizarDesembolsosResponse) ::) {
    <ns2:CrearActualizarDesembolsosResponse>
    {
    for $contratoDesembolso in $ContratoDesembolsoCollection/ns1:ContratoDesembolso
    return
        <ns2:ContratoDesembolso>
            <des:idDesembolso>{fn:data($contratoDesembolso/ns1:id)}</des:idDesembolso>
        </ns2:ContratoDesembolso>
    }
    <ns2:Resultado>
        <res:result>OK</res:result>
        <res:message>Operación Exitosa</res:message>
    </ns2:Resultado>
    </ns2:CrearActualizarDesembolsosResponse>
};

local:func($ContratoDesembolsoCollection)
