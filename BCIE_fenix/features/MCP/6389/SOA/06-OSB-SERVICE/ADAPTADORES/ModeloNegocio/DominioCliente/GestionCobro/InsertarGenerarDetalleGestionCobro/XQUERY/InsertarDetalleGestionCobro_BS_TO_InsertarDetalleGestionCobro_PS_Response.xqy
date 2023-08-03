xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertarGenerarDetalleGestionCobro";
(:: import schema at "../XSD/InsertarGenerarDetalleGestionCobro_table.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $DetalleLoteCollection as element() (:: element(*, ns1:DetalleLoteCollection) ::) external;

declare function local:func($DetalleLoteCollection as element() (:: element(*, ns1:DetalleLoteCollection) ::)) as element() (:: schema-element(ns2:InsertarGenerarDetalleGestionCobroResponse) ::) {
    <ns2:InsertarGenerarDetalleGestionCobroResponse>
        <ns2:idDetalleLote>{fn:data($DetalleLoteCollection/ns1:DetalleLote/ns1:id)}</ns2:idDetalleLote>
        <ns2:Resultado>
            <res:result>OK</res:result> 
              <res:message>Insercion o actulizacion realizada exitosamente</res:message>
        </ns2:Resultado>
    </ns2:InsertarGenerarDetalleGestionCobroResponse>
};

local:func($DetalleLoteCollection)
