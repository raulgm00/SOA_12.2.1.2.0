xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace adq="http://www.bcie.org/AdquisicionMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)

declare namespace adq1 = "http://www.bcie.org/AdquisicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $outInvokeConsultarAdquisicion.response as element() (:: schema-element(adq:ConsultarAdquisicionResponse) ::) external;

declare function local:funcConsultaradquisicion_to_publicarinformereporteadjudicado($outInvokeConsultarAdquisicion.response as element() (:: schema-element(adq:ConsultarAdquisicionResponse) ::)) as element() (:: schema-element(adq:PublicarInformeResultadosAdjudicado) ::) {
    <adq:PublicarInformeResultadosAdjudicado>
        <adq1:idNoObjecion>{fn:data($outInvokeConsultarAdquisicion.response/adq:Adquisicion/adq1:noObjecion[1]/adq1:idNoObjecion)}</adq1:idNoObjecion>
        <adq1:nombrePais>{fn:data($outInvokeConsultarAdquisicion.response/adq:Adquisicion/adq1:cliente/cli:pais/cat:codigoExterno)}</adq1:nombrePais>
        <adq1:nombreOperacion>{fn:data($outInvokeConsultarAdquisicion.response/adq:Adquisicion/adq1:operacion/ope:nombre)}</adq1:nombreOperacion>
        <adq1:numeroOperacion>{fn:data($outInvokeConsultarAdquisicion.response/adq:Adquisicion/adq1:operacion/ope:idOperacion)}</adq1:numeroOperacion>
        <adq1:tituloProceso>{fn:data($outInvokeConsultarAdquisicion.response/adq:Adquisicion/adq1:titulo)}</adq1:tituloProceso>
        <adq1:numeroProceso>{fn:data($outInvokeConsultarAdquisicion.response/adq:Adquisicion/adq1:numero)}</adq1:numeroProceso>
        <adq1:modalidadProceso>{fn:data($outInvokeConsultarAdquisicion.response/adq:Adquisicion/adq1:tipoModalidad/cat:Descripcion)}</adq1:modalidadProceso>
        <adq:resultadoProceso>{fn:data($outInvokeConsultarAdquisicion.response/adq:Adquisicion/adq1:noObjecion/adq1:resultadoProceso/cat:Descripcion)}</adq:resultadoProceso>
        {
        for $concursante in $outInvokeConsultarAdquisicion.response/adq:Adquisicion/adq1:noObjecion/adq1:concursante[adq1:tipoPerfil/cat:Id != 1]
        return
        <adq:adjudicados>
            <adq1:nombre>{fn:data($concursante/adq1:nombre)}</adq1:nombre>
            <adq1:nacionalidad>{fn:data($concursante/adq1:nacionalidad/cat:codigoExterno)}</adq1:nacionalidad>
            <adq1:monto>{fn:data($concursante/adq1:monto/com:importe)}</adq1:monto>
        </adq:adjudicados>
        }
    </adq:PublicarInformeResultadosAdjudicado>
};

local:funcConsultaradquisicion_to_publicarinformereporteadjudicado($outInvokeConsultarAdquisicion.response)
