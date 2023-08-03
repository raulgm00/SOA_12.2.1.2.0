xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/EvaluacionMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Evaluacion/V1/Schema/EvaluacionMO.xsd" ::)
declare namespace ns3="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarOperacionByIdOperacion";
(:: import schema at "../../ConsultarOperacionByIDOperacion/XSD/ConsultarOperacionByIdOperacion.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarCuestionariodb";
(:: import schema at "../XSD/ConsultarCuestionariodb_sp.xsd" ::)

declare variable $ConsultarCuestionarioRequest as element() (:: schema-element(ns1:ConsultarCuestionarioRequest) ::) external;
declare variable $ConsultarOperacionByIdOperacion as element() (:: schema-element(ns3:ConsultarOperacionByIdOperacionOutputCollection) ::) external;

declare function local:func($ConsultarCuestionarioRequest as element() (:: schema-element(ns1:ConsultarCuestionarioRequest) ::), 
                            $ConsultarOperacionByIdOperacion as element() (:: schema-element(ns3:ConsultarOperacionByIdOperacionOutputCollection) ::)) 
                            as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:PCS_INSTITUCIONAL>{fn:data($ConsultarOperacionByIdOperacion/ns3:ConsultarOperacionByIdOperacionOutput/ns3:SECTOR_COD_EXTERNO)}</ns2:PCS_INSTITUCIONAL>
        <ns2:PCS_IBCIE>{fn:data($ConsultarCuestionarioRequest/ns1:SectorIBCIE)}</ns2:PCS_IBCIE>
        <ns2:PCSS_IBCIE>{fn:data($ConsultarCuestionarioRequest/ns1:subSectorIBCIE)}</ns2:PCSS_IBCIE>
    </ns2:InputParameters>
};

local:func($ConsultarCuestionarioRequest, $ConsultarOperacionByIdOperacion)
