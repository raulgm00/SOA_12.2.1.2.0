xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarLineaCreditoByIdLineaCredito";
(:: import schema at "../XSD/ConsultarLineaCreditoByIdLineaCredito.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace cat1 = "http://www.bcie.org/CatalogoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace dec = "http://www.bcie.org/DeclaracionJuradaBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare variable $ConsultarLineaCreditoOutput as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoOutputCollection) ::) external;

declare function local:func($ConsultarLineaCreditoOutput as element() (:: schema-element(ns1:ConsultarLineaCreditoByIdLineaCreditoOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) {
    <ns2:ConsultarLineaCreditoByIdLineaCreditoResponse>
    {for $idOperacion in distinct-values($ConsultarLineaCreditoOutput/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput/ns1:ID_OPERACION)
      let $operacion:= $ConsultarLineaCreditoOutput/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput[ns1:ID_OPERACION = $idOperacion ][1]
      return
      if(fn:string-length($operacion/ns1:ID_OPERACION/text()) > 0 ) then
        <ns2:Operacion>
            <ope:idOperacion>{fn:data($operacion/ns1:ID_OPERACION)}</ope:idOperacion>
            {for $idContrato in distinct-values($ConsultarLineaCreditoOutput/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput/ns1:ID_CONTRATO)
            let $contrato:= $ConsultarLineaCreditoOutput/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput[ns1:ID_CONTRATO = $idContrato ][1]
            where $contrato/ns1:ID_OPERACION = $idOperacion
            return
            <ope:contrato>
                <con:idContrato>{fn:data($contrato/ns1:ID_CONTRATO)}</con:idContrato>
                <con:idOperacion>{fn:data($contrato/ns1:ID_OPERACION)}</con:idOperacion>
                {for $idLineaCredito in distinct-values($ConsultarLineaCreditoOutput/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput/ns1:ID_LINEA_CREDITO)
                let $lineaCredito := $ConsultarLineaCreditoOutput/ns1:ConsultarLineaCreditoByIdLineaCreditoOutput[ns1:ID_LINEA_CREDITO = $idLineaCredito ][1]
                let $montoAmpliacion := (if($lineaCredito/ns1:MONTO_AMPLIACION/text() != "")then data($lineaCredito/ns1:MONTO_AMPLIACION) else 0)
                where $lineaCredito/ns1:ID_CONTRATO = $idContrato
                return
                <con:LineaCredito>
                    <lin:idLineaCredito>{fn:data($lineaCredito/ns1:ID_LINEA_CREDITO)}</lin:idLineaCredito>
                    <lin:idContrato>{fn:data($lineaCredito/ns1:ID_CONTRATO)}</lin:idContrato>
                    <lin:NumeroLineaCredito>{fn:data($lineaCredito/ns1:NUMERO_LINEA_CREDITO)}</lin:NumeroLineaCredito>
                </con:LineaCredito>
                }
            </ope:contrato>
            }
        </ns2:Operacion>
        else ()
       }
      </ns2:ConsultarLineaCreditoByIdLineaCreditoResponse>
};

local:func($ConsultarLineaCreditoOutput)
