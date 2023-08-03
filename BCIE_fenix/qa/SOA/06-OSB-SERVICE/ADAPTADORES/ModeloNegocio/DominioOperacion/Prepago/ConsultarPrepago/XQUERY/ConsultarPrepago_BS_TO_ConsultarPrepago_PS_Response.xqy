xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/PrepagoMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioOperacion/Prepago/V1/Schema/PrepagoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarPrepago";
(:: import schema at "../XSD/ConsultarPrepago_sp.xsd" ::)

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

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

declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ConsultarPrepagoResponse) ::) {
    <ns2:ConsultarPrepagoResponse>
    {
    for $prepago in $OutputParameters/ns1:P_PREPAGO/ns1:Row
    return
        <ns2:Prepago>
            <atr:id>{fn:data($prepago/ns1:Column[@name= 'ID_PREPAGO'])}</atr:id>
            <pre:FechaSolicitud>{fn:data($prepago/ns1:Column[@name= 'FECHA_SOLICITUD'])}</pre:FechaSolicitud>
            <pre:FechaPrepago>{fn:data($prepago/ns1:Column[@name= 'FECHA_PREPAGO'])}</pre:FechaPrepago>
            <pre:Monto>
                <com:tipo>
                    <cat:Descripcion>{fn:data($prepago/ns1:Column[@name= 'MONTO_PREPAGO']/@name)}</cat:Descripcion>
                </com:tipo>
                <com:importe>{fn:data($prepago/ns1:Column[@name= 'MONTO_PREPAGO'])}</com:importe>
                <com:moneda>
                      <cat:Descripcion>{fn:data($prepago/ns1:Column[@name= 'DESCRIPCION_MONEDA'])}</cat:Descripcion>
                      <cat:DescripcionCorta>{fn:data($prepago/ns1:Column[@name= 'MONEDA'])}</cat:DescripcionCorta>
                </com:moneda>
            </pre:Monto>
             <pre:Monto>
                <com:tipo>
                    <cat:Descripcion>MONTO_INTERESES</cat:Descripcion>
                </com:tipo>
                <com:importe>{fn:data($OutputParameters/ns1:P_INTERESES)}</com:importe>
                <com:moneda>
                      <cat:Descripcion>{fn:data($prepago/ns1:Column[@name= 'DESCRIPCION_MONEDA'])}</cat:Descripcion>
                      <cat:DescripcionCorta>{fn:data($prepago/ns1:Column[@name= 'MONEDA'])}</cat:DescripcionCorta>
                </com:moneda>
            </pre:Monto>
            <pre:Tipo>
                <cat:Descripcion>{fn:data($prepago/ns1:Column[@name= 'TIPO_PREPAGO'])}</cat:Descripcion>
            </pre:Tipo>
            <pre:Resolucion>
                <cat:Id>{fn:data($prepago/ns1:Column[@name='ID_TCA_TIPO_RESOLUCION'])}</cat:Id>
                <cat:Descripcion></cat:Descripcion>
                <cat:DescripcionCorta>{fn:data($prepago/ns1:Column[@name='DESCRIPCION_RESOLUCION'])}</cat:DescripcionCorta>
                <cat:estatus></cat:estatus>
                <cat:codigoExterno></cat:codigoExterno>
            </pre:Resolucion>
            <pre:Operacion>
                <ope:idOperacion>{fn:data($prepago/ns1:Column[@name= 'ID_OPERACION'])}</ope:idOperacion>
                <ope:nombre>{fn:data($prepago/ns1:Column[@name= 'NOMBRE'])}</ope:nombre>
            </pre:Operacion>
            <pre:Cliente>
                <cli:idFacturador>{fn:data($prepago/ns1:Column[@name= 'ID_FLEXCUBE'])}</cli:idFacturador>
                <cli:razonSocial>{fn:data($prepago/ns1:Column[@name= 'RAZON_SOCIAL'])}</cli:razonSocial>
                <cli:pais>
                    <cat:Descripcion>{fn:data($prepago/ns1:Column[@name= 'PAIS'])}</cat:Descripcion>
                </cli:pais>
            </pre:Cliente>
         {
         for $cargo in $OutputParameters/ns1:P_CARGOS/ns1:Row
         return
            <pre:Cargo>
                <pre:Rol>
                    <cat:Id>{fn:data($cargo/ns1:Column[@name= 'ID'])}</cat:Id>
                    <cat:Descripcion>{fn:data($cargo/ns1:Column[@name= 'DESCRIPCION'])}</cat:Descripcion>
                </pre:Rol>
                <pre:Monto>
                    <com:tipo>
                        <cat:Descripcion>{fn:data($cargo/ns1:Column[@name= 'CARGOS_TRAMITE']/@name)}</cat:Descripcion>
                    </com:tipo>
                    <com:importe>{fn:data($cargo/ns1:Column[@name= 'CARGOS_TRAMITE'])}</com:importe>
                    <com:moneda>
                        <cat:DescripcionCorta>{fn:data($cargo/ns1:Column[@name= 'MONEDA_CARGOS_TRAMITE'])}</cat:DescripcionCorta>
                    </com:moneda>
                </pre:Monto>
                 <pre:Monto>
                    <com:tipo>
                        <cat:Descripcion>{fn:data($cargo/ns1:Column[@name= 'OTROS_CARGOS']/@name)}</cat:Descripcion>
                    </com:tipo>
                    <com:importe>{fn:data($cargo/ns1:Column[@name= 'OTROS_CARGOS'])}</com:importe>
                    <com:moneda>
                        <cat:DescripcionCorta>{fn:data($cargo/ns1:Column[@name= 'MONEDA_OTROS_CARGOS'])}</cat:DescripcionCorta>
                    </com:moneda>
                </pre:Monto>
            </pre:Cargo>
            }
            {
                for $RowDistinct in distinct-values($OutputParameters/ns1:P_BANCOS/ns1:Row/ns1:Column[@name= 'CUENTA'])
                for $Row in $OutputParameters/ns1:P_BANCOS/ns1:Row[ns1:Column[@name= 'CUENTA']=$RowDistinct][1]
                let $Referencias := distinct-values($OutputParameters/ns1:P_BANCOS/ns1:Row[ns1:Column[@name= 'CUENTA']=$RowDistinct]/ns1:Column[@name= 'REFERENCIA'])
                return 
                <pre:InfoBanco>
                    <ges:banco>{fn:data($Row/ns1:Column[@name= 'NOMBRE_BANCO'])}</ges:banco>
                    <ges:SWIFTBanco>{fn:data($Row/ns1:Column[@name= 'CODIGO_BIC'])}</ges:SWIFTBanco>
                    <ges:cuentaNo>{fn:data($Row/ns1:Column[@name= 'CUENTA'])}</ges:cuentaNo>
                    <ges:beneficiario>BANCO CENTROAMERICANO DE INTEGRACIÓN ECONÓMICA</ges:beneficiario>
                    <ges:SWIFTBeneficiario>BCIEHNTE</ges:SWIFTBeneficiario>
                    <ges:Referencia>{string-join((
                      for $referencia in distinct-values($OutputParameters/ns1:P_BANCOS/ns1:Row[ns1:Column[@name= 'CUENTA']=$RowDistinct]/ns1:Column[@name= 'REFERENCIA'])
                      return 
                        <NumeroLinea>{$referencia}</NumeroLinea>),',')
                    }</ges:Referencia>
                </pre:InfoBanco>
            }
        </ns2:Prepago>
        }
        <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Consulta Exitosa</res:message>
        </ns2:Resultado>
    </ns2:ConsultarPrepagoResponse>
};

local:func($OutputParameters)
