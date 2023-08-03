xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/AdquisicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/top/CrearActualizarNoObjecion";
(:: import schema at "../XSD/CrearActualizarNoObjecion_table.xsd" ::)

declare namespace adq = "http://www.bcie.org/AdquisicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $CrearActualizarNoObjecionRequest as element() (:: schema-element(ns1:CrearActualizarNoObjecionRequest) ::) external;

declare function local:func($CrearActualizarNoObjecionRequest as element() (:: schema-element(ns1:CrearActualizarNoObjecionRequest) ::)) as element() (:: schema-element(ns2:NoObjecionCollection) ::) {
    <ns2:NoObjecionCollection>
        <ns2:NoObjecion>
            <ns2:id>{fn:data($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:idNoObjecion)}</ns2:id>
            <ns2:idAdquisicion>{fn:data($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:idAdquisicion)}</ns2:idAdquisicion>
            <ns2:idTcaTipoNoObjecion>{fn:data($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:tipoNoObjecion/cat:Id)}</ns2:idTcaTipoNoObjecion>
            <ns2:fechaPublicacion>{fn:data($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:fechaPublicacion)}</ns2:fechaPublicacion>
            <ns2:fechaInicioDispDoctoBase>{fn:data($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:fechaInicioDoctoBase)}</ns2:fechaInicioDispDoctoBase>
            <ns2:fechaFinDispDoctoBase>{fn:data($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:fechaFinDoctoBase)}</ns2:fechaFinDispDoctoBase>
            <ns2:fechaRecepcionPropuesta>{fn:data($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:fechaRecepcionPropuesta)}</ns2:fechaRecepcionPropuesta>
            <ns2:lugarObtenerDoctoBase>{fn:data($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:lugarObtenerDoctoBase)}</ns2:lugarObtenerDoctoBase>
            <ns2:correoInformacionAdicional>{fn:data($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:correoInfoAdicional)}</ns2:correoInformacionAdicional>
            <ns2:dirPresentacionPropuesta>{fn:data($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:direccionPresentacionPropuesta)}</ns2:dirPresentacionPropuesta>
            <ns2:idTcaResultadoProceso>{fn:data($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:resultadoProceso/cat:Id)}</ns2:idTcaResultadoProceso>
            <ns2:seHaRevisadoPublicacion>
            {
              if ($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:revisadoPublicacion/text()!='') 
              then
                if ($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:revisadoPublicacion/text() = 'true') 
                then 
                  1 
                else 
                  0
              else()
            }
            </ns2:seHaRevisadoPublicacion>

            <ns2:numeroCorrespondencia>{fn:data($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:numeroCorrespondencia)}</ns2:numeroCorrespondencia>
            <ns2:seOtorgoNoObjecion>
            {
              if ($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:otorgoNoObjecion/text()!='') 
              then
                if ($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:otorgoNoObjecion/text()='true')
                then 
                  1 
                else 
                  0
              else()
            }
            </ns2:seOtorgoNoObjecion>
            <ns2:fechaFirmaDocto>{fn:data($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:fechaFirmaDocto)}</ns2:fechaFirmaDocto>
            {
            if($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:estado/text()!='') then
              <ns2:banEstatus>{if (string(fn:data($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:idNoObjecion))='') then 1 else if($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:estado/text()='true')then 1 else 0}</ns2:banEstatus>
            else ()  
            }
            <ns2:fechaRegistro>{if(string(fn:data($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:idNoObjecion))='') then fn:current-date() else ()}</ns2:fechaRegistro>
            <ns2:enProcesoBpm>{fn:data($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:enProcesoBpm)}</ns2:enProcesoBpm>
            <ns2:publicada>{
              if($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:publicado/text()!= '') then
                if($CrearActualizarNoObjecionRequest/ns1:NoObjecion/adq:publicado/text() = 'true') then 1 else 0
              else()
              }
            </ns2:publicada>
        </ns2:NoObjecion>
    </ns2:NoObjecionCollection>
};

local:func($CrearActualizarNoObjecionRequest)
