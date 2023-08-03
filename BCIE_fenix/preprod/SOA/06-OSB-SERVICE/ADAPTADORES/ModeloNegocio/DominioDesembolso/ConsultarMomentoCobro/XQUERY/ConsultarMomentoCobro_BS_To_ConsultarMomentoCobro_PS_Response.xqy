xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarMomentoCobro_DB";
(:: import schema at "../XSD/ConsultarMomentoCobro_DB.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarMomentoCobro_DBOutputCollection as element() (:: schema-element(ns1:ConsultarMomentoCobro_DBOutputCollection) ::) external;

declare function local:func($ConsultarMomentoCobro_DBOutputCollection as element() (:: schema-element(ns1:ConsultarMomentoCobro_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarMomentoCobroResponse) ::) {
    <ns2:ConsultarMomentoCobroResponse>
        {
        for $momentoCobro in $ConsultarMomentoCobro_DBOutputCollection/ns1:ConsultarMomentoCobro_DBOutput
        where $momentoCobro/ns1:ID = 5
        return
        <ns2:MomentoCobro>
            <cat:Id>{fn:data($momentoCobro/ns1:ID)}</cat:Id>
            <cat:Descripcion>{fn:data($momentoCobro/ns1:DESCRIPCION)}</cat:Descripcion>
            <cat:DescripcionCorta>{fn:data($momentoCobro/ns1:DESCRIPCION_CORTA)}</cat:DescripcionCorta>
            <cat:estatus>{fn:data($momentoCobro/ns1:BAN_ESTATUS)}</cat:estatus>
            <cat:codigoExterno>{fn:data($momentoCobro/ns1:COD_EXTERNO)}</cat:codigoExterno>
        </ns2:MomentoCobro>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{
                if (count($ConsultarMomentoCobro_DBOutputCollection/ns1:ConsultarMomentoCobro_DBOutput) > 0)
                then 'Consulta Exitosa'
                else 'No existen registros'
                }
            </res:message>
        </ns2:Resultado>
    </ns2:ConsultarMomentoCobroResponse>
};

local:func($ConsultarMomentoCobro_DBOutputCollection)