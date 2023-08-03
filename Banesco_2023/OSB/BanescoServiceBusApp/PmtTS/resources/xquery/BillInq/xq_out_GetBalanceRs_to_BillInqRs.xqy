xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://xmlns.banesco.com/eopt/BillCPagInq_V1.0";
(:: import schema at "../../../../Commons/xsd/EOpt/Payment/BillCPagInq/BillCPagInq_V1.0.xsd" ::)

declare namespace par = "http://xmlns.banesco.com/eo/Party_V1.0";

declare namespace com = "http://xmlns.banesco.com/eo/Common_V1.0";

declare namespace sta = "http://xmlns.banesco.com/eo/Status_V1.0";

declare namespace bil = "http://xmlns.banesco.com/eo/Bill_V1.0";

declare namespace ns0 = "http://dto.eis.pasarela.hubpagos.bytesw.com/";

declare variable $GetBalance as element() external;

declare function local:getElementValue($GetBalanceElement as element(), $Fields as element(), $CanonicalNodeName as xs:string) as element(){
  let $nodeName := $Fields/ns1:Field[com:ServiceField = $CanonicalNodeName]/com:FieldXmlProvider/text()
  return 
  <node>{ $GetBalanceElement/ns0:GetBalanceResponse/camposCobranza/node()[local-name(.) = $nodeName]/text() }</node>
};


declare function local:func($GetBalance as element()) as element() (:: schema-element(ns1:BillInqRs) ::) {
let $fieldsValues := <ns1:Fields>{$GetBalance/ns1:Fields/ns1:Field[data(./com:ServiceOper) = 'BillInqRs']}</ns1:Fields>
return
      if(not(exists($GetBalance/ns0:GetBalanceResponse/error/codigo))) then
      <ns1:BillInqRs>
        <ns1:Bill>
            {
              let $openDt := local:getElementValue($GetBalance, $fieldsValues, "OpenDt") 
              let $openDtSplit := tokenize($openDt, "/") return
                if($openDt/text() != '') then
                  if(string-length($openDtSplit[1]) = 4) then
                    <bil:OpenDt>{ concat($openDtSplit[1], "-", $openDtSplit[2], "-", $openDtSplit[3]) }</bil:OpenDt>
                  else if(string-length($openDtSplit[3]) = 4) then
                    <bil:OpenDt>{ concat($openDtSplit[3], "-", $openDtSplit[2], "-", $openDtSplit[1]) }</bil:OpenDt>
                  else ()
                else ()
            }           
            {
              let $partyName := local:getElementValue($GetBalance, $fieldsValues, "PartyName") return
              if ($partyName/text() != '') then
                <bil:PartyName>{ $partyName/text() }</bil:PartyName>
              else ()
              
            }
            <bil:PartyRef>
              <par:IssuedIdentType>{ local:getElementValue($GetBalance, $fieldsValues, "IssuedIdentType")/text() }</par:IssuedIdentType>
              <par:PartyId>{ local:getElementValue($GetBalance, $fieldsValues, "PartyId")/text() }</par:PartyId>                
            </bil:PartyRef>
            <bil:Procedure>
              <com:Type>{ local:getElementValue($GetBalance, $fieldsValues, "Type")/text() }</com:Type>
              <com:Value>{ local:getElementValue($GetBalance, $fieldsValues, "Value")/text() }</com:Value>
            </bil:Procedure>
            {
              let $billId := local:getElementValue($GetBalance, $fieldsValues, "BillId") return
              if ($billId/text() != '') then
                <bil:BillKey>
                  <bil:BillId>{ $billId/text() }</bil:BillId>
                </bil:BillKey>
              else ()
            }
            {
              let $overdueBalance := $GetBalance/ns0:GetBalanceResponse/camposCobranza/node()[local-name(.) = $fieldsValues/ns1:Field[com:ServiceField = 'OverdueBalance']/com:FieldXmlProvider/text()] return
                if($overdueBalance/text() != '') then
                  <bil:BillSummAmt>
                    <bil:BillSummAmtCode>OverdueBalance</bil:BillSummAmtCode>
                    <bil:CurAmt>
                      <com:Amt>{$overdueBalance/text()}</com:Amt>
                    </bil:CurAmt>
                  </bil:BillSummAmt>
                else ()
              }
              {
                let $currentBalance := $GetBalance/ns0:GetBalanceResponse/camposCobranza/node()[local-name(.) = $fieldsValues/ns1:Field[com:ServiceField = 'CurrentBalance']/com:FieldXmlProvider/text()] return
                if($currentBalance/text() != '') then
                  <bil:BillSummAmt>
                    <bil:BillSummAmtCode>CurrentBalance</bil:BillSummAmtCode>
                    <bil:CurAmt>
                      <com:Amt>{$currentBalance/text()}</com:Amt>
                    </bil:CurAmt>
                  </bil:BillSummAmt>
                else ()
              }
              {  
                let $totalBalance := $GetBalance/ns0:GetBalanceResponse/camposCobranza/node()[local-name(.) = $fieldsValues/ns1:Field[com:ServiceField = 'TotalBalance']/com:FieldXmlProvider/text()] return
                if($totalBalance/text() != '') then
                  <bil:BillSummAmt>
                    <bil:BillSummAmtCode>TotalBalance</bil:BillSummAmtCode>
                    <bil:CurAmt>
                      <com:Amt>{$totalBalance/text()}</com:Amt>
                    </bil:CurAmt>
                  </bil:BillSummAmt>
                else ()
              }
              {  
                let $totalPmt := $GetBalance/ns0:GetBalanceResponse/camposCobranza/node()[local-name(.) = $fieldsValues/ns1:Field[com:ServiceField = 'TotalPmt']/com:FieldXmlProvider/text()] return
                if($totalPmt/text() != '') then
                  <bil:BillSummAmt>
                    <bil:BillSummAmtCode>TotalPmt</bil:BillSummAmtCode>
                    <bil:CurAmt>
                      <com:Amt>{$totalPmt/text()}</com:Amt>
                    </bil:CurAmt>
                  </bil:BillSummAmt>
                else ()
              }
              {  
                let $discount := $GetBalance/ns0:GetBalanceResponse/camposCobranza/node()[local-name(.) = $fieldsValues/ns1:Field[com:ServiceField = 'Discount']/com:FieldXmlProvider/text()] return
                if($discount/text() != '') then
                  <bil:BillSummAmt>
                    <bil:BillSummAmtCode>Discount</bil:BillSummAmtCode>
                    <bil:CurAmt>
                      <com:Amt>{$discount/text()}</com:Amt>
                    </bil:CurAmt>
                  </bil:BillSummAmt>
                else ()
              }
              {  
                let $disrespectAmt := $GetBalance/ns0:GetBalanceResponse/camposCobranza/node()[local-name(.) = $fieldsValues/ns1:Field[com:ServiceField = 'DisrespectAmt']/com:FieldXmlProvider/text()] return
                if($disrespectAmt/text() != '') then
                  <bil:BillSummAmt>
                    <bil:BillSummAmtCode>DisrespectAmt</bil:BillSummAmtCode>
                    <bil:CurAmt>
                      <com:Amt>{$disrespectAmt/text()}</com:Amt>
                    </bil:CurAmt>
                  </bil:BillSummAmt>
                else ()
              }
              {  
                let $newBalance := $GetBalance/ns0:GetBalanceResponse/camposCobranza/node()[local-name(.) = $fieldsValues/ns1:Field[com:ServiceField = 'NewBalance']/com:FieldXmlProvider/text()] return
                if($newBalance/text() != '') then
                  <bil:BillSummAmt>
                    <bil:BillSummAmtCode>NewBalance</bil:BillSummAmtCode>
                    <bil:CurAmt>
                      <com:Amt>{$newBalance/text()}</com:Amt>
                    </bil:CurAmt>
                  </bil:BillSummAmt>
                else ()
              }
              {  
                let $agreeBalance := $GetBalance/ns0:GetBalanceResponse/camposCobranza/node()[local-name(.) = $fieldsValues/ns1:Field[com:ServiceField = 'AgreeBalance']/com:FieldXmlProvider/text()] return
                if($agreeBalance/text() != '') then
                  <bil:BillSummAmt>
                    <bil:BillSummAmtCode>AgreeBalance</bil:BillSummAmtCode>
                    <bil:CurAmt>
                      <com:Amt>{$agreeBalance/text()}</com:Amt>
                    </bil:CurAmt>
                  </bil:BillSummAmt>
                else ()
              }
              {  
                let $subTotal := $GetBalance/ns0:GetBalanceResponse/camposCobranza/node()[local-name(.) = $fieldsValues/ns1:Field[com:ServiceField = 'SubTotal']/com:FieldXmlProvider/text()] return
                if($subTotal/text() != '') then
                  <bil:BillSummAmt>
                    <bil:BillSummAmtCode>SubTotal</bil:BillSummAmtCode>
                    <bil:CurAmt>
                      <com:Amt>{$subTotal/text()}</com:Amt>
                    </bil:CurAmt>
                  </bil:BillSummAmt>
                else ()
              }
        </ns1:Bill>
        <ns1:Status>
            <sta:StatusCode>M0000</sta:StatusCode>
            <sta:StatusDesc>OK</sta:StatusDesc>
        </ns1:Status>
        </ns1:BillInqRs>
        else
        <ns1:BillInqRs>
        <ns1:Status>
            <sta:StatusCode>
            { 
              dvmtr:lookup('Commons/dvm/StatusMappingBcknd', 'TELERED', $GetBalance/*:GetBalanceResponse/error/codigo/text(), 'CNCODE', data($GetBalance/ns0:GetBalanceResponse/error/codigo))
            }
            </sta:StatusCode>
            <sta:StatusDesc>
            {
              dvmtr:lookup('Commons/dvm/StatusMappingBcknd', 'TELERED', $GetBalance/*:GetBalanceResponse/error/codigo/text(), 'CNDESC', 'CouldNotBeFoundMessage')
            }
            </sta:StatusDesc>
            <sta:AdditionalStatus>
                <sta:StatusCode>
                { 
                  if(exists($GetBalance/ns0:GetBalanceResponse/error/codigo)) then 
                    data($GetBalance/ns0:GetBalanceResponse/error/codigo)
                  else ()
                }
                </sta:StatusCode>
                <sta:StatusDesc>
                { 
                  if(exists($GetBalance/ns0:GetBalanceResponse/error/descripcion)) then 
                    data($GetBalance/ns0:GetBalanceResponse/error/descripcion)
                  else ()
                }
                </sta:StatusDesc>
            </sta:AdditionalStatus>
        </ns1:Status>
        </ns1:BillInqRs>
};

local:func($GetBalance)
