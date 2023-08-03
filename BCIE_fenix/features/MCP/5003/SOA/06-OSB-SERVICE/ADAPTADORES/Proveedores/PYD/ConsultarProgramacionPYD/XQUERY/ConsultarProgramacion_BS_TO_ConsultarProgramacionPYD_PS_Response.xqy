xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.bcie.org/LineaCreditoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioLineaCredito/LineaCredito/V1/Schema/LineaCreditoMO.xsd" ::)
declare namespace ns2="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarProgramacion_db";
(:: import schema at "../XSD/ConsultarProgramacion_db_sp.xsd" ::)

declare namespace con = "http://www.bcie.org/CondicionBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com1 = "http://www.bcie.org/ComisionBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace atr = "http://www.bcie.org/AtributoBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace doc = "http://www.bcie.org/DocumentoBO";

declare namespace ter = "http://www.bcie.org/TerminoBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $ConsultarProgramacionPYDRequest as element() (:: schema-element(ns1:ConsultarProgramacionPYDRequest) ::) external;
declare variable $consultarProgramacionResponse as element() (:: schema-element(ns2:OutputParameters) ::) external;

declare function local:func($ConsultarProgramacionPYDRequest as element() (:: schema-element(ns1:ConsultarProgramacionPYDRequest) ::), 
                            $consultarProgramacionResponse as element() (:: schema-element(ns2:OutputParameters) ::)) 
                            as element() (:: schema-element(ns1:ConsultarProgramacionPYDResponse) ::) {
    <ns1:ConsultarProgramacionPYDResponse>
<ns1:LineaCredito>
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:idLineaCredito
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:idContrato
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:NumeroLineaCredito
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:Descripcion
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:Flexcube
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:Fondo
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:MontoLinea
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:EsRevolvente
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:TratamientoDiasFeriados
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:FechaValor
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:FechaVencimiento
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:CodigoPantallaLimite
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:CodigoSerialLimite
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:CodigoAsignacion
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:DescripcionAsignacion
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:CondicionEspecial
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:FechaRegistro
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:FechaMaximaDesembolso
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:Estado
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:descCondEspecial
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:frecuenciaGracia
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:plazoGracia
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:frecuenciaFinanciamiento
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:plazoFinanciamiento
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:recursosExternos
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:tasaMinima
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:tasaMaxima
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:montoMinimo
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:montoMaximo
            }
            <lin:Monto>
                <com:tipo>
                    <cat:DescripcionCorta>PROGRAMADO</cat:DescripcionCorta>
                </com:tipo>
                <com:importe>{fn:data($consultarProgramacionResponse/ns2:MONTO_PROGRAMADO)}</com:importe>
                <com:moneda>
                    <cat:codigoExterno>{fn:data($ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:Monto/com:moneda/cat:codigoExterno)}</cat:codigoExterno> 
                </com:moneda>
            </lin:Monto>
            <lin:Monto>
                <com:tipo>
                    <cat:DescripcionCorta>EJECUTADO</cat:DescripcionCorta>
                </com:tipo>
                <com:importe>{fn:data($consultarProgramacionResponse/ns2:MONTO_EJECUTADO)}</com:importe>
                <com:moneda>
                    <cat:Id></cat:Id>
                    <cat:Descripcion></cat:Descripcion>
                    <cat:DescripcionCorta></cat:DescripcionCorta>
                    <cat:estatus></cat:estatus>
                    <cat:codigoExterno></cat:codigoExterno>
                </com:moneda>
            </lin:Monto>
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:Condicion
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:Termino
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:Comision
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:Fuente
            }
            {
                $ConsultarProgramacionPYDRequest/ns1:LineaCredito/lin:atributos
            }
        </ns1:LineaCredito>       
        <ns1:Resultado>
           {
                if (fn:data($consultarProgramacionResponse/ns2:TIPO_RESULTADO)= 0) then
            <res:result>OK</res:result>
             else  <res:result>ERROR</res:result>
        }
         {
                if (fn:data($consultarProgramacionResponse/ns2:TIPO_RESULTADO)= 0) then
            <res:message>Consulta realizada correctamente</res:message>
             else  <res:message>{fn:data($consultarProgramacionResponse/ns2:MENSAJE_ERROR)}</res:message>
        }
        </ns1:Resultado>
    </ns1:ConsultarProgramacionPYDResponse>
};

local:func($ConsultarProgramacionPYDRequest, $consultarProgramacionResponse)
