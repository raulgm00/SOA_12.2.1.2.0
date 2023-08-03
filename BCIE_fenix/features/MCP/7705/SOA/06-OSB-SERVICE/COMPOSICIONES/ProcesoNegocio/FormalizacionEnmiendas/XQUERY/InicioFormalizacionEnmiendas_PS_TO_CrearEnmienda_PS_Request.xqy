xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/MatrizTCCMO";
(:: import schema at "../../../../MDS/Resources/ComponentesComunes/DominioContrato/MatrizTCC/V1/Schema/MatrizTCCMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/bpmn/bpmnProcess/FormalizacionEnmiendasProcess";
(:: import schema at "../../../../MDS/Resources/BPM/Schema/PA15/FormalizacionEnmiendasProcess.xsd" ::)

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare variable $InicioFormalizacionEnmiendas as element() (:: schema-element(ns1:InicioFormalizacionEnmiendas) ::) external;

declare function local:func($InicioFormalizacionEnmiendas as element() (:: schema-element(ns1:InicioFormalizacionEnmiendas) ::)) as element() (:: schema-element(ns2:CrearEnmiendaTCCRequest) ::) {
    <ns2:CrearEnmiendaTCCRequest>
        <ns2:enmiendaTCC>
            <mat:idEnmienda></mat:idEnmienda>
            <mat:idOperacion>{fn:data($InicioFormalizacionEnmiendas/ns1:Header/ns3:Operacion/ns4:CodigoOperacion)}</mat:idOperacion>
            <mat:instanciaProceso>0</mat:instanciaProceso>
            <mat:requiereOGC>0</mat:requiereOGC>
            <mat:requiereASJUR>0</mat:requiereASJUR>
            <mat:fechaRegistro></mat:fechaRegistro>
            <mat:estado>0</mat:estado>
            {
                if (string($InicioFormalizacionEnmiendas/ns1:ConfigGateways/ns1:requiereDocumentacionContractual) eq 'true') then
                    <mat:estado_formalizacion>1</mat:estado_formalizacion>
                else
                    <mat:estado_formalizacion></mat:estado_formalizacion>
            }
            {
                if (string($InicioFormalizacionEnmiendas/ns1:ConfigGateways/ns1:requiereCOF) eq 'true') then
                    <mat:requiereCOF>1</mat:requiereCOF>
                else
                    <mat:requiereCOF></mat:requiereCOF>
            }
             {
                if (string($InicioFormalizacionEnmiendas/ns1:ConfigGateways/ns1:esDesobligacion) eq 'true') then
                    <mat:esDesobligacion>1</mat:esDesobligacion>
                else
                    <mat:esDesobligacion></mat:esDesobligacion>
            }
             {
                if (string($InicioFormalizacionEnmiendas/ns1:ConfigGateways/ns1:esCambioPlazo) eq 'true') then
                    <mat:esCambioPlazo>1</mat:esCambioPlazo>
                else
                    <mat:esCambioPlazo></mat:esCambioPlazo>
            }
        </ns2:enmiendaTCC>
    </ns2:CrearEnmiendaTCCRequest>
};

local:func($InicioFormalizacionEnmiendas)