xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace adq="http://www.bcie.org/AdquisicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarNoObjecion_DB";
(:: import schema at "../../ConsultarNoObjecion/XSD/ConsultarNoObjecion_DB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace ns2 = "http://www.bcie.org/AdquisicionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarNoObjecionOutputCollection as element() (:: schema-element(ns1:ConsultarNoObjecion_DBOutputCollection) ::) external;

declare function local:func($ConsultarNoObjecionOutputCollection as element() (:: schema-element(ns1:ConsultarNoObjecion_DBOutputCollection) ::)) 
                            as element() (:: schema-element(adq:ConsultarNoObjecionResponse) ::) {
    <ns2:ConsultarNoObjecionResponse>
{        
      for $noObjecionId in distinct-values($ConsultarNoObjecionOutputCollection/ns1:ConsultarNoObjecion_DBOutput/ns1:ID_NO_OBJECION)
      let $noObjecion := $ConsultarNoObjecionOutputCollection/ns1:ConsultarNoObjecion_DBOutput[ns1:ID_NO_OBJECION = $noObjecionId]
        return 
        <adq:NoObjecion>
            <ns2:idNoObjecion>{fn:data($noObjecion[1]/ns1:ID_NO_OBJECION)}</ns2:idNoObjecion>
            <ns2:tipoNoObjecion>
                <cat:Id>{fn:data($noObjecion[1]/ns1:ID_TIPO_OBJECION)}</cat:Id>
                <cat:Descripcion>{fn:data($noObjecion[1]/ns1:TIPO_OBJECION)}</cat:Descripcion>
                {
                  for $objecionRelacion in  distinct-values($noObjecion/ns1:OBJECION_RELACION)
                  return 
                    <ns2:NoObjecionRelacion>{data($objecionRelacion)}</ns2:NoObjecionRelacion>
                }
            </ns2:tipoNoObjecion>
            <ns2:fechaInicioDoctoBase>{fn:substring(fn:string($noObjecion[1]/ns1:FECHA_INICIO_DISP_DOCTO_BASE ),1,10)}</ns2:fechaInicioDoctoBase>
            <ns2:fechaFinDoctoBase>{fn:substring(fn:string($noObjecion[1]/ns1:FECHA_FIN_DISP_DOCTO_BASE ),1,10)}</ns2:fechaFinDoctoBase>
            <ns2:fechaRecepcionPropuesta>{fn:substring(fn:string($noObjecion[1]/ns1:FECHA_RECEPCION_PROPUESTA ),1,10)}</ns2:fechaRecepcionPropuesta>
            <ns2:lugarObtenerDoctoBase>{fn:data($noObjecion[1]/ns1:LUGAR_OBTENER_DOCTO_BASE)}</ns2:lugarObtenerDoctoBase>
            <ns2:correoInfoAdicional>{fn:data($noObjecion[1]/ns1:CORREO_INFORMACION_ADICIONAL)}</ns2:correoInfoAdicional>
            <ns2:direccionPresentacionPropuesta>{fn:data($noObjecion[1]/ns1:DIR_PRESENTACION_PROPUESTA)}</ns2:direccionPresentacionPropuesta>
            <ns2:resultadoProceso>
                <cat:Id>{fn:data($noObjecion[1]/ns1:ID_TCA_RESULTADO_PROCESO)}</cat:Id>
                <cat:Descripcion>{fn:data($noObjecion[1]/ns1:RESULTADO_PROCESO)}</cat:Descripcion>
            </ns2:resultadoProceso>
            <ns2:publicado>{
                if($noObjecion[1]/ns1:PUBLICADO = 1) then true() else false()
            }</ns2:publicado>
             <ns2:otorgoNoObjecion>{
                if(string(data($noObjecion[1]/ns1:OTORGO_NO_OBJECION)) = '1') then true() else false()
            }</ns2:otorgoNoObjecion>
            {
            for $concursante in $noObjecion[fn:string-length(string(ns1:ID_CONCURSANTE)) > 0]
            return
            <ns2:concursante>
                <ns2:idConcursante>{fn:data($concursante/ns1:ID_CONCURSANTE)}</ns2:idConcursante>
                <ns2:tipoPerfil>
                    <cat:Id>{fn:data($concursante/ns1:ID_TIPO_PERFIL)}</cat:Id>
                    <cat:Descripcion>{fn:data($concursante/ns1:TIPO_PERFIL)}</cat:Descripcion>
                </ns2:tipoPerfil>
                <ns2:nombre>{fn:data($concursante/ns1:NOMBRE_ADJUDICATARIO)}</ns2:nombre>
                <ns2:nacionalidad>
                   <cat:Id>{fn:data($concursante/ns1:NACIONALIDAD_ID)}</cat:Id>
                   <cat:Descripcion>{fn:data($concursante/ns1:NACIONALIDAD_DESCRIPCION)}</cat:Descripcion>
                   <cat:codigoExterno>{fn:data($concursante/ns1:NACIONALIDAD_ADJUDICATARIO)}</cat:codigoExterno>
                </ns2:nacionalidad>
                <ns2:monto>
                    <com:importe>{fn:data($concursante/ns1:MONTO_ADJUDICATARIO)}</com:importe>
                    <com:moneda>
                        <cat:Id>{fn:data($concursante/ns1:ID_TCA_MONEDA)}</cat:Id>
                        <cat:DescripcionCorta>{fn:data($concursante/ns1:MONEDA)}</cat:DescripcionCorta>
                    </com:moneda>
                </ns2:monto>
            </ns2:concursante>
            }
        </adq:NoObjecion>
 }       
        <adq:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
        </adq:Resultado>
    </ns2:ConsultarNoObjecionResponse>
};

local:func($ConsultarNoObjecionOutputCollection)