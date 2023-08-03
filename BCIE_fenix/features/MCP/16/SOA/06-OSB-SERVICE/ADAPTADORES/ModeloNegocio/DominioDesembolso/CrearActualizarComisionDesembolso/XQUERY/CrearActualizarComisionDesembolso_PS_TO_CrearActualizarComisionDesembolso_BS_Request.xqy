xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearActualizarComisionDesembolso";
(:: import schema at "../XSD/CrearActualizarComisionDesembolso_table.xsd" ::)

declare variable $CrearActualizarComisionDesembolsoRequest as element() (:: schema-element(ns1:CrearActualizarComisionDesembolsoRequest) ::) external;

declare function local:func($CrearActualizarComisionDesembolsoRequest as element() (:: schema-element(ns1:CrearActualizarComisionDesembolsoRequest) ::)) as element() (:: schema-element(ns2:ComisionDesembolsoCollection) ::) {
    <ns2:ComisionDesembolsoCollection>
        <ns2:ComisionDesembolso>
            <ns2:id>{fn:data($CrearActualizarComisionDesembolsoRequest/ns1:idComisionDesembolso)}</ns2:id>
            <ns2:idContratoDesembolso>{fn:data($CrearActualizarComisionDesembolsoRequest/ns1:idContratoDesembolso)}</ns2:idContratoDesembolso>
            <ns2:idComision>{fn:data($CrearActualizarComisionDesembolsoRequest/ns1:idComision)}</ns2:idComision>
            <ns2:codigoBhq>{fn:data($CrearActualizarComisionDesembolsoRequest/ns1:codigoBHQ)}</ns2:codigoBhq>
            {
                if ($CrearActualizarComisionDesembolsoRequest/ns1:porcentaje)
                then <ns2:porcentaje>{fn:data($CrearActualizarComisionDesembolsoRequest/ns1:porcentaje)}</ns2:porcentaje>
                else ()
            }
            <ns2:montoComision>{fn:data($CrearActualizarComisionDesembolsoRequest/ns1:montoComision)}</ns2:montoComision>
            <ns2:fechaRegistro>{if (string($CrearActualizarComisionDesembolsoRequest/ns1:idComisionDesembolso)= '') then fn:current-date() else ()}</ns2:fechaRegistro>
            <ns2:banEstatus>{if (string($CrearActualizarComisionDesembolsoRequest/ns1:idComisionDesembolso)= '') then 1 else if(fn:data($CrearActualizarComisionDesembolsoRequest/ns1:estado = true())) then 1 else 0}</ns2:banEstatus>
        </ns2:ComisionDesembolso>
    </ns2:ComisionDesembolsoCollection>
};

local:func($CrearActualizarComisionDesembolsoRequest)
