UPDATE bcie.ut_plantilla
SET
    contenido = 'Select id_Operacion
      , Nombre_Operacion
      , Monto_Solicitado
      , Descripcion_Moneda Moneda
      , Descripcion_Tipo_Garantia Garantia
      , Nvl(Nombre_Usuario,Upper(Usuario)) Usuario_Ejecutivo
      , Descripcion_Sector_Operacion SECTOR_OP
      , Descripcion_Producto
 PRODUCTO
      , RAZON_SOCIAL CLIENTE
      , ID_CLIENTE      
   From middle.fnx_V_Operaciones
  Where id_Operacion = :ID_PARAMETRO'
WHERE codigo = 'OPERACION_SQL';
    
UPDATE bcie.ut_plantilla
SET
    contenido = '<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html"/>
	<xsl:template match="/">
		<span>
			<table  style="border: 0.5px solid #4f81bd; border-spacing: 0px;"  cellpadding="0" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th class="cell-header" width="50%">
						Descripci�n de la Operaci�n
					</th>
					<th class="cell-header" width="50%">
						C�digo de la Operaci�n
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="cell" align="center">
							<xsl:apply-templates select="//NOMBRE_OPERACION"/>
					</td>
					<td class="cell" align="center">
							<xsl:apply-templates select="//ID_OPERACION"/>
					</td>
				</tr>
			</tbody>
			<thead>
				<tr>
					<th class="cell-header" width="50%">
						Nombre del Cliente
					</th>
					<th class="cell-header" width="50%">
						C�digo del Cliente
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="cell" align="center">
							<xsl:apply-templates select="//CLIENTE"/>
					</td>
					<td class="cell" align="center">
							<xsl:apply-templates select="//ID_CLIENTE"/>
					</td>
				</tr>
			</tbody>
		</table>
		<table style="border: 0.5px solid #4f81bd; border-spacing: 0px;"  cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th class="cell-header" width="100%">
						Tipo
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="cell" align="center">
							<xsl:value-of select="concat(//PRODUCTO,'' '')"/>
							<xsl:value-of select="concat(//SECTOR_OP,'' '')"/>
							<xsl:value-of select="//GARANTIA"/>
					</td>
				</tr>
			</tbody>
		</table>
		</span>
	</xsl:template>
</xsl:stylesheet>'
WHERE codigo = 'OPERACION_XSL';