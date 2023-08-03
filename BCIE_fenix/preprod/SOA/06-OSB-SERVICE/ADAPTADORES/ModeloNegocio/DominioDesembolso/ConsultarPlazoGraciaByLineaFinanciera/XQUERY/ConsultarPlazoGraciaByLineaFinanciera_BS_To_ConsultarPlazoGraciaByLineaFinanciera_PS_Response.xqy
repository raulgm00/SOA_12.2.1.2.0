xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarPlazoGraciaByLineaFinanciera_DB";
(:: import schema at "../XSD/ConsultarPlazoGraciaByLineaFinanciera_DB.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarPlazoGraciaByLineaFinanciera_DBOutputCollection as element() (:: schema-element(ns1:ConsultarPlazoGraciaByLineaFinanciera_DBOutputCollection) ::) external;

declare function local:func($ConsultarPlazoGraciaByLineaFinanciera_DBOutputCollection as element() (:: schema-element(ns1:ConsultarPlazoGraciaByLineaFinanciera_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarPlazoGraciaByLineaFinancieraResponse) ::) {
    <ns2:ConsultarPlazoGraciaByLineaFinancieraResponse>
    {
      for $ConsultarPlazoGraciaByLineaFinanciera in $ConsultarPlazoGraciaByLineaFinanciera_DBOutputCollection/ns1:ConsultarPlazoGraciaByLineaFinanciera_DBOutput
      return
        <ns2:LimitePlazo>
            <com:Tipo>
                <cat:DescripcionCorta>{fn:data($ConsultarPlazoGraciaByLineaFinanciera/ns1:CODIGO_DESTINO)}</cat:DescripcionCorta>
                <cat:codigoExterno>{fn:data($ConsultarPlazoGraciaByLineaFinanciera/ns1:TIPO_PLAZO)}</cat:codigoExterno>
            </com:Tipo>
            <com:Valor>{fn:data($ConsultarPlazoGraciaByLineaFinanciera/ns1:PLAZO)}</com:Valor>
            <des:TipoLimite>
                <cat:Descripcion>{fn:data($ConsultarPlazoGraciaByLineaFinanciera/ns1:DESCRIPCION_TIPO_LIMITE)}</cat:Descripcion>
                <cat:codigoExterno>{fn:data($ConsultarPlazoGraciaByLineaFinanciera/ns1:TIPO_LIMITE)}</cat:codigoExterno>
            </des:TipoLimite>
        </ns2:LimitePlazo>
    }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{
                if (count($ConsultarPlazoGraciaByLineaFinanciera_DBOutputCollection/ns1:ConsultarPlazoGraciaByLineaFinanciera_DBOutput) > 0)
                then 'Consulta Exitosa'
                else 'No existen registros'
                }
            </res:message>
        </ns2:Resultado>
    </ns2:ConsultarPlazoGraciaByLineaFinancieraResponse>
};

local:func($ConsultarPlazoGraciaByLineaFinanciera_DBOutputCollection)
