xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)

declare namespace tns="http://www.w3.org/2005/xquery-local-functions";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarCommitmentResponse as element() (:: schema-element(ns1:ConsultarCommitmentResponse) ::) external;

declare variable $ConsultarLineaCreditoBPELResponse as element() (:: schema-element(ns1:ConsultarLineaCreditoBPELResponse) ::) external;


declare function tns:func($ConsultarCommitmentResponse as element()  (:: schema-element(ns1:ConsultarCommitmentResponse) ::),
                          $ConsultarLineaCreditoBPELResponse as element()  (:: schema-element(ns1:ConsultarLineaCreditoBPELResponse) ::)) as element() (:: schema-element(ns1:ConsultarLineaCreditoBPELResponse) ::) {
    <ns1:ConsultarLineaCreditoBPELResponse>
    
        <ns1:LineaCredito>
           
{
                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:idLineaCredito)
                then <lin:idLineaCredito>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:idLineaCredito)}</lin:idLineaCredito>
                else ()
            }
            {
                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:idContrato)
                then <lin:idContrato>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:idContrato)}</lin:idContrato>
                else ()
            }
            {
                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:idOperacion)
                then <lin:idOperacion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:idOperacion)}</lin:idOperacion>
                else ()
            }
            <lin:NumeroLineaCredito>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:NumeroLineaCredito)}</lin:NumeroLineaCredito>
            <lin:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Descripcion)}</lin:Descripcion>
            {
                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Flexcube)
                then 
                    <lin:Flexcube>
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Flexcube/lin:id)
                            then <lin:id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Flexcube/lin:id)}</lin:id>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Flexcube/lin:idProductoFlexcube)
                            then <lin:idProductoFlexcube>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Flexcube/lin:idProductoFlexcube)}</lin:idProductoFlexcube>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Flexcube/lin:idFlexcubePasivo)
                            then <lin:idFlexcubePasivo>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Flexcube/lin:idFlexcubePasivo)}</lin:idFlexcubePasivo>
                            else ()
                        }
                    </lin:Flexcube>
                else ()
            }
            {
                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Fondo)
                then <lin:Fondo>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Fondo)}</lin:Fondo>
                else ()
            }
            {
                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:IdTipoMonedaMontoLinea)
                then <lin:IdTipoMonedaMontoLinea>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:IdTipoMonedaMontoLinea)}</lin:IdTipoMonedaMontoLinea>
                else ()
            }
            {
                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:MontoLinea)
                then <lin:MontoLinea>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:MontoLinea)}</lin:MontoLinea>
                else ()
            }
            {
                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:EsRevolvente)
                then <lin:EsRevolvente>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:EsRevolvente)}</lin:EsRevolvente>
                else ()
            }
            <lin:TratamientoDiasFeriados>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:TratamientoDiasFeriados)}</lin:TratamientoDiasFeriados>
            <lin:FechaValor>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:FechaValor)}</lin:FechaValor>
            <lin:FechaVencimiento>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:FechaVencimiento)}</lin:FechaVencimiento>
            <lin:CodigoPantallaLimite>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:CodigoPantallaLimite)}</lin:CodigoPantallaLimite>
            <lin:CodigoSerialLimite>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:CodigoSerialLimite)}</lin:CodigoSerialLimite>
            {
                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:CodigoAsignacion)
                then <lin:CodigoAsignacion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:CodigoAsignacion)}</lin:CodigoAsignacion>
                else ()
            }
            <lin:DescripcionAsignacion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:DescripcionAsignacion)}</lin:DescripcionAsignacion>
            {
                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:CondicionEspecial)
                then <lin:CondicionEspecial>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:CondicionEspecial)}</lin:CondicionEspecial>
                else ()
            }
            <lin:FechaRegistro>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:FechaRegistro)}</lin:FechaRegistro>
            <lin:FechaMaximaDesembolso>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:FechaMaximaDesembolso)}</lin:FechaMaximaDesembolso>
            {
                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Estado)
                then <lin:Estado>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Estado)}</lin:Estado>
                else ()
            }
            <lin:descCondEspecial>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:descCondEspecial)}</lin:descCondEspecial>
            <lin:frecuenciaGracia>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:frecuenciaGracia)}</lin:frecuenciaGracia>
            <lin:plazoGracia>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:plazoGracia)}</lin:plazoGracia>
            <lin:frecuenciaFinanciamiento>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:frecuenciaFinanciamiento)}</lin:frecuenciaFinanciamiento>
            <lin:plazoFinanciamiento>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:plazoFinanciamiento)}</lin:plazoFinanciamiento>
            <lin:recursosExternos>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:recursosExternos)}</lin:recursosExternos>
            {
                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:tasaMinima)
                then <lin:tasaMinima>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:tasaMinima)}</lin:tasaMinima>
                else ()
            }
            {
                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:tasaMaxima)
                then <lin:tasaMaxima>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:tasaMaxima)}</lin:tasaMaxima>
                else ()
            }
            {
                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:montoMinimo)
                then <lin:montoMinimo>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:montoMinimo)}</lin:montoMinimo>
                else ()
            }
            {
                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:montoMaximo)
                then <lin:montoMaximo>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:montoMaximo)}</lin:montoMaximo>
                else ()
            }
            {
                for $Monto in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Monto
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
            {
                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:EstadoMonto)
                then <lin:EstadoMonto>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:EstadoMonto)}</lin:EstadoMonto>
                else ()
            }
			
			
			
			 <lin:Condicion>
			
			
			<con:idCondicion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:idCondicion)}</con:idCondicion>
                <con:operacion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:operacion)}</con:operacion>
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:nombre)
                    then <con:nombre>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:nombre)}</con:nombre>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:descripcion)
                    then <con:descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:descripcion)}</con:descripcion>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion)
                    then 
                        <con:tipoCondicion>
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:idCatCondicion)
                                then <con:idCatCondicion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:idCatCondicion)}</con:idCatCondicion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:descripcion)
                                then <con:descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:descripcion)}</con:descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:descripcionCorta)
                                then <con:descripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:descripcionCorta)}</con:descripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:idTipoCondicion)
                                then <con:idTipoCondicion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:idTipoCondicion)}</con:idTipoCondicion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:esEditableEnFormalizacion)
                                then <con:esEditableEnFormalizacion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:esEditableEnFormalizacion)}</con:esEditableEnFormalizacion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:requiereValidarCOFI)
                                then <con:requiereValidarCOFI>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:requiereValidarCOFI)}</con:requiereValidarCOFI>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:sePuedeDispensar)
                                then <con:sePuedeDispensar>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:sePuedeDispensar)}</con:sePuedeDispensar>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:esPlantilla)
                                then <con:esPlantilla>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:esPlantilla)}</con:esPlantilla>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:idCondicionPrecarga)
                                then <con:idCondicionPrecarga>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:idCondicionPrecarga)}</con:idCondicionPrecarga>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:fechaRegistro)
                                then <con:fechaRegistro>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:fechaRegistro)}</con:fechaRegistro>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:estado)
                                then <con:estado>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:estado)}</con:estado>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:codigoExterno)
                                then <con:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:codigoExterno)}</con:codigoExterno>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:tipoCondicion)
                                then 
                                    <con:tipoCondicion>
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:tipoCondicion/cat:Id)
                                            then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:tipoCondicion/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:tipoCondicion/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:tipoCondicion/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:tipoCondicion/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:tipoCondicion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:tipoCondicion/cat:estatus)
                                            then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:tipoCondicion/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:tipoCondicion/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoCondicion/con:tipoCondicion/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </con:tipoCondicion>
                                else ()
                            }
                        </con:tipoCondicion>
                    else ()
                }
                {
                    for $categoriaCondicion in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:categoriaCondicion
                    return 
                    <con:categoriaCondicion>
                        <con:id>{fn:data($categoriaCondicion/con:id)}</con:id>
                        <con:descripcion>{fn:data($categoriaCondicion/con:descripcion)}</con:descripcion>
                        {
                            if ($categoriaCondicion/con:descripcionCorta)
                            then <con:descripcionCorta>{fn:data($categoriaCondicion/con:descripcionCorta)}</con:descripcionCorta>
                            else ()
                        }
                        {
                            if ($categoriaCondicion/con:estatus)
                            then <con:estatus>{fn:data($categoriaCondicion/con:estatus)}</con:estatus>
                            else ()
                        }
                        {
                            if ($categoriaCondicion/con:codigoExterno)
                            then <con:codigoExterno>{fn:data($categoriaCondicion/con:codigoExterno)}</con:codigoExterno>
                            else ()
                        }
                        {
                            for $validadores in $categoriaCondicion/con:validadores
                            return 
                            <con:validadores>
                                {
                                    if ($validadores/cat:Id)
                                    then <cat:Id>{fn:data($validadores/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($validadores/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($validadores/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($validadores/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($validadores/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($validadores/cat:estatus)
                                    then <cat:estatus>{fn:data($validadores/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($validadores/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($validadores/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </con:validadores>
                        }
                    </con:categoriaCondicion>
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:controlCondicion)
                    then 
                        <con:controlCondicion>
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:controlCondicion/cat:Id)
                                then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:controlCondicion/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:controlCondicion/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:controlCondicion/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:controlCondicion/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:controlCondicion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:controlCondicion/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:controlCondicion/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:controlCondicion/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:controlCondicion/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </con:controlCondicion>
                    else ()
                }
                {
                    for $eventoCondicion in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:eventoCondicion
                    return 
                    <con:eventoCondicion>
                        {
                            if ($eventoCondicion/cat:Id)
                            then <cat:Id>{fn:data($eventoCondicion/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($eventoCondicion/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($eventoCondicion/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($eventoCondicion/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($eventoCondicion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($eventoCondicion/cat:estatus)
                            then <cat:estatus>{fn:data($eventoCondicion/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($eventoCondicion/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($eventoCondicion/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </con:eventoCondicion>
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoFechaInicio)
                    then 
                        <con:tipoFechaInicio>
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoFechaInicio/cat:Id)
                                then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoFechaInicio/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoFechaInicio/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoFechaInicio/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoFechaInicio/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoFechaInicio/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoFechaInicio/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoFechaInicio/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoFechaInicio/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoFechaInicio/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </con:tipoFechaInicio>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:fechaInicio)
                    then <con:fechaInicio>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:fechaInicio)}</con:fechaInicio>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:plazo)
                    then <con:plazo>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:plazo)}</con:plazo>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:frecuenciaPlazo)
                    then 
                        <con:frecuenciaPlazo>
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:frecuenciaPlazo/cat:Id)
                                then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:frecuenciaPlazo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:frecuenciaPlazo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:frecuenciaPlazo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:frecuenciaPlazo/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:frecuenciaPlazo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:frecuenciaPlazo/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:frecuenciaPlazo/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:frecuenciaPlazo/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:frecuenciaPlazo/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </con:frecuenciaPlazo>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:fechaFinal)
                    then <con:fechaFinal>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:fechaFinal)}</con:fechaFinal>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:estadoTCC)
                    then 
                        <con:estadoTCC>
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:estadoTCC/atr:id)
                                then <atr:id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:estadoTCC/atr:id)}</atr:id>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:estadoTCC/atr:descripcion)
                                then <atr:descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:estadoTCC/atr:descripcion)}</atr:descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:estadoTCC/atr:descripcionCorta)
                                then <atr:descripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:estadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:estadoTCC/atr:estatus)
                                then <atr:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:estadoTCC/atr:estatus)}</atr:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:estadoTCC/atr:codigoExterno)
                                then <atr:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:estadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:estadoTCC/atr:esSubEstado)
                                then <atr:esSubEstado>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:estadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                                else ()
                            }
                        </con:estadoTCC>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:subEstadoTCC)
                    then 
                        <con:subEstadoTCC>
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:subEstadoTCC/atr:id)
                                then <atr:id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:subEstadoTCC/atr:id)}</atr:id>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:subEstadoTCC/atr:descripcion)
                                then <atr:descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:subEstadoTCC/atr:descripcion)}</atr:descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:subEstadoTCC/atr:descripcionCorta)
                                then <atr:descripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:subEstadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:subEstadoTCC/atr:estatus)
                                then <atr:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:subEstadoTCC/atr:estatus)}</atr:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:subEstadoTCC/atr:codigoExterno)
                                then <atr:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:subEstadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:subEstadoTCC/atr:esSubEstado)
                                then <atr:esSubEstado>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:subEstadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                                else ()
                            }
                        </con:subEstadoTCC>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:fechaRegistro)
                    then <con:fechaRegistro>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:fechaRegistro)}</con:fechaRegistro>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:estado)
                    then <con:estado>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:estado)}</con:estado>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:condicionEnmendada)
                    then <con:condicionEnmendada>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:condicionEnmendada)}</con:condicionEnmendada>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:fechaEnmienda)
                    then <con:fechaEnmienda>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:fechaEnmienda)}</con:fechaEnmienda>
                    else ()
                }
                {
                    for $configAtributo in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:configAtributo
                    return 
                    <con:configAtributo>
                        {
                            if ($configAtributo/atr:id)
                            then <atr:id>{fn:data($configAtributo/atr:id)}</atr:id>
                            else ()
                        }
                        <atr:columna>{fn:data($configAtributo/atr:columna)}</atr:columna>
                        {
                            if ($configAtributo/atr:ordenamiento)
                            then <atr:ordenamiento>{fn:data($configAtributo/atr:ordenamiento)}</atr:ordenamiento>
                            else ()
                        }
                        {
                            if ($configAtributo/atr:soloLectura)
                            then <atr:soloLectura>{fn:data($configAtributo/atr:soloLectura)}</atr:soloLectura>
                            else ()
                        }
                        {
                            if ($configAtributo/atr:esObligatorio)
                            then <atr:esObligatorio>{fn:data($configAtributo/atr:esObligatorio)}</atr:esObligatorio>
                            else ()
                        }
                        {
                            if ($configAtributo/atr:etiqueta)
                            then <atr:etiqueta>{fn:data($configAtributo/atr:etiqueta)}</atr:etiqueta>
                            else ()
                        }
                        {
                            for $valor in $configAtributo/atr:valor
                            return 
                            <atr:valor>{fn:data($configAtributo/atr:valor)}</atr:valor>
                        }
                        {
                            if ($configAtributo/atr:tipoValor)
                            then <atr:tipoValor>{fn:data($configAtributo/atr:tipoValor)}</atr:tipoValor>
                            else ()
                        }
                        {
                            for $catalogo in $configAtributo/atr:catalogo
                            return 
                            <atr:catalogo>
                                {
                                    if ($catalogo/cat:Id)
                                    then <cat:Id>{fn:data($catalogo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($catalogo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($catalogo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($catalogo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($catalogo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($catalogo/cat:estatus)
                                    then <cat:estatus>{fn:data($catalogo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($catalogo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($catalogo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </atr:catalogo>
                        }
                    </con:configAtributo>
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:evidencia)
                    then 
                        <con:evidencia>
                            {
                                for $Documento in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:evidencia/doc:Documento
                                return 
                                <doc:Documento>
                                    {
                                        if ($Documento/doc:idDocumento)
                                        then <doc:idDocumento>{fn:data($Documento/doc:idDocumento)}</doc:idDocumento>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idTipoDocumento)
                                        then <doc:idTipoDocumento>{fn:data($Documento/doc:idTipoDocumento)}</doc:idTipoDocumento>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idTipoDocumentoExterno)
                                        then <doc:idTipoDocumentoExterno>{fn:data($Documento/doc:idTipoDocumentoExterno)}</doc:idTipoDocumentoExterno>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:nombreTipoDocumento)
                                        then <doc:nombreTipoDocumento>{fn:data($Documento/doc:nombreTipoDocumento)}</doc:nombreTipoDocumento>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:etapa)
                                        then <doc:etapa>{fn:data($Documento/doc:etapa)}</doc:etapa>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:codigoDocumento)
                                        then <doc:codigoDocumento>{fn:data($Documento/doc:codigoDocumento)}</doc:codigoDocumento>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idExterno)
                                        then <doc:idExterno>{fn:data($Documento/doc:idExterno)}</doc:idExterno>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idDocAreaTipo)
                                        then <doc:idDocAreaTipo>{fn:data($Documento/doc:idDocAreaTipo)}</doc:idDocAreaTipo>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idOperacion)
                                        then <doc:idOperacion>{fn:data($Documento/doc:idOperacion)}</doc:idOperacion>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idCliente)
                                        then <doc:idCliente>{fn:data($Documento/doc:idCliente)}</doc:idCliente>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idProducto)
                                        then <doc:idProducto>{fn:data($Documento/doc:idProducto)}</doc:idProducto>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idPais)
                                        then <doc:idPais>{fn:data($Documento/doc:idPais)}</doc:idPais>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:nombre)
                                        then <doc:nombre>{fn:data($Documento/doc:nombre)}</doc:nombre>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:filename)
                                        then <doc:filename>{fn:data($Documento/doc:filename)}</doc:filename>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:mime_type)
                                        then <doc:mime_type>{fn:data($Documento/doc:mime_type)}</doc:mime_type>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:tamanio)
                                        then <doc:tamanio>{fn:data($Documento/doc:tamanio)}</doc:tamanio>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idAdjunto)
                                        then <doc:idAdjunto>{fn:data($Documento/doc:idAdjunto)}</doc:idAdjunto>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:esJustificacion)
                                        then <doc:esJustificacion>{fn:data($Documento/doc:esJustificacion)}</doc:esJustificacion>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:comentario)
                                        then <doc:comentario>{fn:data($Documento/doc:comentario)}</doc:comentario>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:fechaDocumento)
                                        then <doc:fechaDocumento>{fn:data($Documento/doc:fechaDocumento)}</doc:fechaDocumento>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:fechaRegistro)
                                        then <doc:fechaRegistro>{fn:data($Documento/doc:fechaRegistro)}</doc:fechaRegistro>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:status)
                                        then <doc:status>{fn:data($Documento/doc:status)}</doc:status>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:estatusTipoDoc)
                                        then <doc:estatusTipoDoc>{fn:data($Documento/doc:estatusTipoDoc)}</doc:estatusTipoDoc>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:codExtTipoDoc)
                                        then <doc:codExtTipoDoc>{fn:data($Documento/doc:codExtTipoDoc)}</doc:codExtTipoDoc>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:tarea)
                                        then <doc:tarea>{fn:data($Documento/doc:tarea)}</doc:tarea>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idtarea)
                                        then <doc:idtarea>{fn:data($Documento/doc:idtarea)}</doc:idtarea>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:content)
                                        then <doc:content>{fn:data($Documento/doc:content)}</doc:content>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:PuedeModificar)
                                        then <doc:PuedeModificar>{fn:data($Documento/doc:PuedeModificar)}</doc:PuedeModificar>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:PuedeBorrar)
                                        then <doc:PuedeBorrar>{fn:data($Documento/doc:PuedeBorrar)}</doc:PuedeBorrar>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:usuarioAgrega)
                                        then <doc:usuarioAgrega>{fn:data($Documento/doc:usuarioAgrega)}</doc:usuarioAgrega>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:usuarioUltimaActualizacion)
                                        then <doc:usuarioUltimaActualizacion>{fn:data($Documento/doc:usuarioUltimaActualizacion)}</doc:usuarioUltimaActualizacion>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:accionARealizar)
                                        then <doc:accionARealizar>{fn:data($Documento/doc:accionARealizar)}</doc:accionARealizar>
                                        else ()
                                    }
                                    {
                                        if ($Documento/doc:idFlujoNegocio)
                                        then <doc:idFlujoNegocio>{fn:data($Documento/doc:idFlujoNegocio)}</doc:idFlujoNegocio>
                                        else ()
                                    }
                                </doc:Documento>
                            }
                        </con:evidencia>
                    else ()
                }
                {
                    for $observaciones in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:observaciones
                    return 
                    <con:observaciones>
                        <con:id>{fn:data($observaciones/con:id)}</con:id>
                        {
                            if ($observaciones/con:observacion)
                            then <con:observacion>{fn:data($observaciones/con:observacion)}</con:observacion>
                            else ()
                        }
                        {
                            if ($observaciones/con:loginUsuario)
                            then <con:loginUsuario>{fn:data($observaciones/con:loginUsuario)}</con:loginUsuario>
                            else ()
                        }
                        {
                            if ($observaciones/con:nombreUsuario)
                            then <con:nombreUsuario>{fn:data($observaciones/con:nombreUsuario)}</con:nombreUsuario>
                            else ()
                        }
                        {
                            if ($observaciones/con:fechaRegistro)
                            then <con:fechaRegistro>{fn:data($observaciones/con:fechaRegistro)}</con:fechaRegistro>
                            else ()
                        }
                        {
                            if ($observaciones/con:estatus)
                            then <con:estatus>{fn:data($observaciones/con:estatus)}</con:estatus>
                            else ()
                        }
                        {
                            if ($observaciones/con:esPrincipal)
                            then <con:esPrincipal>{fn:data($observaciones/con:esPrincipal)}</con:esPrincipal>
                            else ()
                        }
                        {
                            if ($observaciones/con:tareaBPM)
                            then 
                                <con:tareaBPM>
                                    {
                                        if ($observaciones/con:tareaBPM/cat:Id)
                                        then <cat:Id>{fn:data($observaciones/con:tareaBPM/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($observaciones/con:tareaBPM/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($observaciones/con:tareaBPM/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($observaciones/con:tareaBPM/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($observaciones/con:tareaBPM/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($observaciones/con:tareaBPM/cat:estatus)
                                        then <cat:estatus>{fn:data($observaciones/con:tareaBPM/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($observaciones/con:tareaBPM/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($observaciones/con:tareaBPM/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </con:tareaBPM>
                            else ()
                        }
                    </con:observaciones>
                }
                {
                    for $lineaCredito in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:lineaCredito
                    return 
                    <con:lineaCredito>
                        {
                            if ($lineaCredito/atr:id)
                            then <atr:id>{fn:data($lineaCredito/atr:id)}</atr:id>
                            else ()
                        }
                        {
                            if ($lineaCredito/atr:descripcion)
                            then <atr:descripcion>{fn:data($lineaCredito/atr:descripcion)}</atr:descripcion>
                            else ()
                        }
                        {
                            if ($lineaCredito/atr:estatus)
                            then <atr:estatus>{fn:data($lineaCredito/atr:estatus)}</atr:estatus>
                            else ()
                        }
                    </con:lineaCredito>
                }
                {
                    for $fuente in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:fuente
                    return 
                    <con:fuente>
                        {
                            if ($fuente/atr:id)
                            then <atr:id>{fn:data($fuente/atr:id)}</atr:id>
                            else ()
                        }
                        {
                            if ($fuente/atr:descripcion)
                            then <atr:descripcion>{fn:data($fuente/atr:descripcion)}</atr:descripcion>
                            else ()
                        }
                        {
                            if ($fuente/atr:estatus)
                            then <atr:estatus>{fn:data($fuente/atr:estatus)}</atr:estatus>
                            else ()
                        }
                    </con:fuente>
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Accion)
                    then <con:Accion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Accion)}</con:Accion>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:fechaVigencia)
                    then <con:fechaVigencia>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:fechaVigencia)}</con:fechaVigencia>
                    else ()
                }
                <con:Cumplimientos>
                    {
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Id)
                        then <con:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Id)}</con:Id>
                        else ()
                    }
                    {
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Operacion)
                        then 
                            <con:Operacion>
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Operacion/ope:idOperacion)
                                    then <ope:idOperacion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Operacion/ope:idOperacion)}</ope:idOperacion>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Operacion/ope:responsable)
                                    then <ope:responsable>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Operacion/ope:responsable)}</ope:responsable>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Operacion/ope:oficina)
                                    then <ope:oficina>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Operacion/ope:oficina)}</ope:oficina>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Operacion/ope:nombre)
                                    then <ope:nombre>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Operacion/ope:nombre)}</ope:nombre>
                                    else ()
                                }
                            </con:Operacion>
                        else ()
                    }
                    {
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud)
                        then 
                            <con:Solicitud>
                                <des:idDesembolso>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:idDesembolso)}</des:idDesembolso>
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:idFacturador)
                                    then <des:idFacturador>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:idFacturador)}</des:idFacturador>
                                    else ()
                                }
                                <des:fechaCreacion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:fechaCreacion)}</des:fechaCreacion>
                                <des:fechaSolicitud>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:fechaSolicitud)}</des:fechaSolicitud>
                                <des:monto>
                                    <com:tipo>
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:tipo/cat:Id)
                                            then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:tipo/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:tipo/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:tipo/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:tipo/cat:estatus)
                                            then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:tipo/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:tipo/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com:tipo>
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:importe)
                                        then <com:importe>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:importe)}</com:importe>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:moneda)
                                        then 
                                            <com:moneda>
                                                {
                                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:moneda/cat:Id)
                                                    then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:moneda/cat:Id)}</cat:Id>
                                                    else ()
                                                }
                                                {
                                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:moneda/cat:Descripcion)
                                                    then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:moneda/cat:DescripcionCorta)
                                                    then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:moneda/cat:estatus)
                                                    then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:moneda/cat:estatus)}</cat:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:moneda/cat:codigoExterno)
                                                    then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                                    else ()
                                                }
                                            </com:moneda>
                                        else ()
                                    }
                                </des:monto>
                                <des:tipoMoneda>
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:tipoMoneda/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:tipoMoneda/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:tipoMoneda/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:tipoMoneda/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:tipoMoneda/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:tipoMoneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:tipoMoneda/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:tipoMoneda/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:tipoMoneda/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:tipoMoneda/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </des:tipoMoneda>
                                <des:estado>
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:estado/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:estado/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:estado/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:estado/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:estado/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:estado/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:estado/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:estado/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:estado/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </des:estado>
                                <des:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:estatus)}</des:estatus>
                                <des:instanciaProceso>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:instanciaProceso)}</des:instanciaProceso>
                                <des:ValidacionAsignacion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:ValidacionAsignacion)}</des:ValidacionAsignacion>
                                {
                                    for $Excepcion in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:Solicitud/des:Excepcion
                                    return 
                                    <des:Excepcion>
                                        <reg:Id>{fn:data($Excepcion/reg:Id)}</reg:Id>
                                        {
                                            if ($Excepcion/reg:Descripcion)
                                            then <reg:Descripcion>{fn:data($Excepcion/reg:Descripcion)}</reg:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Excepcion/reg:Transaccion)
                                            then <reg:Transaccion>{fn:data($Excepcion/reg:Transaccion)}</reg:Transaccion>
                                            else ()
                                        }
                                        {
                                            if ($Excepcion/reg:Tipo)
                                            then 
                                                <reg:Tipo>
                                                    {
                                                        if ($Excepcion/reg:Tipo/cat:Id)
                                                        then <cat:Id>{fn:data($Excepcion/reg:Tipo/cat:Id)}</cat:Id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Tipo/cat:Descripcion)
                                                        then <cat:Descripcion>{fn:data($Excepcion/reg:Tipo/cat:Descripcion)}</cat:Descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Tipo/cat:DescripcionCorta)
                                                        then <cat:DescripcionCorta>{fn:data($Excepcion/reg:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Tipo/cat:estatus)
                                                        then <cat:estatus>{fn:data($Excepcion/reg:Tipo/cat:estatus)}</cat:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Tipo/cat:codigoExterno)
                                                        then <cat:codigoExterno>{fn:data($Excepcion/reg:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                                        else ()
                                                    }
                                                </reg:Tipo>
                                            else ()
                                        }
                                        {
                                            if ($Excepcion/reg:PosibleExceptuar)
                                            then <reg:PosibleExceptuar>{fn:data($Excepcion/reg:PosibleExceptuar)}</reg:PosibleExceptuar>
                                            else ()
                                        }
                                        {
                                            if ($Excepcion/reg:Exceptuado)
                                            then 
                                                <reg:Exceptuado>
                                                    {
                                                        if ($Excepcion/reg:Exceptuado/cat:Id)
                                                        then <cat:Id>{fn:data($Excepcion/reg:Exceptuado/cat:Id)}</cat:Id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Exceptuado/cat:Descripcion)
                                                        then <cat:Descripcion>{fn:data($Excepcion/reg:Exceptuado/cat:Descripcion)}</cat:Descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Exceptuado/cat:DescripcionCorta)
                                                        then <cat:DescripcionCorta>{fn:data($Excepcion/reg:Exceptuado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Exceptuado/cat:estatus)
                                                        then <cat:estatus>{fn:data($Excepcion/reg:Exceptuado/cat:estatus)}</cat:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Exceptuado/cat:codigoExterno)
                                                        then <cat:codigoExterno>{fn:data($Excepcion/reg:Exceptuado/cat:codigoExterno)}</cat:codigoExterno>
                                                        else ()
                                                    }
                                                </reg:Exceptuado>
                                            else ()
                                        }
                                        {
                                            if ($Excepcion/reg:UsuarioExceptua)
                                            then <reg:UsuarioExceptua>{fn:data($Excepcion/reg:UsuarioExceptua)}</reg:UsuarioExceptua>
                                            else ()
                                        }
                                        {
                                            if ($Excepcion/reg:FechaExcepcion)
                                            then <reg:FechaExcepcion>{fn:data($Excepcion/reg:FechaExcepcion)}</reg:FechaExcepcion>
                                            else ()
                                        }
                                        {
                                            if ($Excepcion/reg:Estado)
                                            then 
                                                <reg:Estado>
                                                    {
                                                        if ($Excepcion/reg:Estado/cat:Id)
                                                        then <cat:Id>{fn:data($Excepcion/reg:Estado/cat:Id)}</cat:Id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Estado/cat:Descripcion)
                                                        then <cat:Descripcion>{fn:data($Excepcion/reg:Estado/cat:Descripcion)}</cat:Descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Estado/cat:DescripcionCorta)
                                                        then <cat:DescripcionCorta>{fn:data($Excepcion/reg:Estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Estado/cat:estatus)
                                                        then <cat:estatus>{fn:data($Excepcion/reg:Estado/cat:estatus)}</cat:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Excepcion/reg:Estado/cat:codigoExterno)
                                                        then <cat:codigoExterno>{fn:data($Excepcion/reg:Estado/cat:codigoExterno)}</cat:codigoExterno>
                                                        else ()
                                                    }
                                                </reg:Estado>
                                            else ()
                                        }
                                        {
                                            if ($Excepcion/reg:Estatus)
                                            then <reg:Estatus>{fn:data($Excepcion/reg:Estatus)}</reg:Estatus>
                                            else ()
                                        }
                                        {
                                            for $DetalleRegla in $Excepcion/reg:DetalleRegla
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
                                        <des:instanciaProceso>{fn:data($Excepcion/des:instanciaProceso)}</des:instanciaProceso>
                                        <des:enProcesoExcepcion>{fn:data($Excepcion/des:enProcesoExcepcion)}</des:enProcesoExcepcion>
                                    </des:Excepcion>
                                }
                            </con:Solicitud>
                        else ()
                    }
                    <con:ContratoDesembolso>
                        <des:idDesembolso>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:idDesembolso)}</des:idDesembolso>
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:idFacturador)
                            then <des:idFacturador>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:idFacturador)}</des:idFacturador>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:producto)
                            then 
                                <des:producto>
                                    <pro:idProducto>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:producto/pro:idProducto)}</pro:idProducto>
                                    <pro:descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:producto/pro:descripcion)}</pro:descripcion>
                                    <pro:descripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:producto/pro:descripcionCorta)}</pro:descripcionCorta>
                                    <pro:fechaRegistro>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:producto/pro:fechaRegistro)}</pro:fechaRegistro>
                                    <pro:codExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:producto/pro:codExterno)}</pro:codExterno>
                                    {
                                        for $Componente in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:producto/des:Componente
                                        return 
                                        <des:Componente>
                                            {
                                                if ($Componente/cat:Id)
                                                then <cat:Id>{fn:data($Componente/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($Componente/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($Componente/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($Componente/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($Componente/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($Componente/cat:estatus)
                                                then <cat:estatus>{fn:data($Componente/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($Componente/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($Componente/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                            <des:TipoComponente>
                                                {
                                                    if ($Componente/des:TipoComponente/cat:Id)
                                                    then <cat:Id>{fn:data($Componente/des:TipoComponente/cat:Id)}</cat:Id>
                                                    else ()
                                                }
                                                {
                                                    if ($Componente/des:TipoComponente/cat:Descripcion)
                                                    then <cat:Descripcion>{fn:data($Componente/des:TipoComponente/cat:Descripcion)}</cat:Descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($Componente/des:TipoComponente/cat:DescripcionCorta)
                                                    then <cat:DescripcionCorta>{fn:data($Componente/des:TipoComponente/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($Componente/des:TipoComponente/cat:estatus)
                                                    then <cat:estatus>{fn:data($Componente/des:TipoComponente/cat:estatus)}</cat:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($Componente/des:TipoComponente/cat:codigoExterno)
                                                    then <cat:codigoExterno>{fn:data($Componente/des:TipoComponente/cat:codigoExterno)}</cat:codigoExterno>
                                                    else ()
                                                }
                                            </des:TipoComponente>
                                            <des:TipoTasa>
                                                {
                                                    if ($Componente/des:TipoTasa/cat:Id)
                                                    then <cat:Id>{fn:data($Componente/des:TipoTasa/cat:Id)}</cat:Id>
                                                    else ()
                                                }
                                                {
                                                    if ($Componente/des:TipoTasa/cat:Descripcion)
                                                    then <cat:Descripcion>{fn:data($Componente/des:TipoTasa/cat:Descripcion)}</cat:Descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($Componente/des:TipoTasa/cat:DescripcionCorta)
                                                    then <cat:DescripcionCorta>{fn:data($Componente/des:TipoTasa/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($Componente/des:TipoTasa/cat:estatus)
                                                    then <cat:estatus>{fn:data($Componente/des:TipoTasa/cat:estatus)}</cat:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($Componente/des:TipoTasa/cat:codigoExterno)
                                                    then <cat:codigoExterno>{fn:data($Componente/des:TipoTasa/cat:codigoExterno)}</cat:codigoExterno>
                                                    else ()
                                                }
                                            </des:TipoTasa>
                                            <des:esPrincipal>{fn:data($Componente/des:esPrincipal)}</des:esPrincipal>
                                            {
                                                if ($Componente/des:Plazo)
                                                then 
                                                    <des:Plazo>
                                                        <com:Tipo>
                                                            {
                                                                if ($Componente/des:Plazo/com:Tipo/cat:Id)
                                                                then <cat:Id>{fn:data($Componente/des:Plazo/com:Tipo/cat:Id)}</cat:Id>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Componente/des:Plazo/com:Tipo/cat:Descripcion)
                                                                then <cat:Descripcion>{fn:data($Componente/des:Plazo/com:Tipo/cat:Descripcion)}</cat:Descripcion>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Componente/des:Plazo/com:Tipo/cat:DescripcionCorta)
                                                                then <cat:DescripcionCorta>{fn:data($Componente/des:Plazo/com:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Componente/des:Plazo/com:Tipo/cat:estatus)
                                                                then <cat:estatus>{fn:data($Componente/des:Plazo/com:Tipo/cat:estatus)}</cat:estatus>
                                                                else ()
                                                            }
                                                            {
                                                                if ($Componente/des:Plazo/com:Tipo/cat:codigoExterno)
                                                                then <cat:codigoExterno>{fn:data($Componente/des:Plazo/com:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                                                else ()
                                                            }
                                                        </com:Tipo>
                                                        <com:Valor>{fn:data($Componente/des:Plazo/com:Valor)}</com:Valor>
                                                    </des:Plazo>
                                                else ()
                                            }
                                        </des:Componente>
                                    }
                                </des:producto>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:referencia)
                            then <des:referencia>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:referencia)}</des:referencia>
                            else ()
                        }
                        {
                            for $monto in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:monto
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
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:estado/cat:Id)
                                then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:estado/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:estado/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:estado/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:estado/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:estado/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:estado/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:estado/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:estado/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </des:estado>
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:programado)
                            then <des:programado>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:programado)}</des:programado>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:fechaEstimadaDesembolsar)
                            then <des:fechaEstimadaDesembolsar>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:fechaEstimadaDesembolsar)}</des:fechaEstimadaDesembolsar>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:fechaEfectiva)
                            then <des:fechaEfectiva>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:fechaEfectiva)}</des:fechaEfectiva>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:fechaAsignacion)
                            then <des:fechaAsignacion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:fechaAsignacion)}</des:fechaAsignacion>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:fechaRegistro)
                            then <des:fechaRegistro>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:fechaRegistro)}</des:fechaRegistro>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:fechaVencimiento)
                            then <des:fechaVencimiento>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:fechaVencimiento)}</des:fechaVencimiento>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:estatus)
                            then <des:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:estatus)}</des:estatus>
                            else ()
                        }
						<des:condicionesFinancieras>
                            <des:idCondicionFinanciera>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:idCondicionFinanciera)}</des:idCondicionFinanciera>
                            <des:tasa>
                                <des:tipo>
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </des:tipo>
                               
                            </des:tasa>
                            <des:fondo>
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Id)
                                    then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:estatus)
                                    then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                                {
                                    for $Validador in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:fondo/des:Validador
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
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:Id)
                                    then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:estatus)
                                    then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </des:baseCalculo>
                            <des:principal>
                                <des:FechaInicio>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:FechaInicio)}</des:FechaInicio>
                                <des:Frecuencia>
                                    <com:Tipo>
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:Id)
                                            then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:estatus)
                                            then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com:Tipo>
                                    <com:Valor>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Valor)}</com:Valor>
                                </des:Frecuencia>
                                <des:fechaVencimiento>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:fechaVencimiento)}</des:fechaVencimiento>
                            </des:principal>
                            <des:interes>
                                <des:FechaInicio>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:FechaInicio)}</des:FechaInicio>
                                <des:Frecuencia>
                                    <com:Tipo>
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:Id)
                                            then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:estatus)
                                            then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com:Tipo>
                                    <com:Valor>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Valor)}</com:Valor>
                                </des:Frecuencia>
                            </des:interes>
                            <des:periodoGracia>
                                <des:FechaInicio>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:FechaInicio)}</des:FechaInicio>
                                <des:Frecuencia>
                                    <com:Tipo>
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:Id)
                                            then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:estatus)
                                            then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com:Tipo>
                                    <com:Valor>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Valor)}</com:Valor>
                                </des:Frecuencia>
                            </des:periodoGracia>
                            <des:tratamientoDiasFeriados>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tratamientoDiasFeriados)}</des:tratamientoDiasFeriados>
                            <des:tipoCalendario>
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:Id)
                                    then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:estatus)
                                    then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </des:tipoCalendario>
                            <des:moverEntreMeses>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:moverEntreMeses)}</des:moverEntreMeses>
                            {
                                for $calendarioComplejo in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:condicionesFinancieras/des:calendarioComplejo
                                return 
                                <des:calendarioComplejo>
                                    <des:TipoPlan>
                                        {
                                            if ($calendarioComplejo/des:TipoPlan/cat:Id)
                                            then <cat:Id>{fn:data($calendarioComplejo/des:TipoPlan/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($calendarioComplejo/des:TipoPlan/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($calendarioComplejo/des:TipoPlan/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($calendarioComplejo/des:TipoPlan/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($calendarioComplejo/des:TipoPlan/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($calendarioComplejo/des:TipoPlan/cat:estatus)
                                            then <cat:estatus>{fn:data($calendarioComplejo/des:TipoPlan/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($calendarioComplejo/des:TipoPlan/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($calendarioComplejo/des:TipoPlan/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </des:TipoPlan>
                                    <des:Frecuencia>
                                        <des:FechaInicio>{fn:data($calendarioComplejo/des:Frecuencia/des:FechaInicio)}</des:FechaInicio>
                                        <des:Frecuencia>
                                            <com:Tipo>
                                                {
                                                    if ($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:Id)
                                                    then <cat:Id>{fn:data($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:Id)}</cat:Id>
                                                    else ()
                                                }
                                                {
                                                    if ($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:Descripcion)
                                                    then <cat:Descripcion>{fn:data($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:Descripcion)}</cat:Descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:DescripcionCorta)
                                                    then <cat:DescripcionCorta>{fn:data($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:estatus)
                                                    then <cat:estatus>{fn:data($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:estatus)}</cat:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:codigoExterno)
                                                    then <cat:codigoExterno>{fn:data($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                                    else ()
                                                }
                                            </com:Tipo>
                                            <com:Valor>{fn:data($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Valor)}</com:Valor>
                                        </des:Frecuencia>
                                    </des:Frecuencia>
                                    <des:Monto>
                                        <com:tipo>
                                            {
                                                if ($calendarioComplejo/des:Monto/com:tipo/cat:Id)
                                                then <cat:Id>{fn:data($calendarioComplejo/des:Monto/com:tipo/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($calendarioComplejo/des:Monto/com:tipo/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($calendarioComplejo/des:Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($calendarioComplejo/des:Monto/com:tipo/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($calendarioComplejo/des:Monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($calendarioComplejo/des:Monto/com:tipo/cat:estatus)
                                                then <cat:estatus>{fn:data($calendarioComplejo/des:Monto/com:tipo/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($calendarioComplejo/des:Monto/com:tipo/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($calendarioComplejo/des:Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </com:tipo>
                                        {
                                            if ($calendarioComplejo/des:Monto/com:importe)
                                            then <com:importe>{fn:data($calendarioComplejo/des:Monto/com:importe)}</com:importe>
                                            else ()
                                        }
                                        {
                                            if ($calendarioComplejo/des:Monto/com:moneda)
                                            then 
                                                <com:moneda>
                                                    {
                                                        if ($calendarioComplejo/des:Monto/com:moneda/cat:Id)
                                                        then <cat:Id>{fn:data($calendarioComplejo/des:Monto/com:moneda/cat:Id)}</cat:Id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($calendarioComplejo/des:Monto/com:moneda/cat:Descripcion)
                                                        then <cat:Descripcion>{fn:data($calendarioComplejo/des:Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($calendarioComplejo/des:Monto/com:moneda/cat:DescripcionCorta)
                                                        then <cat:DescripcionCorta>{fn:data($calendarioComplejo/des:Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($calendarioComplejo/des:Monto/com:moneda/cat:estatus)
                                                        then <cat:estatus>{fn:data($calendarioComplejo/des:Monto/com:moneda/cat:estatus)}</cat:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($calendarioComplejo/des:Monto/com:moneda/cat:codigoExterno)
                                                        then <cat:codigoExterno>{fn:data($calendarioComplejo/des:Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                                        else ()
                                                    }
                                                </com:moneda>
                                            else ()
                                        }
                                    </des:Monto>
                                    <des:NumeroCuotas>{fn:data($calendarioComplejo/des:NumeroCuotas)}</des:NumeroCuotas>
                                </des:calendarioComplejo>
                            }
                        </des:condicionesFinancieras>
                    {
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:idLinea)
                        then <des:idLinea>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:idLinea)}</des:idLinea>
                        else ()
                    }
                    {
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:recursosExternos)
                        then <des:recursosExternos>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:recursosExternos)}</des:recursosExternos>
                        else ()
                    }
                    {
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:cuentaCliente)
                        then <des:cuentaCliente>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:cuentaCliente)}</des:cuentaCliente>
                        else ()
                    }
                    {
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:usuario)
                        then <des:usuario>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:usuario)}</des:usuario>
                        else ()
                    }
                    {
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:fechaDisponibilidadFondos)
                        then <des:fechaDisponibilidadFondos>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:fechaDisponibilidadFondos)}</des:fechaDisponibilidadFondos>
                        else ()
                    }
                    {
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:origenTransferenciaCte)
                        then <des:origenTransferenciaCte>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:origenTransferenciaCte)}</des:origenTransferenciaCte>
                        else ()
                    }
                    {
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa)
                        then 
                            <des:Programa>
                                <her:LineaFinanciera>
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </her:LineaFinanciera>
                                <her:DestinoFinanciamiento>
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </her:DestinoFinanciamiento>
                                <her:ModalidadFinanciamiento>
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </her:ModalidadFinanciamiento>
                                <her:HerramientaCE>
                                    <her:ActividadEconomica>
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Id)
                                            then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:estatus)
                                            then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </her:ActividadEconomica>
                                    <her:EjeEstrategico>
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Id)
                                            then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:estatus)
                                            then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </her:EjeEstrategico>
                                    <her:AreaFocalizacion>
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Id)
                                            then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:estatus)
                                            then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </her:AreaFocalizacion>
                                </her:HerramientaCE>
                                <her:ClasificacionGeneral>
                                    <her:SectorMercado>
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Id)
                                            then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:estatus)
                                            then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </her:SectorMercado>
                                    <her:SectorInstitucional>
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Id)
                                            then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:estatus)
                                            then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </her:SectorInstitucional>
                                </her:ClasificacionGeneral>
                            </des:Programa>
                        else ()
                    }
                    {
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:EstimadoDesembolso)
                        then 
                            <des:EstimadoDesembolso>
                                <des:Plazo>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:EstimadoDesembolso/des:Plazo)}</des:Plazo>
                                <des:Frecuencia>
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </des:Frecuencia>
                            </des:EstimadoDesembolso>
                        else ()
                    }
                    {
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:fechaInicioProceso)
                        then <des:fechaInicioProceso>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:fechaInicioProceso)}</des:fechaInicioProceso>
                        else ()
                    }
                    {
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:fechaEstimadaDisponibilidad)
                        then <des:fechaEstimadaDisponibilidad>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:fechaEstimadaDisponibilidad)}</des:fechaEstimadaDisponibilidad>
                        else ()
                    }
                    {
                        for $Utilizacion in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Utilizacion
                        return 
                        <des:Utilizacion>
                            <lin:idFuente>{fn:data($Utilizacion/lin:idFuente)}</lin:idFuente>
                            <lin:idLineaCredito>{fn:data($Utilizacion/lin:idLineaCredito)}</lin:idLineaCredito>
                            <lin:idLineaPasiva>{fn:data($Utilizacion/lin:idLineaPasiva)}</lin:idLineaPasiva>
                            <lin:codigoLineaPasiva>{fn:data($Utilizacion/lin:codigoLineaPasiva)}</lin:codigoLineaPasiva>
                            <lin:idFacturadorLineaPasiva>{fn:data($Utilizacion/lin:idFacturadorLineaPasiva)}</lin:idFacturadorLineaPasiva>
                            <lin:idFondo>{fn:data($Utilizacion/lin:idFondo)}</lin:idFondo>
                            <lin:Descripcion>{fn:data($Utilizacion/lin:Descripcion)}</lin:Descripcion>
                            <lin:FechaObtenido>{fn:data($Utilizacion/lin:FechaObtenido)}</lin:FechaObtenido>
                            <lin:MontoAsignado>{fn:data($Utilizacion/lin:MontoAsignado)}</lin:MontoAsignado>
                            <lin:montoDisponible>{fn:data($Utilizacion/lin:montoDisponible)}</lin:montoDisponible>
                            {
                                for $Monto in $Utilizacion/lin:Monto
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
                            <lin:NumeroAsignacion>{fn:data($Utilizacion/lin:NumeroAsignacion)}</lin:NumeroAsignacion>
                            <lin:Comentario>{fn:data($Utilizacion/lin:Comentario)}</lin:Comentario>
                            <lin:idContrato>{fn:data($Utilizacion/lin:idContrato)}</lin:idContrato>
                            <lin:FechaRegistro>{fn:data($Utilizacion/lin:FechaRegistro)}</lin:FechaRegistro>
                            <lin:Estado>{fn:data($Utilizacion/lin:Estado)}</lin:Estado>
                            <lin:esExterna>{fn:data($Utilizacion/lin:esExterna)}</lin:esExterna>
                        </des:Utilizacion>
                    }
                    {
                        for $Cargo in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Cargo
                        return 
                        <des:Cargo>
                            {
                                if ($Cargo/cat:Id)
                                then <cat:Id>{fn:data($Cargo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Cargo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Cargo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Cargo/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Cargo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Cargo/cat:estatus)
                                then <cat:estatus>{fn:data($Cargo/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Cargo/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Cargo/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                            <des:Monto>
                                <com:tipo>
                                    {
                                        if ($Cargo/des:Monto/com:tipo/cat:Id)
                                        then <cat:Id>{fn:data($Cargo/des:Monto/com:tipo/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Cargo/des:Monto/com:tipo/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Cargo/des:Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Cargo/des:Monto/com:tipo/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Cargo/des:Monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Cargo/des:Monto/com:tipo/cat:estatus)
                                        then <cat:estatus>{fn:data($Cargo/des:Monto/com:tipo/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Cargo/des:Monto/com:tipo/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Cargo/des:Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com:tipo>
                                {
                                    if ($Cargo/des:Monto/com:importe)
                                    then <com:importe>{fn:data($Cargo/des:Monto/com:importe)}</com:importe>
                                    else ()
                                }
                                {
                                    if ($Cargo/des:Monto/com:moneda)
                                    then 
                                        <com:moneda>
                                            {
                                                if ($Cargo/des:Monto/com:moneda/cat:Id)
                                                then <cat:Id>{fn:data($Cargo/des:Monto/com:moneda/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($Cargo/des:Monto/com:moneda/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($Cargo/des:Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($Cargo/des:Monto/com:moneda/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($Cargo/des:Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($Cargo/des:Monto/com:moneda/cat:estatus)
                                                then <cat:estatus>{fn:data($Cargo/des:Monto/com:moneda/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($Cargo/des:Monto/com:moneda/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($Cargo/des:Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </com:moneda>
                                    else ()
                                }
                            </des:Monto>
                            <des:FechaRegistro>{fn:data($Cargo/des:FechaRegistro)}</des:FechaRegistro>
                            {
                                if ($Cargo/des:Status)
                                then <des:Status>{fn:data($Cargo/des:Status)}</des:Status>
                                else ()
                            }
                            {
                                if ($Cargo/des:SoloLectura)
                                then <des:SoloLectura>{fn:data($Cargo/des:SoloLectura)}</des:SoloLectura>
                                else ()
                            }
                            {
                                if ($Cargo/des:TCC)
                                then 
                                    <des:TCC>
                                        <mat:id>{fn:data($Cargo/des:TCC/mat:id)}</mat:id>
                                        <mat:tipo>{fn:data($Cargo/des:TCC/mat:tipo)}</mat:tipo>
                                        <mat:estado>{fn:data($Cargo/des:TCC/mat:estado)}</mat:estado>
                                        <mat:subEstado>{fn:data($Cargo/des:TCC/mat:subEstado)}</mat:subEstado>
                                    </des:TCC>
                                else ()
                            }
                        </des:Cargo>
                    }
                    {
                        for $Comision in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Comision
                        return 
                        <des:Comision>
                            <com1:idComision>{fn:data($Comision/com1:idComision)}</com1:idComision>
                            <com1:idOperacion>{fn:data($Comision/com1:idOperacion)}</com1:idOperacion>
                            {
                                if ($Comision/com1:nombre)
                                then <com1:nombre>{fn:data($Comision/com1:nombre)}</com1:nombre>
                                else ()
                            }
                            {
                                if ($Comision/com1:descripcion)
                                then <com1:descripcion>{fn:data($Comision/com1:descripcion)}</com1:descripcion>
                                else ()
                            }
                            {
                                if ($Comision/com1:tipoComision)
                                then 
                                    <com1:tipoComision>
                                        {
                                            if ($Comision/com1:tipoComision/com1:idCatComision)
                                            then <com1:idCatComision>{fn:data($Comision/com1:tipoComision/com1:idCatComision)}</com1:idCatComision>
                                            else ()
                                        }
                                        <com1:descripcion>{fn:data($Comision/com1:tipoComision/com1:descripcion)}</com1:descripcion>
                                        <com1:descripcionCorta>{fn:data($Comision/com1:tipoComision/com1:descripcionCorta)}</com1:descripcionCorta>
                                        {
                                            if ($Comision/com1:tipoComision/com1:idTipoComision)
                                            then 
                                                <com1:idTipoComision>
                                                    {
                                                        if ($Comision/com1:tipoComision/com1:idTipoComision/cat:Id)
                                                        then <cat:Id>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:Id)}</cat:Id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Comision/com1:tipoComision/com1:idTipoComision/cat:Descripcion)
                                                        then <cat:Descripcion>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:Descripcion)}</cat:Descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Comision/com1:tipoComision/com1:idTipoComision/cat:DescripcionCorta)
                                                        then <cat:DescripcionCorta>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Comision/com1:tipoComision/com1:idTipoComision/cat:estatus)
                                                        then <cat:estatus>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:estatus)}</cat:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Comision/com1:tipoComision/com1:idTipoComision/cat:codigoExterno)
                                                        then <cat:codigoExterno>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:codigoExterno)}</cat:codigoExterno>
                                                        else ()
                                                    }
                                                </com1:idTipoComision>
                                            else ()
                                        }
                                        <com1:esEditableEnFormalizacion>{fn:data($Comision/com1:tipoComision/com1:esEditableEnFormalizacion)}</com1:esEditableEnFormalizacion>
                                        <com1:requiereValidarCOFI>{fn:data($Comision/com1:tipoComision/com1:requiereValidarCOFI)}</com1:requiereValidarCOFI>
                                        <com1:sePuedeDispensar>{fn:data($Comision/com1:tipoComision/com1:sePuedeDispensar)}</com1:sePuedeDispensar>
                                        <com1:seRegistraEnFlexCube>{fn:data($Comision/com1:tipoComision/com1:seRegistraEnFlexCube)}</com1:seRegistraEnFlexCube>
                                        <com1:esPlantilla>{fn:data($Comision/com1:tipoComision/com1:esPlantilla)}</com1:esPlantilla>
                                        <com1:idComisionPrecarga>{fn:data($Comision/com1:tipoComision/com1:idComisionPrecarga)}</com1:idComisionPrecarga>
                                        <com1:fechaRegistro>{fn:data($Comision/com1:tipoComision/com1:fechaRegistro)}</com1:fechaRegistro>
                                        <com1:estado>{fn:data($Comision/com1:tipoComision/com1:estado)}</com1:estado>
                                        <com1:codigoExterno>{fn:data($Comision/com1:tipoComision/com1:codigoExterno)}</com1:codigoExterno>
                                    </com1:tipoComision>
                                else ()
                            }
                            {
                                if ($Comision/com1:moneda)
                                then 
                                    <com1:moneda>
                                        {
                                            if ($Comision/com1:moneda/cat:Id)
                                            then <cat:Id>{fn:data($Comision/com1:moneda/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:moneda/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Comision/com1:moneda/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:moneda/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Comision/com1:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:moneda/cat:estatus)
                                            then <cat:estatus>{fn:data($Comision/com1:moneda/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:moneda/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Comision/com1:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com1:moneda>
                                else ()
                            }
                            {
                                if ($Comision/com1:montoComision)
                                then <com1:montoComision>{fn:data($Comision/com1:montoComision)}</com1:montoComision>
                                else ()
                            }
                            {
                                if ($Comision/com1:montoBase)
                                then 
                                    <com1:montoBase>
                                        {
                                            if ($Comision/com1:montoBase/com1:idMontoBase)
                                            then <com1:idMontoBase>{fn:data($Comision/com1:montoBase/com1:idMontoBase)}</com1:idMontoBase>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:montoBase/com1:valorMontoBase)
                                            then <com1:valorMontoBase>{fn:data($Comision/com1:montoBase/com1:valorMontoBase)}</com1:valorMontoBase>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:montoBase/com1:porcentajeMontoBase)
                                            then <com1:porcentajeMontoBase>{fn:data($Comision/com1:montoBase/com1:porcentajeMontoBase)}</com1:porcentajeMontoBase>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:montoBase/com1:descripcionMontoBase)
                                            then <com1:descripcionMontoBase>{fn:data($Comision/com1:montoBase/com1:descripcionMontoBase)}</com1:descripcionMontoBase>
                                            else ()
                                        }
                                    </com1:montoBase>
                                else ()
                            }
                            {
                                if ($Comision/com1:fechaValor)
                                then <com1:fechaValor>{fn:data($Comision/com1:fechaValor)}</com1:fechaValor>
                                else ()
                            }
                            {
                                if ($Comision/com1:fechaVencimiento)
                                then <com1:fechaVencimiento>{fn:data($Comision/com1:fechaVencimiento)}</com1:fechaVencimiento>
                                else ()
                            }
                            {
                                if ($Comision/com1:fechaInicioCapital)
                                then <com1:fechaInicioCapital>{fn:data($Comision/com1:fechaInicioCapital)}</com1:fechaInicioCapital>
                                else ()
                            }
                            {
                                if ($Comision/com1:fechaInicioComision)
                                then <com1:fechaInicioComision>{fn:data($Comision/com1:fechaInicioComision)}</com1:fechaInicioComision>
                                else ()
                            }
                            {
                                if ($Comision/com1:tipoFrecuencia)
                                then 
                                    <com1:tipoFrecuencia>
                                        {
                                            if ($Comision/com1:tipoFrecuencia/cat:Id)
                                            then <cat:Id>{fn:data($Comision/com1:tipoFrecuencia/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:tipoFrecuencia/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Comision/com1:tipoFrecuencia/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:tipoFrecuencia/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Comision/com1:tipoFrecuencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:tipoFrecuencia/cat:estatus)
                                            then <cat:estatus>{fn:data($Comision/com1:tipoFrecuencia/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:tipoFrecuencia/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Comision/com1:tipoFrecuencia/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com1:tipoFrecuencia>
                                else ()
                            }
                            {
                                if ($Comision/com1:frecuenciaPago)
                                then <com1:frecuenciaPago>{fn:data($Comision/com1:frecuenciaPago)}</com1:frecuenciaPago>
                                else ()
                            }
                            {
                                if ($Comision/com1:fondo)
                                then <com1:fondo>{fn:data($Comision/com1:fondo)}</com1:fondo>
                                else ()
                            }
                            {
                                if ($Comision/com1:comisionCompartida)
                                then <com1:comisionCompartida>{fn:data($Comision/com1:comisionCompartida)}</com1:comisionCompartida>
                                else ()
                            }
                            {
                                if ($Comision/com1:codigoDesembolso)
                                then <com1:codigoDesembolso>{fn:data($Comision/com1:codigoDesembolso)}</com1:codigoDesembolso>
                                else ()
                            }
                            {
                                if ($Comision/com1:numeroTesoreria)
                                then <com1:numeroTesoreria>{fn:data($Comision/com1:numeroTesoreria)}</com1:numeroTesoreria>
                                else ()
                            }
                            {
                                if ($Comision/com1:codigoContrato)
                                then <com1:codigoContrato>{fn:data($Comision/com1:codigoContrato)}</com1:codigoContrato>
                                else ()
                            }
                            {
                                if ($Comision/com1:momentoCobro)
                                then 
                                    <com1:momentoCobro>
                                        {
                                            if ($Comision/com1:momentoCobro/cat:Id)
                                            then <cat:Id>{fn:data($Comision/com1:momentoCobro/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:momentoCobro/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Comision/com1:momentoCobro/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:momentoCobro/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Comision/com1:momentoCobro/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:momentoCobro/cat:estatus)
                                            then <cat:estatus>{fn:data($Comision/com1:momentoCobro/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:momentoCobro/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Comision/com1:momentoCobro/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com1:momentoCobro>
                                else ()
                            }
                            {
                                if ($Comision/com1:tipoTasa)
                                then 
                                    <com1:tipoTasa>
                                        {
                                            if ($Comision/com1:tipoTasa/cat:Id)
                                            then <cat:Id>{fn:data($Comision/com1:tipoTasa/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:tipoTasa/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Comision/com1:tipoTasa/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:tipoTasa/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Comision/com1:tipoTasa/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:tipoTasa/cat:estatus)
                                            then <cat:estatus>{fn:data($Comision/com1:tipoTasa/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:tipoTasa/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Comision/com1:tipoTasa/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com1:tipoTasa>
                                else ()
                            }
                            {
                                if ($Comision/com1:baseCalculo)
                                then 
                                    <com1:baseCalculo>
                                        {
                                            if ($Comision/com1:baseCalculo/cat:Id)
                                            then <cat:Id>{fn:data($Comision/com1:baseCalculo/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:baseCalculo/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Comision/com1:baseCalculo/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:baseCalculo/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Comision/com1:baseCalculo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:baseCalculo/cat:estatus)
                                            then <cat:estatus>{fn:data($Comision/com1:baseCalculo/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Comision/com1:baseCalculo/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Comision/com1:baseCalculo/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com1:baseCalculo>
                                else ()
                            }
                            {
                                if ($Comision/com1:responsableComision)
                                then <com1:responsableComision>{fn:data($Comision/com1:responsableComision)}</com1:responsableComision>
                                else ()
                            }
                            <com1:estadoTCC>
                                {
                                    if ($Comision/com1:estadoTCC/atr:id)
                                    then <atr:id>{fn:data($Comision/com1:estadoTCC/atr:id)}</atr:id>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:estadoTCC/atr:descripcion)
                                    then <atr:descripcion>{fn:data($Comision/com1:estadoTCC/atr:descripcion)}</atr:descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:estadoTCC/atr:descripcionCorta)
                                    then <atr:descripcionCorta>{fn:data($Comision/com1:estadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:estadoTCC/atr:estatus)
                                    then <atr:estatus>{fn:data($Comision/com1:estadoTCC/atr:estatus)}</atr:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:estadoTCC/atr:codigoExterno)
                                    then <atr:codigoExterno>{fn:data($Comision/com1:estadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:estadoTCC/atr:esSubEstado)
                                    then <atr:esSubEstado>{fn:data($Comision/com1:estadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                                    else ()
                                }
                            </com1:estadoTCC>
                            <com1:subEstadoTCC>
                                {
                                    if ($Comision/com1:subEstadoTCC/atr:id)
                                    then <atr:id>{fn:data($Comision/com1:subEstadoTCC/atr:id)}</atr:id>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:subEstadoTCC/atr:descripcion)
                                    then <atr:descripcion>{fn:data($Comision/com1:subEstadoTCC/atr:descripcion)}</atr:descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:subEstadoTCC/atr:descripcionCorta)
                                    then <atr:descripcionCorta>{fn:data($Comision/com1:subEstadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:subEstadoTCC/atr:estatus)
                                    then <atr:estatus>{fn:data($Comision/com1:subEstadoTCC/atr:estatus)}</atr:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:subEstadoTCC/atr:codigoExterno)
                                    then <atr:codigoExterno>{fn:data($Comision/com1:subEstadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:subEstadoTCC/atr:esSubEstado)
                                    then <atr:esSubEstado>{fn:data($Comision/com1:subEstadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                                    else ()
                                }
                            </com1:subEstadoTCC>
                            <com1:fechaRegistro>{fn:data($Comision/com1:fechaRegistro)}</com1:fechaRegistro>
                            <com1:estado>{fn:data($Comision/com1:estado)}</com1:estado>
                            <com1:comisionEnmendada>{fn:data($Comision/com1:comisionEnmendada)}</com1:comisionEnmendada>
                            <com1:fechaEnmienda>{fn:data($Comision/com1:fechaEnmienda)}</com1:fechaEnmienda>
                            {
                                if ($Comision/com1:banSugerida)
                                then <com1:banSugerida>{fn:data($Comision/com1:banSugerida)}</com1:banSugerida>
                                else ()
                            }
                            {
                                if ($Comision/com1:numeroCuentaCliente)
                                then <com1:numeroCuentaCliente>{fn:data($Comision/com1:numeroCuentaCliente)}</com1:numeroCuentaCliente>
                                else ()
                            }
                            {
                                if ($Comision/com1:observaciones)
                                then <com1:observaciones>{fn:data($Comision/com1:observaciones)}</com1:observaciones>
                                else ()
                            }
                            {
                                for $configAtributo in $Comision/com1:configAtributo
                                return 
                                <com1:configAtributo>
                                    {
                                        if ($configAtributo/atr:id)
                                        then <atr:id>{fn:data($configAtributo/atr:id)}</atr:id>
                                        else ()
                                    }
                                    <atr:columna>{fn:data($configAtributo/atr:columna)}</atr:columna>
                                    {
                                        if ($configAtributo/atr:ordenamiento)
                                        then <atr:ordenamiento>{fn:data($configAtributo/atr:ordenamiento)}</atr:ordenamiento>
                                        else ()
                                    }
                                    {
                                        if ($configAtributo/atr:soloLectura)
                                        then <atr:soloLectura>{fn:data($configAtributo/atr:soloLectura)}</atr:soloLectura>
                                        else ()
                                    }
                                    {
                                        if ($configAtributo/atr:esObligatorio)
                                        then <atr:esObligatorio>{fn:data($configAtributo/atr:esObligatorio)}</atr:esObligatorio>
                                        else ()
                                    }
                                    {
                                        if ($configAtributo/atr:etiqueta)
                                        then <atr:etiqueta>{fn:data($configAtributo/atr:etiqueta)}</atr:etiqueta>
                                        else ()
                                    }
                                    {
                                        for $valor in $configAtributo/atr:valor
                                        return 
                                        <atr:valor>{fn:data($configAtributo/atr:valor)}</atr:valor>
                                    }
                                    {
                                        if ($configAtributo/atr:tipoValor)
                                        then <atr:tipoValor>{fn:data($configAtributo/atr:tipoValor)}</atr:tipoValor>
                                        else ()
                                    }
                                    {
                                        for $catalogo in $configAtributo/atr:catalogo
                                        return 
                                        <atr:catalogo>
                                            {
                                                if ($catalogo/cat:Id)
                                                then <cat:Id>{fn:data($catalogo/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($catalogo/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($catalogo/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($catalogo/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($catalogo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($catalogo/cat:estatus)
                                                then <cat:estatus>{fn:data($catalogo/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($catalogo/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($catalogo/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </atr:catalogo>
                                    }
                                </com1:configAtributo>
                            }
                            {
                                if ($Comision/com1:Accion)
                                then <com1:Accion>{fn:data($Comision/com1:Accion)}</com1:Accion>
                                else ()
                            }
                        </des:Comision>
                    }
                    {
                        for $Transferencia in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:Transferencia
                        return 
                        <des:Transferencia>
                            <des:idTransferencia>{fn:data($Transferencia/des:idTransferencia)}</des:idTransferencia>
                            <des:idDesembolso>{fn:data($Transferencia/des:idDesembolso)}</des:idDesembolso>
                            {
                                if ($Transferencia/des:idFacturador)
                                then <des:idFacturador>{fn:data($Transferencia/des:idFacturador)}</des:idFacturador>
                                else ()
                            }
                            <des:Monto>
                                <com:tipo>
                                    {
                                        if ($Transferencia/des:Monto/com:tipo/cat:Id)
                                        then <cat:Id>{fn:data($Transferencia/des:Monto/com:tipo/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des:Monto/com:tipo/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Transferencia/des:Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des:Monto/com:tipo/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Transferencia/des:Monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des:Monto/com:tipo/cat:estatus)
                                        then <cat:estatus>{fn:data($Transferencia/des:Monto/com:tipo/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des:Monto/com:tipo/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Transferencia/des:Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com:tipo>
                                {
                                    if ($Transferencia/des:Monto/com:importe)
                                    then <com:importe>{fn:data($Transferencia/des:Monto/com:importe)}</com:importe>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des:Monto/com:moneda)
                                    then 
                                        <com:moneda>
                                            {
                                                if ($Transferencia/des:Monto/com:moneda/cat:Id)
                                                then <cat:Id>{fn:data($Transferencia/des:Monto/com:moneda/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($Transferencia/des:Monto/com:moneda/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($Transferencia/des:Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($Transferencia/des:Monto/com:moneda/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($Transferencia/des:Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($Transferencia/des:Monto/com:moneda/cat:estatus)
                                                then <cat:estatus>{fn:data($Transferencia/des:Monto/com:moneda/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($Transferencia/des:Monto/com:moneda/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($Transferencia/des:Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </com:moneda>
                                    else ()
                                }
                            </des:Monto>
                            <des:FormaTransferencia>
                                {
                                    if ($Transferencia/des:FormaTransferencia/cat:Id)
                                    then <cat:Id>{fn:data($Transferencia/des:FormaTransferencia/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des:FormaTransferencia/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Transferencia/des:FormaTransferencia/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des:FormaTransferencia/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Transferencia/des:FormaTransferencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des:FormaTransferencia/cat:estatus)
                                    then <cat:estatus>{fn:data($Transferencia/des:FormaTransferencia/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des:FormaTransferencia/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Transferencia/des:FormaTransferencia/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </des:FormaTransferencia>
                            {
                                if ($Transferencia/des:referenciaMensaje)
                                then <des:referenciaMensaje>{fn:data($Transferencia/des:referenciaMensaje)}</des:referenciaMensaje>
                                else ()
                            }
                            {
                                if ($Transferencia/des:esConsolidada)
                                then <des:esConsolidada>{fn:data($Transferencia/des:esConsolidada)}</des:esConsolidada>
                                else ()
                            }
                            {
                                if ($Transferencia/des:esConsolidacionPadre)
                                then <des:esConsolidacionPadre>{fn:data($Transferencia/des:esConsolidacionPadre)}</des:esConsolidacionPadre>
                                else ()
                            }
                            {
                                if ($Transferencia/des:idConsolidacionPadre)
                                then <des:idConsolidacionPadre>{fn:data($Transferencia/des:idConsolidacionPadre)}</des:idConsolidacionPadre>
                                else ()
                            }
                            {
                                if ($Transferencia/des:bhqTransferencia)
                                then <des:bhqTransferencia>{fn:data($Transferencia/des:bhqTransferencia)}</des:bhqTransferencia>
                                else ()
                            }
                            {
                                if ($Transferencia/des:NumeroReserva)
                                then <des:NumeroReserva>{fn:data($Transferencia/des:NumeroReserva)}</des:NumeroReserva>
                                else ()
                            }
                            {
                                if ($Transferencia/des:idBancoTransferencia)
                                then <des:idBancoTransferencia>{fn:data($Transferencia/des:idBancoTransferencia)}</des:idBancoTransferencia>
                                else ()
                            }
                            {
                                if ($Transferencia/des:numeroCuenta)
                                then <des:numeroCuenta>{fn:data($Transferencia/des:numeroCuenta)}</des:numeroCuenta>
                                else ()
                            }
                            <des:nombreCuenta>{fn:data($Transferencia/des:nombreCuenta)}</des:nombreCuenta>
                            {
                                if ($Transferencia/des:nombreBanco)
                                then <des:nombreBanco>{fn:data($Transferencia/des:nombreBanco)}</des:nombreBanco>
                                else ()
                            }
                            <des:idOperacion>{fn:data($Transferencia/des:idOperacion)}</des:idOperacion>
                            <des:tipoMensaje>{fn:data($Transferencia/des:tipoMensaje)}</des:tipoMensaje>
                            {
                                if ($Transferencia/des:estado)
                                then <des:estado>{fn:data($Transferencia/des:estado)}</des:estado>
                                else ()
                            }
                            <des:fechaRegistro>{fn:data($Transferencia/des:fechaRegistro)}</des:fechaRegistro>
                            {
                                if ($Transferencia/des:Beneficiario)
                                then 
                                    <des:Beneficiario>
                                        {
                                            if ($Transferencia/des:Beneficiario/des:tipoOpcion)
                                            then <des:tipoOpcion>{fn:data($Transferencia/des:Beneficiario/des:tipoOpcion)}</des:tipoOpcion>
                                            else ()
                                        }
                                        {
                                            if ($Transferencia/des:Beneficiario/des:numeroCta)
                                            then <des:numeroCta>{fn:data($Transferencia/des:Beneficiario/des:numeroCta)}</des:numeroCta>
                                            else ()
                                        }
                                        {
                                            if ($Transferencia/des:Beneficiario/des:identificador)
                                            then <des:identificador>{fn:data($Transferencia/des:Beneficiario/des:identificador)}</des:identificador>
                                            else ()
                                        }
                                        {
                                            if ($Transferencia/des:Beneficiario/des:beneficiario)
                                            then <des:beneficiario>{fn:data($Transferencia/des:Beneficiario/des:beneficiario)}</des:beneficiario>
                                            else ()
                                        }
                                        {
                                            if ($Transferencia/des:Beneficiario/des:direccion)
                                            then <des:direccion>{fn:data($Transferencia/des:Beneficiario/des:direccion)}</des:direccion>
                                            else ()
                                        }
                                    </des:Beneficiario>
                                else ()
                            }
                            {
                                if ($Transferencia/des:Banco)
                                then 
                                    <des:Banco>
                                        {
                                            if ($Transferencia/des:Banco/des:tipoOpcion)
                                            then <des:tipoOpcion>{fn:data($Transferencia/des:Banco/des:tipoOpcion)}</des:tipoOpcion>
                                            else ()
                                        }
                                        {
                                            if ($Transferencia/des:Banco/des:numeroCta)
                                            then <des:numeroCta>{fn:data($Transferencia/des:Banco/des:numeroCta)}</des:numeroCta>
                                            else ()
                                        }
                                        {
                                            if ($Transferencia/des:Banco/des:identificador)
                                            then <des:identificador>{fn:data($Transferencia/des:Banco/des:identificador)}</des:identificador>
                                            else ()
                                        }
                                        {
                                            if ($Transferencia/des:Banco/des:beneficiario)
                                            then <des:beneficiario>{fn:data($Transferencia/des:Banco/des:beneficiario)}</des:beneficiario>
                                            else ()
                                        }
                                        {
                                            if ($Transferencia/des:Banco/des:direccion)
                                            then <des:direccion>{fn:data($Transferencia/des:Banco/des:direccion)}</des:direccion>
                                            else ()
                                        }
                                    </des:Banco>
                                else ()
                            }
                            {
                                if ($Transferencia/des:Intermediario)
                                then 
                                    <des:Intermediario>
                                        {
                                            if ($Transferencia/des:Intermediario/des:tipoOpcion)
                                            then <des:tipoOpcion>{fn:data($Transferencia/des:Intermediario/des:tipoOpcion)}</des:tipoOpcion>
                                            else ()
                                        }
                                        {
                                            if ($Transferencia/des:Intermediario/des:numeroCta)
                                            then <des:numeroCta>{fn:data($Transferencia/des:Intermediario/des:numeroCta)}</des:numeroCta>
                                            else ()
                                        }
                                        {
                                            if ($Transferencia/des:Intermediario/des:identificador)
                                            then <des:identificador>{fn:data($Transferencia/des:Intermediario/des:identificador)}</des:identificador>
                                            else ()
                                        }
                                        {
                                            if ($Transferencia/des:Intermediario/des:beneficiario)
                                            then <des:beneficiario>{fn:data($Transferencia/des:Intermediario/des:beneficiario)}</des:beneficiario>
                                            else ()
                                        }
                                        {
                                            if ($Transferencia/des:Intermediario/des:direccion)
                                            then <des:direccion>{fn:data($Transferencia/des:Intermediario/des:direccion)}</des:direccion>
                                            else ()
                                        }
                                    </des:Intermediario>
                                else ()
                            }
                        </des:Transferencia>
                    }
                    {
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:TransferenciaFT05)
                        then 
                            <des:TransferenciaFT05>
                                <des:idTransferenciaFT05>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:TransferenciaFT05/des:idTransferenciaFT05)}</des:idTransferenciaFT05>
                                <des:idDesembolso>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:TransferenciaFT05/des:idDesembolso)}</des:idDesembolso>
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:TransferenciaFT05/des:idFacturador)
                                    then <des:idFacturador>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:TransferenciaFT05/des:idFacturador)}</des:idFacturador>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:TransferenciaFT05/des:FechaEfectiva)
                                    then <des:FechaEfectiva>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:ContratoDesembolso/des:TransferenciaFT05/des:FechaEfectiva)}</des:FechaEfectiva>
                                    else ()
                                }
                            </des:TransferenciaFT05>
                        else ()
                    }
                        <des:TransferenciaRecursos>
                            <des:idTransferencia></des:idTransferencia>
                            <des:idDesembolso></des:idDesembolso>
                            <des:idFacturador></des:idFacturador>
                            <des:idLineaPasiva></des:idLineaPasiva>
                            <des:Monto>
                                <com:tipo>
                                    <cat:Id></cat:Id>
                                    <cat:Descripcion></cat:Descripcion>
                                    <cat:DescripcionCorta></cat:DescripcionCorta>
                                    <cat:estatus></cat:estatus>
                                    <cat:codigoExterno></cat:codigoExterno>
                                </com:tipo>
                                <com:importe></com:importe>
                                <com:moneda>
                                    <cat:Id></cat:Id>
                                    <cat:Descripcion></cat:Descripcion>
                                    <cat:DescripcionCorta></cat:DescripcionCorta>
                                    <cat:estatus></cat:estatus>
                                    <cat:codigoExterno></cat:codigoExterno>
                                </com:moneda>
                            </des:Monto>
                            <des:fecha></des:fecha>
                            <des:fechaEfectiva></des:fechaEfectiva>
                            <des:FormaTransferencia>
                                <cat:Id></cat:Id>
                                <cat:Descripcion></cat:Descripcion>
                                <cat:DescripcionCorta></cat:DescripcionCorta>
                                <cat:estatus></cat:estatus>
                                <cat:codigoExterno></cat:codigoExterno>
                            </des:FormaTransferencia>
                            <des:idBanco></des:idBanco>
                            <des:nombreBanco></des:nombreBanco>
                            <des:numeroCuenta></des:numeroCuenta>
                            <des:nombreCuenta></des:nombreCuenta>
                            <des:estatus></des:estatus>
                            <des:fechaRegistro></des:fechaRegistro>
                        </des:TransferenciaRecursos>
                        <des:HerramientaCE>
                            <her:ActividadEconomica>
                                <cat:Id></cat:Id>
                                <cat:Descripcion></cat:Descripcion>
                                <cat:DescripcionCorta></cat:DescripcionCorta>
                                <cat:estatus></cat:estatus>
                                <cat:codigoExterno></cat:codigoExterno>
                            </her:ActividadEconomica>
                            <her:EjeEstrategico>
                                <cat:Id></cat:Id>
                                <cat:Descripcion></cat:Descripcion>
                                <cat:DescripcionCorta></cat:DescripcionCorta>
                                <cat:estatus></cat:estatus>
                                <cat:codigoExterno></cat:codigoExterno>
                            </her:EjeEstrategico>
                            <her:AreaFocalizacion>
                                <cat:Id></cat:Id>
                                <cat:Descripcion></cat:Descripcion>
                                <cat:DescripcionCorta></cat:DescripcionCorta>
                                <cat:estatus></cat:estatus>
                                <cat:codigoExterno></cat:codigoExterno>
                            </her:AreaFocalizacion>
                        </des:HerramientaCE>
                        <des:modalidadFinan>
                            <cat:Id></cat:Id>
                            <cat:Descripcion></cat:Descripcion>
                            <cat:DescripcionCorta></cat:DescripcionCorta>
                            <cat:estatus></cat:estatus>
                            <cat:codigoExterno></cat:codigoExterno>
                        </des:modalidadFinan>
                    </con:ContratoDesembolso>
                    <con:EstadoTCC>
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EstadoTCC/cat:Id)
                            then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EstadoTCC/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EstadoTCC/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EstadoTCC/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EstadoTCC/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EstadoTCC/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EstadoTCC/cat:estatus)
                            then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EstadoTCC/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EstadoTCC/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EstadoTCC/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </con:EstadoTCC>
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EventoCondicion)
                    then 
                        <con:EventoCondicion>
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EventoCondicion/cat:Id)
                                then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EventoCondicion/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EventoCondicion/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EventoCondicion/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EventoCondicion/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EventoCondicion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EventoCondicion/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EventoCondicion/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EventoCondicion/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:EventoCondicion/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </con:EventoCondicion>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:agrupador)
                    then <con:agrupador>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:agrupador)}</con:agrupador>
                    else ()
                }
                <con:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:estatus)}</con:estatus>
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:subEstadoTCC)
                    then 
                        <con:subEstadoTCC>
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:subEstadoTCC/cat:Id)
                                then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:subEstadoTCC/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:subEstadoTCC/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:subEstadoTCC/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:subEstadoTCC/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:subEstadoTCC/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:subEstadoTCC/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:subEstadoTCC/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:subEstadoTCC/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:subEstadoTCC/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </con:subEstadoTCC>
                    else ()
                }
                <con:enProceso>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:Cumplimientos/con:enProceso)}</con:enProceso>
                </con:Cumplimientos>
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoDesembolso)
                    then 
                        <con:tipoDesembolso>
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoDesembolso/cat:Id)
                                then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoDesembolso/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoDesembolso/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoDesembolso/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoDesembolso/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoDesembolso/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoDesembolso/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoDesembolso/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoDesembolso/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Condicion/con:tipoDesembolso/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </con:tipoDesembolso>
                    else ()
                }
              
               
            </lin:Condicion>
			
			
			
			
			{
                for $Termino in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Termino
                return 
                <lin:Termino>
                    <ter:idTermino>{fn:data($Termino/ter:idTermino)}</ter:idTermino>
                    <ter:operacion>{fn:data($Termino/ter:operacion)}</ter:operacion>
                    <ter:nombre>{fn:data($Termino/ter:nombre)}</ter:nombre>
                    <ter:descripcion>{fn:data($Termino/ter:descripcion)}</ter:descripcion>
                    <ter:tipoTermino>
                        {
                            if ($Termino/ter:tipoTermino/ter:idCatTermino)
                            then <ter:idCatTermino>{fn:data($Termino/ter:tipoTermino/ter:idCatTermino)}</ter:idCatTermino>
                            else ()
                        }
                        <ter:descripcion>{fn:data($Termino/ter:tipoTermino/ter:descripcion)}</ter:descripcion>
                        <ter:descripcionCorta>{fn:data($Termino/ter:tipoTermino/ter:descripcionCorta)}</ter:descripcionCorta>
                        {
                            if ($Termino/ter:tipoTermino/ter:idTipoTermino)
                            then <ter:idTipoTermino>{fn:data($Termino/ter:tipoTermino/ter:idTipoTermino)}</ter:idTipoTermino>
                            else ()
                        }
                        <ter:esEditableEnFormalizacion>{fn:data($Termino/ter:tipoTermino/ter:esEditableEnFormalizacion)}</ter:esEditableEnFormalizacion>
                        <ter:requiereValidarCOFI>{fn:data($Termino/ter:tipoTermino/ter:requiereValidarCOFI)}</ter:requiereValidarCOFI>
                        <ter:sePuedeDispensar>{fn:data($Termino/ter:tipoTermino/ter:sePuedeDispensar)}</ter:sePuedeDispensar>
                        <ter:esPlantilla>{fn:data($Termino/ter:tipoTermino/ter:esPlantilla)}</ter:esPlantilla>
                        <ter:requiereOGC>{fn:data($Termino/ter:tipoTermino/ter:requiereOGC)}</ter:requiereOGC>
                        <ter:idTerminoPrecarga>{fn:data($Termino/ter:tipoTermino/ter:idTerminoPrecarga)}</ter:idTerminoPrecarga>
                        <ter:fechaRegistro>{fn:data($Termino/ter:tipoTermino/ter:fechaRegistro)}</ter:fechaRegistro>
                        <ter:estado>{fn:data($Termino/ter:tipoTermino/ter:estado)}</ter:estado>
                        <ter:codigoExterno>{fn:data($Termino/ter:tipoTermino/ter:codigoExterno)}</ter:codigoExterno>
                    </ter:tipoTermino>
                    <ter:tipoFechaInicio>
                        {
                            if ($Termino/ter:tipoFechaInicio/cat:Id)
                            then <cat:Id>{fn:data($Termino/ter:tipoFechaInicio/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFechaInicio/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Termino/ter:tipoFechaInicio/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFechaInicio/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Termino/ter:tipoFechaInicio/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFechaInicio/cat:estatus)
                            then <cat:estatus>{fn:data($Termino/ter:tipoFechaInicio/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFechaInicio/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Termino/ter:tipoFechaInicio/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ter:tipoFechaInicio>
                    <ter:fechaInicio>{fn:data($Termino/ter:fechaInicio)}</ter:fechaInicio>
                    <ter:plazo>{fn:data($Termino/ter:plazo)}</ter:plazo>
                    <ter:frecuenciaPlazo>
                        {
                            if ($Termino/ter:frecuenciaPlazo/cat:Id)
                            then <cat:Id>{fn:data($Termino/ter:frecuenciaPlazo/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Termino/ter:frecuenciaPlazo/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Termino/ter:frecuenciaPlazo/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Termino/ter:frecuenciaPlazo/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Termino/ter:frecuenciaPlazo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Termino/ter:frecuenciaPlazo/cat:estatus)
                            then <cat:estatus>{fn:data($Termino/ter:frecuenciaPlazo/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Termino/ter:frecuenciaPlazo/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Termino/ter:frecuenciaPlazo/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ter:frecuenciaPlazo>
                    <ter:fechaVencimiento>{fn:data($Termino/ter:fechaVencimiento)}</ter:fechaVencimiento>
                    <ter:moneda>
                        {
                            if ($Termino/ter:moneda/cat:Id)
                            then <cat:Id>{fn:data($Termino/ter:moneda/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Termino/ter:moneda/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Termino/ter:moneda/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Termino/ter:moneda/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Termino/ter:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Termino/ter:moneda/cat:estatus)
                            then <cat:estatus>{fn:data($Termino/ter:moneda/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Termino/ter:moneda/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Termino/ter:moneda/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ter:moneda>
                    <ter:monto>{fn:data($Termino/ter:monto)}</ter:monto>
                    <ter:tasa>{fn:data($Termino/ter:tasa)}</ter:tasa>
                    <ter:tipoTasa>
                        {
                            if ($Termino/ter:tipoTasa/cat:Id)
                            then <cat:Id>{fn:data($Termino/ter:tipoTasa/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoTasa/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Termino/ter:tipoTasa/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoTasa/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Termino/ter:tipoTasa/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoTasa/cat:estatus)
                            then <cat:estatus>{fn:data($Termino/ter:tipoTasa/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoTasa/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Termino/ter:tipoTasa/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ter:tipoTasa>
                    <ter:fecha>{fn:data($Termino/ter:fecha)}</ter:fecha>
                    <ter:frecuenciaRevision>{fn:data($Termino/ter:frecuenciaRevision)}</ter:frecuenciaRevision>
                    <ter:tipoFrecuenciaRevision>
                        {
                            if ($Termino/ter:tipoFrecuenciaRevision/cat:Id)
                            then <cat:Id>{fn:data($Termino/ter:tipoFrecuenciaRevision/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaRevision/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Termino/ter:tipoFrecuenciaRevision/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaRevision/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Termino/ter:tipoFrecuenciaRevision/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaRevision/cat:estatus)
                            then <cat:estatus>{fn:data($Termino/ter:tipoFrecuenciaRevision/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaRevision/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Termino/ter:tipoFrecuenciaRevision/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ter:tipoFrecuenciaRevision>
                    <ter:fechaInicioRevision>{fn:data($Termino/ter:fechaInicioRevision)}</ter:fechaInicioRevision>
                    <ter:frecuenciaPagoInteres>{fn:data($Termino/ter:frecuenciaPagoInteres)}</ter:frecuenciaPagoInteres>
                    <ter:tipoFrecuenciaPagoInteres>
                        {
                            if ($Termino/ter:tipoFrecuenciaPagoInteres/cat:Id)
                            then <cat:Id>{fn:data($Termino/ter:tipoFrecuenciaPagoInteres/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaPagoInteres/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Termino/ter:tipoFrecuenciaPagoInteres/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaPagoInteres/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Termino/ter:tipoFrecuenciaPagoInteres/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaPagoInteres/cat:estatus)
                            then <cat:estatus>{fn:data($Termino/ter:tipoFrecuenciaPagoInteres/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaPagoInteres/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Termino/ter:tipoFrecuenciaPagoInteres/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ter:tipoFrecuenciaPagoInteres>
                    <ter:fechaInicioPagoInteres>{fn:data($Termino/ter:fechaInicioPagoInteres)}</ter:fechaInicioPagoInteres>
                    <ter:frecuenciaAmortizacion>{fn:data($Termino/ter:frecuenciaAmortizacion)}</ter:frecuenciaAmortizacion>
                    <ter:tipoFrecuenciaAmortizacion>
                        {
                            if ($Termino/ter:tipoFrecuenciaAmortizacion/cat:Id)
                            then <cat:Id>{fn:data($Termino/ter:tipoFrecuenciaAmortizacion/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaAmortizacion/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Termino/ter:tipoFrecuenciaAmortizacion/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaAmortizacion/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Termino/ter:tipoFrecuenciaAmortizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaAmortizacion/cat:estatus)
                            then <cat:estatus>{fn:data($Termino/ter:tipoFrecuenciaAmortizacion/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Termino/ter:tipoFrecuenciaAmortizacion/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Termino/ter:tipoFrecuenciaAmortizacion/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </ter:tipoFrecuenciaAmortizacion>
                    <ter:mora>{fn:data($Termino/ter:mora)}</ter:mora>
                    <ter:porcentajeCobertura>{fn:data($Termino/ter:porcentajeCobertura)}</ter:porcentajeCobertura>
                    <ter:seAplicanRecursosConcesion>{fn:data($Termino/ter:seAplicanRecursosConcesion)}</ter:seAplicanRecursosConcesion>
                    <ter:seAplicanRecursosExternos>{fn:data($Termino/ter:seAplicanRecursosExternos)}</ter:seAplicanRecursosExternos>
                    <ter:tipoContraparte>{fn:data($Termino/ter:tipoContraparte)}</ter:tipoContraparte>
                    <ter:montoMinimoDesembolso>{fn:data($Termino/ter:montoMinimoDesembolso)}</ter:montoMinimoDesembolso>
                    <ter:montoMaximoDesembolso>{fn:data($Termino/ter:montoMaximoDesembolso)}</ter:montoMaximoDesembolso>
                    <ter:tasaMinimaDesembolso>{fn:data($Termino/ter:tasaMinimaDesembolso)}</ter:tasaMinimaDesembolso>
                    <ter:tasaMaximaDesembolso>{fn:data($Termino/ter:tasaMaximaDesembolso)}</ter:tasaMaximaDesembolso>
                    <ter:estadoTCC>
                        {
                            if ($Termino/ter:estadoTCC/atr:id)
                            then <atr:id>{fn:data($Termino/ter:estadoTCC/atr:id)}</atr:id>
                            else ()
                        }
                        {
                            if ($Termino/ter:estadoTCC/atr:descripcion)
                            then <atr:descripcion>{fn:data($Termino/ter:estadoTCC/atr:descripcion)}</atr:descripcion>
                            else ()
                        }
                        {
                            if ($Termino/ter:estadoTCC/atr:descripcionCorta)
                            then <atr:descripcionCorta>{fn:data($Termino/ter:estadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                            else ()
                        }
                        {
                            if ($Termino/ter:estadoTCC/atr:estatus)
                            then <atr:estatus>{fn:data($Termino/ter:estadoTCC/atr:estatus)}</atr:estatus>
                            else ()
                        }
                        {
                            if ($Termino/ter:estadoTCC/atr:codigoExterno)
                            then <atr:codigoExterno>{fn:data($Termino/ter:estadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                            else ()
                        }
                        {
                            if ($Termino/ter:estadoTCC/atr:esSubEstado)
                            then <atr:esSubEstado>{fn:data($Termino/ter:estadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                            else ()
                        }
                    </ter:estadoTCC>
                    <ter:subEstadoTCC>
                        {
                            if ($Termino/ter:subEstadoTCC/atr:id)
                            then <atr:id>{fn:data($Termino/ter:subEstadoTCC/atr:id)}</atr:id>
                            else ()
                        }
                        {
                            if ($Termino/ter:subEstadoTCC/atr:descripcion)
                            then <atr:descripcion>{fn:data($Termino/ter:subEstadoTCC/atr:descripcion)}</atr:descripcion>
                            else ()
                        }
                        {
                            if ($Termino/ter:subEstadoTCC/atr:descripcionCorta)
                            then <atr:descripcionCorta>{fn:data($Termino/ter:subEstadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                            else ()
                        }
                        {
                            if ($Termino/ter:subEstadoTCC/atr:estatus)
                            then <atr:estatus>{fn:data($Termino/ter:subEstadoTCC/atr:estatus)}</atr:estatus>
                            else ()
                        }
                        {
                            if ($Termino/ter:subEstadoTCC/atr:codigoExterno)
                            then <atr:codigoExterno>{fn:data($Termino/ter:subEstadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                            else ()
                        }
                        {
                            if ($Termino/ter:subEstadoTCC/atr:esSubEstado)
                            then <atr:esSubEstado>{fn:data($Termino/ter:subEstadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                            else ()
                        }
                    </ter:subEstadoTCC>
                    <ter:fechaRegistro>{fn:data($Termino/ter:fechaRegistro)}</ter:fechaRegistro>
                    <ter:estado>{fn:data($Termino/ter:estado)}</ter:estado>
                    <ter:terminoEnmendado>{fn:data($Termino/ter:terminoEnmendado)}</ter:terminoEnmendado>
                    <ter:fechaEnmienda>{fn:data($Termino/ter:fechaEnmienda)}</ter:fechaEnmienda>
                    <ter:porcentajeModificacion>{fn:data($Termino/ter:porcentajeModificacion)}</ter:porcentajeModificacion>
                    <ter:idTcaTipoAvance>{fn:data($Termino/ter:idTcaTipoAvance)}</ter:idTcaTipoAvance>
                    <ter:idTcaTipoPorcentaje>{fn:data($Termino/ter:idTcaTipoPorcentaje)}</ter:idTcaTipoPorcentaje>
                    <ter:porcentaje>{fn:data($Termino/ter:porcentaje)}</ter:porcentaje>
                    <ter:porcentajeInicial>{fn:data($Termino/ter:porcentajeInicial)}</ter:porcentajeInicial>
                    <ter:porcentajeFinal>{fn:data($Termino/ter:porcentajeFinal)}</ter:porcentajeFinal>
                    {
                        if ($Termino/ter:requiereFondoPresupuestario)
                        then <ter:requiereFondoPresupuestario>{fn:data($Termino/ter:requiereFondoPresupuestario)}</ter:requiereFondoPresupuestario>
                        else ()
                    }
                    {
                        for $configAtributo in $Termino/ter:configAtributo
                        return 
                        <ter:configAtributo>
                            {
                                if ($configAtributo/atr:id)
                                then <atr:id>{fn:data($configAtributo/atr:id)}</atr:id>
                                else ()
                            }
                            <atr:columna>{fn:data($configAtributo/atr:columna)}</atr:columna>
                            {
                                if ($configAtributo/atr:ordenamiento)
                                then <atr:ordenamiento>{fn:data($configAtributo/atr:ordenamiento)}</atr:ordenamiento>
                                else ()
                            }
                            {
                                if ($configAtributo/atr:soloLectura)
                                then <atr:soloLectura>{fn:data($configAtributo/atr:soloLectura)}</atr:soloLectura>
                                else ()
                            }
                            {
                                if ($configAtributo/atr:esObligatorio)
                                then <atr:esObligatorio>{fn:data($configAtributo/atr:esObligatorio)}</atr:esObligatorio>
                                else ()
                            }
                            {
                                if ($configAtributo/atr:etiqueta)
                                then <atr:etiqueta>{fn:data($configAtributo/atr:etiqueta)}</atr:etiqueta>
                                else ()
                            }
                            {
                                for $valor in $configAtributo/atr:valor
                                return 
                                <atr:valor>{fn:data($configAtributo/atr:valor)}</atr:valor>
                            }
                            {
                                if ($configAtributo/atr:tipoValor)
                                then <atr:tipoValor>{fn:data($configAtributo/atr:tipoValor)}</atr:tipoValor>
                                else ()
                            }
                            {
                                for $catalogo in $configAtributo/atr:catalogo
                                return 
                                <atr:catalogo>
                                    {
                                        if ($catalogo/cat:Id)
                                        then <cat:Id>{fn:data($catalogo/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($catalogo/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($catalogo/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($catalogo/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($catalogo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($catalogo/cat:estatus)
                                        then <cat:estatus>{fn:data($catalogo/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($catalogo/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($catalogo/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </atr:catalogo>
                            }
                        </ter:configAtributo>
                    }
                    {
                        if ($Termino/ter:Accion)
                        then <ter:Accion>{fn:data($Termino/ter:Accion)}</ter:Accion>
                        else ()
                    }
                    {
                        for $Contraparte in $Termino/ter:Contraparte
                        return 
                        <ter:Contraparte>
                            {
                                if ($Contraparte/atr:id)
                                then <atr:id>{fn:data($Contraparte/atr:id)}</atr:id>
                                else ()
                            }
                            {
                                if ($Contraparte/atr:descripcion)
                                then <atr:descripcion>{fn:data($Contraparte/atr:descripcion)}</atr:descripcion>
                                else ()
                            }
                            {
                                if ($Contraparte/atr:estatus)
                                then <atr:estatus>{fn:data($Contraparte/atr:estatus)}</atr:estatus>
                                else ()
                            }
                        </ter:Contraparte>
                    }
                </lin:Termino>
            }

			
			
			{
                for $Comision in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Comision
                return 
                <lin:Comision>
                    <com1:idComision>{fn:data($Comision/com1:idComision)}</com1:idComision>
                    <com1:idOperacion>{fn:data($Comision/com1:idOperacion)}</com1:idOperacion>
                    {
                        if ($Comision/com1:nombre)
                        then <com1:nombre>{fn:data($Comision/com1:nombre)}</com1:nombre>
                        else ()
                    }
                    {
                        if ($Comision/com1:descripcion)
                        then <com1:descripcion>{fn:data($Comision/com1:descripcion)}</com1:descripcion>
                        else ()
                    }
                    {
                        if ($Comision/com1:tipoComision)
                        then 
                            <com1:tipoComision>
                                {
                                    if ($Comision/com1:tipoComision/com1:idCatComision)
                                    then <com1:idCatComision>{fn:data($Comision/com1:tipoComision/com1:idCatComision)}</com1:idCatComision>
                                    else ()
                                }
                                <com1:descripcion>{fn:data($Comision/com1:tipoComision/com1:descripcion)}</com1:descripcion>
                                <com1:descripcionCorta>{fn:data($Comision/com1:tipoComision/com1:descripcionCorta)}</com1:descripcionCorta>
                                {
                                    if ($Comision/com1:tipoComision/com1:idTipoComision)
                                    then 
                                        <com1:idTipoComision>
                                            {
                                                if ($Comision/com1:tipoComision/com1:idTipoComision/cat:Id)
                                                then <cat:Id>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($Comision/com1:tipoComision/com1:idTipoComision/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($Comision/com1:tipoComision/com1:idTipoComision/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($Comision/com1:tipoComision/com1:idTipoComision/cat:estatus)
                                                then <cat:estatus>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($Comision/com1:tipoComision/com1:idTipoComision/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </com1:idTipoComision>
                                    else ()
                                }
                                <com1:esEditableEnFormalizacion>{fn:data($Comision/com1:tipoComision/com1:esEditableEnFormalizacion)}</com1:esEditableEnFormalizacion>
                                <com1:requiereValidarCOFI>{fn:data($Comision/com1:tipoComision/com1:requiereValidarCOFI)}</com1:requiereValidarCOFI>
                                <com1:sePuedeDispensar>{fn:data($Comision/com1:tipoComision/com1:sePuedeDispensar)}</com1:sePuedeDispensar>
                                <com1:seRegistraEnFlexCube>{fn:data($Comision/com1:tipoComision/com1:seRegistraEnFlexCube)}</com1:seRegistraEnFlexCube>
                                <com1:esPlantilla>{fn:data($Comision/com1:tipoComision/com1:esPlantilla)}</com1:esPlantilla>
                                <com1:idComisionPrecarga>{fn:data($Comision/com1:tipoComision/com1:idComisionPrecarga)}</com1:idComisionPrecarga>
                                <com1:fechaRegistro>{fn:data($Comision/com1:tipoComision/com1:fechaRegistro)}</com1:fechaRegistro>
                                <com1:estado>{fn:data($Comision/com1:tipoComision/com1:estado)}</com1:estado>
                                <com1:codigoExterno>{fn:data($Comision/com1:tipoComision/com1:codigoExterno)}</com1:codigoExterno>
                            </com1:tipoComision>
                        else ()
                    }
                    {
                        if ($Comision/com1:moneda)
                        then 
                            <com1:moneda>
                                {
                                    if ($Comision/com1:moneda/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com1:moneda/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:moneda/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com1:moneda/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:moneda/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com1:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:moneda/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com1:moneda/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:moneda/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com1:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com1:moneda>
                        else ()
                    }
                    {
                        if ($Comision/com1:montoComision)
                        then <com1:montoComision>{fn:data($Comision/com1:montoComision)}</com1:montoComision>
                        else ()
                    }
                    {
                        if ($Comision/com1:montoBase)
                        then 
                            <com1:montoBase>
                                {
                                    if ($Comision/com1:montoBase/com1:idMontoBase)
                                    then <com1:idMontoBase>{fn:data($Comision/com1:montoBase/com1:idMontoBase)}</com1:idMontoBase>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:montoBase/com1:valorMontoBase)
                                    then <com1:valorMontoBase>{fn:data($Comision/com1:montoBase/com1:valorMontoBase)}</com1:valorMontoBase>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:montoBase/com1:porcentajeMontoBase)
                                    then <com1:porcentajeMontoBase>{fn:data($Comision/com1:montoBase/com1:porcentajeMontoBase)}</com1:porcentajeMontoBase>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:montoBase/com1:descripcionMontoBase)
                                    then <com1:descripcionMontoBase>{fn:data($Comision/com1:montoBase/com1:descripcionMontoBase)}</com1:descripcionMontoBase>
                                    else ()
                                }
                            </com1:montoBase>
                        else ()
                    }
                    {
                        if ($Comision/com1:fechaValor)
                        then <com1:fechaValor>{fn:data($Comision/com1:fechaValor)}</com1:fechaValor>
                        else ()
                    }
                    {
                        if ($Comision/com1:fechaVencimiento)
                        then <com1:fechaVencimiento>{fn:data($Comision/com1:fechaVencimiento)}</com1:fechaVencimiento>
                        else ()
                    }
                    {
                        if ($Comision/com1:fechaInicioCapital)
                        then <com1:fechaInicioCapital>{fn:data($Comision/com1:fechaInicioCapital)}</com1:fechaInicioCapital>
                        else ()
                    }
                    {
                        if ($Comision/com1:fechaInicioComision)
                        then <com1:fechaInicioComision>{fn:data($Comision/com1:fechaInicioComision)}</com1:fechaInicioComision>
                        else ()
                    }
                    {
                        if ($Comision/com1:tipoFrecuencia)
                        then 
                            <com1:tipoFrecuencia>
                                {
                                    if ($Comision/com1:tipoFrecuencia/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com1:tipoFrecuencia/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:tipoFrecuencia/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com1:tipoFrecuencia/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:tipoFrecuencia/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com1:tipoFrecuencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:tipoFrecuencia/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com1:tipoFrecuencia/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:tipoFrecuencia/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com1:tipoFrecuencia/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com1:tipoFrecuencia>
                        else ()
                    }
                    {
                        if ($Comision/com1:frecuenciaPago)
                        then <com1:frecuenciaPago>{fn:data($Comision/com1:frecuenciaPago)}</com1:frecuenciaPago>
                        else ()
                    }
                    {
                        if ($Comision/com1:fondo)
                        then <com1:fondo>{fn:data($Comision/com1:fondo)}</com1:fondo>
                        else ()
                    }
                    {
                        if ($Comision/com1:comisionCompartida)
                        then <com1:comisionCompartida>{fn:data($Comision/com1:comisionCompartida)}</com1:comisionCompartida>
                        else ()
                    }
                    {
                        if ($Comision/com1:codigoDesembolso)
                        then <com1:codigoDesembolso>{fn:data($Comision/com1:codigoDesembolso)}</com1:codigoDesembolso>
                        else ()
                    }
                    {
                        if ($Comision/com1:numeroTesoreria)
                        then <com1:numeroTesoreria>{fn:data($Comision/com1:numeroTesoreria)}</com1:numeroTesoreria>
                        else ()
                    }
                    {
                        if ($Comision/com1:codigoContrato)
                        then <com1:codigoContrato>{fn:data($Comision/com1:codigoContrato)}</com1:codigoContrato>
                        else ()
                    }
                    {
                        if ($Comision/com1:momentoCobro)
                        then 
                            <com1:momentoCobro>
                                {
                                    if ($Comision/com1:momentoCobro/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com1:momentoCobro/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:momentoCobro/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com1:momentoCobro/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:momentoCobro/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com1:momentoCobro/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:momentoCobro/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com1:momentoCobro/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:momentoCobro/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com1:momentoCobro/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com1:momentoCobro>
                        else ()
                    }
                    {
                        if ($Comision/com1:tipoTasa)
                        then 
                            <com1:tipoTasa>
                                {
                                    if ($Comision/com1:tipoTasa/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com1:tipoTasa/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:tipoTasa/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com1:tipoTasa/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:tipoTasa/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com1:tipoTasa/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:tipoTasa/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com1:tipoTasa/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:tipoTasa/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com1:tipoTasa/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com1:tipoTasa>
                        else ()
                    }
                    {
                        if ($Comision/com1:baseCalculo)
                        then 
                            <com1:baseCalculo>
                                {
                                    if ($Comision/com1:baseCalculo/cat:Id)
                                    then <cat:Id>{fn:data($Comision/com1:baseCalculo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:baseCalculo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Comision/com1:baseCalculo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:baseCalculo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Comision/com1:baseCalculo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:baseCalculo/cat:estatus)
                                    then <cat:estatus>{fn:data($Comision/com1:baseCalculo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Comision/com1:baseCalculo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Comision/com1:baseCalculo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com1:baseCalculo>
                        else ()
                    }
                    {
                        if ($Comision/com1:responsableComision)
                        then <com1:responsableComision>{fn:data($Comision/com1:responsableComision)}</com1:responsableComision>
                        else ()
                    }
                    <com1:estadoTCC>
                        {
                            if ($Comision/com1:estadoTCC/atr:id)
                            then <atr:id>{fn:data($Comision/com1:estadoTCC/atr:id)}</atr:id>
                            else ()
                        }
                        {
                            if ($Comision/com1:estadoTCC/atr:descripcion)
                            then <atr:descripcion>{fn:data($Comision/com1:estadoTCC/atr:descripcion)}</atr:descripcion>
                            else ()
                        }
                        {
                            if ($Comision/com1:estadoTCC/atr:descripcionCorta)
                            then <atr:descripcionCorta>{fn:data($Comision/com1:estadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                            else ()
                        }
                        {
                            if ($Comision/com1:estadoTCC/atr:estatus)
                            then <atr:estatus>{fn:data($Comision/com1:estadoTCC/atr:estatus)}</atr:estatus>
                            else ()
                        }
                        {
                            if ($Comision/com1:estadoTCC/atr:codigoExterno)
                            then <atr:codigoExterno>{fn:data($Comision/com1:estadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                            else ()
                        }
                        {
                            if ($Comision/com1:estadoTCC/atr:esSubEstado)
                            then <atr:esSubEstado>{fn:data($Comision/com1:estadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                            else ()
                        }
                    </com1:estadoTCC>
                    <com1:subEstadoTCC>
                        {
                            if ($Comision/com1:subEstadoTCC/atr:id)
                            then <atr:id>{fn:data($Comision/com1:subEstadoTCC/atr:id)}</atr:id>
                            else ()
                        }
                        {
                            if ($Comision/com1:subEstadoTCC/atr:descripcion)
                            then <atr:descripcion>{fn:data($Comision/com1:subEstadoTCC/atr:descripcion)}</atr:descripcion>
                            else ()
                        }
                        {
                            if ($Comision/com1:subEstadoTCC/atr:descripcionCorta)
                            then <atr:descripcionCorta>{fn:data($Comision/com1:subEstadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                            else ()
                        }
                        {
                            if ($Comision/com1:subEstadoTCC/atr:estatus)
                            then <atr:estatus>{fn:data($Comision/com1:subEstadoTCC/atr:estatus)}</atr:estatus>
                            else ()
                        }
                        {
                            if ($Comision/com1:subEstadoTCC/atr:codigoExterno)
                            then <atr:codigoExterno>{fn:data($Comision/com1:subEstadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                            else ()
                        }
                        {
                            if ($Comision/com1:subEstadoTCC/atr:esSubEstado)
                            then <atr:esSubEstado>{fn:data($Comision/com1:subEstadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                            else ()
                        }
                    </com1:subEstadoTCC>
                    <com1:fechaRegistro>{fn:data($Comision/com1:fechaRegistro)}</com1:fechaRegistro>
                    <com1:estado>{fn:data($Comision/com1:estado)}</com1:estado>
                    <com1:comisionEnmendada>{fn:data($Comision/com1:comisionEnmendada)}</com1:comisionEnmendada>
                    <com1:fechaEnmienda>{fn:data($Comision/com1:fechaEnmienda)}</com1:fechaEnmienda>
                    {
                        if ($Comision/com1:banSugerida)
                        then <com1:banSugerida>{fn:data($Comision/com1:banSugerida)}</com1:banSugerida>
                        else ()
                    }
                    {
                        if ($Comision/com1:numeroCuentaCliente)
                        then <com1:numeroCuentaCliente>{fn:data($Comision/com1:numeroCuentaCliente)}</com1:numeroCuentaCliente>
                        else ()
                    }
                    {
                        if ($Comision/com1:observaciones)
                        then <com1:observaciones>{fn:data($Comision/com1:observaciones)}</com1:observaciones>
                        else ()
                    }
                    {
                        for $configAtributo in $Comision/com1:configAtributo
                        return 
                        <com1:configAtributo>
                            {
                                if ($configAtributo/atr:id)
                                then <atr:id>{fn:data($configAtributo/atr:id)}</atr:id>
                                else ()
                            }
                            <atr:columna>{fn:data($configAtributo/atr:columna)}</atr:columna>
                            {
                                if ($configAtributo/atr:ordenamiento)
                                then <atr:ordenamiento>{fn:data($configAtributo/atr:ordenamiento)}</atr:ordenamiento>
                                else ()
                            }
                            {
                                if ($configAtributo/atr:soloLectura)
                                then <atr:soloLectura>{fn:data($configAtributo/atr:soloLectura)}</atr:soloLectura>
                                else ()
                            }
                            {
                                if ($configAtributo/atr:esObligatorio)
                                then <atr:esObligatorio>{fn:data($configAtributo/atr:esObligatorio)}</atr:esObligatorio>
                                else ()
                            }
                            {
                                if ($configAtributo/atr:etiqueta)
                                then <atr:etiqueta>{fn:data($configAtributo/atr:etiqueta)}</atr:etiqueta>
                                else ()
                            }
                            {
                                for $valor in $configAtributo/atr:valor
                                return 
                                <atr:valor>{fn:data($configAtributo/atr:valor)}</atr:valor>
                            }
                            {
                                if ($configAtributo/atr:tipoValor)
                                then <atr:tipoValor>{fn:data($configAtributo/atr:tipoValor)}</atr:tipoValor>
                                else ()
                            }
                            {
                                for $catalogo in $configAtributo/atr:catalogo
                                return 
                                <atr:catalogo>
                                    {
                                        if ($catalogo/cat:Id)
                                        then <cat:Id>{fn:data($catalogo/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($catalogo/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($catalogo/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($catalogo/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($catalogo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($catalogo/cat:estatus)
                                        then <cat:estatus>{fn:data($catalogo/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($catalogo/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($catalogo/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </atr:catalogo>
                            }
                        </com1:configAtributo>
                    }
                    {
                        if ($Comision/com1:Accion)
                        then <com1:Accion>{fn:data($Comision/com1:Accion)}</com1:Accion>
                        else ()
                    }
                </lin:Comision>
            }
			
			
			{
                for $Fuente in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:Fuente
                return 
                <lin:Fuente>
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
                </lin:Fuente>
            }
			
			
			
			 <lin:ContratoDesembolso>
                <des:idDesembolso>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:idDesembolso)}</des:idDesembolso>
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:idFacturador)
                    then <des:idFacturador>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:idFacturador)}</des:idFacturador>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:producto)
                    then 
                        <des:producto>
                            <pro:idProducto>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/pro:idProducto)}</pro:idProducto>
                            <pro:descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/pro:descripcion)}</pro:descripcion>
                            <pro:descripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/pro:descripcionCorta)}</pro:descripcionCorta>
                            <pro:fechaRegistro>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/pro:fechaRegistro)}</pro:fechaRegistro>
                            <pro:codExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/pro:codExterno)}</pro:codExterno>
                            {
                                for $Componente in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:producto/des:Componente
                                return 
                                <des:Componente>
                                    {
                                        if ($Componente/cat:Id)
                                        then <cat:Id>{fn:data($Componente/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Componente/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Componente/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Componente/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Componente/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Componente/cat:estatus)
                                        then <cat:estatus>{fn:data($Componente/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Componente/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Componente/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                    <des:TipoComponente>
                                        {
                                            if ($Componente/des:TipoComponente/cat:Id)
                                            then <cat:Id>{fn:data($Componente/des:TipoComponente/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Componente/des:TipoComponente/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Componente/des:TipoComponente/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Componente/des:TipoComponente/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Componente/des:TipoComponente/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Componente/des:TipoComponente/cat:estatus)
                                            then <cat:estatus>{fn:data($Componente/des:TipoComponente/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Componente/des:TipoComponente/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Componente/des:TipoComponente/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </des:TipoComponente>
                                    <des:TipoTasa>
                                        {
                                            if ($Componente/des:TipoTasa/cat:Id)
                                            then <cat:Id>{fn:data($Componente/des:TipoTasa/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Componente/des:TipoTasa/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Componente/des:TipoTasa/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Componente/des:TipoTasa/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Componente/des:TipoTasa/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Componente/des:TipoTasa/cat:estatus)
                                            then <cat:estatus>{fn:data($Componente/des:TipoTasa/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Componente/des:TipoTasa/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Componente/des:TipoTasa/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </des:TipoTasa>
                                    <des:esPrincipal>{fn:data($Componente/des:esPrincipal)}</des:esPrincipal>
                                    {
                                        if ($Componente/des:Plazo)
                                        then 
                                            <des:Plazo>
                                                <com:Tipo>
                                                    {
                                                        if ($Componente/des:Plazo/com:Tipo/cat:Id)
                                                        then <cat:Id>{fn:data($Componente/des:Plazo/com:Tipo/cat:Id)}</cat:Id>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Componente/des:Plazo/com:Tipo/cat:Descripcion)
                                                        then <cat:Descripcion>{fn:data($Componente/des:Plazo/com:Tipo/cat:Descripcion)}</cat:Descripcion>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Componente/des:Plazo/com:Tipo/cat:DescripcionCorta)
                                                        then <cat:DescripcionCorta>{fn:data($Componente/des:Plazo/com:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Componente/des:Plazo/com:Tipo/cat:estatus)
                                                        then <cat:estatus>{fn:data($Componente/des:Plazo/com:Tipo/cat:estatus)}</cat:estatus>
                                                        else ()
                                                    }
                                                    {
                                                        if ($Componente/des:Plazo/com:Tipo/cat:codigoExterno)
                                                        then <cat:codigoExterno>{fn:data($Componente/des:Plazo/com:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                                        else ()
                                                    }
                                                </com:Tipo>
                                                <com:Valor>{fn:data($Componente/des:Plazo/com:Valor)}</com:Valor>
                                            </des:Plazo>
                                        else ()
                                    }
                                </des:Componente>
                            }
                        </des:producto>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:referencia)
                    then <des:referencia>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:referencia)}</des:referencia>
                    else ()
                }
                {
                    for $monto in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:monto
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
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:estado/cat:Id)
                        then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:estado/cat:Id)}</cat:Id>
                        else ()
                    }
                    {
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:estado/cat:Descripcion)
                        then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:estado/cat:Descripcion)}</cat:Descripcion>
                        else ()
                    }
                    {
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:estado/cat:DescripcionCorta)
                        then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:estado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                        else ()
                    }
                    {
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:estado/cat:estatus)
                        then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:estado/cat:estatus)}</cat:estatus>
                        else ()
                    }
                    {
                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:estado/cat:codigoExterno)
                        then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:estado/cat:codigoExterno)}</cat:codigoExterno>
                        else ()
                    }
                </des:estado>
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:programado)
                    then <des:programado>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:programado)}</des:programado>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaEstimadaDesembolsar)
                    then <des:fechaEstimadaDesembolsar>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaEstimadaDesembolsar)}</des:fechaEstimadaDesembolsar>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaEfectiva)
                    then <des:fechaEfectiva>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaEfectiva)}</des:fechaEfectiva>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaAsignacion)
                    then <des:fechaAsignacion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaAsignacion)}</des:fechaAsignacion>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaRegistro)
                    then <des:fechaRegistro>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaRegistro)}</des:fechaRegistro>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaVencimiento)
                    then <des:fechaVencimiento>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaVencimiento)}</des:fechaVencimiento>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:estatus)
                    then <des:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:estatus)}</des:estatus>
                    else ()
                }
                <des:condicionesFinancieras>
                    <des:idCondicionFinanciera>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:idCondicionFinanciera)}</des:idCondicionFinanciera>
                    <des:tasa>
                        <des:tipo>
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:Id)
                                then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </des:tipo>
                        {
                            <des:fija>
                                    <des:valor></des:valor>
                                </des:fija>
                            }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora)
                            then 
                                <des:spreadMora>
                                    <des:valor>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:valor)}</des:valor>
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:codigo)
                                        then <des:codigo>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:codigo)}</des:codigo>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:revision)
                                        then 
                                            <des:revision>
                                                <des:FechaInicio>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:revision/des:FechaInicio)}</des:FechaInicio>
                                                <des:Frecuencia>
                                                    <com:Tipo>
                                                        {
                                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:revision/des:Frecuencia/com:Tipo/cat:Id)
                                                            then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:revision/des:Frecuencia/com:Tipo/cat:Id)}</cat:Id>
                                                            else ()
                                                        }
                                                        {
                                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:revision/des:Frecuencia/com:Tipo/cat:Descripcion)
                                                            then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:revision/des:Frecuencia/com:Tipo/cat:Descripcion)}</cat:Descripcion>
                                                            else ()
                                                        }
                                                        {
                                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:revision/des:Frecuencia/com:Tipo/cat:DescripcionCorta)
                                                            then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:revision/des:Frecuencia/com:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                            else ()
                                                        }
                                                        {
                                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:revision/des:Frecuencia/com:Tipo/cat:estatus)
                                                            then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:revision/des:Frecuencia/com:Tipo/cat:estatus)}</cat:estatus>
                                                            else ()
                                                        }
                                                        {
                                                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:revision/des:Frecuencia/com:Tipo/cat:codigoExterno)
                                                            then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:revision/des:Frecuencia/com:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                                            else ()
                                                        }
                                                    </com:Tipo>
                                                    <com:Valor>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:revision/des:Frecuencia/com:Valor)}</com:Valor>
                                                </des:Frecuencia>
                                            </des:revision>
                                        else ()
                                    }
                                </des:spreadMora>
                            else ()
                        }
                    </des:tasa>
                    <des:fondo>
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Id)
                            then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:estatus)
                            then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:fondo/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                        {
                            for $Validador in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:fondo/des:Validador
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
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:Id)
                            then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:estatus)
                            then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </des:baseCalculo>
                    <des:principal>
                        <des:FechaInicio>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:FechaInicio)}</des:FechaInicio>
                        <des:Frecuencia>
                            <com:Tipo>
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com:Tipo>
                            <com:Valor>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Valor)}</com:Valor>
                        </des:Frecuencia>
                        <des:fechaVencimiento>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:fechaVencimiento)}</des:fechaVencimiento>
                    </des:principal>
                    <des:interes>
                        <des:FechaInicio>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:FechaInicio)}</des:FechaInicio>
                        <des:Frecuencia>
                            <com:Tipo>
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com:Tipo>
                            <com:Valor>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Valor)}</com:Valor>
                        </des:Frecuencia>
                    </des:interes>
                    <des:periodoGracia>
                        <des:FechaInicio>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:FechaInicio)}</des:FechaInicio>
                        <des:Frecuencia>
                            <com:Tipo>
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:Id)
                                    then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com:Tipo>
                            <com:Valor>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Valor)}</com:Valor>
                        </des:Frecuencia>
                    </des:periodoGracia>
                    <des:tratamientoDiasFeriados>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tratamientoDiasFeriados)}</des:tratamientoDiasFeriados>
                    <des:tipoCalendario>
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:Id)
                            then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:estatus)
                            then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:tipoCalendario/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                    </des:tipoCalendario>
                    <des:moverEntreMeses>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:moverEntreMeses)}</des:moverEntreMeses>
                    {
                        for $calendarioComplejo in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:condicionesFinancieras/des:calendarioComplejo
                        return 
                        <des:calendarioComplejo>
                            <des:TipoPlan>
                                {
                                    if ($calendarioComplejo/des:TipoPlan/cat:Id)
                                    then <cat:Id>{fn:data($calendarioComplejo/des:TipoPlan/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($calendarioComplejo/des:TipoPlan/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($calendarioComplejo/des:TipoPlan/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($calendarioComplejo/des:TipoPlan/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($calendarioComplejo/des:TipoPlan/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($calendarioComplejo/des:TipoPlan/cat:estatus)
                                    then <cat:estatus>{fn:data($calendarioComplejo/des:TipoPlan/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($calendarioComplejo/des:TipoPlan/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($calendarioComplejo/des:TipoPlan/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </des:TipoPlan>
                            <des:Frecuencia>
                                <des:FechaInicio>{fn:data($calendarioComplejo/des:Frecuencia/des:FechaInicio)}</des:FechaInicio>
                                <des:Frecuencia>
                                    <com:Tipo>
                                        {
                                            if ($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:Id)
                                            then <cat:Id>{fn:data($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:estatus)
                                            then <cat:estatus>{fn:data($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Tipo/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com:Tipo>
                                    <com:Valor>{fn:data($calendarioComplejo/des:Frecuencia/des:Frecuencia/com:Valor)}</com:Valor>
                                </des:Frecuencia>
                            </des:Frecuencia>
                            <des:Monto>
                                <com:tipo>
                                    {
                                        if ($calendarioComplejo/des:Monto/com:tipo/cat:Id)
                                        then <cat:Id>{fn:data($calendarioComplejo/des:Monto/com:tipo/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($calendarioComplejo/des:Monto/com:tipo/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($calendarioComplejo/des:Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($calendarioComplejo/des:Monto/com:tipo/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($calendarioComplejo/des:Monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($calendarioComplejo/des:Monto/com:tipo/cat:estatus)
                                        then <cat:estatus>{fn:data($calendarioComplejo/des:Monto/com:tipo/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($calendarioComplejo/des:Monto/com:tipo/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($calendarioComplejo/des:Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com:tipo>
                                {
                                    if ($calendarioComplejo/des:Monto/com:importe)
                                    then <com:importe>{fn:data($calendarioComplejo/des:Monto/com:importe)}</com:importe>
                                    else ()
                                }
                                {
                                    if ($calendarioComplejo/des:Monto/com:moneda)
                                    then 
                                        <com:moneda>
                                            {
                                                if ($calendarioComplejo/des:Monto/com:moneda/cat:Id)
                                                then <cat:Id>{fn:data($calendarioComplejo/des:Monto/com:moneda/cat:Id)}</cat:Id>
                                                else ()
                                            }
                                            {
                                                if ($calendarioComplejo/des:Monto/com:moneda/cat:Descripcion)
                                                then <cat:Descripcion>{fn:data($calendarioComplejo/des:Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                                else ()
                                            }
                                            {
                                                if ($calendarioComplejo/des:Monto/com:moneda/cat:DescripcionCorta)
                                                then <cat:DescripcionCorta>{fn:data($calendarioComplejo/des:Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                else ()
                                            }
                                            {
                                                if ($calendarioComplejo/des:Monto/com:moneda/cat:estatus)
                                                then <cat:estatus>{fn:data($calendarioComplejo/des:Monto/com:moneda/cat:estatus)}</cat:estatus>
                                                else ()
                                            }
                                            {
                                                if ($calendarioComplejo/des:Monto/com:moneda/cat:codigoExterno)
                                                then <cat:codigoExterno>{fn:data($calendarioComplejo/des:Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                                else ()
                                            }
                                        </com:moneda>
                                    else ()
                                }
                            </des:Monto>
                            <des:NumeroCuotas>{fn:data($calendarioComplejo/des:NumeroCuotas)}</des:NumeroCuotas>
                        </des:calendarioComplejo>
                    }
                </des:condicionesFinancieras>
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:idLinea)
                    then <des:idLinea>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:idLinea)}</des:idLinea>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:recursosExternos)
                    then <des:recursosExternos>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:recursosExternos)}</des:recursosExternos>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:cuentaCliente)
                    then <des:cuentaCliente>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:cuentaCliente)}</des:cuentaCliente>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:usuario)
                    then <des:usuario>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:usuario)}</des:usuario>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaDisponibilidadFondos)
                    then <des:fechaDisponibilidadFondos>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaDisponibilidadFondos)}</des:fechaDisponibilidadFondos>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:origenTransferenciaCte)
                    then <des:origenTransferenciaCte>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:origenTransferenciaCte)}</des:origenTransferenciaCte>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa)
                    then 
                        <des:Programa>
                            <her:LineaFinanciera>
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:Id)
                                    then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:estatus)
                                    then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:LineaFinanciera/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:LineaFinanciera>
                            <her:DestinoFinanciamiento>
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:Id)
                                    then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:estatus)
                                    then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:DestinoFinanciamiento/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:DestinoFinanciamiento>
                            <her:ModalidadFinanciamiento>
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:Id)
                                    then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:estatus)
                                    then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ModalidadFinanciamiento/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:ModalidadFinanciamiento>
                            <her:HerramientaCE>
                                <her:ActividadEconomica>
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </her:ActividadEconomica>
                                <her:EjeEstrategico>
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </her:EjeEstrategico>
                                <her:AreaFocalizacion>
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </her:AreaFocalizacion>
                            </her:HerramientaCE>
                            <her:ClasificacionGeneral>
                                <her:SectorMercado>
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorMercado/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </her:SectorMercado>
                                <her:SectorInstitucional>
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Id)
                                        then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:estatus)
                                        then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Programa/her:ClasificacionGeneral/her:SectorInstitucional/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </her:SectorInstitucional>
                            </her:ClasificacionGeneral>
                        </des:Programa>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:EstimadoDesembolso)
                    then 
                        <des:EstimadoDesembolso>
                            <des:Plazo>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:EstimadoDesembolso/des:Plazo)}</des:Plazo>
                            <des:Frecuencia>
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:Id)
                                    then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:estatus)
                                    then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </des:Frecuencia>
                        </des:EstimadoDesembolso>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaInicioProceso)
                    then <des:fechaInicioProceso>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaInicioProceso)}</des:fechaInicioProceso>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaEstimadaDisponibilidad)
                    then <des:fechaEstimadaDisponibilidad>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:fechaEstimadaDisponibilidad)}</des:fechaEstimadaDisponibilidad>
                    else ()
                }
                {
                    for $Utilizacion in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Utilizacion
                    return 
                    <des:Utilizacion>
                        <lin:idFuente>{fn:data($Utilizacion/lin:idFuente)}</lin:idFuente>
                        <lin:idLineaCredito>{fn:data($Utilizacion/lin:idLineaCredito)}</lin:idLineaCredito>
                        <lin:idLineaPasiva>{fn:data($Utilizacion/lin:idLineaPasiva)}</lin:idLineaPasiva>
                        <lin:codigoLineaPasiva>{fn:data($Utilizacion/lin:codigoLineaPasiva)}</lin:codigoLineaPasiva>
                        <lin:idFacturadorLineaPasiva>{fn:data($Utilizacion/lin:idFacturadorLineaPasiva)}</lin:idFacturadorLineaPasiva>
                        <lin:idFondo>{fn:data($Utilizacion/lin:idFondo)}</lin:idFondo>
                        <lin:Descripcion>{fn:data($Utilizacion/lin:Descripcion)}</lin:Descripcion>
                        <lin:FechaObtenido>{fn:data($Utilizacion/lin:FechaObtenido)}</lin:FechaObtenido>
                        <lin:MontoAsignado>{fn:data($Utilizacion/lin:MontoAsignado)}</lin:MontoAsignado>
                        <lin:montoDisponible>{fn:data($Utilizacion/lin:montoDisponible)}</lin:montoDisponible>
                        {
                            for $Monto in $Utilizacion/lin:Monto
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
                        <lin:NumeroAsignacion>{fn:data($Utilizacion/lin:NumeroAsignacion)}</lin:NumeroAsignacion>
                        <lin:Comentario>{fn:data($Utilizacion/lin:Comentario)}</lin:Comentario>
                        <lin:idContrato>{fn:data($Utilizacion/lin:idContrato)}</lin:idContrato>
                        <lin:FechaRegistro>{fn:data($Utilizacion/lin:FechaRegistro)}</lin:FechaRegistro>
                        <lin:Estado>{fn:data($Utilizacion/lin:Estado)}</lin:Estado>
                        <lin:esExterna>{fn:data($Utilizacion/lin:esExterna)}</lin:esExterna>
                    </des:Utilizacion>
                }
                {
                    for $Cargo in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Cargo
                    return 
                    <des:Cargo>
                        {
                            if ($Cargo/cat:Id)
                            then <cat:Id>{fn:data($Cargo/cat:Id)}</cat:Id>
                            else ()
                        }
                        {
                            if ($Cargo/cat:Descripcion)
                            then <cat:Descripcion>{fn:data($Cargo/cat:Descripcion)}</cat:Descripcion>
                            else ()
                        }
                        {
                            if ($Cargo/cat:DescripcionCorta)
                            then <cat:DescripcionCorta>{fn:data($Cargo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                            else ()
                        }
                        {
                            if ($Cargo/cat:estatus)
                            then <cat:estatus>{fn:data($Cargo/cat:estatus)}</cat:estatus>
                            else ()
                        }
                        {
                            if ($Cargo/cat:codigoExterno)
                            then <cat:codigoExterno>{fn:data($Cargo/cat:codigoExterno)}</cat:codigoExterno>
                            else ()
                        }
                        <des:Monto>
                            <com:tipo>
                                {
                                    if ($Cargo/des:Monto/com:tipo/cat:Id)
                                    then <cat:Id>{fn:data($Cargo/des:Monto/com:tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Cargo/des:Monto/com:tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Cargo/des:Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Cargo/des:Monto/com:tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Cargo/des:Monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Cargo/des:Monto/com:tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($Cargo/des:Monto/com:tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Cargo/des:Monto/com:tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Cargo/des:Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com:tipo>
                            {
                                if ($Cargo/des:Monto/com:importe)
                                then <com:importe>{fn:data($Cargo/des:Monto/com:importe)}</com:importe>
                                else ()
                            }
                            {
                                if ($Cargo/des:Monto/com:moneda)
                                then 
                                    <com:moneda>
                                        {
                                            if ($Cargo/des:Monto/com:moneda/cat:Id)
                                            then <cat:Id>{fn:data($Cargo/des:Monto/com:moneda/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Cargo/des:Monto/com:moneda/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Cargo/des:Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Cargo/des:Monto/com:moneda/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Cargo/des:Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Cargo/des:Monto/com:moneda/cat:estatus)
                                            then <cat:estatus>{fn:data($Cargo/des:Monto/com:moneda/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Cargo/des:Monto/com:moneda/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Cargo/des:Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com:moneda>
                                else ()
                            }
                        </des:Monto>
                        <des:FechaRegistro>{fn:data($Cargo/des:FechaRegistro)}</des:FechaRegistro>
                        {
                            if ($Cargo/des:Status)
                            then <des:Status>{fn:data($Cargo/des:Status)}</des:Status>
                            else ()
                        }
                        {
                            if ($Cargo/des:SoloLectura)
                            then <des:SoloLectura>{fn:data($Cargo/des:SoloLectura)}</des:SoloLectura>
                            else ()
                        }
                        {
                            if ($Cargo/des:TCC)
                            then 
                                <des:TCC>
                                    <mat:id>{fn:data($Cargo/des:TCC/mat:id)}</mat:id>
                                    <mat:tipo>{fn:data($Cargo/des:TCC/mat:tipo)}</mat:tipo>
                                    <mat:estado>{fn:data($Cargo/des:TCC/mat:estado)}</mat:estado>
                                    <mat:subEstado>{fn:data($Cargo/des:TCC/mat:subEstado)}</mat:subEstado>
                                </des:TCC>
                            else ()
                        }
                    </des:Cargo>
                }
                {
                    for $Comision in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Comision
                    return 
                    <des:Comision>
                        <com1:idComision>{fn:data($Comision/com1:idComision)}</com1:idComision>
                        <com1:idOperacion>{fn:data($Comision/com1:idOperacion)}</com1:idOperacion>
                        {
                            if ($Comision/com1:nombre)
                            then <com1:nombre>{fn:data($Comision/com1:nombre)}</com1:nombre>
                            else ()
                        }
                        {
                            if ($Comision/com1:descripcion)
                            then <com1:descripcion>{fn:data($Comision/com1:descripcion)}</com1:descripcion>
                            else ()
                        }
                        {
                            if ($Comision/com1:tipoComision)
                            then 
                                <com1:tipoComision>
                                    {
                                        if ($Comision/com1:tipoComision/com1:idCatComision)
                                        then <com1:idCatComision>{fn:data($Comision/com1:tipoComision/com1:idCatComision)}</com1:idCatComision>
                                        else ()
                                    }
                                    <com1:descripcion>{fn:data($Comision/com1:tipoComision/com1:descripcion)}</com1:descripcion>
                                    <com1:descripcionCorta>{fn:data($Comision/com1:tipoComision/com1:descripcionCorta)}</com1:descripcionCorta>
                                    {
                                        if ($Comision/com1:tipoComision/com1:idTipoComision)
                                        then 
                                            <com1:idTipoComision>
                                                {
                                                    if ($Comision/com1:tipoComision/com1:idTipoComision/cat:Id)
                                                    then <cat:Id>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:Id)}</cat:Id>
                                                    else ()
                                                }
                                                {
                                                    if ($Comision/com1:tipoComision/com1:idTipoComision/cat:Descripcion)
                                                    then <cat:Descripcion>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:Descripcion)}</cat:Descripcion>
                                                    else ()
                                                }
                                                {
                                                    if ($Comision/com1:tipoComision/com1:idTipoComision/cat:DescripcionCorta)
                                                    then <cat:DescripcionCorta>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                                    else ()
                                                }
                                                {
                                                    if ($Comision/com1:tipoComision/com1:idTipoComision/cat:estatus)
                                                    then <cat:estatus>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:estatus)}</cat:estatus>
                                                    else ()
                                                }
                                                {
                                                    if ($Comision/com1:tipoComision/com1:idTipoComision/cat:codigoExterno)
                                                    then <cat:codigoExterno>{fn:data($Comision/com1:tipoComision/com1:idTipoComision/cat:codigoExterno)}</cat:codigoExterno>
                                                    else ()
                                                }
                                            </com1:idTipoComision>
                                        else ()
                                    }
                                    <com1:esEditableEnFormalizacion>{fn:data($Comision/com1:tipoComision/com1:esEditableEnFormalizacion)}</com1:esEditableEnFormalizacion>
                                    <com1:requiereValidarCOFI>{fn:data($Comision/com1:tipoComision/com1:requiereValidarCOFI)}</com1:requiereValidarCOFI>
                                    <com1:sePuedeDispensar>{fn:data($Comision/com1:tipoComision/com1:sePuedeDispensar)}</com1:sePuedeDispensar>
                                    <com1:seRegistraEnFlexCube>{fn:data($Comision/com1:tipoComision/com1:seRegistraEnFlexCube)}</com1:seRegistraEnFlexCube>
                                    <com1:esPlantilla>{fn:data($Comision/com1:tipoComision/com1:esPlantilla)}</com1:esPlantilla>
                                    <com1:idComisionPrecarga>{fn:data($Comision/com1:tipoComision/com1:idComisionPrecarga)}</com1:idComisionPrecarga>
                                    <com1:fechaRegistro>{fn:data($Comision/com1:tipoComision/com1:fechaRegistro)}</com1:fechaRegistro>
                                    <com1:estado>{fn:data($Comision/com1:tipoComision/com1:estado)}</com1:estado>
                                    <com1:codigoExterno>{fn:data($Comision/com1:tipoComision/com1:codigoExterno)}</com1:codigoExterno>
                                </com1:tipoComision>
                            else ()
                        }
                        {
                            if ($Comision/com1:moneda)
                            then 
                                <com1:moneda>
                                    {
                                        if ($Comision/com1:moneda/cat:Id)
                                        then <cat:Id>{fn:data($Comision/com1:moneda/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:moneda/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Comision/com1:moneda/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:moneda/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Comision/com1:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:moneda/cat:estatus)
                                        then <cat:estatus>{fn:data($Comision/com1:moneda/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:moneda/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Comision/com1:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com1:moneda>
                            else ()
                        }
                        {
                            if ($Comision/com1:montoComision)
                            then <com1:montoComision>{fn:data($Comision/com1:montoComision)}</com1:montoComision>
                            else ()
                        }
                        {
                            if ($Comision/com1:montoBase)
                            then 
                                <com1:montoBase>
                                    {
                                        if ($Comision/com1:montoBase/com1:idMontoBase)
                                        then <com1:idMontoBase>{fn:data($Comision/com1:montoBase/com1:idMontoBase)}</com1:idMontoBase>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:montoBase/com1:valorMontoBase)
                                        then <com1:valorMontoBase>{fn:data($Comision/com1:montoBase/com1:valorMontoBase)}</com1:valorMontoBase>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:montoBase/com1:porcentajeMontoBase)
                                        then <com1:porcentajeMontoBase>{fn:data($Comision/com1:montoBase/com1:porcentajeMontoBase)}</com1:porcentajeMontoBase>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:montoBase/com1:descripcionMontoBase)
                                        then <com1:descripcionMontoBase>{fn:data($Comision/com1:montoBase/com1:descripcionMontoBase)}</com1:descripcionMontoBase>
                                        else ()
                                    }
                                </com1:montoBase>
                            else ()
                        }
                        {
                            if ($Comision/com1:fechaValor)
                            then <com1:fechaValor>{fn:data($Comision/com1:fechaValor)}</com1:fechaValor>
                            else ()
                        }
                        {
                            if ($Comision/com1:fechaVencimiento)
                            then <com1:fechaVencimiento>{fn:data($Comision/com1:fechaVencimiento)}</com1:fechaVencimiento>
                            else ()
                        }
                        {
                            if ($Comision/com1:fechaInicioCapital)
                            then <com1:fechaInicioCapital>{fn:data($Comision/com1:fechaInicioCapital)}</com1:fechaInicioCapital>
                            else ()
                        }
                        {
                            if ($Comision/com1:fechaInicioComision)
                            then <com1:fechaInicioComision>{fn:data($Comision/com1:fechaInicioComision)}</com1:fechaInicioComision>
                            else ()
                        }
                        {
                            if ($Comision/com1:tipoFrecuencia)
                            then 
                                <com1:tipoFrecuencia>
                                    {
                                        if ($Comision/com1:tipoFrecuencia/cat:Id)
                                        then <cat:Id>{fn:data($Comision/com1:tipoFrecuencia/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:tipoFrecuencia/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Comision/com1:tipoFrecuencia/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:tipoFrecuencia/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Comision/com1:tipoFrecuencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:tipoFrecuencia/cat:estatus)
                                        then <cat:estatus>{fn:data($Comision/com1:tipoFrecuencia/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:tipoFrecuencia/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Comision/com1:tipoFrecuencia/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com1:tipoFrecuencia>
                            else ()
                        }
                        {
                            if ($Comision/com1:frecuenciaPago)
                            then <com1:frecuenciaPago>{fn:data($Comision/com1:frecuenciaPago)}</com1:frecuenciaPago>
                            else ()
                        }
                        {
                            if ($Comision/com1:fondo)
                            then <com1:fondo>{fn:data($Comision/com1:fondo)}</com1:fondo>
                            else ()
                        }
                        {
                            if ($Comision/com1:comisionCompartida)
                            then <com1:comisionCompartida>{fn:data($Comision/com1:comisionCompartida)}</com1:comisionCompartida>
                            else ()
                        }
                        {
                            if ($Comision/com1:codigoDesembolso)
                            then <com1:codigoDesembolso>{fn:data($Comision/com1:codigoDesembolso)}</com1:codigoDesembolso>
                            else ()
                        }
                        {
                            if ($Comision/com1:numeroTesoreria)
                            then <com1:numeroTesoreria>{fn:data($Comision/com1:numeroTesoreria)}</com1:numeroTesoreria>
                            else ()
                        }
                        {
                            if ($Comision/com1:codigoContrato)
                            then <com1:codigoContrato>{fn:data($Comision/com1:codigoContrato)}</com1:codigoContrato>
                            else ()
                        }
                        {
                            if ($Comision/com1:momentoCobro)
                            then 
                                <com1:momentoCobro>
                                    {
                                        if ($Comision/com1:momentoCobro/cat:Id)
                                        then <cat:Id>{fn:data($Comision/com1:momentoCobro/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:momentoCobro/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Comision/com1:momentoCobro/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:momentoCobro/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Comision/com1:momentoCobro/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:momentoCobro/cat:estatus)
                                        then <cat:estatus>{fn:data($Comision/com1:momentoCobro/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:momentoCobro/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Comision/com1:momentoCobro/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com1:momentoCobro>
                            else ()
                        }
                        {
                            if ($Comision/com1:tipoTasa)
                            then 
                                <com1:tipoTasa>
                                    {
                                        if ($Comision/com1:tipoTasa/cat:Id)
                                        then <cat:Id>{fn:data($Comision/com1:tipoTasa/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:tipoTasa/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Comision/com1:tipoTasa/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:tipoTasa/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Comision/com1:tipoTasa/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:tipoTasa/cat:estatus)
                                        then <cat:estatus>{fn:data($Comision/com1:tipoTasa/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:tipoTasa/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Comision/com1:tipoTasa/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com1:tipoTasa>
                            else ()
                        }
                        {
                            if ($Comision/com1:baseCalculo)
                            then 
                                <com1:baseCalculo>
                                    {
                                        if ($Comision/com1:baseCalculo/cat:Id)
                                        then <cat:Id>{fn:data($Comision/com1:baseCalculo/cat:Id)}</cat:Id>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:baseCalculo/cat:Descripcion)
                                        then <cat:Descripcion>{fn:data($Comision/com1:baseCalculo/cat:Descripcion)}</cat:Descripcion>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:baseCalculo/cat:DescripcionCorta)
                                        then <cat:DescripcionCorta>{fn:data($Comision/com1:baseCalculo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:baseCalculo/cat:estatus)
                                        then <cat:estatus>{fn:data($Comision/com1:baseCalculo/cat:estatus)}</cat:estatus>
                                        else ()
                                    }
                                    {
                                        if ($Comision/com1:baseCalculo/cat:codigoExterno)
                                        then <cat:codigoExterno>{fn:data($Comision/com1:baseCalculo/cat:codigoExterno)}</cat:codigoExterno>
                                        else ()
                                    }
                                </com1:baseCalculo>
                            else ()
                        }
                        {
                            if ($Comision/com1:responsableComision)
                            then <com1:responsableComision>{fn:data($Comision/com1:responsableComision)}</com1:responsableComision>
                            else ()
                        }
                        <com1:estadoTCC>
                            {
                                if ($Comision/com1:estadoTCC/atr:id)
                                then <atr:id>{fn:data($Comision/com1:estadoTCC/atr:id)}</atr:id>
                                else ()
                            }
                            {
                                if ($Comision/com1:estadoTCC/atr:descripcion)
                                then <atr:descripcion>{fn:data($Comision/com1:estadoTCC/atr:descripcion)}</atr:descripcion>
                                else ()
                            }
                            {
                                if ($Comision/com1:estadoTCC/atr:descripcionCorta)
                                then <atr:descripcionCorta>{fn:data($Comision/com1:estadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                                else ()
                            }
                            {
                                if ($Comision/com1:estadoTCC/atr:estatus)
                                then <atr:estatus>{fn:data($Comision/com1:estadoTCC/atr:estatus)}</atr:estatus>
                                else ()
                            }
                            {
                                if ($Comision/com1:estadoTCC/atr:codigoExterno)
                                then <atr:codigoExterno>{fn:data($Comision/com1:estadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                                else ()
                            }
                            {
                                if ($Comision/com1:estadoTCC/atr:esSubEstado)
                                then <atr:esSubEstado>{fn:data($Comision/com1:estadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                                else ()
                            }
                        </com1:estadoTCC>
                        <com1:subEstadoTCC>
                            {
                                if ($Comision/com1:subEstadoTCC/atr:id)
                                then <atr:id>{fn:data($Comision/com1:subEstadoTCC/atr:id)}</atr:id>
                                else ()
                            }
                            {
                                if ($Comision/com1:subEstadoTCC/atr:descripcion)
                                then <atr:descripcion>{fn:data($Comision/com1:subEstadoTCC/atr:descripcion)}</atr:descripcion>
                                else ()
                            }
                            {
                                if ($Comision/com1:subEstadoTCC/atr:descripcionCorta)
                                then <atr:descripcionCorta>{fn:data($Comision/com1:subEstadoTCC/atr:descripcionCorta)}</atr:descripcionCorta>
                                else ()
                            }
                            {
                                if ($Comision/com1:subEstadoTCC/atr:estatus)
                                then <atr:estatus>{fn:data($Comision/com1:subEstadoTCC/atr:estatus)}</atr:estatus>
                                else ()
                            }
                            {
                                if ($Comision/com1:subEstadoTCC/atr:codigoExterno)
                                then <atr:codigoExterno>{fn:data($Comision/com1:subEstadoTCC/atr:codigoExterno)}</atr:codigoExterno>
                                else ()
                            }
                            {
                                if ($Comision/com1:subEstadoTCC/atr:esSubEstado)
                                then <atr:esSubEstado>{fn:data($Comision/com1:subEstadoTCC/atr:esSubEstado)}</atr:esSubEstado>
                                else ()
                            }
                        </com1:subEstadoTCC>
                        <com1:fechaRegistro>{fn:data($Comision/com1:fechaRegistro)}</com1:fechaRegistro>
                        <com1:estado>{fn:data($Comision/com1:estado)}</com1:estado>
                        <com1:comisionEnmendada>{fn:data($Comision/com1:comisionEnmendada)}</com1:comisionEnmendada>
                        <com1:fechaEnmienda>{fn:data($Comision/com1:fechaEnmienda)}</com1:fechaEnmienda>
                        {
                            if ($Comision/com1:banSugerida)
                            then <com1:banSugerida>{fn:data($Comision/com1:banSugerida)}</com1:banSugerida>
                            else ()
                        }
                        {
                            if ($Comision/com1:numeroCuentaCliente)
                            then <com1:numeroCuentaCliente>{fn:data($Comision/com1:numeroCuentaCliente)}</com1:numeroCuentaCliente>
                            else ()
                        }
                        {
                            if ($Comision/com1:observaciones)
                            then <com1:observaciones>{fn:data($Comision/com1:observaciones)}</com1:observaciones>
                            else ()
                        }
                        {
                            for $configAtributo in $Comision/com1:configAtributo
                            return 
                            <com1:configAtributo>
                                {
                                    if ($configAtributo/atr:id)
                                    then <atr:id>{fn:data($configAtributo/atr:id)}</atr:id>
                                    else ()
                                }
                                <atr:columna>{fn:data($configAtributo/atr:columna)}</atr:columna>
                                {
                                    if ($configAtributo/atr:ordenamiento)
                                    then <atr:ordenamiento>{fn:data($configAtributo/atr:ordenamiento)}</atr:ordenamiento>
                                    else ()
                                }
                                {
                                    if ($configAtributo/atr:soloLectura)
                                    then <atr:soloLectura>{fn:data($configAtributo/atr:soloLectura)}</atr:soloLectura>
                                    else ()
                                }
                                {
                                    if ($configAtributo/atr:esObligatorio)
                                    then <atr:esObligatorio>{fn:data($configAtributo/atr:esObligatorio)}</atr:esObligatorio>
                                    else ()
                                }
                                {
                                    if ($configAtributo/atr:etiqueta)
                                    then <atr:etiqueta>{fn:data($configAtributo/atr:etiqueta)}</atr:etiqueta>
                                    else ()
                                }
                                {
                                    for $valor in $configAtributo/atr:valor
                                    return 
                                    <atr:valor>{fn:data($configAtributo/atr:valor)}</atr:valor>
                                }
                                {
                                    if ($configAtributo/atr:tipoValor)
                                    then <atr:tipoValor>{fn:data($configAtributo/atr:tipoValor)}</atr:tipoValor>
                                    else ()
                                }
                                {
                                    for $catalogo in $configAtributo/atr:catalogo
                                    return 
                                    <atr:catalogo>
                                        {
                                            if ($catalogo/cat:Id)
                                            then <cat:Id>{fn:data($catalogo/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($catalogo/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($catalogo/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($catalogo/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($catalogo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($catalogo/cat:estatus)
                                            then <cat:estatus>{fn:data($catalogo/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($catalogo/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($catalogo/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </atr:catalogo>
                                }
                            </com1:configAtributo>
                        }
                        {
                            if ($Comision/com1:Accion)
                            then <com1:Accion>{fn:data($Comision/com1:Accion)}</com1:Accion>
                            else ()
                        }
                    </des:Comision>
                }
                {
                    for $Transferencia in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:Transferencia
                    return 
                    <des:Transferencia>
                        <des:idTransferencia>{fn:data($Transferencia/des:idTransferencia)}</des:idTransferencia>
                        <des:idDesembolso>{fn:data($Transferencia/des:idDesembolso)}</des:idDesembolso>
                        {
                            if ($Transferencia/des:idFacturador)
                            then <des:idFacturador>{fn:data($Transferencia/des:idFacturador)}</des:idFacturador>
                            else ()
                        }
                        <des:Monto>
                            <com:tipo>
                                {
                                    if ($Transferencia/des:Monto/com:tipo/cat:Id)
                                    then <cat:Id>{fn:data($Transferencia/des:Monto/com:tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des:Monto/com:tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($Transferencia/des:Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des:Monto/com:tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($Transferencia/des:Monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des:Monto/com:tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($Transferencia/des:Monto/com:tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($Transferencia/des:Monto/com:tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($Transferencia/des:Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com:tipo>
                            {
                                if ($Transferencia/des:Monto/com:importe)
                                then <com:importe>{fn:data($Transferencia/des:Monto/com:importe)}</com:importe>
                                else ()
                            }
                            {
                                if ($Transferencia/des:Monto/com:moneda)
                                then 
                                    <com:moneda>
                                        {
                                            if ($Transferencia/des:Monto/com:moneda/cat:Id)
                                            then <cat:Id>{fn:data($Transferencia/des:Monto/com:moneda/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($Transferencia/des:Monto/com:moneda/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($Transferencia/des:Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($Transferencia/des:Monto/com:moneda/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($Transferencia/des:Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($Transferencia/des:Monto/com:moneda/cat:estatus)
                                            then <cat:estatus>{fn:data($Transferencia/des:Monto/com:moneda/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($Transferencia/des:Monto/com:moneda/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($Transferencia/des:Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com:moneda>
                                else ()
                            }
                        </des:Monto>
                        <des:FormaTransferencia>
                            {
                                if ($Transferencia/des:FormaTransferencia/cat:Id)
                                then <cat:Id>{fn:data($Transferencia/des:FormaTransferencia/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($Transferencia/des:FormaTransferencia/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($Transferencia/des:FormaTransferencia/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($Transferencia/des:FormaTransferencia/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($Transferencia/des:FormaTransferencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($Transferencia/des:FormaTransferencia/cat:estatus)
                                then <cat:estatus>{fn:data($Transferencia/des:FormaTransferencia/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($Transferencia/des:FormaTransferencia/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($Transferencia/des:FormaTransferencia/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </des:FormaTransferencia>
                        {
                            if ($Transferencia/des:referenciaMensaje)
                            then <des:referenciaMensaje>{fn:data($Transferencia/des:referenciaMensaje)}</des:referenciaMensaje>
                            else ()
                        }
                        {
                            if ($Transferencia/des:esConsolidada)
                            then <des:esConsolidada>{fn:data($Transferencia/des:esConsolidada)}</des:esConsolidada>
                            else ()
                        }
                        {
                            if ($Transferencia/des:esConsolidacionPadre)
                            then <des:esConsolidacionPadre>{fn:data($Transferencia/des:esConsolidacionPadre)}</des:esConsolidacionPadre>
                            else ()
                        }
                        {
                            if ($Transferencia/des:idConsolidacionPadre)
                            then <des:idConsolidacionPadre>{fn:data($Transferencia/des:idConsolidacionPadre)}</des:idConsolidacionPadre>
                            else ()
                        }
                        {
                            if ($Transferencia/des:bhqTransferencia)
                            then <des:bhqTransferencia>{fn:data($Transferencia/des:bhqTransferencia)}</des:bhqTransferencia>
                            else ()
                        }
                        {
                            if ($Transferencia/des:NumeroReserva)
                            then <des:NumeroReserva>{fn:data($Transferencia/des:NumeroReserva)}</des:NumeroReserva>
                            else ()
                        }
                        {
                            if ($Transferencia/des:idBancoTransferencia)
                            then <des:idBancoTransferencia>{fn:data($Transferencia/des:idBancoTransferencia)}</des:idBancoTransferencia>
                            else ()
                        }
                        {
                            if ($Transferencia/des:numeroCuenta)
                            then <des:numeroCuenta>{fn:data($Transferencia/des:numeroCuenta)}</des:numeroCuenta>
                            else ()
                        }
                        <des:nombreCuenta>{fn:data($Transferencia/des:nombreCuenta)}</des:nombreCuenta>
                        {
                            if ($Transferencia/des:nombreBanco)
                            then <des:nombreBanco>{fn:data($Transferencia/des:nombreBanco)}</des:nombreBanco>
                            else ()
                        }
                        <des:idOperacion>{fn:data($Transferencia/des:idOperacion)}</des:idOperacion>
                        <des:tipoMensaje>{fn:data($Transferencia/des:tipoMensaje)}</des:tipoMensaje>
                        {
                            if ($Transferencia/des:estado)
                            then <des:estado>{fn:data($Transferencia/des:estado)}</des:estado>
                            else ()
                        }
                        <des:fechaRegistro>{fn:data($Transferencia/des:fechaRegistro)}</des:fechaRegistro>
                        {
                            if ($Transferencia/des:Beneficiario)
                            then 
                                <des:Beneficiario>
                                    {
                                        if ($Transferencia/des:Beneficiario/des:tipoOpcion)
                                        then <des:tipoOpcion>{fn:data($Transferencia/des:Beneficiario/des:tipoOpcion)}</des:tipoOpcion>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des:Beneficiario/des:numeroCta)
                                        then <des:numeroCta>{fn:data($Transferencia/des:Beneficiario/des:numeroCta)}</des:numeroCta>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des:Beneficiario/des:identificador)
                                        then <des:identificador>{fn:data($Transferencia/des:Beneficiario/des:identificador)}</des:identificador>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des:Beneficiario/des:beneficiario)
                                        then <des:beneficiario>{fn:data($Transferencia/des:Beneficiario/des:beneficiario)}</des:beneficiario>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des:Beneficiario/des:direccion)
                                        then <des:direccion>{fn:data($Transferencia/des:Beneficiario/des:direccion)}</des:direccion>
                                        else ()
                                    }
                                </des:Beneficiario>
                            else ()
                        }
                        {
                            if ($Transferencia/des:Banco)
                            then 
                                <des:Banco>
                                    {
                                        if ($Transferencia/des:Banco/des:tipoOpcion)
                                        then <des:tipoOpcion>{fn:data($Transferencia/des:Banco/des:tipoOpcion)}</des:tipoOpcion>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des:Banco/des:numeroCta)
                                        then <des:numeroCta>{fn:data($Transferencia/des:Banco/des:numeroCta)}</des:numeroCta>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des:Banco/des:identificador)
                                        then <des:identificador>{fn:data($Transferencia/des:Banco/des:identificador)}</des:identificador>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des:Banco/des:beneficiario)
                                        then <des:beneficiario>{fn:data($Transferencia/des:Banco/des:beneficiario)}</des:beneficiario>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des:Banco/des:direccion)
                                        then <des:direccion>{fn:data($Transferencia/des:Banco/des:direccion)}</des:direccion>
                                        else ()
                                    }
                                </des:Banco>
                            else ()
                        }
                        {
                            if ($Transferencia/des:Intermediario)
                            then 
                                <des:Intermediario>
                                    {
                                        if ($Transferencia/des:Intermediario/des:tipoOpcion)
                                        then <des:tipoOpcion>{fn:data($Transferencia/des:Intermediario/des:tipoOpcion)}</des:tipoOpcion>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des:Intermediario/des:numeroCta)
                                        then <des:numeroCta>{fn:data($Transferencia/des:Intermediario/des:numeroCta)}</des:numeroCta>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des:Intermediario/des:identificador)
                                        then <des:identificador>{fn:data($Transferencia/des:Intermediario/des:identificador)}</des:identificador>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des:Intermediario/des:beneficiario)
                                        then <des:beneficiario>{fn:data($Transferencia/des:Intermediario/des:beneficiario)}</des:beneficiario>
                                        else ()
                                    }
                                    {
                                        if ($Transferencia/des:Intermediario/des:direccion)
                                        then <des:direccion>{fn:data($Transferencia/des:Intermediario/des:direccion)}</des:direccion>
                                        else ()
                                    }
                                </des:Intermediario>
                            else ()
                        }
                    </des:Transferencia>
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:TransferenciaFT05)
                    then 
                        <des:TransferenciaFT05>
                            <des:idTransferenciaFT05>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:TransferenciaFT05/des:idTransferenciaFT05)}</des:idTransferenciaFT05>
                            <des:idDesembolso>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:TransferenciaFT05/des:idDesembolso)}</des:idDesembolso>
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:TransferenciaFT05/des:idFacturador)
                                then <des:idFacturador>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:TransferenciaFT05/des:idFacturador)}</des:idFacturador>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:TransferenciaFT05/des:FechaEfectiva)
                                then <des:FechaEfectiva>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:TransferenciaFT05/des:FechaEfectiva)}</des:FechaEfectiva>
                                else ()
                            }
                        </des:TransferenciaFT05>
                    else ()
                }
                {
                    for $TransferenciaRecursos in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:TransferenciaRecursos
                    return 
                    <des:TransferenciaRecursos>
                        <des:idTransferencia>{fn:data($TransferenciaRecursos/des:idTransferencia)}</des:idTransferencia>
                        <des:idDesembolso>{fn:data($TransferenciaRecursos/des:idDesembolso)}</des:idDesembolso>
                        {
                            if ($TransferenciaRecursos/des:idFacturador)
                            then <des:idFacturador>{fn:data($TransferenciaRecursos/des:idFacturador)}</des:idFacturador>
                            else ()
                        }
                        <des:idLineaPasiva>{fn:data($TransferenciaRecursos/des:idLineaPasiva)}</des:idLineaPasiva>
                        <des:Monto>
                            <com:tipo>
                                {
                                    if ($TransferenciaRecursos/des:Monto/com:tipo/cat:Id)
                                    then <cat:Id>{fn:data($TransferenciaRecursos/des:Monto/com:tipo/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($TransferenciaRecursos/des:Monto/com:tipo/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($TransferenciaRecursos/des:Monto/com:tipo/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($TransferenciaRecursos/des:Monto/com:tipo/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($TransferenciaRecursos/des:Monto/com:tipo/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($TransferenciaRecursos/des:Monto/com:tipo/cat:estatus)
                                    then <cat:estatus>{fn:data($TransferenciaRecursos/des:Monto/com:tipo/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($TransferenciaRecursos/des:Monto/com:tipo/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($TransferenciaRecursos/des:Monto/com:tipo/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </com:tipo>
                            {
                                if ($TransferenciaRecursos/des:Monto/com:importe)
                                then <com:importe>{fn:data($TransferenciaRecursos/des:Monto/com:importe)}</com:importe>
                                else ()
                            }
                            {
                                if ($TransferenciaRecursos/des:Monto/com:moneda)
                                then 
                                    <com:moneda>
                                        {
                                            if ($TransferenciaRecursos/des:Monto/com:moneda/cat:Id)
                                            then <cat:Id>{fn:data($TransferenciaRecursos/des:Monto/com:moneda/cat:Id)}</cat:Id>
                                            else ()
                                        }
                                        {
                                            if ($TransferenciaRecursos/des:Monto/com:moneda/cat:Descripcion)
                                            then <cat:Descripcion>{fn:data($TransferenciaRecursos/des:Monto/com:moneda/cat:Descripcion)}</cat:Descripcion>
                                            else ()
                                        }
                                        {
                                            if ($TransferenciaRecursos/des:Monto/com:moneda/cat:DescripcionCorta)
                                            then <cat:DescripcionCorta>{fn:data($TransferenciaRecursos/des:Monto/com:moneda/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                            else ()
                                        }
                                        {
                                            if ($TransferenciaRecursos/des:Monto/com:moneda/cat:estatus)
                                            then <cat:estatus>{fn:data($TransferenciaRecursos/des:Monto/com:moneda/cat:estatus)}</cat:estatus>
                                            else ()
                                        }
                                        {
                                            if ($TransferenciaRecursos/des:Monto/com:moneda/cat:codigoExterno)
                                            then <cat:codigoExterno>{fn:data($TransferenciaRecursos/des:Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno>
                                            else ()
                                        }
                                    </com:moneda>
                                else ()
                            }
                        </des:Monto>
                        <des:fecha>{fn:data($TransferenciaRecursos/des:fecha)}</des:fecha>
                        <des:fechaEfectiva>{fn:data($TransferenciaRecursos/des:fechaEfectiva)}</des:fechaEfectiva>
                        <des:FormaTransferencia>
                            {
                                if ($TransferenciaRecursos/des:FormaTransferencia/cat:Id)
                                then <cat:Id>{fn:data($TransferenciaRecursos/des:FormaTransferencia/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($TransferenciaRecursos/des:FormaTransferencia/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($TransferenciaRecursos/des:FormaTransferencia/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($TransferenciaRecursos/des:FormaTransferencia/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($TransferenciaRecursos/des:FormaTransferencia/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($TransferenciaRecursos/des:FormaTransferencia/cat:estatus)
                                then <cat:estatus>{fn:data($TransferenciaRecursos/des:FormaTransferencia/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($TransferenciaRecursos/des:FormaTransferencia/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($TransferenciaRecursos/des:FormaTransferencia/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </des:FormaTransferencia>
                        {
                            if ($TransferenciaRecursos/des:idBanco)
                            then <des:idBanco>{fn:data($TransferenciaRecursos/des:idBanco)}</des:idBanco>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des:nombreBanco)
                            then <des:nombreBanco>{fn:data($TransferenciaRecursos/des:nombreBanco)}</des:nombreBanco>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des:numeroCuenta)
                            then <des:numeroCuenta>{fn:data($TransferenciaRecursos/des:numeroCuenta)}</des:numeroCuenta>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des:nombreCuenta)
                            then <des:nombreCuenta>{fn:data($TransferenciaRecursos/des:nombreCuenta)}</des:nombreCuenta>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des:estatus)
                            then <des:estatus>{fn:data($TransferenciaRecursos/des:estatus)}</des:estatus>
                            else ()
                        }
                        {
                            if ($TransferenciaRecursos/des:fechaRegistro)
                            then <des:fechaRegistro>{fn:data($TransferenciaRecursos/des:fechaRegistro)}</des:fechaRegistro>
                            else ()
                        }
                    </des:TransferenciaRecursos>
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE)
                    then 
                        <des:HerramientaCE>
                            <her:ActividadEconomica>
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:Id)
                                    then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:estatus)
                                    then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:ActividadEconomica>
                            <her:EjeEstrategico>
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:Id)
                                    then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:estatus)
                                    then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:EjeEstrategico>
                            <her:AreaFocalizacion>
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:Id)
                                    then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:Id)}</cat:Id>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)
                                    then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)}</cat:Descripcion>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)
                                    then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:estatus)
                                    then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:estatus)}</cat:estatus>
                                    else ()
                                }
                                {
                                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
                                    then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
                                    else ()
                                }
                            </her:AreaFocalizacion>
                        </des:HerramientaCE>
                    else ()
                }
                {
                    if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:modalidadFinan)
                    then 
                        <des:modalidadFinan>
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:modalidadFinan/cat:Id)
                                then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:modalidadFinan/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:modalidadFinan/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:modalidadFinan/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:modalidadFinan/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:modalidadFinan/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:modalidadFinan/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:modalidadFinan/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:modalidadFinan/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:ContratoDesembolso/des:modalidadFinan/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </des:modalidadFinan>
                    else ()
                }
            </lin:ContratoDesembolso>
			
			 {
                for $atributos in $ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:atributos
                return 
                <lin:atributos>
                    <com:name>{fn:data($atributos/com:name)}</com:name>
                    <com:value>{fn:data($atributos/com:value)}</com:value>
                </lin:atributos>
            }
            {
                if ($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube)
                then 
                    <lin:LineaCreditoFlexcube>
                        <lin:numeroLineaCreditoResp_out>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:numeroLineaCreditoResp_out)}</lin:numeroLineaCreditoResp_out>
                        <lin:NumeroContrato>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:NumeroContrato)}</lin:NumeroContrato>
                        <lin:DescripcionLinea>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:DescripcionLinea)}</lin:DescripcionLinea>
                        <lin:Moneda>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:Moneda)}</lin:Moneda>
                        <lin:MontoAprobado>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:MontoAprobado)}</lin:MontoAprobado>
                        <lin:CodigoCliente>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:CodigoCliente)}</lin:CodigoCliente>
                        <lin:Revolvencia>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:Revolvencia)}</lin:Revolvencia>
                        <lin:Fondo>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:Fondo)}</lin:Fondo>
                        <lin:LineaFinanciera>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:LineaFinanciera)}</lin:LineaFinanciera>
                        <lin:Destino>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:Destino)}</lin:Destino>
                        <lin:TipoRecurso>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:TipoRecurso)}</lin:TipoRecurso>
                        <lin:Pais>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:Pais)}</lin:Pais>
                        <lin:ActividadEconomica>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:ActividadEconomica)}</lin:ActividadEconomica>
                        <lin:SectorInstitucional>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:SectorInstitucional)}</lin:SectorInstitucional>
                        <lin:Ejecutivo>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:Ejecutivo)}</lin:Ejecutivo>
                        <lin:EjeEstrategico>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:EjeEstrategico)}</lin:EjeEstrategico>
                        <lin:AreaFocalizacion>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:AreaFocalizacion)}</lin:AreaFocalizacion>
                        <lin:ObjeticoEstrategico>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:ObjeticoEstrategico)}</lin:ObjeticoEstrategico>
                        <lin:PlantillaLimite>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:PlantillaLimite)}</lin:PlantillaLimite>
                        <lin:SerialLimite>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:SerialLimite)}</lin:SerialLimite>
                        <lin:Saldo>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:Saldo)}</lin:Saldo>
                        <lin:Disponibilidad>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:Disponibilidad)}</lin:Disponibilidad>
                        <lin:DisponibilidadIfacil>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:DisponibilidadIfacil)}</lin:DisponibilidadIfacil>
                        <lin:TieneCondEspeciales>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:TieneCondEspeciales)}</lin:TieneCondEspeciales>
                        <lin:DescCondEspeciales>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:DescCondEspeciales)}</lin:DescCondEspeciales>
                        <lin:FechaMaxDesembolsar>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:FechaMaxDesembolsar)}</lin:FechaMaxDesembolsar>
                        <lin:NumeroDesembolsos>{fn:data($ConsultarCommitmentResponse/ns1:LineaCredito/lin:LineaCreditoFlexcube/lin:NumeroDesembolsos)}</lin:NumeroDesembolsos>
                    </lin:LineaCreditoFlexcube>
                else ()
            }	
            {
                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE)
                then 
                    <lin:HerramientaCE>
                        <her:ActividadEconomica>
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:Id)
                                then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:ActividadEconomica/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:ActividadEconomica>
                        <her:EjeEstrategico>
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:Id)
                                then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:EjeEstrategico/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:EjeEstrategico>
                        <her:AreaFocalizacion>
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:Id)
                                then <cat:Id>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:Id)}</cat:Id>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)
                                then <cat:Descripcion>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:Descripcion)}</cat:Descripcion>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)
                                then <cat:DescripcionCorta>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:DescripcionCorta)}</cat:DescripcionCorta>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:estatus)
                                then <cat:estatus>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:estatus)}</cat:estatus>
                                else ()
                            }
                            {
                                if ($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)
                                then <cat:codigoExterno>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:LineaCredito/lin:HerramientaCE/her:AreaFocalizacion/cat:codigoExterno)}</cat:codigoExterno>
                                else ()
                            }
                        </her:AreaFocalizacion>
                    </lin:HerramientaCE>
                else ()
            }  
        </ns1:LineaCredito>
        <ns1:Resultado>
            <res:result>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:Resultado/res:result)}</res:result>
            <res:message>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:Resultado/res:message)}</res:message>
            {
                if ($ConsultarLineaCreditoBPELResponse/ns1:Resultado/res:error)
                then 
                    <res:error>
                        <err:errorCode>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:Resultado/res:error/err:errorCode)}</err:errorCode>
                        <err:errorDescription>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:Resultado/res:error/err:errorDescription)}</err:errorDescription>
                        <err:errorType>{fn:data($ConsultarLineaCreditoBPELResponse/ns1:Resultado/res:error/err:errorType)}</err:errorType>
                    </res:error>
                else ()
            }
        </ns1:Resultado>
    </ns1:ConsultarLineaCreditoBPELResponse>
};

tns:func($ConsultarCommitmentResponse, $ConsultarLineaCreditoBPELResponse)
            
            
