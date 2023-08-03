xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://www.bcie.org/GestionCobroMO";
(:: import schema at "../../../../../../MDS/Resources/ComponentesComunes/DominioCliente/GestionCobro/V1/Schema/GestionCobroMO.xsd" ::)
declare namespace ns1="http://xmlns.oracle.com/pcbpel/adapter/db/sp/GenerarLoteReciboPago";
(:: import schema at "../XSD/GenerarLoteReciboPago_sp.xsd" ::)

declare namespace des = "http://www.bcie.org/DesembolsoBO";

declare namespace cat = "http://www.bcie.org/CatalogoBO";

declare namespace com = "http://www.bcie.org/CommonBO";

declare namespace lin = "http://www.bcie.org/LineaCreditoBO";

declare namespace ges = "http://www.bcie.org/GestionCobroBO";

declare namespace cli = "http://www.bcie.org/ClienteBO";

declare namespace her = "http://www.bcie.org/HerramientaCEBO";

declare namespace res = "http://www.bcie.org/ResultBO";

declare namespace err = "http://www.bcie.org/ErrorBO";

declare variable $OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::) external;

declare function local:func($OutputParameters as element() (:: schema-element(ns1:OutputParameters) ::)) as element() (:: schema-element(ns2:GenerarLoteReciboPagoResponse) ::) {
    <ns2:GenerarLoteReciboPagoResponse>
    
       <ns2:ReciboPago>  
       {
        for $recibo in  distinct-values($OutputParameters/ns1:P_LISTA_RECIBOS/ns1:Row/ns1:Column[@name ='NUMERO_RECIBO'])
        let $registro := $OutputParameters/ns1:P_LISTA_RECIBOS/ns1:Row[ns1:Column[@name ='NUMERO_RECIBO'] =  $recibo]
        return 
                <ges:recibo>
                  <ges:Recibo>{fn:data($recibo)}</ges:Recibo>
                  <ges:FechaEjecucion>{fn:current-date()}</ges:FechaEjecucion>
                  <ges:FechaEfectiva>{fn:data( distinct-values($registro/ns1:Column[@name ='FECHA_EFECTIVA']))}</ges:FechaEfectiva>
                  <ges:parameter>
                      <com:name>NUMERO_LINEA</com:name>
                      <com:value>{fn:data( distinct-values($registro/ns1:Column[@name ='LINEA']))}</com:value>
                  </ges:parameter>
                  
                {
                for $row in $OutputParameters/ns1:P_LISTA_RECIBOS/ns1:Row
                return
                
                if ($row/ns1:Column[@name ='NUMERO_RECIBO'] = $recibo)
                then
                  <ges:Desembolsos>
                      <des:idFacturador>{fn:data($row/ns1:Column[@name ='NUMERO_PRESTAMO'])}</des:idFacturador>
                      <des:referencia>{fn:data($row/ns1:Column[@name ='NUMERO_TESORERIA'])}</des:referencia>
                      <des:monto>
                          <com:importe>{fn:data($row/ns1:Column[@name ='VALOR_ADEUDADO'])}</com:importe>
                          <com:moneda>
                              <cat:DescripcionCorta>ADEUDADA</cat:DescripcionCorta>
                              <cat:codigoExterno>{fn:data($row/ns1:Column[@name ='MONEDA_ADEUDADA'])}</cat:codigoExterno>
                          </com:moneda>
                      </des:monto>
                       <des:monto>
                          <com:importe>{fn:data($row/ns1:Column[@name ='VALOR_PAGADO'])}</com:importe>
                          <com:moneda>
                              <cat:Descripcion>{fn:data($row/ns1:Column[@name ='SALDO_DESCRIPCION'])}</cat:Descripcion>
                              <cat:DescripcionCorta>PAGADA</cat:DescripcionCorta>
                              <cat:codigoExterno>{fn:data($row/ns1:Column[@name ='MONEDA_PAGADA'])}</cat:codigoExterno>
                          </com:moneda>
                      </des:monto>
                  </ges:Desembolsos>
                  else()
                  }
              </ges:recibo>
              }
          </ns2:ReciboPago>
       <ns2:Resultado>
            <res:result>OK</res:result>
            <res:message>Operacion Exitosa</res:message>
        </ns2:Resultado>
    </ns2:GenerarLoteReciboPagoResponse>
};

local:func($OutputParameters)
