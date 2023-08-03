xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/OperacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Operacion/V1/Schema/OperacionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarOperacionesAsociadas_DB";
(:: import schema at "../XSD/ConsultarOperacionesAsociadas_DB.xsd" ::)

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare variable $ConsultarOperacionesAsociadas_DBOutputCollection as element() (:: schema-element(ns1:ConsultarOperacionesAsociadas_DBOutputCollection) ::) external;

declare function local:func($ConsultarOperacionesAsociadas_DBOutputCollection as element() (:: schema-element(ns1:ConsultarOperacionesAsociadas_DBOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarOperacionesAsociadasResponse) ::) {
    <ns2:ConsultarOperacionesAsociadasResponse>
        {
        for $idOperacionAsociada in distinct-values($ConsultarOperacionesAsociadas_DBOutputCollection/ns1:ConsultarOperacionesAsociadas_DBOutput/ns1:ID_OP_ASOC)
        let $operacionAsociada := $ConsultarOperacionesAsociadas_DBOutputCollection/ns1:ConsultarOperacionesAsociadas_DBOutput[ns1:ID_OP_ASOC = $idOperacionAsociada][1]
        return
            <ns2:Operacion>
                <ope:idOperacion>{fn:data($operacionAsociada/ns1:ID_OP_ASOC)}</ope:idOperacion>
                {
                for $idContrato in distinct-values($ConsultarOperacionesAsociadas_DBOutputCollection/ns1:ConsultarOperacionesAsociadas_DBOutput/ns1:ID_CONTRATO)
                let $contrato := $operacionAsociada[ns1:ID_CONTRATO = $idContrato][1]
                where $contrato/ns1:ID_CONTRATO = $idContrato
                return
                <ope:contrato>
                    <con:idContrato>{fn:data($contrato/ns1:ID_CONTRATO)}</con:idContrato>
                    {
                    for $idLineaCredito in distinct-values($ConsultarOperacionesAsociadas_DBOutputCollection/ns1:ConsultarOperacionesAsociadas_DBOutput[ns1:ID_CONTRATO = $contrato/ns1:ID_CONTRATO]/ns1:ID_LINEA_CREDITO)
                    let $lineaCredito := $ConsultarOperacionesAsociadas_DBOutputCollection/ns1:ConsultarOperacionesAsociadas_DBOutput[ns1:ID_LINEA_CREDITO = $idLineaCredito]
                    return
                    <con:LineaCredito>
                        <lin:idLineaCredito>{fn:data($lineaCredito/ns1:ID_LINEA_CREDITO)}</lin:idLineaCredito>
                    </con:LineaCredito>
                    }
                </ope:contrato>
                }
            </ns2:Operacion>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>
            {
            if (count($ConsultarOperacionesAsociadas_DBOutputCollection/ns1:ConsultarOperacionesAsociadas_DBOutput) > 0)
            then 'Consulta Exitosa'
            else 'No existen registros'
            }
            </res:message>
        </ns2:Resultado>
    </ns2:ConsultarOperacionesAsociadasResponse>
};

local:func($ConsultarOperacionesAsociadas_DBOutputCollection)
