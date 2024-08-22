package ParserOperations;

import Tokens.TokenType;

public class Block {
    private SimpleStatements simpleStmt;
    private CompoundStatements compStmt;

    public Block() {
        simpleStmt = new SimpleStatements();
        compStmt = new CompoundStatements();
    }

    public boolean isValidBlock() {
        if (simpleStmt.isSimpleStatement() || compStmt.isCompoundStatement()) {
            return true;
        } else {
            return false;
        }
    }
}
