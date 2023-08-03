xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/CrearActualizarReglasDesembolso_DB";
(:: import schema at "../XSD/CrearActualizarReglasDesembolso_DB_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:CrearActualizarReglasDesembolsoResponse) ::) {
    <ns2:CrearActualizarReglasDesembolsoResponse>
    {
    for $idInsertado in $OutputParameters/ns1:P_LISTA_REGLA_DESEMBOLSO_OUT/ns1:P_LISTA_REGLA_DESEMBOLSO_ITEM
    return
        <ns2:ReglasDesembolso>
            <reg:Id>{fn:data($idInsertado/ns1:ID_TCA_REGLA)}</reg:Id>
            <reg:Exceptuado>
                {
                    if ($idInsertado/ns1:ID)
                    then <cat:Id>{fn:data($idInsertado/ns1:ID)}</cat:Id>
                    else ()
                }
            </reg:Exceptuado>
        </ns2:ReglasDesembolso>
        }
        <ns2:Resultado>
            <res:result>{
            if (fn:data($OutputParameters/ns1:P_CODIGO_RES)= 0)then 'OK' else 'ERROR'}</res:result>
            <res:message>{fn:data($OutputParameters/ns1:P_MENSAJE)}</res:message>
        </ns2:Resultado>
    </ns2:CrearActualizarReglasDesembolsoResponse>
};

local:func($OutputParameters)
