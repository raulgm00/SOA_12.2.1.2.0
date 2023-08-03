xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns0="http://www.bcie.org/ConversorMonedasMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/Utilidades/ConversorMonedas/V1/Schema/ConversorMonedasMO.xsd" ::)
declare namespace ns2="http://www.bcie.org/DesembolsoMO";
(:: import schema at "../../../../../MDS/Resources/ComponentesComunes/DominioDesembolso/Desembolso/V1/Schema/DesembolsoMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/ConsultarTransitoGrupoPais";
(:: import schema at "../XSD/ConsultarTransitoGrupoPais_sp.xsd" ::)

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace con = "http://www.bcie.org/ConversorMonedasBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare namespace xsi="http://www.w3.org/2001/XMLSchema-instance";

declare variable $ConvertirMonedasImportesResponse as element() (:: schema-element(ns0:ConvertirMonedasImportesResponse) ::) external;
declare variable $SP_ConsultarGrupoPais_Out as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($ConvertirMonedasImportesResponse as element() (:: schema-element(ns0:ConvertirMonedasImportesResponse) ::),
$SP_ConsultarGrupoPais_Out as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:ConsultarTransitoGrupoPaisResponse) ::) {
    <ns2:ConsultarTransitoGrupoPaisResponse>
        {
          if(not($SP_ConsultarGrupoPais_Out/ns1:RECORDSET_GE/@xsi:nil = 'true')) then  
            let $totalGE_Convertido := fn:sum($ConvertirMonedasImportesResponse/ns0:ConvierteMonedasImporte[con:monedaDe/cat:DescripcionCorta = 'RECORDSET_GE']/con:montoConvertido)
            let $totalGE_SP := fn:sum($SP_ConsultarGrupoPais_Out/ns1:RECORDSET_GE/ns1:Row[ns1:Column[@name='COD_EXTERNO']= 'USD']/ns1:Column[@name='MONTO_DESEMBOLSAR'])
            let $totalGE := $totalGE_Convertido + $totalGE_SP
            return 
                <ns2:Montos>
                    <com:tipo>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta>RECORDSET_GE</cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </com:tipo>
                    <com:importe>{fn:data($totalGE)}</com:importe>
                    <com:moneda>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno>USD</cat:codigoExterno>
                    </com:moneda>
                </ns2:Montos>
          else ()
        }

        {
            let $totalPais_Convertido := fn:sum($ConvertirMonedasImportesResponse/ns0:ConvierteMonedasImporte[con:monedaDe/cat:DescripcionCorta = 'RECORDSET_GE']/con:montoConvertido)
            let $totalPais_SP := fn:sum($SP_ConsultarGrupoPais_Out/ns1:RECORDSET_GE/ns1:Row[ns1:Column[@name='COD_EXTERNO']= 'USD']/ns1:Column[@name='MONTO_DESEMBOLSAR'])
            let $totalPais := $totalPais_Convertido + $totalPais_SP
            return 
                <ns2:Montos>
                    <com:tipo>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta>RECORDSET_PAIS</cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno></cat:codigoExterno>
                    </com:tipo>
                    <com:importe>{fn:data($totalPais)}</com:importe>
                    <com:moneda>
                        <cat:Id></cat:Id>
                        <cat:Descripcion></cat:Descripcion>
                        <cat:DescripcionCorta></cat:DescripcionCorta>
                        <cat:estatus></cat:estatus>
                        <cat:codigoExterno>USD</cat:codigoExterno>
                    </com:moneda>
                </ns2:Montos>
        }
        {
        if($SP_ConsultarGrupoPais_Out/ns1:P_CODIGO_RES/text() = '0') then          
          <ns2:Resultado>
              <res:result>OK</res:result>
              <res:message>{fn:data($SP_ConsultarGrupoPais_Out/ns1:P_MENSAJE)}</res:message>
          </ns2:Resultado>
        else
           <ns2:Resultado>
              <res:result>ERROR</res:result>
              <res:message>{fn:data($SP_ConsultarGrupoPais_Out/ns1:P_MENSAJE)}</res:message>
              <res:error>
                  <err:errorCode>{fn:data($SP_ConsultarGrupoPais_Out/ns1:P_CODIGO_RES)}</err:errorCode>
                  <err:errorDescription>{fn:data($SP_ConsultarGrupoPais_Out/ns1:P_MENSAJE)}</err:errorDescription>
                  <err:errorType></err:errorType>
              </res:error>
          </ns2:Resultado>
        }
    </ns2:ConsultarTransitoGrupoPaisResponse>
};

local:func($ConvertirMonedasImportesResponse,$SP_ConsultarGrupoPais_Out)
