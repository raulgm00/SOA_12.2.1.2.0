xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertarGenerarDetalleGestionCobro";
(:: import schema at "../XSD/InsertarGenerarDetalleGestionCobro_table.xsd" ::)

declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $InsertarGenerarDetalleGestionCobroRequest as element() (:: schema-element(ns1:InsertarGenerarDetalleGestionCobroRequest) ::) external;

declare function local:func($InsertarGenerarDetalleGestionCobroRequest as element() (:: schema-element(ns1:InsertarGenerarDetalleGestionCobroRequest) ::)) as element() (:: schema-element(ns2:DetalleLoteCollection) ::) {
    <ns2:DetalleLoteCollection>
        <ns2:DetalleLote>
            <ns2:id>{fn:data($InsertarGenerarDetalleGestionCobroRequest/ns1:DetalleLote/ges:id)}</ns2:id>
            <ns2:idLote>{fn:data($InsertarGenerarDetalleGestionCobroRequest/ns1:DetalleLote/ges:idLote)}</ns2:idLote>
            {
                if ($InsertarGenerarDetalleGestionCobroRequest/ns1:DetalleLote/ges:idCliente)
                then <ns2:idCliente>{fn:data($InsertarGenerarDetalleGestionCobroRequest/ns1:DetalleLote/ges:idCliente)}</ns2:idCliente>
                else ()
            }
            {
                if ($InsertarGenerarDetalleGestionCobroRequest/ns1:DetalleLote/ges:responsableCliente)
                then <ns2:responsable>{fn:data($InsertarGenerarDetalleGestionCobroRequest/ns1:DetalleLote/ges:responsableCliente)}</ns2:responsable>
                else ()
            }
            <ns2:estadoEjecucion>{fn:data($InsertarGenerarDetalleGestionCobroRequest/ns1:DetalleLote/ges:Estado)}</ns2:estadoEjecucion>
            {
                if ($InsertarGenerarDetalleGestionCobroRequest/ns1:DetalleLote/ges:Mensaje_Error/cat:Id)
                then <ns2:idTcaMensajeError>{fn:data($InsertarGenerarDetalleGestionCobroRequest/ns1:DetalleLote/ges:Mensaje_Error/cat:Id)}</ns2:idTcaMensajeError>
                else ()
            }
        </ns2:DetalleLote>
    </ns2:DetalleLoteCollection>
};

local:func($InsertarGenerarDetalleGestionCobroRequest)
