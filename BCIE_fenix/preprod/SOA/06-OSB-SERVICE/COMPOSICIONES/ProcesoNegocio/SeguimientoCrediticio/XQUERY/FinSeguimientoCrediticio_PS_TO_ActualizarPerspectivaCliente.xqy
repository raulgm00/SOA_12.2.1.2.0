xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://xmlns.oracle.com/bpmn/bpmnProcess/SegCrediticioProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA07/SegCrediticioProcess.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ActualizarPerspClie_SB";
(:: import schema at "../../../../ADAPTADORES/ModeloNegocio/DominioCliente/ActualizarPerpectivaCliente/XSD/ActualizarPerspClie_SB_sp.xsd" ::)

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare variable $FinSeguimientoCrediticio as element() (:: schema-element(ns2:FinSeguimientoCrediticio) ::) external;

declare function local:func($FinSeguimientoCrediticio as element() (:: schema-element(ns2:FinSeguimientoCrediticio) ::)) as element() (:: schema-element(ns1:InputParameters) ::) {
    <ns1:InputParameters>
        {
            if ($FinSeguimientoCrediticio/ns2:Header/ns3:Cliente/ns4:IdCliente)
            then <ns1:P_ID_CLIENTE>{fn:data($FinSeguimientoCrediticio/ns2:Header/ns3:Cliente/ns4:IdCliente)}</ns1:P_ID_CLIENTE>
            else ()
        }
        {
            if ($FinSeguimientoCrediticio/ns2:Header/ns3:Proceso/ns5:IdFlujo)
            then <ns1:P_ID_FLUJO>{fn:data($FinSeguimientoCrediticio/ns2:Header/ns3:Proceso/ns5:IdFlujo)}</ns1:P_ID_FLUJO>
            else ()
        }
    </ns1:InputParameters>
};

local:func($FinSeguimientoCrediticio)
