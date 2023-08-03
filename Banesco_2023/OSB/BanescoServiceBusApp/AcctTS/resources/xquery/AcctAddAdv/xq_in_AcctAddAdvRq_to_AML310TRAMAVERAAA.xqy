xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace appoptcommon="http://xmlns.banesco.com/appopt/AOptCommon_V1.0";
(:: import schema at "../../../../Commons/xsd/AppOpt/AOptCommon/AOptCommon_V1.0.xsd" ::)
declare namespace eoptAcctAddAdv="http://xmlns.banesco.com/eopt/AcctAddAdv_V1.0";
(:: import schema at "../../../../Commons/xsd/EOpt/Account/AcctAddAdv/AcctAddAdv_V1.0.xsd" ::)

declare namespace com = "http://xmlns.banesco.com/eo/Common_V1.0";

declare variable $MsgHdrRq as element() (:: schema-element(appoptcommon:MsgHdrRq) ::) external;
declare variable $AcctAddAdvRq as element() (:: schema-element(eoptAcctAddAdv:AcctAddAdvRq) ::) external;

declare function local:func($AcctAddAdvRq as element() (:: schema-element(eoptAcctAddAdv:AcctAddAdvRq) ::)) 
                            as element() {
    <MFL_310_AcctAddAdv>
    <AcctAddAdv>
      <ByteI>ByteI</ByteI>
        <FLG>N</FLG>
        <DD>{fn:day-from-date(fn:current-date())}</DD>
        <MM>{fn:month-from-date(fn:current-date())}</MM>
        <YY>{fn:year-from-date(fn:current-date())}</YY>
        <HH>{fn:hours-from-time(fn:current-time())}</HH>
        <MN>{fn:minutes-from-time(fn:current-time())}</MN>
        <EVENT>310</EVENT>
        <ISSUER>{fn:data($MsgHdrRq/appoptcommon:ClientApp/appoptcommon:UserId)}</ISSUER>
        
        <PersonalId></PersonalId>
        <OpenAcctDate></OpenAcctDate>
        <TxnTime></TxnTime>
        <PersonType></PersonType>
        <TxnAccount></TxnAccount>
        <ProductCode></ProductCode>
        <SubProdCode></SubProdCode>
        <BranchId></BranchId>
        <TerminalId></TerminalId>
        <UserCode></UserCode>
        <FlagVip></FlagVip>
        <CustomerName></CustomerName>
        <PersonAddr></PersonAddr>
        <HousePhone></HousePhone>
        <OfficePhone></OfficePhone>
        <OtherPhone></OtherPhone>
        <Email></Email>
        <Segment></Segment>
        <BirthDt></BirthDt>
        <BirthPlace></BirthPlace>
        <Gender></Gender>
        <MaritalStatus></MaritalStatus>
        <Sector></Sector>
        <Authoriser></Authoriser>
        <CustomerId></CustomerId>
        
        <AFutureUse1></AFutureUse1>
        <AFutureUse2></AFutureUse2>
        <NFutureUse1></NFutureUse1>
        <NFutureUse2></NFutureUse2>

        <ByteF>ByteF</ByteF>
    </AcctAddAdv>
</MFL_310_AcctAddAdv>
};

local:func($AcctAddAdvRq)