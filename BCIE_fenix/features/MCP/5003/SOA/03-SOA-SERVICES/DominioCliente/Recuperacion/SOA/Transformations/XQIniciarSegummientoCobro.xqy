xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace doc="http://www.bcie.org/DocumentoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDocumento/Documento/V1/Schema/DocumentoMO.xsd" ::)
declare namespace ges="http://www.bcie.org/GestionCobroMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ges1="http://xmlns.oracle.com/bpmn/bpmnProcess/GestionCobroProcess";
(:: import schema at "oramds:/apps/Resources/BPM/Schema/PC07/GestionCobroProcess.xsd" ::)

declare namespace ns2 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Operacion/V1";

declare namespace ns1 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Header/V1";

declare namespace ns4 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Tarea/V1";

declare namespace ns3 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Cliente/V1";

declare namespace ns5 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Proceso/V1";

declare namespace ns6 = "http://xmlns.bcie.org/ObjetoProceso/Comun/Parameter/V1";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace ges2 = "http://www.bcie.org/GestionCobroBO";

declare namespace doc1 = "http://www.bcie.org/DocumentoBO";

declare variable $inputVariable.GenerarReciboPagoProcesoRequest as element() (:: schema-element(ges:GenerarReciboPagoProcesoRequest) ::) external;
declare variable $ListaDocumentosAdjuntos.request as element() (:: schema-element(doc:CargarDocumentoFenixRequest) ::) external;

declare function local:funcXqiniciarsegummientocobro($inputVariable.GenerarReciboPagoProcesoRequest as element() (:: schema-element(ges:GenerarReciboPagoProcesoRequest) ::), 
                                                     $ListaDocumentosAdjuntos.request as element() (:: schema-element(doc:CargarDocumentoFenixRequest) ::)) 
                                                     as element() (:: schema-element(ges1:InicioSeguimientoCobro) ::) {
    <ges1:InicioSeguimientoCobro>
        <ges1:Header>
                    <ns1:Cliente>
                        <ns3:IdCliente>{fn:data($inputVariable.GenerarReciboPagoProcesoRequest/ges:ReciboPago/ges2:recibo/ges2:Cliente[1]/cli:idCliente)}</ns3:IdCliente>
                        <ns3:RazonSocial>{fn:data($inputVariable.GenerarReciboPagoProcesoRequest/ges:ReciboPago/ges2:recibo/ges2:Cliente[1]/cli:razonSocial)}</ns3:RazonSocial>
                        <ns3:ResponsableCliente>{fn:data($inputVariable.GenerarReciboPagoProcesoRequest/ges:ReciboPago/ges2:recibo/ges2:Cliente[1]/cli:responsableCliente)}</ns3:ResponsableCliente>
                    </ns1:Cliente>
            <ns6:ParameterType>
                <ns6:parameterName>TIPO_INICIO</ns6:parameterName>
                <ns6:parameterValue>AUTOMATICO</ns6:parameterValue>
            </ns6:ParameterType>
            {for $documento in $ListaDocumentosAdjuntos.request/doc:Documentos/doc1:Documento
            return
              if ($documento/doc1:mime_type = 'application/pdf') 
                then
                  <ns6:ParameterType>
                      <ns6:parameterName>ID_ADJUNTO_PDF</ns6:parameterName>
                      <ns6:parameterValue>{fn:data($documento/doc1:idAdjunto)}</ns6:parameterValue>
                  </ns6:ParameterType>
                else
                  <ns6:ParameterType>
                      <ns6:parameterName>ID_ADJUNTO_WORD</ns6:parameterName>
                      <ns6:parameterValue>{fn:data($documento/doc1:idAdjunto)}</ns6:parameterValue>
                  </ns6:ParameterType>
            }
        </ges1:Header>
    </ges1:InicioSeguimientoCobro>
};

local:funcXqiniciarsegummientocobro($inputVariable.GenerarReciboPagoProcesoRequest, $ListaDocumentosAdjuntos.request)
