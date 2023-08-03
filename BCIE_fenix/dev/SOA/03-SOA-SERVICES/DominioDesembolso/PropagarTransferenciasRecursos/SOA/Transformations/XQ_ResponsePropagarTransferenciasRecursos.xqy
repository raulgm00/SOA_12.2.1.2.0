xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $outConsultarTransferenciasRecursos.response as element() (:: schema-element(des:ConsultarTransferenciasRecursosByIdResponse) ::) external;

declare function local:funcXq_responsepropagartransferenciasrecursos($outConsultarTransferenciasRecursos.response as element() (:: schema-element(des:ConsultarTransferenciasRecursosByIdResponse) ::)) as element() (:: schema-element(des:PropagarTransferenciaRecursosResponse) ::) {
    <des:PropagarTransferenciaRecursosResponse>
        {
            for $TransferenciaRecursos in $outConsultarTransferenciasRecursos.response/des:ContratoDesembolso/des1:TransferenciaRecursos
            return 
            <des:TransferenciasRecurso>
                <des1:idTransferencia>{fn:data($TransferenciaRecursos/des1:idTransferencia)}</des1:idTransferencia>
                <des1:idDesembolso>{fn:data($TransferenciaRecursos/des1:idDesembolso)}</des1:idDesembolso>
                {
                    if ($TransferenciaRecursos/des1:idFacturador)
                    then <des1:idFacturador>{fn:data($TransferenciaRecursos/des1:idFacturador)}</des1:idFacturador>
                    else ()
                }
                <des1:idLineaPasiva>{fn:data($TransferenciaRecursos/des1:idLineaPasiva)}</des1:idLineaPasiva>
                <des1:Monto>
                    <com:tipo>
                        {
                            if ($TransferenciaRecursos/des1:Monto/com:tipo/cat:Id)
                            then <cat:Id>{fn:data($TransferenciaRecursos/des1:Monto/com:tipo/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des1:Monto/com:tipo/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($TransferenciaRecursos/des1:Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des1:Monto/com:tipo/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($TransferenciaRecursos/des1:Monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des1:Monto/com:tipo/cat:estatus)
                            then <cat:estatus>{fn:data($TransferenciaRecursos/des1:Monto/com:tipo/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des1:Monto/com:tipo/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($TransferenciaRecursos/des1:Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </com:tipo>
                    {
                        if ($TransferenciaRecursos/des1:Monto/com:importe)
                        then <com:importe>{fn:data($TransferenciaRecursos/des1:Monto/com:importe)}</com:importe>
                        else ()
                    }
                    {
                        if ($TransferenciaRecursos/des1:Monto/com:moneda)
                        then 
                            <com:moneda>
                                {
                                    if ($TransferenciaRecursos/des1:Monto/com:moneda/cat:Id)
                                    then <cat:Id>{fn:data($TransferenciaRecursos/des1:Monto/com:moneda/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($TransferenciaRecursos/des1:Monto/com:moneda/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($TransferenciaRecursos/des1:Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($TransferenciaRecursos/des1:Monto/com:moneda/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($TransferenciaRecursos/des1:Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($TransferenciaRecursos/des1:Monto/com:moneda/cat:estatus)
                                    then <cat:estatus>{fn:data($TransferenciaRecursos/des1:Monto/com:moneda/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($TransferenciaRecursos/des1:Monto/com:moneda/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($TransferenciaRecursos/des1:Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com:moneda>
                        else ()
                    }
                </des1:Monto>
                <des1:fecha>{fn:data($TransferenciaRecursos/des1:fecha)}</des1:fecha>
                <des1:fechaEfectiva>{fn:data($TransferenciaRecursos/des1:fechaEfectiva)}</des1:fechaEfectiva>
                <des1:FormaTransferencia>
                    {
                        if ($TransferenciaRecursos/des1:FormaTransferencia/cat:Id)
                        then <cat:Id>{fn:data($TransferenciaRecursos/des1:FormaTransferencia/cat:Id)}</cat:Id>
                        else ()
                    }
                    {
                        if ($TransferenciaRecursos/des1:FormaTransferencia/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($TransferenciaRecursos/des1:FormaTransferencia/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($TransferenciaRecursos/des1:FormaTransferencia/cat:DescripcionCorta)
                        then <cat:DescripcionCorta>{fn:data($TransferenciaRecursos/des1:FormaTransferencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        else ()
                    }
                    {
                        if ($TransferenciaRecursos/des1:FormaTransferencia/cat:estatus)
                        then <cat:estatus>{fn:data($TransferenciaRecursos/des1:FormaTransferencia/cat:estatus)}</cat:estatus>
                        else ()
                    }
                    {
                        if ($TransferenciaRecursos/des1:FormaTransferencia/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($TransferenciaRecursos/des1:FormaTransferencia/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                </des1:FormaTransferencia>
                {
                    if ($TransferenciaRecursos/des1:idBanco)
                    then <des1:idBanco>{fn:data($TransferenciaRecursos/des1:idBanco)}</des1:idBanco>
                    else ()
                }
                {
                    if ($TransferenciaRecursos/des1:nombreBanco)
                    then <des1:nombreBanco>{fn:data($TransferenciaRecursos/des1:nombreBanco)}</des1:nombreBanco>
                    else ()
                }
                {
                    if ($TransferenciaRecursos/des1:numeroCuenta)
                    then <des1:numeroCuenta>{fn:data($TransferenciaRecursos/des1:numeroCuenta)}</des1:numeroCuenta>
                    else ()
                }
                {
                    if ($TransferenciaRecursos/des1:nombreCuenta)
                    then <des1:nombreCuenta>{fn:data($TransferenciaRecursos/des1:nombreCuenta)}</des1:nombreCuenta>
                    else ()
                }
                {
                    if ($TransferenciaRecursos/des1:estatus)
                    then <des1:estatus>{fn:data($TransferenciaRecursos/des1:estatus)}</des1:estatus>
                    else ()
                }
                {
                    if ($TransferenciaRecursos/des1:fechaRegistro)
                    then <des1:fechaRegistro>{fn:data($TransferenciaRecursos/des1:fechaRegistro)}</des1:fechaRegistro>
                    else ()
                }
            </des:TransferenciasRecurso>
        }
        <des:Resultado>
            <res:result>{
            if(count($outConsultarTransferenciasRecursos.response/des:ContratoDesembolso/des1:TransferenciaRecursos[des1:idFacturador=''])>0)then 'ERROR' else 'OK'}</res:result>
            <res:message>{if(count($outConsultarTransferenciasRecursos.response/des:ContratoDesembolso/des1:TransferenciaRecursos[des1:idFacturador=''])>0)then fn:concat("Hay lineas pasivas sin propagar: ",fn:data($outConsultarTransferenciasRecursos.response/des:ContratoDesembolso/des1:TransferenciaRecursos[des1:idFacturador='']/des1:idTransferencia[1])) else 'Propagación exitosa'}</res:message>
        </des:Resultado>
    </des:PropagarTransferenciaRecursosResponse>
};

local:funcXq_responsepropagartransferenciasrecursos($outConsultarTransferenciasRecursos.response)
