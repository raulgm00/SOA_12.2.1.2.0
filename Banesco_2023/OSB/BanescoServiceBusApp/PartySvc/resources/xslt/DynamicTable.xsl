<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <dinamicRule>
            <rule id="PartyRiskInq" tipo="composed">
                <auditRecord>true</auditRecord>
                <component_target>PartySvc/pipeline/PL_PartyRiskInq</component_target>
                <backend_operation>PartyRiskInq</backend_operation>
            </rule>
            <rule id="PartyInfoInq" tipo="composed">
                <auditRecord>true</auditRecord>
                <component_target>PartySvc/pipeline/PL_PartyInfoInq_V1.0</component_target>
                <backend_operation>PartyInfoInq</backend_operation>
                <type_pn>Natural</type_pn>
                <type_pj>Juridico</type_pj>
                <op_pn>PersonPartyInfoInq</op_pn>
                <op_pj>OrgPartyInfoInq</op_pj>
            </rule>
            <rule id="PersonPartyInfoInq" tipo="atomic">
                <auditRecord>true</auditRecord>
                <component_target>PartyTS/proxy/PX_Party_V1.0</component_target>
                <backend_operation>PersonPartyInfoInq</backend_operation>
            </rule>
            <rule id="OrgPartyInfoInq" tipo="atomic">
                <auditRecord>true</auditRecord>
                <component_target>PartyTS/proxy/PX_Party_V1.0</component_target>
                <backend_operation>OrgPartyInfoInq</backend_operation>
            </rule>
            <rule id="EmpPartyInq" tipo="atomic">
                <auditRecord>true</auditRecord>
                <component_target>PartyTS/proxy/PX_Party_V1.0</component_target>
                <backend_operation>EmpPartyInq</backend_operation>
            </rule>
            <rule id="EmpPartyStatusMod" tipo="atomic">
                <auditRecord>false</auditRecord>
                <component_target>PartyTS/proxy/PX_Party_V1.0</component_target>
                <backend_operation>EmpPartyStatusMod</backend_operation>
            </rule>
            <rule id="PartyInq" tipo="atomic">
                <auditRecord>true</auditRecord>
                <component_target>PartyTS/proxy/PX_Party_V1.0</component_target>
                <backend_operation>PartyInq</backend_operation>
            </rule>
            <rule id="PartyStaffInq" tipo="atomic">
                <auditRecord>true</auditRecord>
                <component_target>PartyTS/proxy/PX_Party_V1.0</component_target>
                <backend_operation>PartyStaffInq</backend_operation>
            </rule>
            <rule id="PartyRefInq" tipo="atomic">
                <auditRecord>true</auditRecord>
                <component_target>PartyTS/proxy/PX_Party_V1.0</component_target>
                <backend_operation>PartyRefInq</backend_operation>
            </rule>
            <rule id="PartyRefAdd" tipo="atomic">
                <auditRecord>true</auditRecord>
                <component_target>PartyTS/proxy/PX_Party_V1.0</component_target>
                <backend_operation>PartyRefAdd</backend_operation>
            </rule>
            <rule id="PartyRefMod" tipo="atomic">
                <auditRecord>true</auditRecord>
                <component_target>PartyTS/proxy/PX_Party_V1.0</component_target>
                <backend_operation>PartyRefMod</backend_operation>
            </rule>
            <rule id="PartyAdd" tipo="composed">
                <auditRecord>true</auditRecord>
                <component_target>PartySvc/pipeline/PL_PartyAdd_V1.0</component_target>
                <backend_operation>PartyAdd</backend_operation>                
            </rule>
            <rule id="PartyMod" tipo="atomic">
                <auditRecord>true</auditRecord>
                <component_target>PartyTS/proxy/PX_Party_V1.0</component_target>
                <backend_operation>PartyMod</backend_operation>
            </rule>
        </dinamicRule>
    </xsl:template>
</xsl:stylesheet>