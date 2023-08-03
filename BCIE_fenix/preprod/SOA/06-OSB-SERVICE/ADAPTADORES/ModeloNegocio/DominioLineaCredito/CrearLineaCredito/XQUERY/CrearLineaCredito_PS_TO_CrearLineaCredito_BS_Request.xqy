xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearLineaCredito";
(:: import schema at "../XSD/CrearLineaCredito_table.xsd" ::)

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare variable $CrearLineaCreditoRequest as element() (:: schema-element(ns1:CrearLineaCreditoRequest) ::) external;

declare function local:func($CrearLineaCreditoRequest as element() (:: schema-element(ns1:CrearLineaCreditoRequest) ::)) as element() (:: schema-element(ns2:LineaCreditoCollection) ::) {
    <ns2:LineaCreditoCollection>
    {
    for $LineaCredito in $CrearLineaCreditoRequest/ns1:LineaCredito
    return
        <ns2:LineaCredito>
             <ns2:idContrato>{fn:data($LineaCredito/lin:idContrato)}</ns2:idContrato>
            <ns2:numeroLineaCredito>{fn:data($LineaCredito/lin:NumeroLineaCredito)}</ns2:numeroLineaCredito>
            <ns2:descripcionLinea>{fn:data($LineaCredito/lin:Descripcion)}</ns2:descripcionLinea>
            {
                if ($LineaCredito/lin:Flexcube/lin:id)
                then <ns2:idFlexcube>{fn:data($LineaCredito/lin:Flexcube/lin:id)}</ns2:idFlexcube>
                else ()
            }
            <ns2:fondo>{fn:data($LineaCredito/lin:Fondo)}</ns2:fondo>
            <ns2:montoLinea>{fn:data($LineaCredito/lin:MontoLinea)}</ns2:montoLinea>
            <ns2:esRevolvente>{
              if(fn:data($LineaCredito/lin:EsRevolvente) = true())
                then(1)
                  else(0)
            }</ns2:esRevolvente>
            <ns2:tratamientoDiasFeriados>{fn:data($LineaCredito/lin:TratamientoDiasFeriados)}</ns2:tratamientoDiasFeriados>
            {
                if ($LineaCredito/lin:Flexcube/lin:idProductoFlexcube)
                then <ns2:idProductoFlexcube>{fn:data($LineaCredito/lin:Flexcube/lin:idProductoFlexcube)}</ns2:idProductoFlexcube>
                else ()
            }
            <ns2:fechaValor>{fn:data($LineaCredito/lin:FechaValor)}</ns2:fechaValor>
            <ns2:fechaVencimiento>{fn:data($LineaCredito/lin:FechaVencimiento)}</ns2:fechaVencimiento>
            <ns2:codigoPlantillaLimite>{fn:data($LineaCredito/lin:CodigoPantallaLimite)}</ns2:codigoPlantillaLimite>
            <ns2:codigoSerialLimite>{fn:data($LineaCredito/lin:CodigoSerialLimite)}</ns2:codigoSerialLimite>
            <ns2:codigoAsignacion>{fn:data($LineaCredito/lin:CodigoAsignacion)}</ns2:codigoAsignacion>
            <ns2:descripcionAsignacion>{fn:data($LineaCredito/lin:DescripcionAsignacion)}</ns2:descripcionAsignacion>
            {
                if ($LineaCredito/lin:Flexcube/lin:idFlexcubePasivo)
                then <ns2:idFlexcubePasivo>{fn:data($LineaCredito/lin:Flexcube/lin:idFlexcubePasivo)}</ns2:idFlexcubePasivo>
                else ()
            }
            <ns2:condicionEspecial>{
              if(fn:data($LineaCredito/lin:CondicionEspecial) = true())
                then(1)
                  else(0)
            }</ns2:condicionEspecial>
            <ns2:descripcionCondEspecial>{fn:data($LineaCredito/lin:descCondEspecial)}</ns2:descripcionCondEspecial>
            <ns2:fechaRegistro>{fn:current-date()}</ns2:fechaRegistro>
            <ns2:banEstatus>1</ns2:banEstatus>
        
                <ns2:banActMonto>{
                if(fn:data($LineaCredito/lin:EstadoMonto)=true())
                then (1) 
                else (0)}</ns2:banActMonto>
              {
                if ($LineaCredito/lin:IdTipoMonedaMontoLinea)
                then <ns2:idTcaTipoMoneda>{fn:data($LineaCredito/lin:IdTipoMonedaMontoLinea)}</ns2:idTcaTipoMoneda>
                else ()
            }

        </ns2:LineaCredito>
    }
    </ns2:LineaCreditoCollection>
};

local:func($CrearLineaCreditoRequest)
