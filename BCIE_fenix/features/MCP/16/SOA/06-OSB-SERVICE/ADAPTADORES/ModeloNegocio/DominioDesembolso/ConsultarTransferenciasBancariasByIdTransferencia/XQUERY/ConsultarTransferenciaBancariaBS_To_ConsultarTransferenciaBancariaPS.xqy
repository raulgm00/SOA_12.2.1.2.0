xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarTransferenciaBancariaDB";
(:: import schema at "../XSD/ConsultarTransferenciaBancariaDB_sp.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare variable $ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns2:ConsultarDesembolsosByIdResponse) ::) external;
declare variable $ConcultarTransferenciaBancariaOutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($ConsultarDesembolsosByIdResponse as element() (:: schema-element(ns2:ConsultarDesembolsosByIdResponse) ::), 
$ConcultarTransferenciaBancariaOutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ConsultarTransferenciasBancariasByIdResponse) ::) {
     <ns2:ConsultarTransferenciasBancariasByIdResponse>
    {
    for $IdDesembolsos in distinct-values($ConcultarTransferenciaBancariaOutputParameters/ns1:RS_TRANSFERENCIAS/ns1:Row/ns1:Column[@name='ID_CONTRATO_DESEMBOLSO'])
    for $Row in $ConcultarTransferenciaBancariaOutputParameters/ns1:RS_TRANSFERENCIAS/ns1:Row[ns1:Column[@name='ID_CONTRATO_DESEMBOLSO'] = $IdDesembolsos][1]
      return 
        <ns2:ContratoDesembolso>
           {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:idDesembolso/text() = $Row/ns1:Column[@name='ID_CONTRATO_DESEMBOLSO'])
                then <des:idDesembolso>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:idDesembolso)}</des:idDesembolso>
                else 
                <des:idDesembolso>{fn:data($Row/ns1:Column[@name='ID_CONTRATO_DESEMBOLSO'])}</des:idDesembolso>
            }
            {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:idFacturador)
                then <des:idFacturador>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:idFacturador)}</des:idFacturador>
                else ()
            }
            {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:producto)
                then 
                    <des:producto>
                        <pro:idProducto>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:producto/pro:idProducto)}</pro:idProducto>
                        <pro:descripcion>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:producto/pro:descripcion)}</pro:descripcion>
                        <pro:descripcionCorta>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:producto/pro:descripcionCorta)}</pro:descripcionCorta>
                        <pro:fechaRegistro>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:producto/pro:fechaRegistro)}</pro:fechaRegistro>
                        <pro:codExterno>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:producto/pro:codExterno)}</pro:codExterno>
                    </des:producto>
                else ()
            }
            {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:referencia)
                then <des:referencia>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:referencia)}</des:referencia>
                else ()
            }
            {
                for $monto in $ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:monto
                return 
                <des:monto>
                    <com:tipo>
                        {
                            if ($monto/com:tipo/cat:Id)
                            then <cat:Id>{fn:data($monto/com:tipo/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($monto/com:tipo/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($monto/com:tipo/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($monto/com:tipo/cat:estatus)
                            then <cat:estatus>{fn:data($monto/com:tipo/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($monto/com:tipo/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </com:tipo>
                    {
                        if ($monto/com:importe)
                        then <com:importe>{fn:data($monto/com:importe)}</com:importe>
                        else ()
                    }
                    {
                        if ($monto/com:moneda)
                        then 
                            <com:moneda>
                                {
                                    if ($monto/com:moneda/cat:Id)
                                    then <cat:Id>{fn:data($monto/com:moneda/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($monto/com:moneda/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($monto/com:moneda/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($monto/com:moneda/cat:estatus)
                                    then <cat:estatus>{fn:data($monto/com:moneda/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($monto/com:moneda/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com:moneda>
                        else ()
                    }
                </des:monto>
            }
            <des:estado>
                {
                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:estado/cat:Id)
                    then <cat:Id>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:estado/cat:Id)}</cat:Id>
                    else ()
                }
                {
                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:estado/cat:Descripcion)
                    then <cat:Descripcion>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:estado/cat:Descripcion)}</cat:Descripcion>
                    else ()
                }
                {
                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:estado/cat:DescripcionCorta)
                    then <cat:DescripcionCorta>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                    else ()
                }
                {
                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:estado/cat:estatus)
                    then <cat:estatus>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:estado/cat:estatus)}</cat:estatus>
                    else ()
                }
                {
                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:estado/cat:codigoExterno)
                    then <cat:codigoExterno>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:estado/cat:codigoExterno)}</cat:codigoExterno>
                    else ()
                }
            </des:estado>
            {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:programado)
                then <des:programado>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:programado)}</des:programado>
                else ()
            }
            {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:fechaEstimadaDesembolsar)
                then <des:fechaEstimadaDesembolsar>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:fechaEstimadaDesembolsar)}</des:fechaEstimadaDesembolsar>
                else ()
            }
            {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:fechaEfectiva)
                then <des:fechaEfectiva>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:fechaEfectiva)}</des:fechaEfectiva>
                else ()
            }
            {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:fechaAsignacion)
                then <des:fechaAsignacion>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:fechaAsignacion)}</des:fechaAsignacion>
                else ()
            }
            {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:fechaRegistro)
                then <des:fechaRegistro>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:fechaRegistro)}</des:fechaRegistro>
                else ()
            }
            {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:fechaVencimiento)
                then <des:fechaVencimiento>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:fechaVencimiento)}</des:fechaVencimiento>
                else ()
            }
            {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:estatus)
                then <des:estatus>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:estatus)}</des:estatus>
                else ()
            }
            <des:condicionesFinancieras>
                <des:fondo>
                    {
                        if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Id)
                        then <cat:Id>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Id)}</cat:Id>
                        else ()
                    }
                    {
                        if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:DescripcionCorta)
                        then <cat:DescripcionCorta>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        else ()
                    }
                    {
                        if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:estatus)
                        then <cat:estatus>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:estatus)}</cat:estatus>
                        else ()
                    }
                    {
                        if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                    {
                        for $Validador in $ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:condicionesFinancieras/des:fondo/des:Validador
                        return 
                        <des:Validador>
                            {
                                if ($Validador/cat:Id)
                                then <cat:Id>{fn:data($Validador/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Validador/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Validador/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Validador/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Validador/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Validador/cat:estatus)
                                then <cat:estatus>{fn:data($Validador/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Validador/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Validador/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </des:Validador>
                    }
                </des:fondo>
                <des:baseCalculo>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </des:baseCalculo>
            </des:condicionesFinancieras>
            {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:idLinea)
                then <des:idLinea>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:idLinea)}</des:idLinea>
                else ()
            }
            {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:recursosExternos)
                then <des:recursosExternos>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:recursosExternos)}</des:recursosExternos>
                else ()
            }
            {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:cuentaCliente)
                then <des:cuentaCliente>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:cuentaCliente)}</des:cuentaCliente>
                else ()
            }
            {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:usuario)
                then <des:usuario>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:usuario)}</des:usuario>
                else ()
            }
            {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:fechaDisponibilidadFondos)
                then <des:fechaDisponibilidadFondos>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:fechaDisponibilidadFondos)}</des:fechaDisponibilidadFondos>
                else ()
            }
            {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:origenTransferenciaCte)
                then <des:origenTransferenciaCte>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:origenTransferenciaCte)}</des:origenTransferenciaCte>
                else ()
            }
            {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa)
                then 
                    <des:Programa>
                        <her:LineaFinanciera>
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:Id)
                                then <cat:Id>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:LineaFinanciera>
                        <her:DestinoFinanciamiento>
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:Id)
                                then <cat:Id>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:DestinoFinanciamiento>
                        <her:ModalidadFinanciamiento>
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:Id)
                                then <cat:Id>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:ModalidadFinanciamiento>
                        <her:HerramientaCE>
                            <her:ActividadEconomica>
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Id)
                                    then <cat:Id>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:estatus)
                                    then <cat:estatus>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:ActividadEconomica>
                            <her:EjeEstrategico>
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Id)
                                    then <cat:Id>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:estatus)
                                    then <cat:estatus>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:EjeEstrategico>
                            <her:AreaFocalizacion>
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Id)
                                    then <cat:Id>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:estatus)
                                    then <cat:estatus>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:AreaFocalizacion>
                        </her:HerramientaCE>
                        <her:ClasificacionGeneral>
                            <her:SectorMercado>
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Id)
                                    then <cat:Id>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:estatus)
                                    then <cat:estatus>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:SectorMercado>
                            <her:SectorInstitucional>
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Id)
                                    then <cat:Id>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:estatus)
                                    then <cat:estatus>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:SectorInstitucional>
                        </her:ClasificacionGeneral>
                    </des:Programa>
                else ()
            }
            {
                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:EstimadoDesembolso)
                then 
                    <des:EstimadoDesembolso>
                        <des:Plazo>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:EstimadoDesembolso/des:Plazo)}</des:Plazo>
                        <des:Frecuencia>
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:Id)
                                then <cat:Id>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </des:Frecuencia>
                    </des:EstimadoDesembolso>
                else ()
            }
            {
               for $utilizacion in $ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Utilizacion
                return 
                    <des:Utilizacion>
                        <lin:idFuente>{fn:data($utilizacion/lin:idFuente)}</lin:idFuente>
                        <lin:idLineaCredito>{fn:data($utilizacion/lin:idLineaCredito)}</lin:idLineaCredito>
                        <lin:idLineaPasiva>{fn:data($utilizacion/lin:idLineaPasiva)}</lin:idLineaPasiva>
                        <lin:codigoLineaPasiva>{fn:data($utilizacion/lin:codigoLineaPasiva)}</lin:codigoLineaPasiva>
                        <lin:idFacturadorLineaPasiva>{fn:data($utilizacion/lin:idFacturadorLineaPasiva)}</lin:idFacturadorLineaPasiva>
                        <lin:idFondo>{fn:data($utilizacion/lin:idFondo)}</lin:idFondo>
                        <lin:Descripcion>{fn:data($utilizacion/lin:Descripcion)}</lin:Descripcion>
                        <lin:FechaObtenido>{fn:data($utilizacion/lin:FechaObtenido)}</lin:FechaObtenido>
                        <lin:MontoAsignado>{fn:data($utilizacion/lin:MontoAsignado)}</lin:MontoAsignado>
                        <lin:montoDisponible>{fn:data($utilizacion/lin:montoDisponible)}</lin:montoDisponible>
                        {
                            for $Monto in $utilizacion/lin:Monto
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
                        <lin:NumeroAsignacion>{fn:data($utilizacion/lin:NumeroAsignacion)}</lin:NumeroAsignacion>
                        <lin:Comentario>{fn:data($utilizacion/lin:Comentario)}</lin:Comentario>
                        <lin:idContrato>{fn:data($utilizacion/lin:idContrato)}</lin:idContrato>
                        <lin:FechaRegistro>{fn:data($utilizacion/lin:FechaRegistro)}</lin:FechaRegistro>
                        <lin:Estado>{fn:data($utilizacion/lin:Estado)}</lin:Estado>
                    </des:Utilizacion>
            }
                <des:Cargo>{$ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Cargo/*}</des:Cargo>

            {
                <des:Comision>{$ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:Comision/*}</des:Comision>
            }
            {
           for $RowTransferencias in $ConcultarTransferenciaBancariaOutputParameters/ns1:RS_TRANSFERENCIAS/ns1:Row[ns1:Column[@name='ID_CONTRATO_DESEMBOLSO'] = $IdDesembolsos]
            return
                <des:Transferencia>
                <des:idTransferencia>{fn:data($RowTransferencias/ns1:Column[@name='ID_TRANFERENCIA_BAN'])}</des:idTransferencia>
                <des:idFacturador>{fn:data($RowTransferencias/ns1:Column[@name='BHQ_TRANSFERENCIA'])}</des:idFacturador>
                <des:Monto>
                    <com:tipo>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta>TRANSFERENCIA_BANCARIA</cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </com:tipo>
                    <com:importe>{fn:data($RowTransferencias/ns1:Column[@name='MONTO_TRANSFERENCIA'])}</com:importe>
                    <com:moneda>
                        <cat:Id>{fn:data($RowTransferencias/ns1:Column[@name='ID_TCA_TIPO_MONEDA'])}</cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta>{fn:data($RowTransferencias/ns1:Column[@name='DESCRIPCION_CORTA_MONEDA'])}</cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno>{fn:data($RowTransferencias/ns1:Column[@name='COD_EXTERNO_MONEDA'])}</cat:codigoExterno>
                    </com:moneda>
                </des:Monto>
                <des:referenciaMensaje>{fn:data($RowTransferencias/ns1:Column[@name='REFERENCIA_MSJ'])}</des:referenciaMensaje>
                {
                if(string($RowTransferencias/ns1:Column[@name='EN_CONSOLIDACION'])!= '')then
                <des:esConsolidada>{
                if(fn:data($RowTransferencias/ns1:Column[@name='EN_CONSOLIDACION']/text())=1) then true() else false()}</des:esConsolidada>
                else()}
                <des:esConsolidacionPadre>{fn:data($RowTransferencias/ns1:Column[@name='ES_CONSOLIDACION_PADRE'])}</des:esConsolidacionPadre>
                <des:idConsolidacionPadre>{fn:data($RowTransferencias/ns1:Column[@name='ID_CONSOLIDACION_PADRE'])}</des:idConsolidacionPadre>
                <des:bhqTransferencia>{fn:data($RowTransferencias/ns1:Column[@name='BHQ_TRANSFERENCIA'])}</des:bhqTransferencia>
                <des:idBancoTransferencia>{fn:data($RowTransferencias/ns1:Column[@name='ID_BANCO_TRANSFERENCIA'])}</des:idBancoTransferencia>
                <des:numeroCuenta>{fn:data($RowTransferencias/ns1:Column[@name='NUMERO_CUENTA'])}</des:numeroCuenta>
                <des:nombreBanco>{fn:data($RowTransferencias/ns1:Column[@name='NOMBRE_BANCO_TRANSFERENCIA'])}</des:nombreBanco>
                <des:idOperacion>{fn:data($RowTransferencias/ns1:Column[@name='ID_OPERACION'])}</des:idOperacion>
                <des:tipoMensaje>{fn:data($RowTransferencias/ns1:Column[@name='TIPO_MENSAJE'])}</des:tipoMensaje>
                <des:estado>{fn:data($RowTransferencias/ns1:Column[@name='ESTADO_INSTRUCCION'])}</des:estado>
                <des:Beneficiario>
                    <des:tipoOpcion>{fn:data($RowTransferencias/ns1:Column[@name='TIPO_OPCION_BENEF'])}</des:tipoOpcion>
                    <des:numeroCta>{fn:data($RowTransferencias/ns1:Column[@name='NUMERO_CTA_BENEF'])}</des:numeroCta>
                    <des:identificador>{fn:data($RowTransferencias/ns1:Column[@name='IDENTIFICADOR_BENEF'])}</des:identificador>
                    <des:beneficiario>{fn:data($RowTransferencias/ns1:Column[@name='BENEFICIARIO_BENEF'])}</des:beneficiario>
                    <des:direccion>{fn:data($RowTransferencias/ns1:Column[@name='DIRECCION_BENEF'])}</des:direccion>
                </des:Beneficiario>
                <des:Banco>
                    <des:tipoOpcion>{fn:data($RowTransferencias/ns1:Column[@name='TIPO_OPCION_BAN_BENEF'])}</des:tipoOpcion>
                    <des:numeroCta>{fn:data($RowTransferencias/ns1:Column[@name='NUMERO_CTA_BAN_BENEF'])}</des:numeroCta>
                    <des:identificador>{fn:data($RowTransferencias/ns1:Column[@name='IDENTIFICADOR_BAN_BENEF'])}</des:identificador>
                    <des:beneficiario>{fn:data($RowTransferencias/ns1:Column[@name='BENEFICIARIO_BAN_BENEF'])}</des:beneficiario>
                    <des:direccion>{fn:data($RowTransferencias/ns1:Column[@name='DIRECCION_BAN_BENEF'])}</des:direccion>
                </des:Banco>
                <des:Intermediario>
                    <des:tipoOpcion>{fn:data($RowTransferencias/ns1:Column[@name='TIPO_OPCION_BAN_INTER'])}</des:tipoOpcion>
                    <des:numeroCta>{fn:data($RowTransferencias/ns1:Column[@name='NUMERO_CTA_BAN_INTER'])}</des:numeroCta>
                    <des:identificador>{fn:data($RowTransferencias/ns1:Column[@name='IDENTIFICADOR_BAN_INTER'])}</des:identificador>
                    <des:beneficiario>{fn:data($RowTransferencias/ns1:Column[@name='BENEFICIARIO_BAN_INTER'])}</des:beneficiario>
                    <des:direccion>{fn:data($RowTransferencias/ns1:Column[@name='DIRECCION_BAN_INTER'])}</des:direccion>
                </des:Intermediario>
            </des:Transferencia>
            }
          <des:HerramientaCE>{$ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso/des:HerramientaCE/*}</des:HerramientaCE>
        </ns2:ContratoDesembolso>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{
            if (count($ConsultarDesembolsosByIdResponse/ns2:ContratoDesembolso)>0)then 'Consulta Exitosa'
            else 'No existen registros'}</res:message>
        </ns2:Resultado>
    </ns2:ConsultarTransferenciasBancariasByIdResponse>
};

local:func($ConsultarDesembolsosByIdResponse,$ConcultarTransferenciaBancariaOutputParameters)
