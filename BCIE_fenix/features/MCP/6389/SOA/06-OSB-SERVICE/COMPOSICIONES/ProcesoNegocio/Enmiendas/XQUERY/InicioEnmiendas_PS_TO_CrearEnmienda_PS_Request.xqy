xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/EnmiendasProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA04/EnmiendasProcess.xsd" ::)

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $InicioEnmiendas as element() (:: schema-element(ns1:InicioEnmiendas) ::) external;

declare function local:func($InicioEnmiendas as element() (:: schema-element(ns1:InicioEnmiendas) ::)) as element() (:: schema-element(ns2:CrearEnmiendaTCCRequest) ::) {
    <ns2:CrearEnmiendaTCCRequest>
        <ns2:enmiendaTCC>
            <mat:idEnmienda></mat:idEnmienda>
            <mat:idOperacion>{fn:data($InicioEnmiendas/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</mat:idOperacion>
            <mat:instanciaProceso>0</mat:instanciaProceso>
            <mat:requiereOGC>0</mat:requiereOGC>
            <mat:requiereASJUR>0</mat:requiereASJUR>
            <mat:fechaRegistro></mat:fechaRegistro>
            <mat:estado>0</mat:estado>
           
        </ns2:enmiendaTCC>
    </ns2:CrearEnmiendaTCCRequest>
};

local:func($InicioEnmiendas)
