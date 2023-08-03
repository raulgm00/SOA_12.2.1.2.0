xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/ConsultarTcaTermino";
(:: import schema at "../../../DominioContrato/Termino/ConsultarTerminobyIdLineaCredito/XSD/ConsultarTcaTermino.xsd" ::)

declare namespace con = "http://www.bcie.org/ContratoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace reg = "http://www.bcie.org/ReglaBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace con1 = "http://www.bcie.org/CondicionBO";

declare variable $ConsultarTcaTerminoInput as element() (:: schema-element(ns1:ConsultarTcaTerminoInput) ::) external;
declare variable $ConsultarTcaTerminoOutputCollection as element() (:: schema-element(ns1:ConsultarTcaTerminoOutputCollection) ::) external;

declare function local:func($ConsultarTcaTerminoInput as element() (:: schema-element(ns1:ConsultarTcaTerminoInput) ::), 
                            $ConsultarTcaTerminoOutputCollection as element() (:: schema-element(ns1:ConsultarTcaTerminoOutputCollection) ::)) 
                            as element() (:: schema-element(ns2:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) {
    <ns2:ConsultarLineaCreditoByIdLineaCreditoResponse>
        <ns2:Contrato>
            <con:idContrato></con:idContrato>
            <con:LineaCredito>
                <lin:idLineaCredito>{fn:data($ConsultarTcaTerminoInput/ns1:P_ID_LINEA_CREDITO)}</lin:idLineaCredito>
                { for $termino in $ConsultarTcaTerminoOutputCollection/ns1:ConsultarTcaTerminoOutput
                return
                <lin:Termino>
                    <ter:idTermino></ter:idTermino>
                    <ter:tipoTermino>
                        <ter:descripcionCorta>{fn:data($termino/ns1:DESCRIPCION_CORTA_TCA)}</ter:descripcionCorta>
                        <ter:codigoExterno>{fn:data($termino/ns1:COD_EXTERNO)}</ter:codigoExterno>
                    </ter:tipoTermino>
                    <ter:plazo>{fn:data($termino/ns1:PLAZO)}</ter:plazo>
                    <ter:frecuenciaPlazo>
                        <cat:codigoExterno>{fn:data($termino/ns1:COD_EXTERNO)}</cat:codigoExterno>
                    </ter:frecuenciaPlazo>
                    <ter:fechaVencimiento>{fn:data($termino/ns1:FECHA_VENCIMIENTO)}</ter:fechaVencimiento>
                    <ter:seAplicanRecursosExternos>{fn:data($termino/ns1:SE_APLICAN_RECURSOS_EXTERNOS)}</ter:seAplicanRecursosExternos>
                    <ter:montoMinimoDesembolso>{fn:data($termino/ns1:MONTO_MINIMO_DESEMBOLSO)}</ter:montoMinimoDesembolso>
                    <ter:montoMaximoDesembolso>{fn:data($termino/ns1:MONTO_MAXIMO_DESEMBOLSO)}</ter:montoMaximoDesembolso>
                    <ter:tasaMinimaDesembolso>{fn:data($termino/ns1:TASA_MINIMA_DESEMBOLSO)}</ter:tasaMinimaDesembolso>
                    <ter:tasaMaximaDesembolso>{fn:data($termino/ns1:TASA_MAXIMA_DESEMBOLSO)}</ter:tasaMaximaDesembolso>
                    <ter:requiereFondoPresupuestario>
                      {if (xs:string($termino/ns1:REQUIERE_FONDO_PRESUPUESTARIO) = '1')
                      then true()
                      else false()
                      }
                    </ter:requiereFondoPresupuestario>
                </lin:Termino>
                }
            </con:LineaCredito>
        </ns2:Contrato>
    </ns2:ConsultarLineaCreditoByIdLineaCreditoResponse>
};

local:func($ConsultarTcaTerminoInput, $ConsultarTcaTerminoOutputCollection)
