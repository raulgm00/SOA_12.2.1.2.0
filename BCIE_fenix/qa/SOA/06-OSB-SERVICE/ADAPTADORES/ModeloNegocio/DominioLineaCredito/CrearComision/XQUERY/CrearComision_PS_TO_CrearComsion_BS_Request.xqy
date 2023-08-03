xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ComisionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/Comision/V1/Schema/ComisionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearComision_DB";
(:: import schema at "../XSD/CrearComision_DB_table.xsd" ::)

declare namespace com = "http://www.bcie.org/ComisionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare variable $CrearComisionRequest as element() (:: schema-element(ns1:CrearComisionRequest) ::) external;

declare function local:func($CrearComisionRequest as element() (:: schema-element(ns1:CrearComisionRequest) ::)) as element() (:: schema-element(ns2:ComisionCollection) ::) {
    <ns2:ComisionCollection>
        <ns2:Comision>
            <ns2:idOperacion>{fn:data($CrearComisionRequest/ns1:Comision/com:idOperacion)}</ns2:idOperacion>
            {
                if ($CrearComisionRequest/ns1:Comision/com:descripcion)
                then <ns2:descripcion>{fn:data($CrearComisionRequest/ns1:Comision/com:descripcion)}</ns2:descripcion>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:tipoComision/com:idCatComision)
                then <ns2:idTcaComision>{fn:data($CrearComisionRequest/ns1:Comision/com:tipoComision/com:idCatComision)}</ns2:idTcaComision>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:moneda/cat:Id)
                then <ns2:idTcaMoneda>{fn:data($CrearComisionRequest/ns1:Comision/com:moneda/cat:Id)}</ns2:idTcaMoneda>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:montoComision)
                then <ns2:montoComision>{fn:data($CrearComisionRequest/ns1:Comision/com:montoComision)}</ns2:montoComision>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:montoBase/com:idMontoBase)
                then <ns2:idTcaMontoBase>{fn:data($CrearComisionRequest/ns1:Comision/com:montoBase/com:idMontoBase)}</ns2:idTcaMontoBase>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:montoBase/com:valorMontoBase)
                then <ns2:montoBase>{fn:data($CrearComisionRequest/ns1:Comision/com:montoBase/com:valorMontoBase)}</ns2:montoBase>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:montoBase/com:porcentajeMontoBase)
                then <ns2:porcentajeSobreMontoBase>{fn:data($CrearComisionRequest/ns1:Comision/com:montoBase/com:porcentajeMontoBase)}</ns2:porcentajeSobreMontoBase>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:fechaValor)
                then <ns2:fechaValor>{fn:data($CrearComisionRequest/ns1:Comision/com:fechaValor)}</ns2:fechaValor>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:fechaVencimiento)
                then <ns2:fechaVencimiento>{fn:data($CrearComisionRequest/ns1:Comision/com:fechaVencimiento)}</ns2:fechaVencimiento>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:fechaInicioCapital)
                then <ns2:fechaInicioCapital>{fn:data($CrearComisionRequest/ns1:Comision/com:fechaInicioCapital)}</ns2:fechaInicioCapital>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:fechaInicioComision)
                then <ns2:fechaInicioComision>{fn:data($CrearComisionRequest/ns1:Comision/com:fechaInicioComision)}</ns2:fechaInicioComision>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:tipoFrecuencia/cat:Id)
                then <ns2:idTcaTipoFrecuencia>{fn:data($CrearComisionRequest/ns1:Comision/com:tipoFrecuencia/cat:Id)}</ns2:idTcaTipoFrecuencia>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:frecuenciaPago)
                then <ns2:frecuenciaPago>{fn:data($CrearComisionRequest/ns1:Comision/com:frecuenciaPago)}</ns2:frecuenciaPago>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:fondo)
                then <ns2:idTcaFondo>{fn:data($CrearComisionRequest/ns1:Comision/com:fondo)}</ns2:idTcaFondo>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:comisionCompartida)
                then <ns2:comisionCompartida>{fn:data($CrearComisionRequest/ns1:Comision/com:comisionCompartida)}</ns2:comisionCompartida>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:estadoTCC/atr:id)
                then <ns2:idTcaEstadoTcc>{fn:data($CrearComisionRequest/ns1:Comision/com:estadoTCC/atr:id)}</ns2:idTcaEstadoTcc>
                else ()
            }
            <ns2:banSugerida>0</ns2:banSugerida>
            <ns2:fechaRegistro>{fn:current-date()}</ns2:fechaRegistro>
            <ns2:banEstatus>1</ns2:banEstatus>
            {
                if ($CrearComisionRequest/ns1:Comision/com:codigoContrato)
                then <ns2:codigoContrato>{fn:data($CrearComisionRequest/ns1:Comision/com:codigoContrato)}</ns2:codigoContrato>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:tipoTasa/cat:Id)
                then <ns2:idTcaTipoTasa>{fn:data($CrearComisionRequest/ns1:Comision/com:tipoTasa/cat:Id)}</ns2:idTcaTipoTasa>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:baseCalculo/cat:Id)
                then <ns2:idTcaBaseCalculo>{fn:data($CrearComisionRequest/ns1:Comision/com:baseCalculo/cat:Id)}</ns2:idTcaBaseCalculo>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:codigoDesembolso)
                then <ns2:codigoDesembolso>{fn:data($CrearComisionRequest/ns1:Comision/com:codigoDesembolso)}</ns2:codigoDesembolso>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:nombre)
                then <ns2:nombre>{fn:data($CrearComisionRequest/ns1:Comision/com:nombre)}</ns2:nombre>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:numeroTesoreria)
                then <ns2:numeroTesoreria>{fn:data($CrearComisionRequest/ns1:Comision/com:numeroTesoreria)}</ns2:numeroTesoreria>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:responsableComision)
                then <ns2:responsableComision>{fn:data($CrearComisionRequest/ns1:Comision/com:responsableComision)}</ns2:responsableComision>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:subEstadoTCC/atr:id)
                then <ns2:idTcaSubEstadoTcc>{fn:data($CrearComisionRequest/ns1:Comision/com:subEstadoTCC/atr:id)}</ns2:idTcaSubEstadoTcc>
                else ()
            }
            {
                if ($CrearComisionRequest/ns1:Comision/com:momentoCobro/cat:Id)
                then <ns2:idTcaMomentoCobro>{fn:data($CrearComisionRequest/ns1:Comision/com:momentoCobro/cat:Id)}</ns2:idTcaMomentoCobro>
                else ()
            }
        </ns2:Comision>
    </ns2:ComisionCollection>
};

local:func($CrearComisionRequest)
