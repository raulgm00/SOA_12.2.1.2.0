xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/UpSertGestionCobro";
(:: import schema at "../XSD/UpSertGestionCobro_table.xsd" ::)

declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $UpSertGestionCobroRequest as element() (:: schema-element(ns1:UpSertGestionCobroRequest) ::) external;

declare function local:func($UpSertGestionCobroRequest as element() (:: schema-element(ns1:UpSertGestionCobroRequest) ::)) as element() (:: schema-element(ns2:LoteGestionCobroCollection) ::) {
    <ns2:LoteGestionCobroCollection>
        <ns2:LoteGestionCobro>
            <ns2:id>{fn:data($UpSertGestionCobroRequest/ns1:DetalleLote/ges:id)}</ns2:id>
            <ns2:idEjecucion>{fn:data($UpSertGestionCobroRequest/ns1:DetalleLote/ges:idLote)}</ns2:idEjecucion>
            <ns2:estadoEjecucion>{fn:data($UpSertGestionCobroRequest/ns1:DetalleLote/ges:Estado)}</ns2:estadoEjecucion>
            {
                if ($UpSertGestionCobroRequest/ns1:DetalleLote/ges:Mensaje_Error/cat:Id)
                  then <ns2:idTcaMensajeError>{fn:data($UpSertGestionCobroRequest/ns1:DetalleLote/ges:Mensaje_Error/cat:Id)}</ns2:idTcaMensajeError>
                else ()
            }
            <ns2:fechaRegistro>{fn:current-date()}</ns2:fechaRegistro>
            <ns2:banEstatus>{fn:data($UpSertGestionCobroRequest/ns1:DetalleLote/ges:Estatus)}</ns2:banEstatus>
            {
                if ($UpSertGestionCobroRequest/ns1:DetalleLote/ges:tipoLote)
                then <ns2:tipoLote>{fn:data($UpSertGestionCobroRequest/ns1:DetalleLote/ges:tipoLote)}</ns2:tipoLote>
                else ()
            }
            {
                if ($UpSertGestionCobroRequest/ns1:DetalleLote/ges:esDetallado)
                then <ns2:esReciboDetallado>{fn:data($UpSertGestionCobroRequest/ns1:DetalleLote/ges:esDetallado)}</ns2:esReciboDetallado>
                else ()
            }
        </ns2:LoteGestionCobro>
    </ns2:LoteGestionCobroCollection>
};

local:func($UpSertGestionCobroRequest)
