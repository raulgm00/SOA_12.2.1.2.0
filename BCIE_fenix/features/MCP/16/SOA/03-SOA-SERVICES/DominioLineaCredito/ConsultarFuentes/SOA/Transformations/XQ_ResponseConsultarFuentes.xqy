xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $consultarFuenteLineaCredito_OutputVariable.response as element() (:: schema-element(lin:ConsultarLineaCreditoFuenteResponse) ::) external;
declare variable $inputVariable.request as element() (:: schema-element(lin:ConsultarFuentesRequest) ::) external;

declare function local:funcXq_responseconsultarfuentes($consultarFuenteLineaCredito_OutputVariable.response as element() (:: schema-element(lin:ConsultarLineaCreditoFuenteResponse) ::), 
                                                        $inputVariable.request as element() (:: schema-element(lin:ConsultarFuentesRequest) ::)) 
                                                        as element() (:: schema-element(lin:ConsultarFuentesResponse) ::) {
    <lin:ConsultarFuentesResponse>
        <lin:LineaCredito>
            {
                if ($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:idLineaCredito)
                then <lin1:idLineaCredito>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:idLineaCredito)}</lin1:idLineaCredito>
                else ()
            }
            {
                if ($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:idContrato)
                then <lin1:idContrato>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:idContrato)}</lin1:idContrato>
                else ()
            }
            <lin1:NumeroLineaCredito>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:NumeroLineaCredito)}</lin1:NumeroLineaCredito>
            <lin1:Descripcion>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:Descripcion)}</lin1:Descripcion>
            {
                if ($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:Flexcube)
                then 
                    <lin1:Flexcube>
                        {
                            if ($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:Flexcube/lin1:id)
                            then <lin1:id>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:Flexcube/lin1:id)}</lin1:id>
                            else ()
                        }
                        {
                            if ($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:Flexcube/lin1:idProductoFlexcube)
                            then <lin1:idProductoFlexcube>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:Flexcube/lin1:idProductoFlexcube)}</lin1:idProductoFlexcube>
                            else ()
                        }
                        {
                            if ($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:Flexcube/lin1:idFlexcubePasivo)
                            then <lin1:idFlexcubePasivo>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:Flexcube/lin1:idFlexcubePasivo)}</lin1:idFlexcubePasivo>
                            else ()
                        }
                    </lin1:Flexcube>
                else ()
            }
            <lin1:Fondo>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:Fondo)}</lin1:Fondo>
            <lin1:MontoLinea>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:MontoLinea)}</lin1:MontoLinea>
            {
                if ($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:EsRevolvente)
                then <lin1:EsRevolvente>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:EsRevolvente)}</lin1:EsRevolvente>
                else ()
            }
            <lin1:TratamientoDiasFeriados>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:TratamientoDiasFeriados)}</lin1:TratamientoDiasFeriados>
            <lin1:FechaValor>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:FechaValor)}</lin1:FechaValor>
            <lin1:FechaVencimiento>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:FechaVencimiento)}</lin1:FechaVencimiento>
            <lin1:CodigoPantallaLimite>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:CodigoPantallaLimite)}</lin1:CodigoPantallaLimite>
            <lin1:CodigoSerialLimite>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:CodigoSerialLimite)}</lin1:CodigoSerialLimite>
            <lin1:CodigoAsignacion>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:CodigoAsignacion)}</lin1:CodigoAsignacion>
            <lin1:DescripcionAsignacion>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:DescripcionAsignacion)}</lin1:DescripcionAsignacion>
            {
                if ($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:CondicionEspecial)
                then <lin1:CondicionEspecial>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:CondicionEspecial)}</lin1:CondicionEspecial>
                else ()
            }
            <lin1:FechaRegistro>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:FechaRegistro)}</lin1:FechaRegistro>
            <lin1:FechaMaximaDesembolso>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:FechaMaximaDesembolso)}</lin1:FechaMaximaDesembolso>
            {
                if ($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:Estado)
                then <lin1:Estado>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:Estado)}</lin1:Estado>
                else ()
            }
            <lin1:descCondEspecial>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:descCondEspecial)}</lin1:descCondEspecial>
            <lin1:frecuenciaGracia>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:frecuenciaGracia)}</lin1:frecuenciaGracia>
            <lin1:plazoGracia>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:plazoGracia)}</lin1:plazoGracia>
            <lin1:frecuenciaFinanciamiento>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:frecuenciaFinanciamiento)}</lin1:frecuenciaFinanciamiento>
            <lin1:plazoFinanciamiento>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:plazoFinanciamiento)}</lin1:plazoFinanciamiento>
            <lin1:recursosExternos>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:recursosExternos)}</lin1:recursosExternos>
            <lin1:tasaMinima>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:tasaMinima)}</lin1:tasaMinima>
            <lin1:tasaMaxima>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:tasaMaxima)}</lin1:tasaMaxima>
            <lin1:montoMinimo>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:montoMinimo)}</lin1:montoMinimo>
            <lin1:montoMaximo>{fn:data($consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:montoMaximo)}</lin1:montoMaximo>
            {
                for $Monto in $consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:Monto
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
            {
                for $Fuente in $consultarFuenteLineaCredito_OutputVariable.response/lin:lineaCredito/lin1:Fuente
                return 
                if(fn:string($inputVariable.request/lin:LineaCredito/lin1:Fondo) != '')then
                  if (fn:data($Fuente/lin1:idFondo) = $inputVariable.request/lin:LineaCredito/lin1:Fondo) then
                <lin1:Fuente>
                    <lin1:idFuente>{fn:data($Fuente/lin1:idFuente)}</lin1:idFuente>
                    <lin1:idLineaCredito>{fn:data($Fuente/lin1:idLineaCredito)}</lin1:idLineaCredito>
                    <lin1:idLineaPasiva>{fn:data($Fuente/lin1:idLineaPasiva)}</lin1:idLineaPasiva>
                    <lin1:codigoLineaPasiva>{fn:data($Fuente/lin1:codigoLineaPasiva)}</lin1:codigoLineaPasiva>
                    <lin1:idFacturadorLineaPasiva>{fn:data($Fuente/lin1:idFacturadorLineaPasiva)}</lin1:idFacturadorLineaPasiva>
                    <lin1:idFondo>{fn:data($Fuente/lin1:idFondo)}</lin1:idFondo>
                    <lin1:Descripcion>{fn:data($Fuente/lin1:Descripcion)}</lin1:Descripcion>
                    <lin1:FechaObtenido>{fn:data($Fuente/lin1:FechaObtenido)}</lin1:FechaObtenido>
                    <lin1:MontoAsignado>{fn:data($Fuente/lin1:MontoAsignado)}</lin1:MontoAsignado>
                    <lin1:montoDisponible>{fn:data($Fuente/lin1:montoDisponible)}</lin1:montoDisponible>
                    {
                        for $Monto1 in $Fuente/lin1:Monto
                        return 
                        <lin1:Monto>
                            <com:tipo>
                                {
                                    if ($Monto1/com:tipo/cat:Id)
                                    then <cat:Id>{fn:data($Monto1/com:tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Monto1/com:tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Monto1/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Monto1/com:tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Monto1/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Monto1/com:tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($Monto1/com:tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Monto1/com:tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Monto1/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com:tipo>
                            {
                                if ($Monto1/com:importe)
                                then <com:importe>{fn:data($Monto1/com:importe)}</com:importe>
                                else ()
                            }
                            {
                                if ($Monto1/com:moneda)
                                then 
                                    <com:moneda>
                                        {
                                            if ($Monto1/com:moneda/cat:Id)
                                            then <cat:Id>{fn:data($Monto1/com:moneda/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Monto1/com:moneda/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Monto1/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Monto1/com:moneda/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Monto1/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Monto1/com:moneda/cat:estatus)
                                            then <cat:estatus>{fn:data($Monto1/com:moneda/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Monto1/com:moneda/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Monto1/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com:moneda>
                                else ()
                            }
                        </lin1:Monto>
                    }
                    <lin1:NumeroAsignacion>{fn:data($Fuente/lin1:NumeroAsignacion)}</lin1:NumeroAsignacion>
                    <lin1:Comentario>{fn:data($Fuente/lin1:Comentario)}</lin1:Comentario>
                    <lin1:idContrato>{fn:data($Fuente/lin1:idContrato)}</lin1:idContrato>
                    <lin1:FechaRegistro>{fn:data($Fuente/lin1:FechaRegistro)}</lin1:FechaRegistro>
                    <lin1:Estado>{fn:data($Fuente/lin1:Estado)}</lin1:Estado>
                </lin1:Fuente>
                else()
                else(
                    <lin1:Fuente>
                    <lin1:idFuente>{fn:data($Fuente/lin1:idFuente)}</lin1:idFuente>
                    <lin1:idLineaCredito>{fn:data($Fuente/lin1:idLineaCredito)}</lin1:idLineaCredito>
                    <lin1:idLineaPasiva>{fn:data($Fuente/lin1:idLineaPasiva)}</lin1:idLineaPasiva>
                    <lin1:codigoLineaPasiva>{fn:data($Fuente/lin1:codigoLineaPasiva)}</lin1:codigoLineaPasiva>
                    <lin1:idFacturadorLineaPasiva>{fn:data($Fuente/lin1:idFacturadorLineaPasiva)}</lin1:idFacturadorLineaPasiva>
                    <lin1:idFondo>{fn:data($Fuente/lin1:idFondo)}</lin1:idFondo>
                    <lin1:Descripcion>{fn:data($Fuente/lin1:Descripcion)}</lin1:Descripcion>
                    <lin1:FechaObtenido>{fn:data($Fuente/lin1:FechaObtenido)}</lin1:FechaObtenido>
                    <lin1:MontoAsignado>{fn:data($Fuente/lin1:MontoAsignado)}</lin1:MontoAsignado>
                    <lin1:montoDisponible>{fn:data($Fuente/lin1:montoDisponible)}</lin1:montoDisponible>
                    {
                        for $Monto1 in $Fuente/lin1:Monto
                        return 
                        <lin1:Monto>
                            <com:tipo>
                                {
                                    if ($Monto1/com:tipo/cat:Id)
                                    then <cat:Id>{fn:data($Monto1/com:tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Monto1/com:tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Monto1/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Monto1/com:tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Monto1/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Monto1/com:tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($Monto1/com:tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Monto1/com:tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Monto1/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com:tipo>
                            {
                                if ($Monto1/com:importe)
                                then <com:importe>{fn:data($Monto1/com:importe)}</com:importe>
                                else ()
                            }
                            {
                                if ($Monto1/com:moneda)
                                then 
                                    <com:moneda>
                                        {
                                            if ($Monto1/com:moneda/cat:Id)
                                            then <cat:Id>{fn:data($Monto1/com:moneda/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Monto1/com:moneda/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Monto1/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Monto1/com:moneda/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Monto1/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Monto1/com:moneda/cat:estatus)
                                            then <cat:estatus>{fn:data($Monto1/com:moneda/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Monto1/com:moneda/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Monto1/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com:moneda>
                                else ()
                            }
                        </lin1:Monto>
                    }
                    <lin1:NumeroAsignacion>{fn:data($Fuente/lin1:NumeroAsignacion)}</lin1:NumeroAsignacion>
                    <lin1:Comentario>{fn:data($Fuente/lin1:Comentario)}</lin1:Comentario>
                    <lin1:idContrato>{fn:data($Fuente/lin1:idContrato)}</lin1:idContrato>
                    <lin1:FechaRegistro>{fn:data($Fuente/lin1:FechaRegistro)}</lin1:FechaRegistro>
                    <lin1:Estado>{fn:data($Fuente/lin1:Estado)}</lin1:Estado>
                    <lin1:esExterna>{fn:data($Fuente/lin1:esExterna)}</lin1:esExterna>
                </lin1:Fuente>
                )
            }
        </lin:LineaCredito>
        <lin:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Éxitosa</res:message>
        </lin:Resultado>
    </lin:ConsultarFuentesResponse>
};

local:funcXq_responseconsultarfuentes($consultarFuenteLineaCredito_OutputVariable.response, $inputVariable.request)
