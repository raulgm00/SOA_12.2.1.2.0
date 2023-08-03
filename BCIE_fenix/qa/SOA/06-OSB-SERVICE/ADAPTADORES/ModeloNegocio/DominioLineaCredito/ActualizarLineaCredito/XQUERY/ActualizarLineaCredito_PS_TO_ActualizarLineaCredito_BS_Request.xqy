xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarLineaCredito";
(:: import schema at "../XSD/ActualizarLineaCredito_table.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $ActualizarLineaCreditoRequest as element() (:: schema-element(ns1:ActualizarLineaCreditoRequest) ::) external;

declare function local:func($ActualizarLineaCreditoRequest as element() (:: schema-element(ns1:ActualizarLineaCreditoRequest) ::)) as element() (:: schema-element(ns2:LineaCreditoCollection) ::) {
    <ns2:LineaCreditoCollection>
        <ns2:LineaCredito>
            <ns2:id>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:idLineaCredito)}</ns2:id>
            {
                if ($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:idContrato)
                then <ns2:idContrato>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:idContrato)}</ns2:idContrato>
                else ()
            }
            <ns2:numeroLineaCredito>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:NumeroLineaCredito)}</ns2:numeroLineaCredito>
            <ns2:descripcionLinea>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:Descripcion)}</ns2:descripcionLinea>
            {
                if ($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:Flexcube/lin:id)
                then <ns2:idFlexcube>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:Flexcube/lin:id)}</ns2:idFlexcube>
                else ()
            }            
            <ns2:fondo>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:Fondo)}</ns2:fondo>
            <ns2:montoLinea>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:MontoLinea)}</ns2:montoLinea>

            {
                if ($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:EsRevolvente) then
                    <ns2:esRevolvente>{
                     if (fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:EsRevolvente) = true())
                      then(1)
                        else(0)
                    }</ns2:esRevolvente>
                else()
            }

            <ns2:tratamientoDiasFeriados>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:TratamientoDiasFeriados)}</ns2:tratamientoDiasFeriados>
            {
                if ($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:Flexcube/lin:idProductoFlexcube)
                then <ns2:idProductoFlexcube>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:Flexcube/lin:idProductoFlexcube)}</ns2:idProductoFlexcube>
                else ()
            }            
            <ns2:fechaValor>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:FechaValor)}</ns2:fechaValor>
            <ns2:fechaVencimiento>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:FechaVencimiento)}</ns2:fechaVencimiento>
            <ns2:codigoPlantillaLimite>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:CodigoPantallaLimite)}</ns2:codigoPlantillaLimite>
            <ns2:codigoSerialLimite>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:CodigoSerialLimite)}</ns2:codigoSerialLimite>
            <ns2:codigoAsignacion>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:CodigoAsignacion)}</ns2:codigoAsignacion>
            <ns2:descripcionAsignacion>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:DescripcionAsignacion)}</ns2:descripcionAsignacion>            
            {
                if ($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:CondicionEspecial) then
                    <ns2:condicionEspecial>{
                      if(fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:CondicionEspecial) = true())
                        then(1)
                          else(0)
                    }
                    </ns2:condicionEspecial>
                else()
            }
            <ns2:descripcionCondEspecial>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:descCondEspecial)}</ns2:descripcionCondEspecial>
            <ns2:fechaRegistro>{fn:current-date()}</ns2:fechaRegistro>
            <ns2:banEstatus>1</ns2:banEstatus>
            {
                if (string($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:EstadoMonto)!='')then
            <ns2:banActMonto>{
            if (
            string($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:EstadoMonto)='true' )
            then 1 else 0
             }       
            </ns2:banActMonto>
           else ()}
            {
                if ($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:MoverEntreMeses)
                then <ns2:sePuedeMoverEntreMeses>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:MoverEntreMeses)}</ns2:sePuedeMoverEntreMeses>
                else ()
            }
            {
                if ($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:diasSpotTasa)
                then <ns2:diasSpotTasa>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:diasSpotTasa)}</ns2:diasSpotTasa>
                else ()
            }
            {
                if ($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:idTcaTipoRedondeo)
                then <ns2:idTcaTipoRedondeo>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:idTcaTipoRedondeo)}</ns2:idTcaTipoRedondeo>
                else ()
            }
            {
                if ($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:cantidadDecimales)
                then <ns2:cantidadDecimales>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:cantidadDecimales)}</ns2:cantidadDecimales>
                else ()
            }
            {
                if ($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:idTcaTipoMora)
                then <ns2:idTcaTipoMora>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:idTcaTipoMora)}</ns2:idTcaTipoMora>
                else ()
            }
            {
                if ($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:codigoTasaReferencia)
                then <ns2:codigoTasaReferencia>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:codigoTasaReferencia)}</ns2:codigoTasaReferencia>
                else ()
            }
            {
                if ($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:valorTasa)
                then <ns2:valorTasa>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:valorTasa)}</ns2:valorTasa>
                else ()
            }
            {
                if ($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:spreadTasa)
                then <ns2:spreadTasa>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:spreadTasa)}</ns2:spreadTasa>
                else ()
            }
            {
                if ($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:frecuencia)
                then <ns2:frecuencia>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:frecuencia)}</ns2:frecuencia>
                else ()
            }
            {
                if ($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:idTcaTipoFrecuencia)
                then <ns2:idTcaTipoFrecuencia>{fn:data($ActualizarLineaCreditoRequest/ns1:LineaCredito/lin:idTcaTipoFrecuencia)}</ns2:idTcaTipoFrecuencia>
                else ()
            }
   
               
        </ns2:LineaCredito>
    </ns2:LineaCreditoCollection>
};

local:func($ActualizarLineaCreditoRequest)
