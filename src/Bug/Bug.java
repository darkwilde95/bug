/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bug;

import java.io.FileInputStream;
import java.io.InputStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author darkw
 */
public class Bug {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String inputFile = "test.bug";
        //if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;
        if ( inputFile!=null ) is = new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        bugLexer lexer = new bugLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        bugParser parser = new bugParser(tokens);
        ParseTree tree = parser.program(); // parse

        Compiler eval = new Compiler();
        eval.visit(tree);
    }
}
