xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace des="http://www.bcie.org/DesembolsoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/Desembolsomo.xsd" ::)
declare namespace lin="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "oramds:/apps/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd"::)

declare namespace des1 = "http://www.bcie.org/DesembolsoBO";

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace lin1 = "http://www.bcie.org/LineaCreditoBO";

declare namespace con1 = "http://www.bcie.org/ContratoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare variable $outConsultarLineaCreditoByIdLineaCredito.response as element() (:: schema-element(lin:ConsultarLineaCreditoByIdLineaCreditoResponse) ::) external;
declare variable $outDetalleCompensacion.response as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::) external;

declare function local:funcXq_requesteliminarutilizacionare($outConsultarLineaCreditoByIdLineaCredito.response as element() (:: schema-element(lin:ConsultarLineaCreditoByIdLineaCreditoResponse) ::), 
                                                            $outDetalleCompensacion.response as element() (:: schema-element(des:ConsultarDetalleTransaccionResponse) ::)) 
                                                            as element() (:: schema-element(lin:EliminarUtilizacionARERequest) ::) {
    <lin:EliminarUtilizacionARERequest>
        <lin:LineaCredito>
            <lin1:CodigoAsignacion>{fn:data($outConsultarLineaCreditoByIdLineaCredito.response/lin:Operacion/ope:contrato/con1:LineaCredito/lin1:CodigoAsignacion)}</lin1:CodigoAsignacion>
            <lin1:ContratoDesembolso>
                {
                for $detalleTransaccion in $outDetalleCompensacion.response/des:TransaccionDesembolso[des1:plataforma = 'ARE' and des1:operacion = 'registrarUtilizacion']/des1:detalleTransaccion/des1:Parameters
                let $agrupador := $detalleTransaccion[com:name = 'numeroAsignacion']/com:agrupador
                let $numeroAsignacion := $detalleTransaccion[com:name = 'numeroAsignacion' and com:agrupador = $agrupador]/com:value
                let $importe := $detalleTransaccion[com:name = 'importe' and com:agrupador = $agrupador]/com:value
                return
                if ($numeroAsignacion != '')then
                <des1:Utilizacion>
                    <lin1:MontoAsignado>{fn:data($outDetalleCompensacion.response/des:TransaccionDesembolso/des1:detalleTransaccion/des1:Parameters[com:name = 'importe' and com:agrupador = $agrupador]/com:value)}</lin1:MontoAsignado>
                    <lin1:NumeroAsignacion>{fn:data($numeroAsignacion)}</lin1:NumeroAsignacion>
                </des1:Utilizacion>
                else ()
                }
            </lin1:ContratoDesembolso>
            <lin1:atributos>
                <com:name>REFERENCIA</com:name>
                <com:value>{fn:concat(fn:data(string($outDetalleCompensacion.response/des:TransaccionDesembolso[des1:plataforma = 'ARE' and des1:operacion = 'registrarUtilizacion']/des1:idDesembolso)),'/', string($outDetalleCompensacion.response/des:TransaccionDesembolso[des1:plataforma = 'ARE' and des1:operacion = 'registrarUtilizacion']/des1:id))}</com:value>
            </lin1:atributos>
        </lin:LineaCredito>
    </lin:EliminarUtilizacionARERequest>
};

local:funcXq_requesteliminarutilizacionare($outConsultarLineaCreditoByIdLineaCredito.response, $outDetalleCompensacion.response)
