xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/CondicionMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/Condicion/V1/Schema/CondicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarCategoriaByCondicion";
(:: import schema at "../XSD/ConsultarTreCategoriaByIdCondicion.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarCategoriaByCondicionOutputCollection as element() (:: schema-element(ns1:ConsultarCategoriaByCondicionOutputCollection) ::) external;

declare function local:func($ConsultarCategoriaByCondicionOutputCollection as element() (:: schema-element(ns1:ConsultarCategoriaByCondicionOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarTreCategoriaByIdCondicionResponse) ::) {
   
    <ns2:ConsultarTreCategoriaByIdCondicionResponse>
    {
    for $TreCat in $ConsultarCategoriaByCondicionOutputCollection/ns1:ConsultarCategoriaByCondicionOutput
    return
        <ns2:TreCategoriaCondicion>
            <con:id>{fn:data($TreCat/ns1:ID)}</con:id>
            <con:descripcion>{fn:data($TreCat/ns1:DESCRIPCION)}</con:descripcion>
            <con:descripcionCorta>{fn:data($TreCat/ns1:DESCRIPCION_CORTA)}</con:descripcionCorta>
            <con:codigoExterno>{fn:data($TreCat/ns1:COD_EXTERNO)}</con:codigoExterno>
        </ns2:TreCategoriaCondicion>
        }
    <ns2:Resultado>
        <res:result>OK</res:result>
        <res:message>Consulta Exitosa</res:message>
      
    </ns2:Resultado>
    </ns2:ConsultarTreCategoriaByIdCondicionResponse>
};

local:func($ConsultarCategoriaByCondicionOutputCollection)
