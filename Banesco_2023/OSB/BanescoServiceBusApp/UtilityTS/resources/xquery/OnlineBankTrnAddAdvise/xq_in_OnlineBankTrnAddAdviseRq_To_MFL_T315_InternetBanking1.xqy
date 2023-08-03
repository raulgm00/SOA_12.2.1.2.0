xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns2="http://xmlns.banesco.com/appopt/AOptCommon_V1.0";
(:: import schema at "../../../../Commons/xsd/AppOpt/AOptCommon/AOptCommon_V1.0.xsd" ::)
declare namespace ns1="http://xmlns.banesco.com/eopt/OnlineBankTrnAddAdvise_V1.0";
(:: import schema at "../../../../Commons/xsd/EOpt/Utility/OnlineBankTrnAddAdvise/OnlineBankTrnAddAdvise_V1.0.xsd" ::)

declare namespace par = "http://xmlns.banesco.com/eo/Party_V1.0";

declare namespace com = "http://xmlns.banesco.com/eo/Common_V1.0";

declare variable $OnlineBankTrnAddAdviseRq1 as element() (:: schema-element(ns1:OnlineBankTrnAddAdviseRq) ::) external;
declare variable $MsgHdrRq1 as element() (:: schema-element(ns2:MsgHdrRq) ::) external;

declare function local:func($OnlineBankTrnAddAdviseRq1 as element() (:: schema-element(ns1:OnlineBankTrnAddAdviseRq) ::), 
                            $MsgHdrRq1 as element() (:: schema-element(ns2:MsgHdrRq) ::)) 
                            as element() {
  <MFL_IB>
    <InternetBanking>
       <ByteI>ByteI</ByteI>
        <FLG>N</FLG>
        <DD>{fn:day-from-date(fn:current-date())}</DD>
        <MM>{fn:month-from-date(fn:current-date())}</MM>
        <YY>{fn:year-from-date(fn:current-date())}</YY>
        <HH>{fn:hours-from-time(fn:current-time())}</HH>
        <MN>{fn:minutes-from-time(fn:current-time())}</MN>
        <EVENT>315</EVENT>
        <ISSUER>{fn:substring(fn:data($MsgHdrRq1/ns2:ClientApp/ns2:UserId),1,10)}</ISSUER>
        <ChannelId>{fn:substring(fn:data($MsgHdrRq1/ns2:ClientApp/ns2:ChannelId),1,3)}</ChannelId>
        <CustomerId>{fn:substring(fn:data($OnlineBankTrnAddAdviseRq1/ns1:PartyKey/par:IssuedIdentValue),1,30)}</CustomerId>
        <PersonType>{fn:substring(fn:data($OnlineBankTrnAddAdviseRq1/ns1:PartyKey/par:PartyType),1,1)}</PersonType>
        <DateTrx>{concat(fn:substring(xs:string($OnlineBankTrnAddAdviseRq1/ns1:EffDt),1,4),
        fn:substring(xs:string($OnlineBankTrnAddAdviseRq1/ns1:EffDt),6,2),
        fn:substring(xs:string($OnlineBankTrnAddAdviseRq1/ns1:EffDt),9,2))}
        </DateTrx>
        <TimeTrx>{
        fn:concat(fn:substring(xs:string($OnlineBankTrnAddAdviseRq1/ns1:EffHr),1 , 2),
        fn:substring(xs:string($OnlineBankTrnAddAdviseRq1/ns1:EffHr),4 , 2),
        fn:substring(xs:string($OnlineBankTrnAddAdviseRq1/ns1:EffHr),7 , 2))}</TimeTrx>
        <IPAddress>{fn:data($OnlineBankTrnAddAdviseRq1/ns1:SessionInfo/com:NetworkTrnData/com:IpAddress)}</IPAddress>
        <ISP>{fn:substring(fn:data($OnlineBankTrnAddAdviseRq1/ns1:SessionInfo/com:ISPCode),1,8)}</ISP>
        <CodeTrx>{fn:substring(fn:data($OnlineBankTrnAddAdviseRq1/ns1:TrnCode),1,6)}</CodeTrx>
        <ResponseCode>{fn:substring(fn:data($OnlineBankTrnAddAdviseRq1/ns1:TrnStatus),1,4)}</ResponseCode>
        <AccountNumber>{if( fn:data($OnlineBankTrnAddAdviseRq1/ns1:DebitAcctId) !='' )
        then fn:data($OnlineBankTrnAddAdviseRq1/ns1:DebitAcctId) else
       fn:data($OnlineBankTrnAddAdviseRq1/ns1:CreditAcctId) }</AccountNumber>
        <CustomerName>{fn:substring(fn:data($OnlineBankTrnAddAdviseRq1/ns1:CustomerName),1,40)}</CustomerName>
        <LocatedIP>{fn:data($OnlineBankTrnAddAdviseRq1/ns1:SessionInfo/com:Prefix)}</LocatedIP>
        <IBUser>{ fn:substring(fn:data($MsgHdrRq1/ns2:ClientApp/ns2:UserId),1,12)}</IBUser>
        <RefNumber>{fn:substring(fn:data($OnlineBankTrnAddAdviseRq1/ns1:TrnRef),1,20)}</RefNumber>
        <Email>{fn:substring(fn:data($OnlineBankTrnAddAdviseRq1/ns1:Email),1,40)}</Email>
        <ChckbType>{fn:data($OnlineBankTrnAddAdviseRq1/ns1:ChkBookType)}</ChckbType>
        <ChkbCount>{fn:data($OnlineBankTrnAddAdviseRq1/ns1:ChkBookQty)}</ChkbCount>
        <BlockReason>{fn:data($OnlineBankTrnAddAdviseRq1/ns1:BlockReason)}</BlockReason>
        <NotificationPerson>{fn:data($OnlineBankTrnAddAdviseRq1/ns1:NotifyName)}</NotificationPerson>
        <NotificationEmail>{fn:data($OnlineBankTrnAddAdviseRq1/ns1:NotifyEmail)}</NotificationEmail>
        <NotificationPhone>{fn:data($OnlineBankTrnAddAdviseRq1/ns1:NotifyPhone)}</NotificationPhone>
        <DestinationCountry>{fn:data($OnlineBankTrnAddAdviseRq1/ns1:CountryVisit/com:CountryName)}</DestinationCountry>
        <EntryDate>{concat(fn:substring(xs:string($OnlineBankTrnAddAdviseRq1/ns1:IngressDt),1,4),
        fn:substring(xs:string($OnlineBankTrnAddAdviseRq1/ns1:IngressDt),6,2),
        fn:substring(xs:string($OnlineBankTrnAddAdviseRq1/ns1:IngressDt),9,2))}</EntryDate>
        <DepartureDate>{concat(fn:substring(xs:string($OnlineBankTrnAddAdviseRq1/ns1:ExitDt),1,4),
        fn:substring(xs:string($OnlineBankTrnAddAdviseRq1/ns1:ExitDt),6,2),
        fn:substring(xs:string($OnlineBankTrnAddAdviseRq1/ns1:ExitDt),9,2))}</DepartureDate>
        <EmployeeFlag>{if(fn:data($OnlineBankTrnAddAdviseRq1/ns1:Employee)= true())
        then 'S' else 'N'}</EmployeeFlag>
        <CustomerCode>{fn:data($OnlineBankTrnAddAdviseRq1/ns1:PartyKey/par:PartyId)}</CustomerCode>
        <AFutureUse1>{fn:data($OnlineBankTrnAddAdviseRq1/ns1:Element1)}</AFutureUse1>
        <AFutureUse2>{fn:data($OnlineBankTrnAddAdviseRq1/ns1:Element2)}</AFutureUse2>
        <NFutureUse1>{fn:data($OnlineBankTrnAddAdviseRq1/ns1:Element3)}</NFutureUse1>
        <NFutureUse2>{fn:data($OnlineBankTrnAddAdviseRq1/ns1:Element4)}</NFutureUse2>
        <ByteF>ByteF</ByteF>
    </InternetBanking>
</MFL_IB>
};

local:func($OnlineBankTrnAddAdviseRq1, $MsgHdrRq1)
