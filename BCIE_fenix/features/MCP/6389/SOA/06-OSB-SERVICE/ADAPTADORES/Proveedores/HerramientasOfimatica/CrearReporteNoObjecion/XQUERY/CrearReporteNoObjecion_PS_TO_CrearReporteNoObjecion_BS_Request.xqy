xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://service.org.bcie.www/";
(:: import schema at "../XSD/ReporteNoObjeciones.xsd" ::)
declare namespace ns1="http://www.bcie.org/AdquisicionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)

declare namespace rep = "http://www.bcie.org/herramientaOfismatica/ReporteNoObjeciones";

declare namespace adq = "http://www.bcie.org/AdquisicionBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare variable $CrearReporteNoObjecionRequest as element() (:: schema-element(ns1:CrearReporteNoObjecionRequest) ::) external;

declare function local:func($CrearReporteNoObjecionRequest as element() (:: schema-element(ns1:CrearReporteNoObjecionRequest) ::)) as element() (:: schema-element(ns2:generarReporteNoObjeciones) ::) {
    <ns2:generarReporteNoObjeciones>
        <RepNoObjeciones>
            <rep:ReporteTemplate >
                <rep:Pais>{fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:cliente/cli:pais/cat:Descripcion)}</rep:Pais>
                <IDTipoNoObjecion>{fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:noObjecion/adq:tipoNoObjecion/cat:Id)}</IDTipoNoObjecion>
                <rep:NombreOperacion>{concat(fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:operacion/ope:nombre),' (',fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:operacion/ope:idOperacion),')')}</rep:NombreOperacion>
                <rep:TituloProceso>{fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:titulo)}</rep:TituloProceso>
                <rep:NumeroProceso>{fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:numero)}</rep:NumeroProceso>
                {
                if ($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:noObjecion/adq:tipoNoObjecion/cat:Id = 2)then
                <rep:Aviso>
                    <rep:modalidadProceso>{fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:tipoModalidad/cat:Descripcion)}</rep:modalidadProceso>
                    <rep:nombreOperacion>{fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:operacion/ope:nombre)}</rep:nombreOperacion>
                    <rep:nombreOrganismoEjecutor>{fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:operacion/ope:unidadEjecutora)}</rep:nombreOrganismoEjecutor>
                    <rep:objetivoAdquisicion>{fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:objetivo)}</rep:objetivoAdquisicion>
                    <rep:LugarPropuesta>{fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:noObjecion/adq:lugarObtenerDoctoBase)}</rep:LugarPropuesta>
                    <rep:direccionPropuesta>{fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:noObjecion/adq:direccionPresentacionPropuesta)}</rep:direccionPropuesta>
                    <rep:fechaInicio>{local:formatDate(fn:substring(fn:string($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:noObjecion/adq:fechaInicioDoctoBase),1,10))}</rep:fechaInicio>
                    <rep:fechaFin>{local:formatDate( fn:substring(fn:string($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:noObjecion/adq:fechaFinDoctoBase),1,10))}</rep:fechaFin>
                    <rep:direccionElectronica>{fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:noObjecion/adq:correoInfoAdicional)}</rep:direccionElectronica>
                    <rep:fechaRecepcionPropuestas>{local:formatDate( fn:substring(fn:string($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:noObjecion/adq:fechaRecepcionPropuesta),1,10))}</rep:fechaRecepcionPropuestas>
                </rep:Aviso>
                else()
                }
                {
                if (fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:noObjecion/adq:tipoNoObjecion/cat:Id) = 7 and fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:noObjecion/adq:resultadoProceso/cat:Id = 1) )then
                <rep:informeResultadosAdjudicados>
                    <rep:Adjudicado>{fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:noObjecion/adq:resultadoProceso/cat:Descripcion)}</rep:Adjudicado>
                    {
                    for $concursante in $CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:noObjecion/adq:concursante[adq:tipoPerfil/cat:Id != 1]
                    return
                    <rep:lstAdjudicados>
                        <rep:nombreAdjudicatario>{fn:data($concursante/adq:nombre)}</rep:nombreAdjudicatario>
                        <rep:nacionalidad>{fn:data($concursante/adq:nacionalidad/cat:Descripcion)}</rep:nacionalidad>
                        <rep:montoAdjudicadoUSD>{fn:data($concursante/adq:monto/com:importe)}</rep:montoAdjudicadoUSD>
                    </rep:lstAdjudicados>
                    }
                </rep:informeResultadosAdjudicados>
                else()
                }
                 {
                if (fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:noObjecion/adq:tipoNoObjecion/cat:Id) = 7 and fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:noObjecion/adq:resultadoProceso/cat:Id != 1) )then
                <rep:informeResultadosOtros>
                    <rep:estatusProceso>{fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:noObjecion/adq:resultadoProceso/cat:Descripcion)}</rep:estatusProceso>
                </rep:informeResultadosOtros>
                else()
                }
                 {
                if (fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:noObjecion/adq:tipoNoObjecion/cat:Id) = 9)then
                <rep:informeResultadosAdjudicados>
                    <rep:Adjudicado>{fn:data($CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:noObjecion/adq:resultadoProceso/cat:Descripcion)}</rep:Adjudicado>
                    {
                    for $concursante in $CrearReporteNoObjecionRequest/ns1:Adquisicion/adq:noObjecion/adq:concursante
                    return
                    <rep:lstAdjudicados>
                        <rep:nombreAdjudicatario>{fn:data($concursante/adq:nombre)}</rep:nombreAdjudicatario>
                        <rep:nacionalidad>{fn:data($concursante/adq:nacionalidad/cat:Descripcion)}</rep:nacionalidad>
                        <rep:montoAdjudicadoUSD>{fn:data($concursante/adq:monto/com:importe)}</rep:montoAdjudicadoUSD>
                    </rep:lstAdjudicados>
                    }
                </rep:informeResultadosAdjudicados>
                else()
                }
            </rep:ReporteTemplate>
        </RepNoObjeciones>
    </ns2:generarReporteNoObjeciones>
};
declare function local:formatDate($date as xs:string)
as xs:string {
 if( fn:contains(fn:substring($date,6,7),'01/') or fn:contains(fn:substring($date,6,7),'01-'))then
    fn:concat ( fn:substring(fn:string($date),1,5),'Ene',fn:substring(fn:string($date),8,10))
 else if( fn:contains(fn:substring($date,6,7),'02/') or fn:contains(fn:substring($date,6,7),'02-'))then
    fn:concat ( fn:substring(fn:string($date),1,5),'Feb',fn:substring(fn:string($date),8,10))
 else if( fn:contains(fn:substring($date,6,7),'03/') or fn:contains(fn:substring($date,6,7),'03-'))then
    fn:concat ( fn:substring(fn:string($date),1,5),'Mar',fn:substring(fn:string($date),8,10))
 else if( fn:contains(fn:substring($date,6,7),'04/') or fn:contains(fn:substring($date,6,7),'04-'))then
    fn:concat ( fn:substring(fn:string($date),1,5),'Abr',fn:substring(fn:string($date),8,10))
 else if( fn:contains(fn:substring($date,6,7),'05/') or fn:contains(fn:substring($date,6,7),'05-'))then
    fn:concat ( fn:substring(fn:string($date),1,5),'May',fn:substring(fn:string($date),8,10))
 else if( fn:contains(fn:substring($date,6,7),'06/') or fn:contains(fn:substring($date,6,7),'06-'))then
    fn:concat ( fn:substring(fn:string($date),1,5),'Jun',fn:substring(fn:string($date),8,10))
 else if( fn:contains(fn:substring($date,6,7),'07/') or fn:contains(fn:substring($date,6,7),'07-'))then
    fn:concat ( fn:substring(fn:string($date),1,5),'Jul',fn:substring(fn:string($date),8,10))
 else if( fn:contains(fn:substring($date,6,7),'08/') or fn:contains(fn:substring($date,6,7),'08-'))then
    fn:concat ( fn:substring(fn:string($date),1,5),'Ago',fn:substring(fn:string($date),8,10))
 else if( fn:contains(fn:substring($date,6,7),'09/') or fn:contains(fn:substring($date,6,7),'09-'))then
    fn:concat ( fn:substring(fn:string($date),1,5),'Sep',fn:substring(fn:string($date),8,10))
 else if( fn:contains(fn:substring($date,6,7),'10/') or fn:contains(fn:substring($date,6,7),'10-'))then
    fn:concat ( fn:substring(fn:string($date),1,5),'Oct',fn:substring(fn:string($date),8,10))
 else if( fn:contains(fn:substring($date,6,7),'11/') or fn:contains(fn:substring($date,6,7),'11-'))then
    fn:concat ( fn:substring(fn:string($date),1,5),'Nov',fn:substring(fn:string($date),8,10))
 else if( fn:contains(fn:substring($date,6,7),'12/') or fn:contains(fn:substring($date,6,7),'12-'))then
    fn:concat ( fn:substring(fn:string($date),1,5),'Dic',fn:substring(fn:string($date),8,10))
 else 
 ''     
};

local:func($CrearReporteNoObjecionRequest)