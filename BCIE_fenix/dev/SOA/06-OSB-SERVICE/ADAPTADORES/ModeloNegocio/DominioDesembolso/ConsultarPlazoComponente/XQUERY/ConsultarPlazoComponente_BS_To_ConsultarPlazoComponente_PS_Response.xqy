xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarPlazoComponente_DB";
(:: import schema at "../XSD/ConsultarPlazoComponente_DB.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarPlazoComponente_DBOutputCollection as element() (:: schema-element(ns1:ConsultarPlazoComponente_DBOutputCollection) ::) external;

declare function local:func($ConsultarPlazoComponente_DBOutputCollection as element() (:: schema-element(ns1:ConsultarPlazoComponente_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarPlazoComponenteResponse) ::) {
    <ns2:ConsultarPlazoComponenteResponse>
        {
            for $ConsultarPlazoComponente_DBOutput in $ConsultarPlazoComponente_DBOutputCollection/ns1:ConsultarPlazoComponente_DBOutput
            return 
            <ns2:Componente>
                <cat:codigoExterno>{fn:data($ConsultarPlazoComponente_DBOutput/ns1:CODIGO_COMPONENTE_FLEXCUBE)}</cat:codigoExterno>
                <des:TipoComponente>
                    <cat:codigoExterno>{fn:data($ConsultarPlazoComponente_DBOutput/ns1:CODIGO_COMPONENTE)}</cat:codigoExterno>
                </des:TipoComponente>
                <des:Plazo>
                    <com:Tipo>
                        <cat:codigoExterno>{fn:data($ConsultarPlazoComponente_DBOutput/ns1:TIPO_PLAZO)}</cat:codigoExterno>
                    </com:Tipo>
                    <com:Valor>{fn:data($ConsultarPlazoComponente_DBOutput/ns1:PLAZO)}</com:Valor>
                </des:Plazo>
            </ns2:Componente>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>{
                if (count($ConsultarPlazoComponente_DBOutputCollection/ns1:ConsultarPlazoComponente_DBOutput) > 0)
                then 'Consulta Exitosa'
                else 'No existen registros'
                }
            </res:message>
        </ns2:Resultado>
    </ns2:ConsultarPlazoComponenteResponse>
};

local:func($ConsultarPlazoComponente_DBOutputCollection)
