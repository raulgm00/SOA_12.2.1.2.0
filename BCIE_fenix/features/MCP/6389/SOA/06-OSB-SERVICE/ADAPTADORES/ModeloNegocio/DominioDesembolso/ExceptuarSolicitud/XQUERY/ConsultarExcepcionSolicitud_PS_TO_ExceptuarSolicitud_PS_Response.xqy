xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarExcepcionSolicitudResponse as element() (:: schema-element(ns1:ConsultarExcepcionSolicitudResponse) ::) external;
declare variable $ExceptuarSolicitudRequest as element() (:: schema-element(ns1:ExceptuarSolicitudRequest) ::) external;

declare function local:func($ConsultarExcepcionSolicitudResponse as element() (:: schema-element(ns1:ConsultarExcepcionSolicitudResponse) ::), 
                            $ExceptuarSolicitudRequest as element() (:: schema-element(ns1:ExceptuarSolicitudRequest) ::)) 
                            as element() (:: schema-element(ns1:ExceptuarSolicitudResponse) ::) {
    <ns1:ExceptuarSolicitudResponse>
    {
    for $solicitud in  $ConsultarExcepcionSolicitudResponse/ns1:SolicitudDesembolso
    where $solicitud/des:Excepcion/des:instanciaProceso = $ExceptuarSolicitudRequest/ns1:instanciaProceso
      return
        <ns1:Excepcion>
            <reg:Id>{fn:data($solicitud/des:Excepcion/reg:Id)}</reg:Id>
            {
                if ($solicitud/des:Excepcion/reg:Exceptuado)
                then 
                    <reg:Exceptuado>
                        {
                            if ($solicitud/des:Excepcion/reg:Exceptuado/cat:Id)
                            then <cat:Id>{fn:data($solicitud/des:Excepcion/reg:Exceptuado/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($solicitud/des:Excepcion/reg:Exceptuado/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($solicitud/des:Excepcion/reg:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($solicitud/des:Excepcion/reg:Exceptuado/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($solicitud/des:Excepcion/reg:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                            <cat:estatus>{true()}</cat:estatus>
                        {
                            if ($solicitud/des:Excepcion/reg:Exceptuado/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($solicitud/des:Excepcion/reg:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </reg:Exceptuado>
                else ()
            }
            {
                for $DetalleRegla in $solicitud/des:Excepcion/reg:DetalleRegla
                return 
                <reg:DetalleRegla>
                    {
                        if ($DetalleRegla/atr:id)
                        then <atr:id>{fn:data($DetalleRegla/atr:id)}</atr:id>
                        else ()
                    }
                    {
                        if ($DetalleRegla/atr:descripcion)
                        then <atr:descripcion>{fn:data($DetalleRegla/atr:descripcion)}</atr:descripcion>
                        else ()
                    }
                    {
                        if ($DetalleRegla/atr:estatus)
                        then <atr:estatus>{fn:data($DetalleRegla/atr:estatus)}</atr:estatus>
                        else ()
                    }
                </reg:DetalleRegla>
            }
            <des:instanciaProceso>{fn:data($solicitud/des:Excepcion/des:instanciaProceso)}</des:instanciaProceso>
            <des:enProcesoExcepcion>{fn:data($solicitud/des:Excepcion/des:enProcesoExcepcion)}</des:enProcesoExcepcion>
        </ns1:Excepcion>
        }
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>Operación Exitosa</res:message>
        </ns1:Resultado>
    </ns1:ExceptuarSolicitudResponse>
};

local:func($ConsultarExcepcionSolicitudResponse, $ExceptuarSolicitudRequest)
