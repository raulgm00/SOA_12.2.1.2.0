xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/ConsultarExcepcionSolicitud";
(:: import schema at "../XSD/ConsultarExcepcionSolicitud_table.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $TreExcepcionSolicitudCollection as element() (:: schema-element(ns1:TreExcepcionSolicitudCollection) ::) external;

declare function local:func($TreExcepcionSolicitudCollection as element() (:: schema-element(ns1:TreExcepcionSolicitudCollection) ::)) as element() (:: schema-element(ns2:ConsultarExcepcionSolicitudResponse) ::) {
    <ns2:ConsultarExcepcionSolicitudResponse>
     {if(fn:count($TreExcepcionSolicitudCollection/ns1:TreExcepcionSolicitud) > 0)then
     for $solicitud in $TreExcepcionSolicitudCollection/ns1:TreExcepcionSolicitud
      return
        <ns2:SolicitudDesembolso>
            <des:idDesembolso>{fn:data($solicitud/ns1:idSolicitud)}</des:idDesembolso>
            <des:Excepcion>
                <reg:Id>{fn:data($solicitud/ns1:id)}</reg:Id>
                <reg:Exceptuado>
                    <cat:estatus>{
                    if (fn:data($solicitud/ns1:exceptuado) = 1) then true() else false()}</cat:estatus>
                </reg:Exceptuado>
                <reg:DetalleRegla>
                    {
                        if ($solicitud/ns1:idTcaRegla)
                        then <atr:id>{fn:data($solicitud/ns1:idTcaRegla)}</atr:id>
                        else ()
                    }
                </reg:DetalleRegla>
                <des:instanciaProceso>{fn:data($solicitud/ns1:instanciaProceso)}</des:instanciaProceso>
                <des:enProcesoExcepcion>{fn:data($solicitud/ns1:enProcesoExcepcion)}</des:enProcesoExcepcion>
            </des:Excepcion>
        </ns2:SolicitudDesembolso>
        else()
       } 
        <ns2:Resultado>
            <res:result>OK</res:result>
            {if(fn:count($TreExcepcionSolicitudCollection/ns1:TreExcepcionSolicitud) > 0)then
              <res:message>Consulta exitosa</res:message>
            else
              <res:message>Consulta sin resultados</res:message>
            }
        </ns2:Resultado>
    </ns2:ConsultarExcepcionSolicitudResponse>
        
};

local:func($TreExcepcionSolicitudCollection)
