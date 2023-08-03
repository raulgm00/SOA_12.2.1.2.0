xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/ImplementacionPctMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/implementacionPCT/V1/Schema/ImplementacionPctMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/bpmn/bpmnProcess/ImplPCTProcess";
(:: import schema at "../../../../../MDS/Resources/BPM/Schema/PA11/ImpPCTProcess.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns7 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns8 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace imp = "http://www.bcie.org/ImplementacionPctBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $ImplementacionPctRequest as element() (:: schema-element(ns1:procesoLoteRequest) ::) external;
declare variable $counter as xs:int external;
declare function local:func($ImplementacionPctRequest as element() (:: schema-element(ns1:procesoLoteRequest) ::), $counter as xs:int) as element() (:: schema-element(ns2:InicioImplementacionPCT) ::) {
    <ns2:InicioImplementacionPCT>
        <ns2:Header>
                    <ns3:Operacion>
                        <ns4:CodigoOperacion>{fn:data($ImplementacionPctRequest/ope:idOperacion)}</ns4:CodigoOperacion>
                        <ns4:NombreOperacion>{fn:data($ImplementacionPctRequest/ope:nombre)}</ns4:NombreOperacion>
                         <ns4:ResponsableOperacion>{fn:data($ImplementacionPctRequest/ope:responsable)}</ns4:ResponsableOperacion>
                    </ns3:Operacion>
            <ns8:ParameterType>
                <ns8:parameterName>TIPO_INICIO</ns8:parameterName>
                <ns8:parameterValue>FIN_CONCURSO</ns8:parameterValue>
            </ns8:ParameterType>
            <ns8:ParameterType>
                <ns8:parameterName>ID_LOTE</ns8:parameterName>
                <ns8:parameterValue>{fn:data($ImplementacionPctRequest/ns1:implementacionPCT/imp:loteImplementacion[$counter]/imp:idLote)}</ns8:parameterValue>
            </ns8:ParameterType>

            
        </ns2:Header>
    </ns2:InicioImplementacionPCT>
};

local:func($ImplementacionPctRequest,$counter)
