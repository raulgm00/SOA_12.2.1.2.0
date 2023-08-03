<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:con1="http://www.bea.com/wli/sb/stages/transform/config"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                version="2.0">
  <xsl:output encoding="UTF-8"/>
  <!-- Fecha de formato YYYYMMDD a YYYY-MM-DD -->
  <xsl:template name="DatePattern">
    <xsl:param name="date"/>    
    <xsl:variable name="Year">
      <xsl:value-of select="substring($date,1,4)"/>
    </xsl:variable>
    <xsl:variable name="Month">
      <xsl:value-of select="substring($date,5,2)"/>
    </xsl:variable>
    <xsl:variable name="Day">
      <xsl:value-of select="substring($date,7,2)"/>
    </xsl:variable>
    <xsl:value-of select="concat($Year,'-',$Month,'-',$Day)"/>        
  </xsl:template>
  <!-- Respuesta de SI, NO a 1,0 respectivamente -->
  <!-- OUTPUT -->
  <xsl:template name="Si_No">
    <xsl:param name="ResponseSiNo"/>    
    <xsl:choose>
      
      <xsl:when test="$ResponseSiNo='NO'">0</xsl:when>
      <xsl:when test="$ResponseSiNo='SI'">1</xsl:when>
     
      <!--<xsl:when test="$ResponseSiNo='NO'">false</xsl:when>
      <xsl:when test="$ResponseSiNo='SI'">true</xsl:when> -->
      <xsl:otherwise>
        <xsl:value-of select="$ResponseSiNo"></xsl:value-of>
      </xsl:otherwise>
    </xsl:choose>     
  </xsl:template>
  <!-- Respuesta de X, != X a 1,0 respectivamente -->
  <!-- OUTPUT -->
  <xsl:template name="X_res">
    <xsl:param name="ResponseX"/>    
    <xsl:choose>
      <xsl:when test="$ResponseX='X'">true</xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$ResponseX">false</xsl:value-of>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  <!-- Respuesta de Y, N a 1,0 respectivamente -->
  <!-- INPUT -->
  <xsl:template name="Y_N">
    <xsl:param name="ResponseY_N"/>    
    <xsl:choose>
      
      <xsl:when test="$ResponseY_N='0'">N</xsl:when>
      <xsl:when test="$ResponseY_N='1'">Y</xsl:when>
      
      <!--<xsl:when test="$ResponseY_N='false'">N</xsl:when>
      <xsl:when test="$ResponseY_N='true'">Y</xsl:when>-->
      <xsl:otherwise>
        <xsl:value-of select="$ResponseY_N"></xsl:value-of>
      </xsl:otherwise>
    </xsl:choose>     
  </xsl:template>
  <xsl:template name="Boolean_Regex">
  <xsl:param name="operation"/>
  <xsl:param name="parametro"/>
  <xsl:choose>
      <!-- Creacion Add-->
      <xsl:when test="xp20:upper-case($operation) = 'ADD'">
          <xsl:choose>
          <xsl:when test="xp20:matches ( xp20:upper-case( $parametro ) , '(^Y$|^YES$|^S$|^SI$|^X$|^TRUE$)' )">Y</xsl:when>         
          <xsl:when test="xp20:matches ( xp20:upper-case( $parametro ), '(^N$|^NO$|^FALSE$)' )">N</xsl:when>
          <xsl:otherwise>
              <xsl:value-of select="$parametro"></xsl:value-of>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:when>
      <!-- Creacion Mod-->
      <xsl:when test="xp20:upper-case($operation) = 'MOD' ">
          <xsl:choose>
          <xsl:when test="xp20:matches ( xp20:upper-case( $parametro ) , '(^Y$|^YES$|^S$|^SI$|^X$|^TRUE)' )">Y</xsl:when>         
          <xsl:when test="xp20:matches ( xp20:upper-case( $parametro ), '(^N$|^NO$|^FALSE$)' )">N</xsl:when>
          <xsl:otherwise>
              <xsl:value-of select="$parametro"></xsl:value-of>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:when>
      <!-- Consulta Inq-->
      <xsl:when test="xp20:upper-case($operation) = 'INQ'">
          <xsl:choose>
            <xsl:when test="xp20:matches ( xp20:upper-case( $parametro ) , '(^1$|^Y$|^YES$|^S$|^SI$|^X$)' )">true</xsl:when>         
          <xsl:when test="xp20:matches ( xp20:upper-case( $parametro ), '(^0$|^N$|^NO$)' )">false</xsl:when>
          <xsl:otherwise>
              <xsl:value-of select="$parametro"></xsl:value-of>
          </xsl:otherwise>
        </xsl:choose>
      </xsl:when>
    </xsl:choose>
  </xsl:template>
</xsl:stylesheet>