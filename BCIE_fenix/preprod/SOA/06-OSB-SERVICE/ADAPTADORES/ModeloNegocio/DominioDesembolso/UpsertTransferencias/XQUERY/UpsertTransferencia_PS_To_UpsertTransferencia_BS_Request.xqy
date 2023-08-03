xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/UpsertTransferencia_DB";
(:: import schema at "../XSD/UpsertTransferencia_DB_sp.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare variable $UpsertTransferenciasRequest as element() (:: schema-element(ns1:UpsertTransferenciasRequest) ::) external;

declare function local:func($UpsertTransferenciasRequest as element() (:: schema-element(ns1:UpsertTransferenciasRequest) ::)) as element() (:: schema-element(ns2:InputParameters) ::) {
    <ns2:InputParameters>
        <ns2:T_LISTA_TRANSFERENCIA_BANCARIA>
        {
          for $transferencia in $UpsertTransferenciasRequest/ns1:ContratoDesembolso/des:Transferencia
          return
            <ns2:T_LISTA_TRANSFERENCIA_BANCARIA_ITEM>
                <ns2:ID_TRANSFERENCIA>{fn:data($transferencia/des:idTransferencia)}</ns2:ID_TRANSFERENCIA>
                {
                    if ($transferencia/des:bhqTransferencia)
                    then <ns2:BHQ_TRANSFERENCIA>{fn:data($transferencia/des:bhqTransferencia)}</ns2:BHQ_TRANSFERENCIA>
                    else ()
                }
                {
                    if ($transferencia/des:idConsolidacionPadre)
                    then <ns2:ID_CONSOLIDACION_PADRE>{fn:data($transferencia/des:idConsolidacionPadre)}</ns2:ID_CONSOLIDACION_PADRE>
                    else ()
                }
                {
                    if ($transferencia/des:NumeroReserva)
                    then <ns2:NUMERO_RESERVA>{fn:data($transferencia/des:NumeroReserva)}</ns2:NUMERO_RESERVA>
                    else ()
                }
            </ns2:T_LISTA_TRANSFERENCIA_BANCARIA_ITEM>
        }
        </ns2:T_LISTA_TRANSFERENCIA_BANCARIA>
    </ns2:InputParameters>
};

local:func($UpsertTransferenciasRequest)
