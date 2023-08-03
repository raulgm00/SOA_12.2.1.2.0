xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarLimitesPrograma_DB";
(:: import schema at "../XSD/ConsultarLimitesPrograma_DB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarLimitesPrograma_DBOutputCollection as element() (:: schema-element(ns1:ConsultarLimitesPrograma_DBOutputCollection) ::) external;

declare function local:func($ConsultarLimitesPrograma_DBOutputCollection as element() (:: schema-element(ns1:ConsultarLimitesPrograma_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarLimitesProgramaResponse) ::) {
    <ns2:ConsultarLimitesProgramaResponse>
    {
      for $LimitePrograma in $ConsultarLimitesPrograma_DBOutputCollection/ns1:ConsultarLimitesPrograma_DBOutput
      return
        <ns2:LimiteGlobalLineaFinanciera>
            <des:Codigo>
                <cat:Id></cat:Id>
                <cat:Descripcion>{fn:data($LimitePrograma/ns1:DESCRIPCION)}</cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno>{fn:data($LimitePrograma/ns1:CODIGO_LIMITE)}</cat:codigoExterno>
            </des:Codigo>
            <des:LineaFinanciera>
                <des:codigo>{fn:data($LimitePrograma/ns1:CODIGO_LINEA_FINANCIERA)}</des:codigo>
            </des:LineaFinanciera>
            <des:Monto>
                <com:importe>{fn:data($LimitePrograma/ns1:LIMITE)}</com:importe>
            </des:Monto>
            <des:LineasGrupo>{fn:data($LimitePrograma/ns1:LISTALINEAFINANCIERA)}</des:LineasGrupo>
            <des:MonedasGrupo>{fn:data($LimitePrograma/ns1:LISTAMONEDA)}</des:MonedasGrupo>
            { if (fn:data($LimitePrograma/ns1:CONCENTRACION_PAIS))
              then <des:ConcentradoPais>{fn:data($LimitePrograma/ns1:CONCENTRACION_PAIS)}</des:ConcentradoPais>
              else ()
            }
        </ns2:LimiteGlobalLineaFinanciera>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{
            if (count($ConsultarLimitesPrograma_DBOutputCollection/ns1:ConsultarLimitesPrograma_DBOutput) > 0)
            then 'Consulta Exitosa'
            else 'No existen registros'
            }</res:message>
        </ns2:Resultado>
    </ns2:ConsultarLimitesProgramaResponse>
};

local:func($ConsultarLimitesPrograma_DBOutputCollection)
