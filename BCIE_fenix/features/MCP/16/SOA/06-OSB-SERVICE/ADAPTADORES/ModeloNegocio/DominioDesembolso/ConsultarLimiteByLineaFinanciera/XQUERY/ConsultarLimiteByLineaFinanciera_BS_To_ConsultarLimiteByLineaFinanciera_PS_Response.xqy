xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarLimiteByLineaFinanciera_DB";
(:: import schema at "../XSD/ConsultarLimiteByLineaFinanciera_DB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarReglasDesembolso_DBOutputCollection as element() (:: schema-element(ns1:ConsultarLimiteByLineaFinanciera_DBOutputCollection) ::) external;

declare function local:func($ConsultarReglasDesembolso_DBOutputCollection as element() (:: schema-element(ns1:ConsultarLimiteByLineaFinanciera_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarLimiteByLineaFinancieraResponse) ::) {
    <ns2:ConsultarLimiteByLineaFinancieraResponse>
    {for $Limite in $ConsultarReglasDesembolso_DBOutputCollection/ns1:ConsultarLimiteByLineaFinanciera_DBOutput
      return
        <ns2:LimiteGlobalLineaFinanciera>
            <des:Codigo>
                <cat:Id>{fn:data($Limite/ns1:CODIGO_LIMITE)}</cat:Id>
                <cat:Descripcion>{fn:data($Limite/ns1:DESCRIPCION)}</cat:Descripcion>
                <cat:DescripcionCorta></cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno>{fn:data($Limite/ns1:CODIGO_LIMITE)}</cat:codigoExterno>
            </des:Codigo>
            <des:LineaFinanciera>
                <des:codigo>{fn:data($Limite/ns1:CODIGO_LINEA_FINANCIERA)}</des:codigo>
                <des:descripcion></des:descripcion>
                <des:activo></des:activo>
            </des:LineaFinanciera>
            <des:Monto>
                <com:tipo>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com:tipo>
                <com:importe>{fn:data($Limite/ns1:LIMITE)}</com:importe>
                <com:moneda>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com:moneda>
            </des:Monto>
            <des:LineasGrupo>{fn:data($Limite/ns1:LISTALINEAFINANCIERA)}</des:LineasGrupo>
            <des:MonedasGrupo>{fn:data($Limite/ns1:LISTAMONEDA)}</des:MonedasGrupo>
            <des:ConcentradoPais>{fn:data($Limite/ns1:CONCENTRACION_PAIS)}</des:ConcentradoPais>
        </ns2:LimiteGlobalLineaFinanciera>
        }
    <ns2:Resultado>
        <res:result>OK</res:result>
        <res:message>{
            if (count($ConsultarReglasDesembolso_DBOutputCollection/ns1:ConsultarLimiteByLineaFinanciera_DBOutput) > 0)
            then 'Consulta Exitosa'
            else 'No existen registros'
            }</res:message>
        </ns2:Resultado>
    </ns2:ConsultarLimiteByLineaFinancieraResponse>
    
};

local:func($ConsultarReglasDesembolso_DBOutputCollection)
