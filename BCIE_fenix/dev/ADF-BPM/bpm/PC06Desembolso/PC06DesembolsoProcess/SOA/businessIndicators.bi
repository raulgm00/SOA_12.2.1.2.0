<?xml version="1.0" encoding="UTF-8"?>
<ns0:businessIndicators xmlns:ns0="http://xmlns.oracle.com/bpm/analytics/businessIndicator" fileVersion="20130625">
   <ns0:businessIndicator name="totalDesperdicioPC06">
      <ns0:id>e0ef92ad-70bb-4929-b855-675c738029cd</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter3')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="totalDesperdicio">
      <ns0:id>de6a52a2-6f25-4eba-8047-0f519430d45b</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter1')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="totalReprocesosPC06">
      <ns0:id>58f7baa2-a8a4-479d-94b1-65fef149f4a6</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter4')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="totalReprocesos">
      <ns0:id>209bd96f-9721-4bbe-b1a9-4140eec68fc5</ns0:id>
      <ns0:type>COUNTER</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('projectDataObjectCounter2')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="accionTareaPC06">
      <ns0:id>4a744b58-d752-44b1-ae5e-8cad7d97e128</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('accionTareaPC06')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="nombreOperacion">
      <ns0:id>4d7bd8da-172b-445f-a8a0-c4959f94bea1</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('nombreOperacion')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="valorSCR">
      <ns0:id>5815a9a0-9655-47de-b9bc-15b4f4f359d0</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Decimal</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings/>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="accionTarea">
      <ns0:id>0ff2398d-b85d-4726-ac38-fe557447116d</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('accionTarea')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="nombreOperacionPC06">
      <ns0:id>03b379f2-01e3-48f3-bc5c-7cf02eeec70f</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>String</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('nombreOperacionPC06')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="idEstadoOperacionPC06">
      <ns0:id>a6d9ca4a-80a6-4d62-b179-7763cbfd5e8f</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('idEstadoOperacionPC06')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="idEstadoOperacion">
      <ns0:id>0e5f1ef8-e0dd-44a5-ac59-f32eb7f7b486</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('idEstadoOperacion')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="valorSCRPC06">
      <ns0:id>1c629b7a-7fab-46e7-953d-32b386ab3be0</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Decimal</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings/>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="idOperacionPC06">
      <ns0:id>26083fbf-af40-41b5-8b7f-a4a9e773b6fe</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('idOperacionPC06')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
   <ns0:businessIndicator name="idOperacion">
      <ns0:id>eb1037d1-2e26-47d4-a1dd-2be9effbf87c</ns0:id>
      <ns0:type>DIMENSION</ns0:type>
      <ns0:dataType>Integer</ns0:dataType>
      <ns0:dataRange>
         <ns0:dataRangeInfo name="Otherwise"/>
      </ns0:dataRange>
      <ns0:mappings>
         <ns0:mapping componentName="projectInfo" componentType="BPMN">bpmn:getDataObject('idOperacion')</ns0:mapping>
      </ns0:mappings>
      <ns0:properties/>
   </ns0:businessIndicator>
</ns0:businessIndicators>
