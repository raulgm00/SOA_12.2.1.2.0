package org.bcie.fenix.view.documentos;

import javax.faces.event.ActionEvent;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import org.apache.myfaces.trinidad.component.UIXTree;
import org.apache.myfaces.trinidad.model.ModelUtils;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.RowKeySetTreeImpl;

import org.bcie.fenix.common.utils.JSFUtils;

public class TreeBean {
    private static ADFLogger logger = null;
    private RichOutputText otExpandTree;

    public TreeBean() {
        
        if (logger == null) {
            logger = ADFLogger.createADFLogger(this.getClass());
        }
    }
    
    private UIXTree getTree() {
        if(JSFUtils.findComponentInRoot("docTree") != null)
            return (UIXTree) JSFUtils.findComponentInRoot("docTree");
        else
            return null;
    }
    
    public void expandFirstNodeActionListener(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside expandFirstNodeActionListener: " + actionEvent.getComponent().getId());

        discloseFirstNode(getTree());
    }
         
    /**
     * Abre el primer nodo del tree
     * @param tree
     * @see Para mayor información https://docs.oracle.com/html/E18745_01/apidocs/oracle/adf/view/faces/model/TreeModel.html
     */
    private void discloseFirstNode(UIXTree tree) {
        
        if(tree != null){
            tree.setRowIndex(0); // Nos posicionamos en nodo raíz
            
            if (tree.isContainer()) { // Verificamos si el current row (raíz) es un container, i.e. contiene otros rows (hijos)
                
                tree.enterContainer(); // Accesamos a los child rows (hijos)
                
                if (tree.getRowCount() > 0) { // Verificamos que exista al menos un hijo 
                    
                    tree.exitContainer(); // Return back to the parent row
                    tree.getDisclosedRowKeys().add(); // Adds the current key to this set (expande el tree)
                } 
            }
        }
    }
            
    /**
     * Expand all tree nodes
     * @param act
     */
    public void expandAllTreeNodes(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside expandAllTreeNodes: " + actionEvent.getComponent().getId());

        UIXTree tree = getTree();
        RowKeySet _disclosedRowKeys = new RowKeySetTreeImpl(true);        
        _disclosedRowKeys.setCollectionModel(ModelUtils.toTreeModel(tree.getValue())); 
        tree.setDisclosedRowKeys(_disclosedRowKeys);
    }
         
    /**
     *Collapse all tree nodes
     * @param act
     */
    public void collapseAllTreeNodes(ActionEvent actionEvent) {
        logger.log(ADFLogger.TRACE, "Inside collapseAllTreeNodes: " + actionEvent.getComponent().getId());
        
        UIXTree tree = getTree();
        RowKeySet _disclosedRowKeys = tree.getDisclosedRowKeys();
        if (_disclosedRowKeys != null && _disclosedRowKeys.size() > 0) {
            _disclosedRowKeys.clear();
        }
        tree.setDisclosedRowKeys(_disclosedRowKeys);
    }

    public void setOtExpandTree(RichOutputText otExpandTree) {
        this.otExpandTree = otExpandTree;
    }

    public RichOutputText getOtExpandTree() {
        // Ejecuta método que abre el tree al primer nivel (este outputText está al final de la página)
        discloseFirstNode(getTree()); 
        
        return otExpandTree;
    }
}

