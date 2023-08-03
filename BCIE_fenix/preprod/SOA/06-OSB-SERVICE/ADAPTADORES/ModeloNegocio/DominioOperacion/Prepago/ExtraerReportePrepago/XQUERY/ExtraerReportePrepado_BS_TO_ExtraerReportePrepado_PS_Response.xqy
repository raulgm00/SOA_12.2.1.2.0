xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ExtraerReportePrepago";
(:: import schema at "../XSD/ExtraerReportePrepago_sp.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace pre = "http://www.bcie.org/PrepagoBO";

declare namespace mat = "http://www.bcie.org/MatrizTCCBO";

declare namespace pro = "http://www.bcie.org/ProductoBO";

declare namespace ope = "http://www.bcie.org/OperacionBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ExtraerReportePrepagoResponse) ::) {
    <ns2:ExtraerReportePrepagoResponse>
        <ns2:Reporte>
          
              {
            for $observacion in $OutputParameters/ns1:P_OBSERVACIONES/ns1:Row
            return
            <pre:Observaciones>
                <pre:Rol>
                    <cat:DescripcionCorta>{fn:data($observacion/ns1:Column[@name ='DESCRIPCION_CORTA'])}</cat:DescripcionCorta>
                </pre:Rol>
                <pre:Observacion>{fn:data($observacion/ns1:Column[@name ='OBSERVACION'])}</pre:Observacion>
            </pre:Observaciones>
            }
            {
            for $penalidad in $OutputParameters/ns1:P_PENALIDAD/ns1:Row
            return
            <pre:Penalidad>
                <des:idFacturador>{fn:data($penalidad/ns1:Column[@name ='NUMERO_TESORERIA'])}</des:idFacturador>
                <pre:Monto>
                    <com:tipo>
                        <cat:Descripcion>{fn:data($penalidad/ns1:Column[@name ='PENALIDAD']/@name)}</cat:Descripcion>
                    </com:tipo>
                    <com:importe>{fn:data($penalidad/ns1:Column[@name ='PENALIDAD'])}</com:importe>
                    <com:moneda>
                        <cat:DescripcionCorta>{fn:data($penalidad/ns1:Column[@name ='MONEDA'])}</cat:DescripcionCorta>
                    </com:moneda>
                </pre:Monto>
                 <pre:Monto>
                    <com:tipo>
                        <cat:Descripcion>{fn:data($penalidad/ns1:Column[@name ='MONTO_PREPAGO']/@name)}</cat:Descripcion>
                    </com:tipo>
                    <com:importe>{fn:data($penalidad/ns1:Column[@name ='MONTO_PREPAGO'])}</com:importe>
                </pre:Monto>
                 <pre:Monto>
                    <com:tipo>
                        <cat:Descripcion>{fn:data($penalidad/ns1:Column[@name ='INTERESES']/@name)}</cat:Descripcion>
                    </com:tipo>
                    <com:importe>{fn:data($penalidad/ns1:Column[@name ='INTERESES'])}</com:importe>
                </pre:Monto>
                <pre:Resolucion>{fn:data($penalidad/ns1:Column[@name ='RESOLUCION'])}</pre:Resolucion>
                <pre:Inicio>{fn:data($penalidad/ns1:Column[@name ='FECHA_INICIO'])}</pre:Inicio>
                <pre:Fin>{fn:data($penalidad/ns1:Column[@name ='FECHA_FIN'])}</pre:Fin>
                <pre:Plazo>{fn:data($penalidad/ns1:Column[@name ='PLAZO_TRANSCURRIDO'])}</pre:Plazo>
                <pre:Tasa>{fn:data($penalidad/ns1:Column[@name ='TASA_PREPAGO'])}</pre:Tasa>
                <pre:ProximoPago>{fn:data($penalidad/ns1:Column[@name ='PROXIMO_PAGO'])}</pre:ProximoPago>
                <pre:ConFuentes>{fn:data($penalidad/ns1:Column[@name ='CON_FUENTES'])}</pre:ConFuentes>
                <pre:Spread>{fn:data($penalidad/ns1:Column[@name ='SPREAD'])}</pre:Spread>
                <pre:FraccionLibor>{fn:data($penalidad/ns1:Column[@name ='FRACCION_LIBOR'])}</pre:FraccionLibor>
            </pre:Penalidad>
            }
            {
            for $condicion in $OutputParameters/ns1:P_CONDICIONES/ns1:Row
            return
            <pre:CondicionesEspeciales>
                <pre:Linea>
                    <lin:NumeroLineaCredito>{fn:data($condicion/ns1:Column[@name ='NUMERO_LINEA_CREDITO'])}</lin:NumeroLineaCredito>
                    <lin:Descripcion>{fn:data($condicion/ns1:Column[@name ='DESCRIPCION_CONDICION'])}</lin:Descripcion>
                </pre:Linea>
            </pre:CondicionesEspeciales>
            }
            {
            for $cobertura in $OutputParameters/ns1:P_COBERTURAS/ns1:Row
            return
            <pre:Coberturas>
                    <des:idFacturador>{fn:data($cobertura/ns1:Column[@name ='CONTRATO_DESEMBOLSO'])}</des:idFacturador>
                <pre:Linea>
                    <lin:NumeroLineaCredito>{fn:data($cobertura/ns1:Column[@name ='NUMERO_LINEA_CREDITO'])}</lin:NumeroLineaCredito>
                </pre:Linea>
                <pre:Contraparte>
                    <cat:Descripcion>{fn:data($cobertura/ns1:Column[@name ='CONTRAPARTE'])}</cat:Descripcion>
                    <cat:codigoExterno>{fn:data($cobertura/ns1:Column[@name ='CODIGO_COBERTURA'])}</cat:codigoExterno>
                </pre:Contraparte>
                <pre:FechaEfectiva>{fn:data($cobertura/ns1:Column[@name ='FECHA_EFECTIVA'])}</pre:FechaEfectiva>
                <pre:FechaVencimiento>{fn:data($cobertura/ns1:Column[@name ='FECHA_VENCIMIENTO'])}</pre:FechaVencimiento>
            </pre:Coberturas>
            }
            {
            for $venta in $OutputParameters/ns1:P_VENTA_CARTERA/ns1:Row
            return
            <pre:VentaCartera>
                    <des:idFacturador>{fn:data($venta/ns1:Column[@name ='CONTRATO_DESEMBOLSO'])}</des:idFacturador>
                <pre:Linea>
                    <lin:NumeroLineaCredito>{fn:data($venta/ns1:Column[@name ='NUMERO_LINEA_CREDITO'])}</lin:NumeroLineaCredito>
                    <lin:Fondo>{fn:data($venta/ns1:Column[@name ='FONDO'])}</lin:Fondo>
                </pre:Linea>
            </pre:VentaCartera>
            }
            {
            for $fuente in $OutputParameters/ns1:P_FUENTES_EXTERNAS/ns1:Row
            return
            <pre:FuentesExternas>
                    <des:idFacturador>{fn:data($fuente/ns1:Column[@name ='CONTRATO_DESEMBOLSO'])}</des:idFacturador>
                <pre:Linea>
                    <lin:NumeroLineaCredito>{fn:data($fuente/ns1:Column[@name ='NUMERO_LINEA_CREDITO'])}</lin:NumeroLineaCredito>
                </pre:Linea>
                <pre:Fuente>
                    <lin:codigoLineaPasiva>{fn:data($fuente/ns1:Column[@name ='CODIGO_LINEA_PASIVA'])}</lin:codigoLineaPasiva>
                    <lin:Descripcion>{fn:data($fuente/ns1:Column[@name ='DESC_FUENTE'])}</lin:Descripcion>
                    <lin:Monto>
                        <com:importe>{fn:data($fuente/ns1:Column[@name ='MONTO'])}</com:importe>
                    </lin:Monto>
                </pre:Fuente>
            </pre:FuentesExternas>
            }
        </ns2:Reporte>
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
        </ns2:Resultado>
    </ns2:ExtraerReportePrepagoResponse>
};

local:func($OutputParameters)
