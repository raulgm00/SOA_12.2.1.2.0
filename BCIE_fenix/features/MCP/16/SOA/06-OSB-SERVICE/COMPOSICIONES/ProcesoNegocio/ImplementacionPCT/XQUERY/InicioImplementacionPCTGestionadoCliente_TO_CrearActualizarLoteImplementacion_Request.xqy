xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/ImplementacionPctMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioOperacion/implementacionPCT/V1/Schema/ImplementacionPctMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/ImplPCTProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA11/ImpPCTProcess.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace imp = "http://www.bcie.org/ImplementacionPctBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $InicioImplementacionPCT as element() (:: schema-element(ns1:InicioImplementacionPCT) ::) external;
declare variable $CrearActualizarImplementacionResponse as element() (:: schema-element(ns2:CrearActualizarImplementacionResponse) ::) external;

declare function local:func($InicioImplementacionPCT as element() (:: schema-element(ns1:InicioImplementacionPCT) ::), 
                            $CrearActualizarImplementacionResponse as element() (:: schema-element(ns2:CrearActualizarImplementacionResponse) ::)) 
                            as element() (:: schema-element(ns2:CrearActualizarLoteImplementacionRequest) ::) {
    <ns2:CrearActualizarLoteImplementacionRequest>
        <ns2:LoteImplementacion>
            <imp:idLote>{fn:data($CrearActualizarImplementacionResponse/ns2:Implementacon/imp:idLote)}</imp:idLote>
           <imp:loteImpementacion>
                <imp:idLote></imp:idLote>
                <imp:nombre>{concat('Lote gestionado cliente ',fn:data($InicioImplementacionPCT/ns1:Header/ns3:Operacion/ns4:CodigoOperacion))}</imp:nombre>
                <imp:monto>
                    <com:importe>0</com:importe>
                    <com:moneda>
                        <cat:Id>1</cat:Id>
                    </com:moneda>
                </imp:monto>
                <imp:estatus>{true()}</imp:estatus>
            </imp:loteImpementacion>
        </ns2:LoteImplementacion>
    </ns2:CrearActualizarLoteImplementacionRequest>
};

local:func($InicioImplementacionPCT, $CrearActualizarImplementacionResponse)
