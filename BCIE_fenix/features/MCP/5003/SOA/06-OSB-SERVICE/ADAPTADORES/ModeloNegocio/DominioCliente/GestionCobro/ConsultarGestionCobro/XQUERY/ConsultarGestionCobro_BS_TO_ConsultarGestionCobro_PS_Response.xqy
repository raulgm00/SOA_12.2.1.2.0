xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarGestionCobro";
(:: import schema at "../XSD/ConsultarGestionCobro.xsd" ::)

declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare variable $ConsultarGestionCobroOutputCollection as element() (:: schema-element(ns1:ConsultarGestionCobroOutputCollection) ::) external;

declare function local:func($ConsultarGestionCobroOutputCollection as element() (:: schema-element(ns1:ConsultarGestionCobroOutputCollection) ::)) as element() (:: schema-element(ns2:ConsultarGestionCobroResponse) ::) {
    <ns2:ConsultarGestionCobroResponse>
      {if(fn:count($ConsultarGestionCobroOutputCollection/ns1:ConsultarGestionCobroOutput) > 0)then
        for $detalle in $ConsultarGestionCobroOutputCollection/ns1:ConsultarGestionCobroOutput
          return
            <ns2:DetalleLote>
                <ges:id>{fn:data($detalle/ns1:ID_LOTE)}</ges:id>
                <ges:idLote>{fn:data($detalle/ns1:ID_EJECUCION)}</ges:idLote>
                <ges:Estado>{fn:data($detalle/ns1:ESTADO)}</ges:Estado>
                <ges:Mensaje_Error>
                    <cat:Id>{fn:data($detalle/ns1:ERROR)}</cat:Id>
                </ges:Mensaje_Error>
              <ges:fechaRegistro>{fn:data($detalle/ns1:FECHA)}</ges:fechaRegistro>
              <ges:Estatus>{fn:data($detalle/ns1:ESTATUS)}</ges:Estatus>
                <ges:tipoLote>{fn:data($detalle/ns1:TIPO_LOTE)}</ges:tipoLote>
                <ges:esDetallado>{fn:data($detalle/ns1:ES_RECIBO_DETALLADO)}</ges:esDetallado>
          </ns2:DetalleLote>
      else()
      }
      <ns2:Resultado>
            <res:result>OK</res:result>
            {if(fn:count($ConsultarGestionCobroOutputCollection/ns1:ConsultarGestionCobroOutput) > 0)then
            <res:message>Consulta Exitosa</res:message>
            else
            <res:message>Consulta sin resultados</res:message>
            }
        </ns2:Resultado>
    </ns2:ConsultarGestionCobroResponse>
};

local:func($ConsultarGestionCobroOutputCollection)
