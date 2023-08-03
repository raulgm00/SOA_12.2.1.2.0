xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/ActualizarComision_DB";
(:: import schema at "../XSD/ActualizarComision_DB_table.xsd" ::)

declare namespace car = "http://www.bcie.org/ComisionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $ActualizarComisionRequest as element() (:: schema-element(ns1:ActualizarComisionRequest) ::) external;

declare function local:func($ActualizarComisionRequest as element() (:: schema-element(ns1:ActualizarComisionRequest) ::)) as element() (:: schema-element(ns2:ComisionCollection) ::) {
    <ns2:ComisionCollection>
        <ns2:Comision>
            <ns2:id>{fn:data($ActualizarComisionRequest/ns1:Comision/car:idComision)}</ns2:id>
            <ns2:idOperacion>{fn:data($ActualizarComisionRequest/ns1:Comision/car:idOperacion)}</ns2:idOperacion>
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:descripcion)
                then <ns2:descripcion>{fn:data($ActualizarComisionRequest/ns1:Comision/car:descripcion)}</ns2:descripcion>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:moneda/cat:Id)
                then <ns2:idTcaMoneda>{fn:data($ActualizarComisionRequest/ns1:Comision/car:moneda/cat:Id)}</ns2:idTcaMoneda>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:montoComision)
                then <ns2:montoComision>{fn:data($ActualizarComisionRequest/ns1:Comision/car:montoComision)}</ns2:montoComision>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:montoBase/car:idMontoBase)
                then <ns2:idTcaMontoBase>{fn:data($ActualizarComisionRequest/ns1:Comision/car:montoBase/car:idMontoBase)}</ns2:idTcaMontoBase>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:montoBase/car:valorMontoBase)
                then <ns2:montoBase>{fn:data($ActualizarComisionRequest/ns1:Comision/car:montoBase/car:valorMontoBase)}</ns2:montoBase>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:montoBase/car:porcentajeMontoBase)
                then <ns2:porcentajeSobreMontoBase>{fn:data($ActualizarComisionRequest/ns1:Comision/car:montoBase/car:porcentajeMontoBase)}</ns2:porcentajeSobreMontoBase>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:fechaValor)
                then <ns2:fechaValor>{fn:data($ActualizarComisionRequest/ns1:Comision/car:fechaValor)}</ns2:fechaValor>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:fechaVencimiento)
                then <ns2:fechaVencimiento>{fn:data($ActualizarComisionRequest/ns1:Comision/car:fechaVencimiento)}</ns2:fechaVencimiento>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:fechaInicioCapital)
                then <ns2:fechaInicioCapital>{fn:data($ActualizarComisionRequest/ns1:Comision/car:fechaInicioCapital)}</ns2:fechaInicioCapital>
                else ()
            }

               {
                if ($ActualizarComisionRequest/ns1:Comision/car:fechaInicioComision)
                then <ns2:fechaInicioComision>{fn:data($ActualizarComisionRequest/ns1:Comision/car:fechaInicioComision)}</ns2:fechaInicioComision>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:tipoFrecuencia/cat:Id)
                then <ns2:idTcaTipoFrecuencia>{fn:data($ActualizarComisionRequest/ns1:Comision/car:tipoFrecuencia/cat:Id)}</ns2:idTcaTipoFrecuencia>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:frecuenciaPago)
                then <ns2:frecuenciaPago>{fn:data($ActualizarComisionRequest/ns1:Comision/car:frecuenciaPago)}</ns2:frecuenciaPago>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:fondo)
                then <ns2:idTcaFondo>{fn:data($ActualizarComisionRequest/ns1:Comision/car:fondo)}</ns2:idTcaFondo>
                else ()
            }
            <ns2:comisionCompartida>{
                if  (string ($ActualizarComisionRequest/ns1:Comision/car:comisionCompartida)='true' )then
             (1)              
               
                else (0)
            } </ns2:comisionCompartida>
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:estadoTCC/atr:id)
                then <ns2:idTcaEstadoTcc>{fn:data($ActualizarComisionRequest/ns1:Comision/car:estadoTCC/atr:id)}</ns2:idTcaEstadoTcc>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:banSugerida)
                then <ns2:banSugerida>{fn:data($ActualizarComisionRequest/ns1:Comision/car:banSugerida)}</ns2:banSugerida>
                else ()
            }
            <ns2:banEstatus>{
            if(not(fn:empty(xs:string(fn:data($ActualizarComisionRequest/ns1:Comision/car:estado)))))
                then(
              if (xs:string(fn:data($ActualizarComisionRequest/ns1:Comision/car:estado)) = 'true' or xs:string(fn:data($ActualizarComisionRequest/ns1:Comision/car:estado)) = '')
                then(1)
                  else(0))
                else(1)
            }
            </ns2:banEstatus>
              {
                if ($ActualizarComisionRequest/ns1:Comision/car:codigoContrato)
                then <ns2:codigoContrato>{fn:data($ActualizarComisionRequest/ns1:Comision/car:codigoContrato)}</ns2:codigoContrato>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:tipoTasa/cat:Id)
                then <ns2:idTcaTipoTasa>{fn:data($ActualizarComisionRequest/ns1:Comision/car:tipoTasa/cat:Id)}</ns2:idTcaTipoTasa>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:baseCalculo/cat:Id)
                then <ns2:idTcaBaseCalculo>{fn:data($ActualizarComisionRequest/ns1:Comision/car:baseCalculo/cat:Id)}</ns2:idTcaBaseCalculo>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:codigoDesembolso)
                then <ns2:codigoDesembolso>{fn:data($ActualizarComisionRequest/ns1:Comision/car:codigoDesembolso)}</ns2:codigoDesembolso>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:nombre)
                then <ns2:nombre>{fn:data($ActualizarComisionRequest/ns1:Comision/car:nombre)}</ns2:nombre>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:numeroTesoreria)
                then <ns2:numeroTesoreria>{fn:data($ActualizarComisionRequest/ns1:Comision/car:numeroTesoreria)}</ns2:numeroTesoreria>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:responsableComision)
                then <ns2:responsableComision>{fn:data($ActualizarComisionRequest/ns1:Comision/car:responsableComision)}</ns2:responsableComision>
                else ()
            }
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:subEstadoTCC/atr:id)
                then <ns2:idTcaSubEstadoTcc>{fn:data($ActualizarComisionRequest/ns1:Comision/car:subEstadoTCC/atr:id)}</ns2:idTcaSubEstadoTcc>
                else ()
            }
            <ns2:fechaEnmienda>{fn:data($ActualizarComisionRequest/ns1:Comision/car:fechaEnmienda)}</ns2:fechaEnmienda>
            {
                if ($ActualizarComisionRequest/ns1:Comision/car:momentoCobro/cat:Id)
                then <ns2:idTcaMomentoCobro>{fn:data($ActualizarComisionRequest/ns1:Comision/car:momentoCobro/cat:Id)}</ns2:idTcaMomentoCobro>
                else ()
            }
            
         
          
               
         
        </ns2:Comision>
    </ns2:ComisionCollection>
};

local:func($ActualizarComisionRequest)