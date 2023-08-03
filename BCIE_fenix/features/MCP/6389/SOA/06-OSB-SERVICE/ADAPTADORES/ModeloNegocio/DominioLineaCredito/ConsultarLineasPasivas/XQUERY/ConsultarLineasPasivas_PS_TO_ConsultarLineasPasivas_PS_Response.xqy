xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarLineasPasivasResponse as element() (:: schema-element(ns1:ConsultarLineasPasivasResponse) ::) external;

declare function local:func($ConsultarLineasPasivasResponse as element() (:: schema-element(ns1:ConsultarLineasPasivasResponse) ::)) as element() (:: schema-element(ns1:ConsultarLineasPasivasResponse) ::) {
    <ns1:ConsultarLineasPasivasResponse>
        {
            for $Fuente in $ConsultarLineasPasivasResponse/ns1:Fuente
            return 
            <ns1:Fuente>
                <lin:idFuente>{fn:data($Fuente/lin:idFuente)}</lin:idFuente>
                <lin:idLineaCredito>{fn:data($Fuente/lin:idLineaCredito)}</lin:idLineaCredito>
                <lin:idLineaPasiva>{fn:data($Fuente/lin:idLineaPasiva)}</lin:idLineaPasiva>
                <lin:codigoLineaPasiva>{fn:data($Fuente/lin:codigoLineaPasiva)}</lin:codigoLineaPasiva>
                <lin:idFacturadorLineaPasiva>{fn:data($Fuente/lin:idFacturadorLineaPasiva)}</lin:idFacturadorLineaPasiva>
                <lin:idFondo>{fn:data($Fuente/lin:idFondo)}</lin:idFondo>
                <lin:Descripcion>{fn:data($Fuente/lin:Descripcion)}</lin:Descripcion>
                <lin:FechaObtenido>{fn:data($Fuente/lin:FechaObtenido)}</lin:FechaObtenido>
                <lin:MontoAsignado>{fn:data($Fuente/lin:MontoAsignado)}</lin:MontoAsignado>
                <lin:montoDisponible>{fn:data($Fuente/lin:montoDisponible)}</lin:montoDisponible>
                {
                    for $Monto in $Fuente/lin:Monto
                    return 
                    <lin:Monto>
                        <com:tipo>
                            {
                                if ($Monto/com:tipo/cat:Id)
                                then <cat:Id>{fn:data($Monto/com:tipo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Monto/com:tipo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Monto/com:tipo/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Monto/com:tipo/cat:estatus)
                                then <cat:estatus>{fn:data($Monto/com:tipo/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Monto/com:tipo/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </com:tipo>
                        {
                            if ($Monto/com:importe)
                            then <com:importe>{fn:data($Monto/com:importe)}</com:importe>
                            else ()
                        }
                        {
                            if ($Monto/com:moneda)
                            then 
                                <com:moneda>
                                    {
                                        if ($Monto/com:moneda/cat:Id)
                                        then <cat:Id>{fn:data($Monto/com:moneda/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Monto/com:moneda/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Monto/com:moneda/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Monto/com:moneda/cat:estatus)
                                        then <cat:estatus>{fn:data($Monto/com:moneda/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Monto/com:moneda/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com:moneda>
                            else ()
                        }
                    </lin:Monto>
                }
                <lin:NumeroAsignacion>{fn:data($Fuente/lin:NumeroAsignacion)}</lin:NumeroAsignacion>
                <lin:Comentario>{fn:data($Fuente/lin:Comentario)}</lin:Comentario>
                <lin:idContrato>{fn:data($Fuente/lin:idContrato)}</lin:idContrato>
                <lin:FechaRegistro>{fn:data($Fuente/lin:FechaRegistro)}</lin:FechaRegistro>
                <lin:Estado>{fn:data($Fuente/lin:Estado)}</lin:Estado>
                <lin:esExterna>{fn:data($Fuente/lin:esExterna)}</lin:esExterna>
            </ns1:Fuente>
        }
        <ns1:Resultado>
            <res:result>OK</res:result>
            <res:message>{
            if (count($ConsultarLineasPasivasResponse/ns1:Fuente)>0)then ('Consulta Exitosa')
            else ('No hay registros')}</res:message>
            <res:error>
                <err:errorCode></err:errorCode>
                <err:errorDescription></err:errorDescription>
                <err:errorType></err:errorType>
            </res:error>
        </ns1:Resultado>
    </ns1:ConsultarLineasPasivasResponse>
};

local:func($ConsultarLineasPasivasResponse)
