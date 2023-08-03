xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://dto.eis.pasarela.hubpagos.bytesw.com/";
(:: import schema at "../../../../Commons/backends/TELERED/resources/wsdl/pasarelaWSLocal.wsdl" ::)
declare namespace ns1="http://xmlns.banesco.com/eopt/BillCPagInq_V1.0";
(:: import schema at "../../../../Commons/xsd/EOpt/Payment/BillCPagInq/BillCPagInq_V1.0.xsd" ::)

declare namespace bil = "http://xmlns.banesco.com/eo/Bill_V1.0";
declare namespace ns9="http://xmlns.banesco.com/eo/Common_V1.0";

declare variable $BillInqRq as element() (:: schema-element(ns1:BillInqRq) ::) external;

declare function local:func($BillInqRq as element() (:: schema-element(ns1:BillInqRq) ::)) as element() (:: schema-element(ns2:GetBalanceRequest) ::) {
let $campos:= 
    <ns1:Fields>
        {$BillInqRq/ns1:Fields/ns1:Field[./ns9:ServiceOper/text()='BillInqRq']}
    </ns1:Fields>

let $split:=<split>
        {
            for $index in fn:tokenize($BillInqRq/ns1:Bill/bil:Billers/bil:BillerId,'(\D+)') 
            return 
            <valor >
                {$index}
            </valor>
         }
    </split>
    
let $elementos:=<xml>{
                      for $index in $BillInqRq//node()                       
                      return                      
                      if (local-name($index)=$campos/ns1:Field/ns9:ServiceField) then
                        <campo fielId="{local-name($index)}">                           
                            {$index/text()}
                        </campo>
                      else if (local-name($index)='BillSummAmt') then 
                        <campo fielId="{local-name($index)}">
                           {$index}
                        </campo>
                      else()
                      
                   }
              </xml>
return
    <ns2:GetBalanceRequest>
        <sesionId>{fn:data($BillInqRq/ns1:SessionId)}</sesionId>
        <facturador>{$split/valor[1]/text()}</facturador>
        <cobranza>{$split/valor[2]/text()}</cobranza>
        <servicio>{$split/valor[3]/text()}</servicio>
        <idTrxColector></idTrxColector>
        <idConsulta>{fn:data($BillInqRq/ns1:Bill/bil:BillKey/bil:BillId)}</idConsulta>
        <camposCobranza>
        {
            for $index in $campos/ns1:Field
            let $valorElemento:=$index/ns9:ServiceField 
            return
            if ($valorElemento='TotalPmt') then           
            element {$index/ns9:FieldXmlProvider/text()} {$elementos/campo[@fielId='BillSummAmt']/*:BillSummAmt[./*:BillSummAmtCode=$valorElemento]/*:CurAmt/*:Amt/text()}
            else if ($valorElemento='AgreeBalance') then           
            element {$index/ns9:FieldXmlProvider/text()} {$elementos/campo[@fielId='BillSummAmt']/*:BillSummAmt[./*:BillSummAmtCode=$valorElemento]/*:CurAmt/*:Amt/text()}
            else if ($valorElemento='CurrentBal') then           
            element {$index/ns9:FieldXmlProvider/text()} {$elementos/campo[@fielId='BillSummAmt']/*:BillSummAmt[./*:BillSummAmtCode=$valorElemento]/*:CurAmt/*:Amt/text()}            
            else if ($valorElemento='TotalBal') then           
            element {$index/ns9:FieldXmlProvider/text()} {$elementos/campo[@fielId='BillSummAmt']/*:BillSummAmt[./*:BillSummAmtCode=$valorElemento]/*:CurAmt/*:Amt/text()}            
            else if ($valorElemento='TotalBalance') then           
            element {$index/ns9:FieldXmlProvider/text()} {$elementos/campo[@fielId='BillSummAmt']/*:BillSummAmt[./*:BillSummAmtCode=$valorElemento]/*:CurAmt/*:Amt/text()}
            else if ($valorElemento='CurrentBalance') then           
            element {$index/ns9:FieldXmlProvider/text()} {$elementos/campo[@fielId='BillSummAmt']/*:BillSummAmt[./*:BillSummAmtCode=$valorElemento]/*:CurAmt/*:Amt/text()}
            else 
            element {$index/ns9:FieldXmlProvider/text()} {$elementos/campo[@fielId=$valorElemento]/text()}
        }
        </camposCobranza>
        <codigoIdioma>{dvmtr:lookup('Commons/dvm/ServiceProperties', 'CODE', 'TeleredPmt.CodigoIdioma', 'VALUE', 'NA')}</codigoIdioma>
    </ns2:GetBalanceRequest>
};

local:func($BillInqRq)