
-- 20171208 Vista VCA_CTA_CONTABLE_PASIVA

CREATE OR REPLACE VIEW VCA_CTA_CONTABLE_PASIVA AS		
		SELECT  ACCOUNT, AC_DESC, AC_GL_CCY, AC_CLASS
		FROM MIDDLE.FC_MV_CUENTAS 		
		WHERE AC_CLASS = 'CONTA' AND ACCOUNT LIKE '%';
		/