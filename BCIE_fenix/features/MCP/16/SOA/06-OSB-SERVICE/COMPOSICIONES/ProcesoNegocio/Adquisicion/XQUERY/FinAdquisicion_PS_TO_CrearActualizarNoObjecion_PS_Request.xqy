xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/AdquisicionMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Adquisicion/V1/Schema/AdquisicionMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/AdquisicionProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA09/AdquisicionProcess.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace adq = "http://www.bcie.org/AdquisicionBO";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare variable $FinAdquisicion as element() (:: schema-element(ns1:FinAdquisicion) ::) external;

declare function local:func($FinAdquisicion as element() (:: schema-element(ns1:FinAdquisicion) ::)) as element() (:: schema-element(ns2:CrearActualizarNoObjecionRequest) ::) {
    <ns2:CrearActualizarNoObjecionRequest>
        <ns2:NoObjecion>
            <adq:idNoObjecion>{fn:data($FinAdquisicion/ns1:Header/ns3:ParameterType[ns3:parameterName ='ID_NO_OBJECION']/ns3:parameterValue)}</adq:idNoObjecion>
            <adq:enProcesoBpm>2</adq:enProcesoBpm>
        </ns2:NoObjecion>
    </ns2:CrearActualizarNoObjecionRequest>
};

local:func($FinAdquisicion)
