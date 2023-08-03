xquery version "1.0" encoding "UTF-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ban = "http://xmlns.banesco.com//xQuery/fn";

declare namespace ns2="http://dto.eis.pasarela.hubpagos.bytesw.com/";
(:: import schema at "../../../../Commons/backends/TELERED/resources/xsd/pasarela.xsd" ::)
declare namespace ns1="http://xmlns.banesco.com/eopt/PmtUtilAdd_V1.0";
(:: import schema at "../../../../Commons/xsd/EOpt/Payment/PmtUtilAdd/PmtUtilAdd_V1.0.xsd" ::)
declare namespace common="http://xmlns.banesco.com/appopt/AOptCommon_V1.0";
(:: import schema at "../../../../Commons/xsd/AppOpt/AOptCommon/AOptCommon_V1.0.xsd" ::)



declare namespace pmt = "http://xmlns.banesco.com/eo/Pmt_V1.0";
declare namespace ns9="http://xmlns.banesco.com/eo/Common_V1.0";
declare namespace ns4="http://xmlns.banesco.com/eo/Acct_V1.0";
declare namespace bil = "http://xmlns.banesco.com/eo/Bill_V1.0";

declare variable $PmtUtilAddRq as element() (:: schema-element(ns1:PmtUtilAddRq) ::) external;
declare variable $header as element() (:: schema-element(ns1:PmtUtilAddRq) ::) external;




declare function ban:formatoFecha($fecha as xs:date) as element(*){


let $mes := <vMes>
            {            
                if (fn:string-length(string(fn:month-from-date($fecha)))=1) then
                <mes>
                 {fn:concat('0',fn:month-from-date($fecha))}
                </mes>
                else
                <mes>
                 {fn:month-from-date($fecha)}
                </mes>
            }
            </vMes>


let $dia := <vDia>
            {
                if (fn:string-length(string(fn:day-from-date($fecha)))=1) then
                <dia>
                 {fn:concat('0',fn:month-from-date($fecha))}
                </dia>
                else
                <dia>
                 {fn:month-from-date($fecha)}
                </dia>            
            }
            </vDia>
let $anio := fn:year-from-date($fecha)
let $fechaBackend := fn:concat($mes/mes,'/',$dia/dia,'/',$anio)
return            
         <Result>{$fechaBackend}</Result>
         
};

declare function local:PmtUtilAdd($PmtUtilAddRq as element() (:: schema-element(ns1:PmtUtilAddRq) ::), $header as element() (:: schema-element(common:MsgHdrRq) ::)) as element() (:: schema-element(ns2:PayBillRequest) ::) {


let $campos:= 
    <ns1:Fields>
        {$PmtUtilAddRq/ns1:Fields/ns1:Field[./ns9:ServiceOper/text()='PmtUtilAddRq']}
     </ns1:Fields>


let $elementos:=<xml>{
                      for $index in $PmtUtilAddRq//ns1:Pmt//node()                       
                      return                      
                      if (local-name($index)=$campos/ns1:Field/ns9:ServiceField) then
                        <campo fielId="{local-name($index)}">                           
                            {$index/text()}
                        </campo>
                      else if (local-name($index)='Amt') then 
                        <campo fielId="{local-name($index)}">
                           {$index}
                        </campo>
                      else()
                      
                   }
              </xml>
 

let $split:=<split>
        {
            for $index in fn:tokenize($PmtUtilAddRq/ns1:Bill/ns1:Biller/bil:BillerId,'(\D+)') 
            return 
            <valor>
                {$index}
            </valor>
         }
    </split>   

return   
    <ns2:PayBillRequest>      
      <sesionId>
        {$PmtUtilAddRq/ns1:SessionId/text()}
      </sesionId>
      <facturador>
        {$split/valor[1]/text()}
      </facturador>
      <cobranza>
        {$split/valor[2]/text()}
      </cobranza>
      <servicio>
        {$split/valor[3]/text()}
      </servicio> 
      <idTrxColector>
       { 
       if ($header/common:RequestId/text()) then
       $header/common:RequestId/text()
       else
       dvmtr:lookup('Commons/dvm/ServiceProperties', 'CODE', 'TeleredPmt.IdTrxColector', 'VALUE', 'NA')
       }
      </idTrxColector>
      <camposCobranza>        
        {
            for $index in $campos/ns1:Field
            let $valorElemento:=$index/ns9:ServiceField            
            return
            if ($valorElemento='TotalPmt') then           
            element {$index/ns9:FieldXmlProvider/text()} {$elementos/campo[@fielId='Amt']/*:Amt[./*:AmtCode=$valorElemento]/*:CurAmt/*:Amt/text()}
            else if ($valorElemento='AgreeBalance') then           
            element {$index/ns9:FieldXmlProvider/text()} {$elementos/campo[@fielId='Amt']/*:Amt[./*:AmtCode=$valorElemento]/*:CurAmt/*:Amt/text()}
            else if ($valorElemento='CurrentBal') then           
            element {$index/ns9:FieldXmlProvider/text()} {$elementos/campo[@fielId='Amt']/*:Amt[./*:AmtCode=$valorElemento]/*:CurAmt/*:Amt/text()}
            else if ($valorElemento='Discount') then           
            element {$index/ns9:FieldXmlProvider/text()} {$elementos/campo[@fielId='Amt']/*:Amt[./*:AmtCode=$valorElemento]/*:CurAmt/*:Amt/text()}
            else if ($valorElemento='SubTotal') then           
            element {$index/ns9:FieldXmlProvider/text()} {$elementos/campo[@fielId='Amt']/*:Amt[./*:AmtCode=$valorElemento]/*:CurAmt/*:Amt/text()}
            else if ($valorElemento='TotalBal') then           
            element {$index/ns9:FieldXmlProvider/text()} {$elementos/campo[@fielId='Amt']/*:Amt[./*:AmtCode=$valorElemento]/*:CurAmt/*:Amt/text()}
            else if ($valorElemento='TotalTax') then           
            element {$index/ns9:FieldXmlProvider/text()} {$elementos/campo[@fielId='Amt']/*:Amt[./*:AmtCode=$valorElemento]/*:CurAmt/*:Amt/text()}
            else if ($valorElemento='OverDueBalance') then           
            element {$index/ns9:FieldXmlProvider/text()} {$elementos/campo[@fielId='Amt']/*:Amt[./*:AmtCode=$valorElemento]/*:CurAmt/*:Amt/text()}
            else if ($valorElemento='TotalBalance') then           
            element {$index/ns9:FieldXmlProvider/text()} {$elementos/campo[@fielId='Amt']/*:Amt[./*:AmtCode=$valorElemento]/*:CurAmt/*:Amt/text()}
            else if ($valorElemento='CurrentBalance') then           
            element {$index/ns9:FieldXmlProvider/text()} {$elementos/campo[@fielId='Amt']/*:Amt[./*:AmtCode=$valorElemento]/*:CurAmt/*:Amt/text()}
            else if ($valorElemento='DueDt' and $PmtUtilAddRq/ns1:Bill/ns1:Biller/bil:BillerId='11-15-1') then 
            element {$index/ns9:FieldXmlProvider/text()} {ban:formatoFecha($elementos/campo[@fielId=$valorElemento])/text()}
            else 
            element {$index/ns9:FieldXmlProvider/text()} {$elementos/campo[@fielId=$valorElemento]/text()}       
                       
        }       
      </camposCobranza>
      <subTotal>
        {$PmtUtilAddRq/ns1:Pmt/pmt:PmtCreditDetail/pmt:Amt[./pmt:AmtCode='SubTotal']/pmt:CurAmt/ns9:Amt/text()}
      </subTotal>
      <impuestos>
        {$PmtUtilAddRq/ns1:Pmt/pmt:PmtCreditDetail/pmt:Amt[./pmt:AmtCode='TotalTax']/pmt:CurAmt/ns9:Amt/text()}
      </impuestos>
      <total>
        {$PmtUtilAddRq/ns1:Pmt/pmt:PmtCreditDetail/pmt:Amt[./pmt:AmtCode='TotalBalance']/pmt:CurAmt/ns9:Amt/text()}
      </total>
      <autorizacionDebitoColector>
        {$PmtUtilAddRq/ns1:Pmt/pmt:DebtorData/pmt:DebtAuthCode/text()}
      </autorizacionDebitoColector>
      {
       if ($PmtUtilAddRq/ns1:Pmt/pmt:PmtKey/pmt:PmtId/text()) then
       <idPago>
        {$PmtUtilAddRq/ns1:Pmt/pmt:PmtKey/pmt:PmtId/text()}
       </idPago>
       else
       <idPago>        
        {$PmtUtilAddRq/ns1:Pmt/pmt:PmtCreditDetail/pmt:BillRef/text()}
       </idPago>
      }
      
      <moneda>
        {$PmtUtilAddRq/ns1:Pmt/pmt:CurCode/text()}
      </moneda>
      <numeroCuenta>
        {$PmtUtilAddRq/ns1:Pmt/pmt:DebtorData/pmt:DebtorAcct/ns4:AcctKey/ns4:AcctId/text()}
      </numeroCuenta>
      <producto>
        {$PmtUtilAddRq/ns1:Pmt/pmt:DebtorData/pmt:DebtorAcct/ns4:AcctType/ns9:Cod/text()}
      </producto>
      <codigoIdioma>
      {dvmtr:lookup('Commons/dvm/ServiceProperties', 'CODE', 'TeleredPmt.CodigoIdioma', 'VALUE', 'NA')}
      </codigoIdioma>
    </ns2:PayBillRequest>
};


local:PmtUtilAdd($PmtUtilAddRq, $header)