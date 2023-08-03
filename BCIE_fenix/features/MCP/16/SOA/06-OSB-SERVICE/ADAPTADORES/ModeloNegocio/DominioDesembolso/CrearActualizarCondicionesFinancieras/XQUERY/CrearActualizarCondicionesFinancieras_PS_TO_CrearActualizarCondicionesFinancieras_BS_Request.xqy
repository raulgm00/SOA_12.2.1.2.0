xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearActualizarCondicionesFinancieras";
(:: import schema at "../XSD/CrearActualizarCondicionesFinancieras_table.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $CrearActualizarCondicionesFinancierasRequest as element() (:: schema-element(ns1:CrearActualizarCondicionesFinancierasRequest) ::) external;

declare function local:func($CrearActualizarCondicionesFinancierasRequest as element() (:: schema-element(ns1:CrearActualizarCondicionesFinancierasRequest) ::)) as element() (:: schema-element(ns2:CondicionesFinancierasCollection) ::) {
    <ns2:CondicionesFinancierasCollection>
        <ns2:CondicionesFinancieras>
            <ns2:id>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:idCondicionFinanciera)}</ns2:id>
            <ns2:idContratoDesembolso>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:idDesembolso)}</ns2:idContratoDesembolso>
            <ns2:idTcaTipoTasaDesembolso>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:tipo/cat:Id)}</ns2:idTcaTipoTasaDesembolso>
            <ns2:idProductoFlexcube>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:producto/pro:idProducto)}</ns2:idProductoFlexcube>
            <ns2:idTcaEspecificacionFecha>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:estatus)}</ns2:idTcaEspecificacionFecha>
            <ns2:idTcaBaseCalculo>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:baseCalculo/cat:Id)}</ns2:idTcaBaseCalculo>
            <ns2:idTcaTipoCalendario>1</ns2:idTcaTipoCalendario>
            <ns2:tratamientoDiasFeriados>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tratamientoDiasFeriados)}</ns2:tratamientoDiasFeriados>
            <ns2:sePuedeMoverEntreMeses></ns2:sePuedeMoverEntreMeses>
            <ns2:sePuedeAlinearDiaPago></ns2:sePuedeAlinearDiaPago>
            <ns2:exceptoVencimiento></ns2:exceptoVencimiento>
            <ns2:frecuenciaPagoCapital>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Valor)}</ns2:frecuenciaPagoCapital>
            <ns2:idTcaFrecuenciaPagoCapital>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:Frecuencia/com:Tipo/cat:Id)}</ns2:idTcaFrecuenciaPagoCapital>
            <ns2:fechaPrimerPagoCapital>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:principal/des:FechaInicio)}</ns2:fechaPrimerPagoCapital>
            <ns2:fechaVencimiento>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:fechaVencimiento)}</ns2:fechaVencimiento>
            <ns2:numeroCuotasPagoCapital></ns2:numeroCuotasPagoCapital>
            <ns2:fechaProximoPagoInteres>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:FechaInicio)}</ns2:fechaProximoPagoInteres>
            <ns2:frecuenciaPagoInteres>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Valor)}</ns2:frecuenciaPagoInteres>
            <ns2:idTcaFrecuenciaPagoInteres>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:interes/des:Frecuencia/com:Tipo/cat:Id)}</ns2:idTcaFrecuenciaPagoInteres>
            <ns2:numeroCuotasPagoInteres></ns2:numeroCuotasPagoInteres>
            <ns2:limiteTasaMinima>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:limite/com:minimo)}</ns2:limiteTasaMinima>
            <ns2:limiteTasaMaxima>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:limite/com:maximo)}</ns2:limiteTasaMaxima>
            <ns2:codigoTasaReferencia>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:tasaReferencia/cat:Id)}</ns2:codigoTasaReferencia>
            <ns2:valorTasa>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:fija/des:valor)}</ns2:valorTasa>
            <ns2:spreadTasa>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:spread/des:valor)}</ns2:spreadTasa>
            <ns2:tasaTotal></ns2:tasaTotal>
            <ns2:fechaProximaRevisionTasa>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:revision/des:FechaInicio)}</ns2:fechaProximaRevisionTasa>
            <ns2:frecuenciaRevisionTasa>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:revision/des:Frecuencia/com:Valor)}</ns2:frecuenciaRevisionTasa>
            <ns2:idTcaFrecuenciaRevTasa>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:revision/des:Frecuencia/com:Tipo/cat:Id)}</ns2:idTcaFrecuenciaRevTasa>
            <ns2:numeroRevisionesTasa></ns2:numeroRevisionesTasa>
            <ns2:codigoTasaReferenciaSpread>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:spread/des:codigo)}</ns2:codigoTasaReferenciaSpread>
            <ns2:valorTasaReferenciaSpread></ns2:valorTasaReferenciaSpread>
            <ns2:fechaProximaRevisionSpread>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:spread/des:revision/des:FechaInicio)}</ns2:fechaProximaRevisionSpread>
            <ns2:frecuenciaRevisionSpread>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:spread/des:revision/des:Frecuencia/com:Valor)}</ns2:frecuenciaRevisionSpread>
            <ns2:idTcaFrecuenciaRevSpread>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:variable/des:spread/des:revision/des:Frecuencia/com:Tipo/cat:Id)}</ns2:idTcaFrecuenciaRevSpread>
            <ns2:numeroRevisionesSpread></ns2:numeroRevisionesSpread>
            <ns2:spreadMora>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:tasa/des:spreadMora/des:valor)}</ns2:spreadMora>
            <ns2:frecuenciaPlazo>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:EstimadoDesembolso/des:Plazo)}</ns2:frecuenciaPlazo>
            <ns2:idTcaFrecuenciaPlazo>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:EstimadoDesembolso/des:Frecuencia/cat:Id)}</ns2:idTcaFrecuenciaPlazo>
            <ns2:frecuenciaPeriodoGracia>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Valor)}</ns2:frecuenciaPeriodoGracia>
            <ns2:idTcaFrecuenciaPeriodoGra>{fn:data($CrearActualizarCondicionesFinancierasRequest/ns1:ContratoDesembolso/des:condicionesFinancieras/des:periodoGracia/des:Frecuencia/com:Tipo/cat:Id)}</ns2:idTcaFrecuenciaPeriodoGra>
            <ns2:montoDescuento></ns2:montoDescuento>
        </ns2:CondicionesFinancieras>
    </ns2:CondicionesFinancierasCollection>
};

local:func($CrearActualizarCondicionesFinancierasRequest)
