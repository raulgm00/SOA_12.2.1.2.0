xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://tempuri.org/";
(:: import schema at "../../WSDL/WsGetDocumentOnBaseService.wsdl" ::)

declare variable $ConnectResponse as element() (:: schema-element(ns1:ConnectResponse) ::) external;

declare function local:func($ConnectResponse as element() (:: schema-element(ns1:ConnectResponse) ::)) as element() (:: schema-element(ns1:Disconnect) ::) {
    <ns1:Disconnect>
        <ns1:parameters>
            <ns1:DocumentHandler></ns1:DocumentHandler>
            {
                if ($ConnectResponse/ns1:ConnectResult/ns1:Result)
                then <ns1:SessionId>{fn:data($ConnectResponse/ns1:ConnectResult/ns1:Result)}</ns1:SessionId>
                else ()
            }
            <ns1:DataTypeReturn></ns1:DataTypeReturn>
        </ns1:parameters>
    </ns1:Disconnect>
};

local:func($ConnectResponse)
