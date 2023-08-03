xquery version "1.0" encoding "utf-8";

(:: OracleAnnotationVersion "1.0" ::)

declare namespace ns1="http://www.oracle.com/retail/integration/base/bo/ASNOutDesc/v1";
(:: import schema at "../../../MetaData/Components/EnterpriseObjectLibrary/Custom/Schemas/ASNOutDesc.xsd" ::)
declare namespace ns2="http://www.oracle.com/retail/integration/rib/RibMessages";
(:: import schema at "../../../MetaData/Components/EnterpriseObjectLibrary/Custom/Schemas/RibMessages.xsd" ::)

declare variable $ASNout as element() (:: schema-element(ns1:ASNOutDesc) ::) external;

declare function local:func($ASNout as element() (:: schema-element(ns1:ASNOutDesc) ::)) as element() (:: schema-element(ns2:RibMessages) ::) {
    <ns2:RibMessages>
        <ns2:ribMessage>
            <ns2:family>{'Receiving'}</ns2:family>
            <ns2:type>{'appointcre'}</ns2:type>
            <ns2:id>{fn:concat(
                                    fn-bea:dateTime-to-string-with-format(
                                                                        "yyyyMMddhhmmsS",
                                                                        fn:adjust-dateTime-to-timezone(fn:current-dateTime(),())
                                                                        )
                                    ,"")}</ns2:id>
            <ns2:ribmessageID>{concat(
                                  'publishRibMessageBS',
                                  '|',
                                  fn-bea:dateTime-to-string-with-format(
                                                                        "yyyy-MM-dd hh:mm:s.S z",
                                                                        fn:adjust-dateTime-to-timezone(fn:current-dateTime(),())
                                                                        )
                                  )
                          }
            </ns2:ribmessageID>
            <ns2:publishTime>{fn:concat(
                                    fn-bea:dateTime-to-string-with-format(
                                                                        "yyyy-MM-dd hh:mm:s.S z",
                                                                        fn:adjust-dateTime-to-timezone(fn:current-dateTime(),())
                                                                        )
                                    ,"")
                          }
                                    </ns2:publishTime>
            <ns2:hospitalID></ns2:hospitalID>
            <ns2:messageData>  
              {fn-bea:serialize(  fn:concat('<![CDATA[',fn-bea:serialize($ASNout),']]>'))}
            </ns2:messageData>
            <ns2:customData></ns2:customData>
            <ns2:customFlag></ns2:customFlag>
        </ns2:ribMessage>
    </ns2:RibMessages>
};

local:func($ASNout)