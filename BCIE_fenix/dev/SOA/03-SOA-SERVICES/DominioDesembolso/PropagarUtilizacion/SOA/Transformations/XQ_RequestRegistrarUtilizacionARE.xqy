xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd"::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace con1 = "http://www.bcie.org/ContratoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::) external;
declare variable $outConsultarLineaCredito.response as element() (:: schema-element(lin:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) external;
declare variable $outCrearActualizarDetalleTransaccion.CrearActualizarDetalleTransaccionSADResponse as element() (:: schema-element(des:CrearActualizarDetalleTransaccionResponse) ::) external;

declare function local:funcXq_requestregistrarutilizacionare($outConsultarDesembolsoById.response as element() (:: schema-element(des:ConsultarDesembolsosByIdResponse) ::), 
                                                             $outConsultarLineaCredito.response as element() (:: schema-element(lin:ConsultarLineaCreditoByIdLineaCreditoResponse) ::), 
                                                             $outCrearActualizarDetalleTransaccion.CrearActualizarDetalleTransaccionSADResponse as element() (:: schema-element(des:CrearActualizarDetalleTransaccionResponse) ::)) 
                                                             as element() (:: schema-element(lin:RegistrarUtilizacionARERequest) ::) {
    <lin:RegistrarUtilizacionARERequest>
        <lin:LineaCredito>
            <lin1:FechaValor>{fn:data($outConsultarLineaCredito.response/lin:Operacion/ope:contrato/con1:LineaCredito/lin1:FechaValor)}</lin1:FechaValor>
            <lin1:CodigoAsignacion>{fn:data($outConsultarLineaCredito.response/lin:Operacion/ope:contrato/con1:LineaCredito/lin1:CodigoAsignacion)}</lin1:CodigoAsignacion>
            <lin1:ContratoDesembolso>
                {
                for $utilizacion in $outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:Utilizacion
                return
                <des1:Utilizacion>
                    <lin1:idFuente>{fn:data($utilizacion/lin1:idFuente)}</lin1:idFuente>
                    <lin1:idLineaCredito>{fn:data($utilizacion/lin1:idLineaCredito)}</lin1:idLineaCredito>
                    <lin1:idLineaPasiva>{fn:data($utilizacion/lin1:idLineaPasiva)}</lin1:idLineaPasiva>
                    <lin1:codigoLineaPasiva>{fn:data($utilizacion/lin1:codigoLineaPasiva)}</lin1:codigoLineaPasiva>
                    <lin1:Descripcion>{fn:data($utilizacion/lin1:Descripcion)}</lin1:Descripcion>
                    <lin1:FechaObtenido>{fn:data($utilizacion/lin1:FechaObtenido)}</lin1:FechaObtenido>
                    <lin1:MontoAsignado>{fn:data($utilizacion/lin1:MontoAsignado)}</lin1:MontoAsignado>
                    <lin1:montoDisponible>{fn:data($utilizacion/lin1:montoDisponible)}</lin1:montoDisponible>
                    {
                        for $Monto in $utilizacion/lin1:Monto
                        return 
                        <lin1:Monto>
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
                        </lin1:Monto>
                    }
                    <lin1:NumeroAsignacion>{fn:data($utilizacion/lin1:NumeroAsignacion)}</lin1:NumeroAsignacion>
                    <lin1:Comentario>{fn:data($utilizacion/lin1:Comentario)}</lin1:Comentario>
                    <lin1:idContrato>{fn:data($utilizacion/lin1:idContrato)}</lin1:idContrato>
                    <lin1:FechaRegistro>{fn:data($utilizacion/lin1:FechaRegistro)}</lin1:FechaRegistro>
                    <lin1:Estado>{fn:data($utilizacion/lin1:Estado)}</lin1:Estado>
                </des1:Utilizacion>
                }
            </lin1:ContratoDesembolso>
            <lin1:atributos>
                <com:name>REFERENCIA</com:name>
                <com:value>{fn:concat(fn:data(string($outConsultarDesembolsoById.response/des:ContratoDesembolso/des1:idDesembolso)),"/",fn:data($outCrearActualizarDetalleTransaccion.CrearActualizarDetalleTransaccionSADResponse/des:TransaccionDesembolso/des1:id))}</com:value>
            </lin1:atributos>
        </lin:LineaCredito>
    </lin:RegistrarUtilizacionARERequest>
};

local:funcXq_requestregistrarutilizacionare($outConsultarDesembolsoById.response, $outConsultarLineaCredito.response, $outCrearActualizarDetalleTransaccion.CrearActualizarDetalleTransaccionSADResponse)
