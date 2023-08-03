xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ProcesarEnmienda";
(:: import schema at "../XSD/ProcesarEnmienda_sp.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare variable $ProcesarEnmiendaRequest as element() (:: schema-element(ns1:ProcesarEnmiendaRequest) ::) external;

declare function local:func($ProcesarEnmiendaRequest as element() (:: schema-element(ns1:ProcesarEnmiendaRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:PID_OPERACION>{fn:data($ProcesarEnmiendaRequest/mat:PID_OPERACION)}</ns2:PID_OPERACION>
        <ns2:PID_ENMIENDA>{fn:data($ProcesarEnmiendaRequest/mat:PID_ENMIENDA)}</ns2:PID_ENMIENDA>
        <ns2:PID_TCC>{fn:data($ProcesarEnmiendaRequest/mat:PID_TCC)}</ns2:PID_TCC>
        <ns2:PIDTAREA>{fn:data($ProcesarEnmiendaRequest/mat:PIDTAREA)}</ns2:PIDTAREA> 
    </ns2:InputParameters>
};

local:func($ProcesarEnmiendaRequest)
